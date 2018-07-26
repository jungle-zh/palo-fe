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

import com.baidu.palo.catalog.*;
import com.baidu.palo.common.AnalysisException;
import com.baidu.palo.common.LoadException;
import com.baidu.palo.common.MetaNotFoundException;
import com.baidu.palo.common.Pair;
import com.baidu.palo.load.Source;
import com.baidu.palo.persist.ReplicaPersistInfo;
import com.baidu.palo.thrift.*;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * This class group tasks by backend 
 */
public class AgentStreamingPush {


    // descriptor used to register all column and table need
    //private DescriptorTable desc;

    // destination Db and table get from request
    // Data will load to this table
    //private   OlapTable destTable;

    private final Database db;
    private final OlapTable table;
    private final long id ;


    private final ConcurrentLinkedQueue<Map<String,Long>> deltaFileQueue ;

    private static final Logger LOG = LogManager.getLogger(AgentStreamingPush.class);




    private Lock pushUpdateLock = new ReentrantLock();
    private Condition nextPush = pushUpdateLock.newCondition();
    private SubmitPushTask submitPushTask;


    public AgentStreamingPush(Database db, OlapTable table, long id, ConcurrentLinkedQueue<Map<String, Long>> deltaFileQueue) {

        this.db = db;
        this.table = table;
        this.id = id;

        this.deltaFileQueue = deltaFileQueue;


        submitPushTask = new SubmitPushTask();

        Thread thread = new Thread(submitPushTask);
        thread.start();

    }

    private class SubmitPushTask implements Runnable {

        @Override
        public void run() {

            while (true){

                while (!deltaFileQueue.isEmpty()){
                    LOG.info("deltaFileQueue is not empty");
                    Map<String,Long>  delta = deltaFileQueue.poll();

                    submitPushTask(delta);


                    pushUpdateLock.lock();

                    try {
                        while (!pushJobFinsh()){
                            nextPush.await(600, TimeUnit.SECONDS);
                            LOG.info("push job wait ");
                        }

                        LOG.info("push job wake up");
                        updateFinishPushInfo();
                        resetPushInfo();
                        Catalog.getInstance().saveImage();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        pushUpdateLock.unlock();
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
    public void submitPushTask(Map<String,Long>  deltaFileMap ){

        AgentBatchTask batchTask = new AgentBatchTask();
        for (Map.Entry<String, Long> deltaFile : deltaFileMap.entrySet()) {

            String filePath = deltaFile.getKey();
            Long fileSize = deltaFile.getValue();


            String partitionIndexBucket = null;
            try {
                partitionIndexBucket = getPartitionIndexBucketString(filePath);
            } catch (LoadException e) {
                e.printStackTrace();
            }

            //String.format("%d.%d.%d", partitionId, indexId, bucket);
            String[] info = partitionIndexBucket.split("\\.");
            Long partitionId = Long.parseLong(info[0]);
            int indexId = Integer.parseInt(info[1]);
            int tabletIndex = Integer.parseInt(info[2]);

            Partition partition = table.getPartition(partitionId);
            MaterializedIndex index = null;
            for (MaterializedIndex mindex : partition.getMaterializedIndices()) {
                if (mindex.getId() == indexId) {
                    index = mindex;
                    break;
                }
            }

            Tablet tablet = index.getTablets().get(tabletIndex);
            Long versionHash = getEtlTaskLable(filePath);
            Long version = partition.getCommittedVersion() + 1;

            LOG.info("versionHash is :" + versionHash +",version is :" + version);

            int schemaHash = table.getIndexIdToSchemaHash().get(index.getId()); //when scheme change ,get the new schemaHash
            //if push to the old schema table,need to convert to new schema ,else clear the schema change info

            //

            partitionIdToPartitionVersion.put(partitionId, new Pair<>(version, versionHash));
            for (Replica replica : tablet.getReplicas()) {

                AgentTask pushTask = new PushTask(null,
                        replica.getBackendId(), db.getId(), table.getId(),
                        partitionId, indexId,
                        tablet.getId(), replica.getId(), schemaHash,
                        version, versionHash, filePath, fileSize, 0,
                        id, TPushType.LOAD, null,
                        false, null, TTaskType.STREAMING_PUSH, pushTaskIndex, System.currentTimeMillis() + pushTaskIndex);

                LOG.info("submit push task id :" + pushTaskIndex + ",backendId:" + replica.getBackendId() +
                        ",table id :" + table.getId() + ",partition id:" + partitionId + ",index id:" + indexId +
                        ",tablet id :" + tablet.getId() + ",replica id:" + replica.getId() + ",schemaHash :" + schemaHash +
                        ",version:" + version + ",versionHash:" + versionHash + ",file path :" + filePath);


                batchTask.addTask(pushTask);

                addStartPushTask(pushTaskIndex, (PushTask) pushTask);
                pushTaskIndex++;

            }
        }
        AgentTaskExecutor.submit(batchTask);
    }


    //call in MasterImpl  updateReplicaInfo
    public TMasterResult updateMiniPushTaskStatus(TFinishTaskRequest request) {


        LOG.info("call updateMiniPushTaskStatus");
        TMasterResult result = new TMasterResult();
        TStatus tStatus = new TStatus(TStatusCode.OK);
        result.setStatus(tStatus);

        List<TTabletInfo> finishTabletInfos = request.getFinish_tablet_infos();
        TTabletInfo tabletInfo = finishTabletInfos.get(0);

        //lock

        pushUpdateLock.lock();
        try {
            addFinishPushTaskAndUpdate(request.getTask_id(), tabletInfo);
            if (pushJobFinsh()) {
                LOG.info("notify push job finished ");
                nextPush.signalAll();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pushUpdateLock.unlock();
        }

        //unlock
        return result;


    }


    public void start() throws LoadException, AnalysisException {


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





    public Map<Long, PushTask> startPushTaskIds = Maps.newHashMap();
    public Set<Long> finishPushTaskIds = Sets.newHashSet();


    public Map<Long, Pair<Long, Long>> partitionIdToPartitionVersion = Maps.newHashMap();
    public Map<Long, ReplicaPersistInfo> finishPushTaskIdToPersistInfo = Maps.newHashMap();
    public Source source = new Source();
    public Long pushTaskIndex = 0L;


    public void addStartPushTask(Long taskId, PushTask task) {
        startPushTaskIds.put(taskId, task);
    }

    public void addFinishPushTaskAndUpdate(Long taskId, TTabletInfo tabletInfo) {
        LOG.info("finish push task :" + taskId + ", tablet id :" + tabletInfo.getTablet_id() + " ,count:" + tabletInfo.getData_size());
        finishPushTaskIds.add(taskId);

        try {
            finishPushTaskIdToPersistInfo.put(taskId, updateReplicaInfo(tabletInfo, taskId));
        } catch (MetaNotFoundException e) {
            e.printStackTrace();
        }


    }

    public boolean pushJobFinsh() {
        return startPushTaskIds.size() == finishPushTaskIds.size()
                && finishPushTaskIds.size() > 0L && finishPushTaskIds.containsAll(startPushTaskIds.keySet())  ;

    }


    public void resetPushInfo() {
        startPushTaskIds.clear();
        finishPushTaskIds.clear();
        partitionIdToPartitionVersion.clear();
        finishPushTaskIdToPersistInfo.clear();

    }




    private ReplicaPersistInfo updateReplicaInfo(TTabletInfo tTabletInfo, long taskId) throws MetaNotFoundException {

        PushTask task = startPushTaskIds.get(taskId);
        Partition partition = table.getPartition(task.getPartitionId());
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
                LOG.info("table : " + table.getId() + ",partition :" + partition.getId() + ",index:" + index.getId() + ",tablet:" + tablet.getId() +
                        ",replica:" + replica.getId() + ", replica updateInfo :" + info.getVersion() + "," + info.getVersionHash() + "," + info.getRowCount());
                replica.updateInfo(info.getVersion(), info.getVersionHash(),
                        info.getDataSize(), info.getRowCount());
            }
        }

        for (Map.Entry<Long, Pair<Long, Long>> entry : partitionIdToPartitionVersion.entrySet()) {
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




    private Long getEtlTaskLable(String filePath) {
        String[] vals = filePath.split("_");
        return Long.parseLong(vals[vals.length - 1].split("\\.")[0]);
    }

}



