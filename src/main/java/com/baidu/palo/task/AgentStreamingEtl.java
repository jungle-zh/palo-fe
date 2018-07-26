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

package com.baidu.palo.task;

import com.baidu.palo.analysis.DescriptorTable;
import com.baidu.palo.analysis.SlotDescriptor;
import com.baidu.palo.analysis.TupleDescriptor;
import com.baidu.palo.catalog.Catalog;
import com.baidu.palo.catalog.Column;
import com.baidu.palo.catalog.Database;
import com.baidu.palo.catalog.OlapTable;
import com.baidu.palo.common.*;
import com.baidu.palo.load.PartitionLoadInfo;
import com.baidu.palo.load.Source;
import com.baidu.palo.planner.*;
import com.baidu.palo.system.Backend;
import com.baidu.palo.thrift.*;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/*
 * This class group tasks by backend 
 */
public class AgentStreamingEtl {


    // descriptor used to register all column and table need
    //private DescriptorTable desc;

    // destination Db and table get from request
    // Data will load to this table
    //private   OlapTable destTable;

    private  final  Database db;
    private  final  OlapTable table;
    private  static final Logger LOG = LogManager.getLogger(AgentStreamingEtl.class);
    private  final  Map<Long, PartitionLoadInfo> idToPartitionLoadInfo;
    private final  Long id;

    private final Long backendId;

    private Source source = new Source();
    private ConcurrentLinkedQueue<Pair<Long,Source>> sourceFileQueue =  new ConcurrentLinkedQueue();
    private Map<Long,Source> etlInFlightSource = new HashMap<>();
    private final ConcurrentLinkedQueue<Map<String,Long>> deltaFileQueue ;

    private SubmitEtlTask submitEtlTask ;


    private class SubmitEtlTask implements Runnable {

        @Override
        public void run() {
            while (true){
                while (!sourceFileQueue.isEmpty()){

                    try {
                        Source cursource = sourceFileQueue.poll().second;
                        Long taskId = submitBackendEtlTask(cursource);
                        if(taskId != -1){
                            etlInFlightSource.put(taskId,cursource);
                        }

                    } catch (LoadException e) {
                        e.printStackTrace();
                    } catch (AnalysisException e) {
                        e.printStackTrace();
                    }

                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }
    }

    public AgentStreamingEtl(Database db, OlapTable table, Map<Long, PartitionLoadInfo> idToPartitionLoadInfo, Long id, Long backendId, ConcurrentLinkedQueue<Map<String, Long>> deltaFileQueue) {

        this.db = db;
        this.table = table;
        this.idToPartitionLoadInfo = idToPartitionLoadInfo;
        this.id = id;
        this.backendId = backendId;
        this.deltaFileQueue = deltaFileQueue;

        try {
            initSourceFile();
        } catch (LoadException e) {
            e.printStackTrace();
        } catch (AnalysisException e) {
            e.printStackTrace();
        }
        submitEtlTask = new SubmitEtlTask();

        Thread thread = new Thread(submitEtlTask);
        thread.start();


    }


    public Long submitBackendEtlTask(Source source ) throws LoadException, AnalysisException {



        Long taskId  = Math.abs(new Random().nextLong()) ;
        TMiniLoadEtlTaskRequest request = createRequest(taskId,backendId,source);
        Backend backend = Catalog.getCurrentSystemInfo().getBackend(backendId);

        LOG.info("submit etl task file path " + source.getFileUrls() + " backend id :" +  backendId );
        if(source.getFileUrls().size() == 0){
            LOG.info("empty file  ");
            return -1L;
        }
        AgentClient client = new AgentClient(backend.getHost(), backend.getBePort());
        TAgentResult submitResult = client.submitEtlTask(request);

        if (submitResult.getStatus().getStatus_code() != TStatusCode.OK) {
            return -1L;
        }else {

            return taskId;
        }

    }


    private TMiniLoadEtlTaskRequest createRequest(long taskId,long backEndId,Source source) throws LoadException, AnalysisException {

        DescriptorTable desc = new  DescriptorTable();
        TupleDescriptor destTupleDesc =  createDesc(desc,table);
        CsvScanNode csvScanNode = new CsvScanNode(new PlanNodeId(0), destTupleDesc, table, source);
        desc.computeMemLayout();
        try {
            csvScanNode.finalize(null);
        } catch (InternalException e) {
            LOG.warn("csvScanNode finalize failed[err={}]", e);
            throw new LoadException("CSV scan finalize failed.", e);
        }


        DataSplitSink tableSink = new DataSplitSink(table, destTupleDesc);

        PlanFragment fragment = new PlanFragment(new PlanFragmentId(0), csvScanNode, DataPartition.UNPARTITIONED);
        fragment.setSink(tableSink);

        try {
            fragment.finalize(null, false);
        } catch (Exception e) {
            LOG.info("fragment finalize faild.e = {}", e);
            throw new LoadException("Fragment finalize failed.", e);
        }

        TMiniLoadEtlTaskRequest request = new TMiniLoadEtlTaskRequest();
        request.setProtocol_version(TAgentServiceVersion.V1);
        TExecPlanFragmentParams params = new TExecPlanFragmentParams();
        params.setProtocol_version(PaloInternalServiceVersion.V1);
        params.setFragment(fragment.toThrift());
        params.setDesc_tbl(desc.toThrift());
        params.setImport_label("stream_" + taskId);
        params.setDb_name(db.getFullName());
        LOG.info("set load job id :" + id);
        params.setLoad_job_id(id);



        TPlanFragmentExecParams execParams = new TPlanFragmentExecParams();
        // Only use fragment id
        TUniqueId uniqueId = new TUniqueId(backEndId, taskId);
        execParams.setQuery_id(new TUniqueId(uniqueId));
        execParams.setFragment_instance_id(uniqueId);
        execParams.per_node_scan_ranges = Maps.newHashMap();
        execParams.per_exch_num_senders = Maps.newHashMap();
        execParams.destinations = Lists.newArrayList();
        params.setParams(execParams);
        TQueryOptions queryOptions = new TQueryOptions();
        queryOptions.setQuery_type(TQueryType.LOAD);
        params.setQuery_options(queryOptions);
        request.setParams(params);
        return request;
    }

    private TupleDescriptor createDesc(DescriptorTable desc,OlapTable destTable) {
        //DescriptorTable desc = new  DescriptorTable();
        TupleDescriptor destTupleDesc = desc.createTupleDescriptor();
        destTupleDesc.setTable(destTable);
        // Lock database and get its schema hash??
        // Make sure that import job has its corresponding schema
        for (Column col : destTable.getBaseSchema()) {
            SlotDescriptor slot = desc.addSlotDescriptor(destTupleDesc);
            // All this slot is needed
            slot.setIsMaterialized(true);
            slot.setColumn(col);
            if (true == col.isAllowNull()) {
                slot.setIsNullable(true);
            } else {
                slot.setIsNullable(false);
            }
        }

        return  destTupleDesc;
    }



    public TFeResult updateMiniEtlTaskStatus(TUpdateMiniEtlTaskStatusRequest request) {  //be call this

        LOG.info("call updateMiniEtlTaskStatus");
        TFeResult result = new TFeResult();
        result.setProtocolVersion(FrontendServiceVersion.V1);
        TStatus status = new TStatus(TStatusCode.OK);
        result.setStatus(status);


        TMiniLoadEtlStatusResult statusResult = request.getEtlTaskStatus();

        TUniqueId etlTaskId = request.getEtlTaskId();
        long backendId = etlTaskId.getHi();
        long taskId = etlTaskId.getLo();


        Backend backend = Catalog.getCurrentSystemInfo().getBackend(backendId);
        AgentClient client = new AgentClient(backend.getHost(), backend.getBePort());

        client.mark_etl_file_done(etlInFlightSource.get(taskId).getFileUrls());
        List<String> newSouceFile =  getBackEndNewSouceFile();
        //genTask(newSouceFile);
        etlInFlightSource.remove(taskId);
        sourceFileQueue.add(new Pair<>(System.currentTimeMillis(),createNewSource(newSouceFile)));

        deltaFileQueue.add(statusResult.getFile_map());


        return result;


        //submitBackendEtlTask(backendId);

    }
    //call in MasterImpl  updateReplicaInfo

    public Source createNewSource( List<String> fileUrls){
        Source newsource = new Source(fileUrls,source.getColumnNames(),
                source.getColumnSeparator(),
                source.getLineDelimiter(),
                source.isNegative());
        return newsource;
    }


    public void initSourceFile() throws LoadException, AnalysisException {

        if(idToPartitionLoadInfo.values().size() < 1)
            return;
        PartitionLoadInfo  partitionLoadInfo  =  idToPartitionLoadInfo.values().iterator().next();
        Source initSource = partitionLoadInfo.getSources().get(0);
        String dataDirPath  = initSource.getFileUrls().get(0);
        source.setFileUrls(Lists.newArrayList(dataDirPath));
        List<String> sourceFile =  getBackEndNewSouceFile();
        sourceFileQueue.add(new Pair<>(System.currentTimeMillis(),createNewSource(sourceFile)));

    }




    protected String getPartitionIndexBucketString(String filePath) throws LoadException {
        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
        // label.partitionId.indexId.bucket
        String[] fileNameArr = fileName.split("\\.");
        if (fileNameArr.length != 4) {
            throw new LoadException("etl file name format error, name: " + fileName);
        }

        String partitionIndexBucket = fileName.substring(fileName.indexOf(".") + 1);
        LOG.info("partitionIndexBucket :" + partitionIndexBucket);
        return partitionIndexBucket;
    }



    public List<String> getBackEndNewSouceFile() { //get the new etl file


        Backend backend = Catalog.getCurrentSystemInfo().getBackend(backendId);
        AgentClient client = new AgentClient(backend.getHost(), backend.getBePort());
        List<String> newFilePath =  client.get_streaming_etl_file_path(source.getFileUrls().get(0),1);
        for(String path : newFilePath){
            LOG.info("backendid get new File Path :" + path );
        }
        return newFilePath;

    }



}
