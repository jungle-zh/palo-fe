// Copyright (c) 2017, Baidu.com, Inc. All Rights Reserved

// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

package com.baidu.palo.load;

import com.baidu.palo.analysis.DataDescription;
import com.baidu.palo.analysis.LoadStmt;
import com.baidu.palo.catalog.*;
import com.baidu.palo.catalog.OlapTable.OlapTableState;
import com.baidu.palo.catalog.Table.TableType;
import com.baidu.palo.common.*;
import com.baidu.palo.load.LoadJob.JobState;
import com.baidu.palo.metric.MetricRepo;
import com.baidu.palo.qe.ConnectContext;
import com.baidu.palo.task.AgentStreamingJob;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class StreamingLoad {
    private static final Logger LOG = LogManager.getLogger(StreamingLoad.class);

    // valid state change map
    private static final Map<JobState, Set<JobState>> STATE_CHANGE_MAP = Maps.newHashMap();

    // system dpp config
    public static DppConfig dppDefaultConfig = null;
    public static Map<String, DppConfig> clusterToDppConfig = Maps.newHashMap();



     private Map<Long, AgentStreamingJob> idToStreamingLoadJob = Maps.newHashMap(); // loadJobId to pending loadJob



    private Set<Long> loadingPartitionIds; // loading partition id set



    // lock for load job
    // lock is private and must use after db lock
    private ReentrantReadWriteLock lock;

    static {
        Set<JobState> pendingDestStates = Sets.newHashSet();
        pendingDestStates.add(JobState.ETL);
        pendingDestStates.add(JobState.CANCELLED);
        STATE_CHANGE_MAP.put(JobState.PENDING, pendingDestStates);

        Set<JobState> etlDestStates = Sets.newHashSet();
        etlDestStates.add(JobState.LOADING);
        etlDestStates.add(JobState.CANCELLED);
        STATE_CHANGE_MAP.put(JobState.ETL, etlDestStates);

        Set<JobState> loadingDestStates = Sets.newHashSet();
        loadingDestStates.add(JobState.QUORUM_FINISHED);
        loadingDestStates.add(JobState.CANCELLED);
        STATE_CHANGE_MAP.put(JobState.LOADING, loadingDestStates);

        Set<JobState> quorumFinishedDestStates = Sets.newHashSet();
        quorumFinishedDestStates.add(JobState.FINISHED);
        STATE_CHANGE_MAP.put(JobState.QUORUM_FINISHED, quorumFinishedDestStates);

        // system dpp config
        Gson gson = new Gson();
        try {
            Map<String, String> defaultConfig =
                    (HashMap<String, String>) gson.fromJson(Config.dpp_default_config_str, HashMap.class);
            dppDefaultConfig = DppConfig.create(defaultConfig);

            Map<String, Map<String, String>> clusterToConfig =
                    (HashMap<String, Map<String, String>>) gson.fromJson(Config.dpp_config_str, HashMap.class);
            for (Entry<String, Map<String, String>> entry : clusterToConfig.entrySet()) {
                String cluster = entry.getKey();
                DppConfig dppConfig = dppDefaultConfig.getCopiedDppConfig();
                dppConfig.update(DppConfig.create(entry.getValue()));
                dppConfig.check();

                clusterToDppConfig.put(cluster, dppConfig);
            }

            if (!clusterToDppConfig.containsKey(Config.dpp_default_cluster)) {
                throw new LoadException("Default cluster not exist");
            }
        } catch (Throwable e) {
            LOG.error("dpp default config ill-formed", e);
            System.exit(-1);
        }
    }



    public StreamingLoad() {

        loadingPartitionIds = Sets.newHashSet();
        lock = new ReentrantReadWriteLock(true);
    }

    public void readLock() {
        lock.readLock().lock();
    }

    public void readUnlock() {
        lock.readLock().unlock();
    }

    private void writeLock() {
        lock.writeLock().lock();
    }

    private void writeUnlock() {
        lock.writeLock().unlock();
    }



    public void addStreamingLoadJob(LoadStmt stmt, long timestamp) throws DdlException, LoadException, AnalysisException {
        // get db

        String dbName = stmt.getLabel().getDbName();
        Database db = Catalog.getInstance().getDb(dbName);  //jungle comment:get db of olap here
        if (db == null) {
            throw new DdlException("Database[" + dbName + "] does not exist");
        }

        // create job
        AgentStreamingJob job = createStreamingLoadJob(stmt, db, timestamp); //jungle comment:create LoadJob ,determine the

        addStreamingLoadJobAndStart(job, db);
    }



    private void addStreamingLoadJobAndStart(AgentStreamingJob job, Database db) throws DdlException, LoadException, AnalysisException {
        LOG.info("Load::addStreamingLoadJobAndStart");
        //com.baidu.palo.common.util.Util.printStack();
        // check cluster capacity
        Catalog.getCurrentSystemInfo().checkClusterCapacity(db.getClusterName());
        // check db quota
        db.checkQuota();

        writeLock();
        try {
            //unprotectAddLoadJob(job);
            idToStreamingLoadJob.put(job.getId(), job);
            LOG.info("add streaming job:" + job.getId());
            MetricRepo.METER_LOAD_ADD.mark();
            job.start();
            //Catalog.getInstance().getEditLog().logLoadStart(job);
        } finally {
            writeUnlock();
        }
        LOG.info("add load job. job: {}", job);
    }

    private AgentStreamingJob createStreamingLoadJob(LoadStmt stmt,Database db, long timestamp) throws DdlException {
        LOG.info("Load.createLoadJob()");
        // get params
        String label = stmt.getLabel().getLabelName();
        List<DataDescription> dataDescriptions = stmt.getDataDescriptions();
        Map<String, String> properties = stmt.getProperties();

        // check params
        try {
            FeNameFormat.checkLabel(label);
        } catch (AnalysisException e) {
            throw new DdlException(e.getMessage());
        }
        if (dataDescriptions == null || dataDescriptions.isEmpty()) {
            throw new DdlException("No data file in load statement.");
        }

        // create job
        AgentStreamingJob job = new AgentStreamingJob(db);
        job.setTimestamp(timestamp);
        job.setBrokerDesc(stmt.getBrokerDesc());

        // resource info
        if (ConnectContext.get() != null) {
            job.setResourceInfo(ConnectContext.get().toResourceCtx());
            job.setExecMemLimit(ConnectContext.get().getSessionVariable().getMaxExecMemByte());
        }




        // tableId partitionId sources
        // Map<Long, Map<Long, List<Source>>> tableToPartitionSources = Maps.newHashMap();
        Map<Long, Source> partitionToSources = Maps.newHashMap();
        DataDescription dataDescription =  dataDescriptions.get(0);
            // create source
        OlapTable table = (OlapTable )createSource(db, dataDescription, partitionToSources);

        Map<Long,PartitionLoadInfo>  idToPartitionLoadInfo = Maps.newHashMap();
        for (Entry<Long, Source> partitionEntry : partitionToSources.entrySet()) {
            List<Source> sources = new ArrayList<>();
            sources.add(partitionEntry.getValue());
            PartitionLoadInfo info = new PartitionLoadInfo(sources);
            idToPartitionLoadInfo.put(partitionEntry.getKey(), info);
        }

        job.setTable( table);
        job.setIdToPartitionLoadInfo(idToPartitionLoadInfo);

        job.setId(Catalog.getInstance().getNextId());


        return job;
    }

    private Table createSource(Database db, DataDescription dataDescription,
                              Map<Long, Source> partitionToSources) throws DdlException {
        Source source = new Source(dataDescription.getFilePathes());
        //long tableId = -1;
        Set<Long> sourcePartitionIds = Sets.newHashSet();

        Table table=  null;
        // source column names and partitions
        String tableName = dataDescription.getTableName();
        Map<String, Pair<String, List<String>>> columnToFunction = null;
        db.readLock();
        try {
            table = db.getTable(tableName);
            if (table == null) {
                throw new DdlException("Table [" + tableName + "] does not exist");
            }
            if (table.getType() != TableType.OLAP) {
                throw new DdlException("Table [" + tableName + "] is not olap table");
            }

            if (((OlapTable) table).getState() == OlapTableState.RESTORE) {
                throw new DdlException("Table [" + tableName + "] is under restore");
            }

            if (((OlapTable) table).getKeysType() != KeysType.AGG_KEYS && dataDescription.isNegative()) {
                throw new DdlException("Load for AGG_KEYS table should not specify NEGATIVE");
            }



            // get table schema
            List<Column> tableSchema = table.getBaseSchema();
            Map<String, Column> nameToTableColumn = Maps.newTreeMap(String.CASE_INSENSITIVE_ORDER);
            for (Column column : tableSchema) {
                nameToTableColumn.put(column.getName(), column);
            }

            // source columns
            List<String> columnNames = Lists.newArrayList();
            List<String> assignColumnNames = dataDescription.getColumnNames();
            if (assignColumnNames == null) {
                // use table columns
                for (Column column : tableSchema) {
                    columnNames.add(column.getName());
                }
            } else {
                // convert column to schema format
                for (String assignCol : assignColumnNames) {
                    if (nameToTableColumn.containsKey(assignCol)) {
                        columnNames.add(nameToTableColumn.get(assignCol).getName());
                    } else {
                        columnNames.add(assignCol);
                    }
                }
            }
            source.setColumnNames(columnNames);   //jungle comment : source is the  hdfs  or local file source


            // check default value
            Map<String, Pair<String, List<String>>> assignColumnToFunction = dataDescription.getColumnMapping();
            for (Column column : tableSchema) {
                String columnName = column.getName();
                if (columnNames.contains(columnName)) {
                    continue;
                }

                if (assignColumnToFunction != null && assignColumnToFunction.containsKey(columnName)) {
                    continue;
                }

                if (column.getDefaultValue() != null || column.isAllowNull()) {
                    continue;
                }


                throw new DdlException("Column has no default value. column: " + columnName);
            }


            // check mapping column exist in table
            // check function
            // convert mapping column and func arg columns to schema format
            Map<String, String> columnNameMap = Maps.newTreeMap(String.CASE_INSENSITIVE_ORDER);
            for (String columnName : columnNames) {
                columnNameMap.put(columnName, columnName);
            }
            if (assignColumnToFunction != null) {
                columnToFunction = Maps.newHashMap();
                for (Entry<String, Pair<String, List<String>>> entry : assignColumnToFunction.entrySet()) {
                    String mappingColumnName = entry.getKey();
                    if (!nameToTableColumn.containsKey(mappingColumnName)) {
                        throw new DdlException("Mapping column is not in table. column: " + mappingColumnName);
                    }

                    Column mappingColumn = nameToTableColumn.get(mappingColumnName);
                    Pair<String, List<String>> function = entry.getValue();
                    try {
                        DataDescription.validateMappingFunction(function.first, function.second, columnNameMap,
                                mappingColumn, dataDescription.isPullLoad());
                    } catch (AnalysisException e) {
                        throw new DdlException(e.getMessage());
                    }

                    columnToFunction.put(mappingColumn.getName(), function);
                }
            }


            // partitions of this source
            OlapTable olapTable = (OlapTable) table;     //jungle comment: the table to insert data
            List<String> partitionNames = dataDescription.getPartitionNames(); //jungle comment:user define the insert partition
            if (partitionNames == null || partitionNames.isEmpty()) {
                partitionNames = new ArrayList<String>();
                for (Partition partition : olapTable.getPartitions()) {   //jungle comment : use the partition when create table
                    partitionNames.add(partition.getName());
                }
            }
            for (String partitionName : partitionNames) {
                Partition partition = olapTable.getPartition(partitionName);
                if (partition == null) {
                    throw new DdlException("Partition [" + partitionName + "] does not exist");
                }
                sourcePartitionIds.add(partition.getId());    //jungle comment: source  add to all partitions ?
            }
        } finally {
            db.readUnlock();
        }

        // column separator
        String columnSeparator = dataDescription.getColumnSeparator();
        if (!Strings.isNullOrEmpty(columnSeparator)) {
            source.setColumnSeparator(columnSeparator);
        }

        // line delimiter
        String lineDelimiter = dataDescription.getLineDelimiter();
        if (!Strings.isNullOrEmpty(lineDelimiter)) {
            source.setLineDelimiter(lineDelimiter);
        }

        // source negative
        source.setNegative(dataDescription.isNegative());

        // column mapping functions
        if (columnToFunction != null) {
            source.setColumnToFunction(columnToFunction);
        }

        // add source to table partition map    //jungle comment :  partitionId -> sources

        for (long partitionId : sourcePartitionIds) {

            partitionToSources.put(partitionId, source);
            LOG.info("partitionId :" + partitionId  + " ,source file urls:" + source.getFileUrls());

        }

        return table;
    }



    /**
    public boolean cancelLoadJob(CancelLoadStmt stmt) throws DdlException {
        // get params
        String dbName = stmt.getDbName();
        String label = stmt.getLabel();

        // get load job and check state
        Database db = Catalog.getInstance().getDb(dbName);
        if (db == null) {
            throw new DdlException("Db does not exist. name: " + dbName);
        }
        LoadJob job = null;
        readLock();
        try {
            Map<String, List<LoadJob>> labelToLoadJobs = dbLabelToLoadJobs.get(db.getId());
            if (labelToLoadJobs == null) {
                throw new DdlException("Load job does not exist");
            }

            List<LoadJob> loadJobs = labelToLoadJobs.get(label);
            if (loadJobs == null) {
                throw new DdlException("Load job does not exist");
            }
            // only the last one should be running
            job = loadJobs.get(loadJobs.size() - 1);
            JobState state = job.getState();
            if (state == JobState.CANCELLED) {
                throw new DdlException("Load job has been cancelled");
            } else if (state == JobState.QUORUM_FINISHED || state == JobState.FINISHED) {
                throw new DdlException("Load job has been finished");
            }
        } finally {
            readUnlock();
        }

        // cancel job
        if (!cancelLoadJob(job, CancelType.USER_CANCEL, "user cancel")) {
            throw new DdlException("Cancel load job fail");
        }

        return true;
    }

    public boolean cancelLoadJob(LoadJob job, CancelType cancelType, String msg) {
        // update job to cancelled
        JobState srcState = job.getState();
        if (!updateLoadJobState(job, JobState.CANCELLED, cancelType, msg)) {
            LOG.warn("cancel load job failed. job: {}", job);
            return false;
        }

        // clear
        if (job.getHadoopDppConfig() != null) {
            clearJob(job, srcState);
        }

        if (job.getBrokerDesc() != null) {
            if (srcState == JobState.ETL) {
                // Cancel job id
                Catalog.getInstance().getPullLoadJobMgr().cancelJob(job.getId());
            }
        }
        LOG.info("cancel load job success. job: {}", job);
        return true;
    }

    **/


    public AgentStreamingJob getLoadJob(long jobId) {
        LOG.info("get streaming job:" + jobId +" ,idToStreamingLoadJob size:" + idToStreamingLoadJob.size());
       return idToStreamingLoadJob.get(jobId);
    }
}
