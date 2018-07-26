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

import com.baidu.palo.catalog.Database;
import com.baidu.palo.catalog.OlapTable;
import com.baidu.palo.load.PartitionLoadInfo;
import com.baidu.palo.thrift.TPriority;
import com.baidu.palo.thrift.TResourceInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

/*
 * This class group tasks by backend 
 */
public class AgentStreamingJob {


    // descriptor used to register all column and table need
    //private DescriptorTable desc;

    // destination Db and table get from request
    // Data will load to this table
    //private   OlapTable destTable;

    private  final  Database db;
    private  OlapTable table;



    private static final Logger LOG = LogManager.getLogger(AgentStreamingJob.class);


    private long startTime;
    private TResourceInfo resourceInfo;
    private TPriority priority;
    private long execMemLimit;
    private Map<Long, PartitionLoadInfo> idToPartitionLoadInfo;
    private long id;
    private long etlbackendId;

    private ConcurrentLinkedQueue<Map<String,Long>> deltaFileQueue = new ConcurrentLinkedQueue<>();


    private AgentStreamingEtl etl ;
    private AgentStreamingPush push ;





    public AgentStreamingJob(Database db) {

        this.db = db;


    }
    public void  init(){
        etlbackendId = 10002;
        etl = new AgentStreamingEtl(db,table,idToPartitionLoadInfo,id,etlbackendId,deltaFileQueue);
        push  = new AgentStreamingPush(db,table,id,deltaFileQueue);
    }

    public void setTimestamp(long timestamp) {
        this.startTime = timestamp;
    }


    public void setResourceInfo(TResourceInfo resourceInfo) {
        this.resourceInfo = resourceInfo;
    }

    public void setExecMemLimit(long execMemLimit) {
        this.execMemLimit = execMemLimit;
    }

    public void setIdToPartitionLoadInfo(Map<Long,PartitionLoadInfo> idToPartitionLoadInfo) {
        this.idToPartitionLoadInfo = idToPartitionLoadInfo;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setTable(OlapTable table) {
        this.table = table;
    }

    public AgentStreamingEtl getEtl() {
        return etl;
    }

    public AgentStreamingPush getPush() {
        return push;
    }

    /***

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


        etlUpdateLock.lock();
        loadHelper.addFinshEtlTaskAndUpdate(backendId,statusResult.getFile_map());
        if(loadHelper.etlJobFinsh()){  // add source alreay update
            LOG.info(" etl job finished ");

            //loadHelper.updateEtlFinishedFile();
            loadHelper.submitPushJob();

        }
        etlUpdateLock.unlock();

        return result;


        //submitBackendEtlTask(backendId);

    }
    //call in MasterImpl  updateReplicaInfo
    public TMasterResult updateMiniPushTaskStatus (TFinishTaskRequest request) {


        LOG.info("call updateMiniPushTaskStatus");
        TMasterResult result = new TMasterResult();
        TStatus tStatus = new TStatus(TStatusCode.OK);
        result.setStatus(tStatus);

        List<TTabletInfo> finishTabletInfos = request.getFinish_tablet_infos();
        TTabletInfo tabletInfo = finishTabletInfos.get(0);

        //lock

        pushUpdateLock.lock();
        try {
            loadHelper.addFinishPushTaskAndUpdate(request.getTask_id(),tabletInfo);
            if(loadHelper.pushJobFinsh()){
                LOG.info("push job finished ");
                loadHelper.updateFinishPushInfo();

                Catalog.getInstance().saveImage();

                loadHelper.updateEtlFinishedFile();
                loadHelper.resetPushInfo();
                loadHelper.resetEtlInfo();
                loadHelper.getBackEndNewSouceFile();
                loadHelper.submitEtlJob();


            }
        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            pushUpdateLock.unlock();
        }

        //unlock
        return result ;


    }


    public void start() throws LoadException, AnalysisException {

        if(idToPartitionLoadInfo.values().size() < 1)
            return;
        PartitionLoadInfo  partitionLoadInfo  =  idToPartitionLoadInfo.values().iterator().next();
        Source initSource = partitionLoadInfo.getSources().get(0);
        String dataDirPath  = initSource.getFileUrls().get(0);
        loadHelper.source.setFileUrls(Lists.newArrayList(dataDirPath));
        loadHelper.getBackEndNewSouceFile();
        loadHelper.submitEtlJob();

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







    private class BatchLoadHelper{

        //public  Long version;
        public  Set<Long> startEtlBackendIds = Sets.newHashSet();
        public  Set<Long> finishEtlBackednIds = Sets.newHashSet();
        public  Map<Long,PushTask> startPushTaskIds = Maps.newHashMap();
        public  Set<Long> finishPushTaskIds  = Sets.newHashSet();

        public  Map<Long, Source> backendSourceFile = Maps.newHashMap();
        public  Map<Long, Map<String, Long>> backendToFileMap = Maps.newHashMap();;

        public  Map<Long,Pair<Long,Long>> partitionIdToPartitionVersion = Maps.newHashMap();
        public  Map<Long,ReplicaPersistInfo> finishPushTaskIdToPersistInfo  = Maps.newHashMap();
        public  Source source = new Source();
        public  Long   pushTaskIndex = 0L;



        public  void addFinshEtlTaskAndUpdate(Long backendId,Map<String,Long> fileMap ){
            LOG.info("etl task finished ,backendId :" + backendId   );
            for(String path : fileMap.keySet()){
                LOG.info("etl task file path :" + path);
            }
            finishEtlBackednIds.add(backendId);

            backendToFileMap.put(backendId,fileMap);
        }

        public  void addStartEtlTask(Long taskId){
            startEtlBackendIds.add(taskId);
        }


        public boolean etlJobFinsh(){
            return  startEtlBackendIds.size() == finishEtlBackednIds.size();
        }


        public   void addStartPushTask(Long taskId,PushTask task){
            startPushTaskIds.put(taskId,task);
        }
        public   void addFinishPushTaskAndUpdate(Long taskId,TTabletInfo tabletInfo){
            LOG.info("finish push task :"  + taskId +", tablet id :" + tabletInfo.getTablet_id()  + " ,count:" + tabletInfo.getData_size());
            finishPushTaskIds.add(taskId);

            try {
                finishPushTaskIdToPersistInfo.put(taskId,updateReplicaInfo(tabletInfo,taskId));
            } catch (MetaNotFoundException e) {
                e.printStackTrace();
            }


        }

        public   boolean pushJobFinsh(){
            return  startPushTaskIds.size() ==  finishPushTaskIds.size();
        }

        public void resetEtlInfo(){
            startEtlBackendIds.clear();
            finishEtlBackednIds.clear();
            backendSourceFile.clear();
            backendToFileMap.clear();
        }
        public void resetPushInfo(){
            startPushTaskIds.clear();
            finishPushTaskIds.clear();
            partitionIdToPartitionVersion.clear();
            finishPushTaskIdToPersistInfo.clear();

        }

        public void submitEtlJob() throws LoadException, AnalysisException {

            LOG.info("submitEtlJob");
            for(Long backendId : backendSourceFile.keySet()){
                submitBackendEtlTask(backendId);
            }
        }


        public void submitBackendEtlTask(Long backendId) throws LoadException, AnalysisException {

            Source source = backendSourceFile.get(backendId);

            Long taskId  = Math.abs(new Random().nextLong()) ;
            TMiniLoadEtlTaskRequest request = createRequest(taskId,backendId,source);
            Backend backend = Catalog.getCurrentSystemInfo().getBackend(backendId);

            LOG.info("submit etl task file path " + source.getFileUrls() + " backend id :" +  backendId );
            if(source.getFileUrls().size() == 0){
                LOG.info("empty file  ");
                return;
            }
            AgentClient client = new AgentClient(backend.getHost(), backend.getBePort());
            TAgentResult submitResult = client.submitEtlTask(request);

            if (submitResult.getStatus().getStatus_code() != TStatusCode.OK) {
                //return new EtlSubmitResult(submitResult.getStatus(), null);
            }else {
                addStartEtlTask(backendId);
            }

        }

        public void submitPushJob(){

            LOG.info("submitPushJob");
            db.readLock();

            //Long versionHash  = Math.abs(new Random().nextLong()) ;  //jungle comment : one push job has one versionHash


            for(Map<String,Long>  deltaFileMap: backendToFileMap.values()){

                int  taskNum = 0;
                AgentBatchTask batchTask = new AgentBatchTask();
                for(Map.Entry<String,Long> deltaFile : deltaFileMap.entrySet()){

                    String filePath = deltaFile.getKey();
                    Long fileSize = deltaFile.getValue();


                    String   partitionIndexBucket = null;
                    try {
                        partitionIndexBucket = getPartitionIndexBucketString(filePath);
                    } catch (LoadException e) {
                        e.printStackTrace();
                    }

                    //String.format("%d.%d.%d", partitionId, indexId, bucket);
                    String  [] info = partitionIndexBucket.split("\\.");
                    Long     partitionId = Long.parseLong(info[0]);
                    int      indexId = Integer.parseInt(info[1]);
                    int      tabletIndex = Integer.parseInt(info[2]);

                    Partition partition = table.getPartition(partitionId);
                    MaterializedIndex index = null;
                    for(MaterializedIndex mindex : partition.getMaterializedIndices() ){
                        if(mindex.getId() == indexId){
                            index = mindex;
                            break;
                        }
                    }

                    Tablet  tablet = index.getTablets().get(tabletIndex);


                   // Long partitionCommitedVersion = partition.getCommittedVersion();


                    Long versionHash = getEtlTaskLable(filePath);
                    VersionHashSet.add(versionHash);
                    Long version = partition.getCommittedVersion() + VersionHashSet.size();

                    LOG.info("versionHash is :" + versionHash);

                    int  schemaHash = table.getIndexIdToSchemaHash().get(index.getId()); //when scheme change ,get the new schemaHash
                    //if push to the old schema table,need to convert to new schema ,else clear the schema change info

                    //

                    partitionIdToPartitionVersion.put(partitionId,new Pair<>(version,versionHash));
                    for(Replica replica : tablet.getReplicas()){

                        AgentTask pushTask = new PushTask(null,
                                replica.getBackendId(), db.getId(), table.getId(),
                                partitionId, indexId,
                                tablet.getId() , replica.getId(), schemaHash,
                                version, versionHash, filePath, fileSize, 0,
                                id, TPushType.LOAD, null,
                                false, priority ,TTaskType.STREAMING_PUSH,pushTaskIndex,System.currentTimeMillis() + pushTaskIndex);

                        LOG.info("submit push task id :" + pushTaskIndex  + ",backendId:" +  replica.getBackendId() +
                          ",table id :" + table.getId() + ",partition id:" + partitionId + ",index id:" + indexId +
                          ",tablet id :" + tablet.getId() + ",replica id:" + replica.getId() + ",schemaHash :" + schemaHash +
                          ",version:" + version + ",versionHash:" + versionHash  + ",file path :"+filePath) ;

                        //AgentTaskQueue.addTask(pushTask);
                        batchTask.addTask(pushTask);
                       // taskIdToPartitionInfo.put(pushTaskIndex,new Pair<>(partitionId,new Pair<>(version,versionHash)));



                        addStartPushTask(pushTaskIndex,(PushTask) pushTask);
                        pushTaskIndex ++;
                        taskNum ++;

                    }
                }
                AgentTaskExecutor.submit(batchTask);
                //wait until the delta file all finish

                countDownLatch = new MarkedCountDownLatch(taskNum);
                long timeout =  1000L ;
                boolean ok = false;
                try {
                    ok = countDownLatch.await(timeout, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    LOG.warn("InterruptedException: ", e);
                    ok = false;
                }
                if(!ok){
                    LOG.warn("push job timeout ");
                }


            }


            db.readUnlock();


        }

        private ReplicaPersistInfo updateReplicaInfo(TTabletInfo tTabletInfo,long taskId) throws MetaNotFoundException {

            PushTask task = startPushTaskIds.get(taskId);
            Partition partition = table.getPartition( task.getPartitionId());
            long backendId = task.getBackendId();

            long tabletId = tTabletInfo.getTablet_id();
            int schemaHash = tTabletInfo.getSchema_hash();
            long version = tTabletInfo.getVersion();
            long versionHash = tTabletInfo.getVersion_hash();
            long rowCount = tTabletInfo.getRow_count();
            long dataSize = tTabletInfo.getData_size();

            long pushIndexId = Catalog.getCurrentInvertedIndex().getIndexId(tabletId);


            int currentSchemaHash = table.getSchemaHashByIndexId(pushIndexId);
            if (schemaHash != currentSchemaHash) {

                    // this should not happend. observe(cmy)
                    throw new MetaNotFoundException("Diff tablet[" + tabletId + "] schemaHash. index[" + pushIndexId + "]: "
                            + currentSchemaHash + " vs. " + schemaHash);

            }

            MaterializedIndex materializedIndex = partition.getIndex(pushIndexId);
            if (materializedIndex == null) {
                throw new MetaNotFoundException("Cannot find index[" + pushIndexId + "]");
            }
            Tablet tablet = materializedIndex.getTablet(tabletId);
            if (tablet == null) {
                throw new MetaNotFoundException("Cannot find tablet[" + tabletId + "]");
            }

            // update replica info
            Replica replica = tablet.getReplicaByBackendId(backendId);
            if (replica == null) {
                throw new MetaNotFoundException("cannot find replica in tablet[" + tabletId + "], backend[" + backendId
                        + "]");
            }
            replica.updateInfo(version, versionHash, dataSize, rowCount);

            LOG.debug("replica[{}] report schemaHash:{}", replica.getId(), schemaHash);
            return ReplicaPersistInfo.createForLoad(table.getId(), partition.getId(), pushIndexId, tabletId,
                    replica.getId(), version, versionHash, dataSize, rowCount);
        }

        public void updateFinishPushInfo() {

            db.writeLock();

            if (finishPushTaskIdToPersistInfo != null) {
                for (ReplicaPersistInfo info : finishPushTaskIdToPersistInfo.values()) {
                    OlapTable table = (OlapTable) db.getTable(info.getTableId());
                    if (table == null) {
                        LOG.warn("the table[{}] is missing", info.getIndexId());
                        continue;
                    }
                    Partition partition = table.getPartition(info.getPartitionId());
                    if (partition == null) {
                        LOG.warn("the partition[{}] is missing", info.getIndexId());
                        continue;
                    }
                    MaterializedIndex index = partition.getIndex(info.getIndexId());
                    if (index == null) {
                        LOG.warn("the index[{}] is missing", info.getIndexId());
                        continue;
                    }
                    Tablet tablet = index.getTablet(info.getTabletId());
                    if (tablet == null) {
                        LOG.warn("the tablet[{}] is missing", info.getTabletId());
                        continue;
                    }

                    Replica replica = tablet.getReplicaById(info.getReplicaId());
                    if (replica == null) {
                        LOG.warn("the replica[{}] is missing", info.getReplicaId());
                        continue;
                    }
                    LOG.info("table : "  +  table.getId() + ",partition :" + partition.getId() + ",index:" + index.getId() + ",tablet:" + tablet.getId() +
                            ",replica:" + replica.getId() + ", replica updateInfo :" + info.getVersion() +"," + info.getVersionHash() + "," + info.getRowCount());
                    replica.updateInfo(info.getVersion(), info.getVersionHash(),
                             info.getDataSize(), info.getRowCount());
                }
            }

            for (Map.Entry<Long, Pair<Long,Long>> entry : partitionIdToPartitionVersion.entrySet()) {
                long partitionId = entry.getKey();

                Partition partition = table.getPartition(partitionId);


                updatePartitionVersion(partition, entry.getValue().first,
                        entry.getValue().second, 0);

                // update table row count
                for (MaterializedIndex materializedIndex : partition.getMaterializedIndices()) {
                    long tableRowCount = 0L;
                    for (Tablet tablet : materializedIndex.getTablets()) {
                        long tabletRowCount = 0L;
                        for (Replica replica : tablet.getReplicas()) {
                            long replicaRowCount = replica.getRowCount();
                            if (replicaRowCount > tabletRowCount) {
                                tabletRowCount = replicaRowCount;
                            }
                        }
                        tableRowCount += tabletRowCount;
                    }
                    materializedIndex.setRowCount(tableRowCount);
                } // end for indices
            } // end for partitions

            db.writeUnlock();
        }

        private void updatePartitionVersion(Partition partition, long version, long versionHash, long jobId) {
            long partitionId = partition.getId();
            partition.setCommittedVersion(version);
            partition.setCommittedVersionHash(versionHash);
            LOG.info("update partition version success. version: {}, version hash: {}, job id: {}, partition id: {}",
                    version, versionHash, jobId, partitionId);
        }


        public Source createNewSource( List<String> fileUrls){
            Source newsource = new Source(fileUrls,source.getColumnNames(),
                    source.getColumnSeparator(),
                    source.getLineDelimiter(),
                    source.isNegative());
            return newsource;
        }

        public void getBackEndNewSouceFile() { //get the new etl file

            List<Long>  backendIds = Catalog.getCurrentSystemInfo().getBackendIds(true);

            for(Long  backendId :backendIds) {

                Backend backend = Catalog.getCurrentSystemInfo().getBackend(backendId);
                AgentClient client = new AgentClient(backend.getHost(), backend.getBePort());
                List<String> newFilePath =  client.get_streaming_etl_file_path(source.getFileUrls().get(0),1);
                for(String path : newFilePath){
                    LOG.info("backendid get new File Path :" + path );
                }
                if(!newFilePath.isEmpty()){
                    backendSourceFile.put(backendId,createNewSource(newFilePath));
                }else {

                }

            }

        }
        public void updateEtlFinishedFile() {

            for(Long  backendId :backendSourceFile.keySet()) {

                Backend backend = Catalog.getCurrentSystemInfo().getBackend(backendId);
                AgentClient client = new AgentClient(backend.getHost(), backend.getBePort());

                client.mark_etl_file_done(backendSourceFile.get(backendId).getFileUrls());
                for(String url : backendSourceFile.get(backendId).getFileUrls()){
                    LOG.info("update etl file done : " + url);
                }
            }
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

    }

    private Long getEtlTaskLable(String filePath) {
       String [] vals =  filePath.split("_");
       return Long.parseLong( vals[vals.length-1].split("\\.")[0]);
    }

    ****/


}
