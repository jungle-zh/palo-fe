package com.baidu.palo.metadata;

import com.baidu.palo.catalog.*;
import com.baidu.palo.metadata.dao.*;
import com.baidu.palo.metadata.pojo.*;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class FeMetadataService {

    private static final Logger LOG = LogManager.getLogger(FeMetadataService.class);

    public void save(OlapTable olapTable) {

        MetaOlapTable metaOlapTable = toMetaOlapTable(olapTable);
        List<MetaSchemaIndex> metaSchemaIndexList = toMetaSchemaIndexList(olapTable);
        List<MetaPartition> metaPartitionList = toMetaPartitionList(olapTable);
        List<MetaMaterializedIndex> metaMaterializedIndexList = toMaterializedIndex(olapTable);
        List<MetaTablet> metaTabletList = toMetaTabletList(olapTable);
        List<MetaReplica> metaReplicaList = toMetaReplicaList(olapTable);

        SqlSession session = null;
        try {
            session =  MybatisConfig.getInstance().getSessionFactory();

            MetaOlapTableMapper olapTablePojoMapper = session.getMapper(MetaOlapTableMapper.class);
            olapTablePojoMapper.insert(metaOlapTable);

            if (metaSchemaIndexList.size() > 0) {
                MetaSchemaIndexMapper metaSchemaIndexMapper = session.getMapper(MetaSchemaIndexMapper.class);
                metaSchemaIndexMapper.batchInsert(metaSchemaIndexList);
            }

            if (metaPartitionList.size() > 0) {
                MetaPartitionMapper metaPartitionMapper = session.getMapper(MetaPartitionMapper.class);
                metaPartitionMapper.batchInsert(metaPartitionList);
            }

            if (metaMaterializedIndexList.size()>0) {
                MetaMaterializedIndexMapper metaMaterializedIndexMapper = session.getMapper(MetaMaterializedIndexMapper.class);
                metaMaterializedIndexMapper.batchInsert(metaMaterializedIndexList);
            }

            if (metaTabletList.size()>0) {
                MetaTabletMapper metaTabletMapper = session.getMapper(MetaTabletMapper.class);
                metaTabletMapper.batchInsert(metaTabletList);
            }

            if (metaReplicaList.size()>0) {
                MetaReplicaMapper metaReplicaMapper = session.getMapper(MetaReplicaMapper.class);
                metaReplicaMapper.batchInsert(metaReplicaList);
            }

            session.commit(); // 需要考虑回滚问题
        } catch (Exception e) {
            session.rollback();
            LOG.error("fe meta data save to mysql error : " + e.getMessage());
        } finally {
            if (null != session) {
                session.close();
            }
        }


        LOG.info("fe meta data save to mysql sucessful, table[{}].", olapTable.getName());
    }

    private MetaOlapTable toMetaOlapTable(OlapTable olapTable) {
        MetaOlapTable metaOlapTable = new MetaOlapTable();
        metaOlapTable.setTableId(olapTable.getId());
        metaOlapTable.setTableName(olapTable.getName());
        //枚举转换
        metaOlapTable.setTableType(olapTable.getType().toString());
        // 此处也是枚举转为String，其中还有个方法是name，
        metaOlapTable.setOlapTableState(olapTable.getState().toString());
        metaOlapTable.setKeysType(olapTable.getKeysType().toString());

        // 对象转换为String
        metaOlapTable.setPartitionInfo(JsonHelper.toJson(olapTable.getPartitionInfo()));
        // 对象转换为String
        metaOlapTable.setDistributionInfo(JsonHelper.toJson(olapTable.getDefaultDistributionInfo()));

        List<Long> partitionIdList = new ArrayList<Long>();
        for (Partition partition : olapTable.getPartitions()) {
            partitionIdList.add(partition.getId());
        }
        metaOlapTable.setPartitionIdList(JsonHelper.toJson(partitionIdList));

        // List转化为JSON数组的字符串
        metaOlapTable.setSchemaIndexIdList(JsonHelper.toJson(olapTable.getIndexIdToSchema().keySet()));
        // Set<Columns>转化为String
        metaOlapTable.setBfColumns(JsonHelper.toJson(olapTable.getCopiedBfColumns()));
        metaOlapTable.setBfFpp(olapTable.getBfFpp());

        return metaOlapTable;
    }

    private List<MetaSchemaIndex> toMetaSchemaIndexList(OlapTable olapTable) {
        List<MetaSchemaIndex> metaSchemaIndexList = new ArrayList<MetaSchemaIndex>();
        for (Map.Entry<Long, List<Column>> entry : olapTable.getIndexIdToSchema().entrySet()) {
            MetaSchemaIndex metaSchemaIndex = new MetaSchemaIndex();
            Long indexId = entry.getKey();

            metaSchemaIndex.setId(indexId);
            metaSchemaIndex.setSchemaVersion(olapTable.getSchemaVersionByIndexId(indexId));
            metaSchemaIndex.setSchemaHash(olapTable.getSchemaHashByIndexId(indexId));
            metaSchemaIndex.setShortKeyColumnCount(olapTable.getShortKeyColumnCountByIndexId(indexId));
            metaSchemaIndex.setStorageType(olapTable.getStorageTypeByIndexId(indexId).getValue());
            // 把List<Column>转换为String
            metaSchemaIndex.setSchemaColumn(JsonHelper.toJson(entry.getValue()));

            metaSchemaIndexList.add(metaSchemaIndex);

        }
        return metaSchemaIndexList;
    }


    private List<MetaPartition> toMetaPartitionList(OlapTable olapTable) {
        List<MetaPartition> metaPartitionList = new ArrayList<MetaPartition>();
        for (Partition partition : olapTable.getPartitions()) {
            MetaPartition metaPartition = new MetaPartition();
            metaPartition.setPartitionId(partition.getId());
            metaPartition.setPartitionName(partition.getName());
            // 枚举
            metaPartition.setPartitionState(partition.getState().toString());

            metaPartition.setCommittedVersion(partition.getCommittedVersion());
            metaPartition.setCommittedVersionHash(metaPartition.getCommittedVersionHash());

            // 类转换
            metaPartition.setDistributionInfo(JsonHelper.toJson(partition.getDistributionInfo()));

            //和rollup 的对应
            List<Long> materializedIndexIdList = new ArrayList<Long>();
            for (MaterializedIndex materializedIndex : partition.getMaterializedIndices()) {
                materializedIndexIdList.add(materializedIndex.getId());
            }
            metaPartition.setMaterializedIndexIdList(JsonHelper.toJson(materializedIndexIdList));

            metaPartitionList.add(metaPartition);
        }
        return metaPartitionList;
    }


    // 现在是层级慢慢递减的情况：OlapTable → Partition → MaterializedIndex → Tablet → Replica 。
    // 备注：可能是单Partition，也可能是多Partition —— 判断依据为： partitionInfo.getType() == PartitionType.RANGE
    private List<MetaMaterializedIndex> toMaterializedIndex(OlapTable olapTable) {

        List<MetaMaterializedIndex> materializedIndexList = new ArrayList<MetaMaterializedIndex>();

        for (Partition partition :olapTable.getPartitions()) {

            for (MaterializedIndex materializedIndex : partition.getMaterializedIndices()) {


                MetaMaterializedIndex metaMaterializedIndex = new MetaMaterializedIndex();

                metaMaterializedIndex.setMaterializedIndexId(materializedIndex.getId());
                metaMaterializedIndex.setPartitionId(partition.getId());

                // NORMAL, ROLLUP, SCHEMA_CHANGE
                metaMaterializedIndex.setMaterializedIndexState(materializedIndex.getState().toString());
                metaMaterializedIndex.setRowCount(materializedIndex.getRowCount());
                metaMaterializedIndex.setRollupIndexId(materializedIndex.getRollupIndexId());
                metaMaterializedIndex.setRollupFinishedVersion(metaMaterializedIndex.getRollupFinishedVersion());

                metaMaterializedIndex.setTabletIdList(JsonHelper.toJson(materializedIndex.getTabletIdsInOrder()));

                materializedIndexList.add(metaMaterializedIndex);
            }
        }

        return materializedIndexList;
    }


    private List<MetaTablet> toMetaTabletList(OlapTable olapTable) {
        List<MetaTablet> metaTabletList = new ArrayList<MetaTablet>();

        for (Partition partition : olapTable.getPartitions()) {
            for (MaterializedIndex materializedIndex : partition.getMaterializedIndices()) {
                for (Tablet tablet : materializedIndex.getTablets()) {

                    MetaTablet metaTablet = new MetaTablet();
                    metaTablet.setTabletId(tablet.getId());
                    metaTablet.setCheckedVersion(tablet.getCheckedVersion());
                    metaTablet.setCheckedVersionHash(tablet.getCheckedVersionHash());

                    int isConsistent = tablet.isConsistent() ? 1: 0;
                    metaTablet.setIsConsistent(isConsistent);

                    List<Long> replicaIdList = new ArrayList<Long>();
                    for (Replica replica : tablet.getReplicas()) {
                        replicaIdList.add(replica.getId());
                    }
                    metaTablet.setReplicaIdList(JsonHelper.toJson(replicaIdList));
                    metaTabletList.add(metaTablet);
                }
            }
        }
        return metaTabletList;
    }


    private List<MetaReplica> toMetaReplicaList(OlapTable olapTable) {
        List<MetaReplica> metaReplicaList = new ArrayList<MetaReplica>();

        for (Partition partition :olapTable.getPartitions()) {
            for (MaterializedIndex materializedIndex : partition.getMaterializedIndices()) {
                for (Tablet tablet : materializedIndex.getTablets()) {
                    for (Replica replica :  tablet.getReplicas()) {

                        MetaReplica metaReplica = new MetaReplica();
                        metaReplica.setReplicaId(replica.getId());
                        metaReplica.setBackendId(replica.getBackendId());
                        metaReplica.setVersion(replica.getVersion());
                        metaReplica.setVersionHash(replica.getVersionHash());
                        metaReplica.setDataSize(replica.getRowCount());
                        // 集中类型： NORMAL, ROLLUP, SCHEMA_CHANGE, CLONE
                        metaReplica.setReplicaState(replica.getState().toString());
                        metaReplicaList.add(metaReplica);
                    }
                }
            }
        }

        return metaReplicaList;
    }


    /**
     * @param olapTable
     */
    public void delete(OlapTable olapTable) {
        SqlSession session = null;
        try {
            session =  MybatisConfig.getInstance().getSessionFactory();

            MetaOlapTableMapper olapTablePojoMapper = session.getMapper(MetaOlapTableMapper.class);
            olapTablePojoMapper.deleteByPrimaryKey(olapTable.getId());

            MetaSchemaIndexMapper metaSchemaIndexMapper = session.getMapper(MetaSchemaIndexMapper.class);
            for (Long indexId : olapTable.getIndexIdToSchema().keySet()) {
                metaSchemaIndexMapper.deleteByPrimaryKey(indexId);
            }

            MetaPartitionMapper metaPartitionMapper = session.getMapper(MetaPartitionMapper.class);
            for (Partition partition : olapTable.getPartitions()) {
                metaPartitionMapper.deleteByPrimaryKey(partition.getId());
            }

            MetaMaterializedIndexMapper metaMaterializedIndexMapper = session.getMapper(MetaMaterializedIndexMapper.class);
            for (Partition partition : olapTable.getPartitions()) {
                for (MaterializedIndex materializedIndex : partition.getMaterializedIndices()) {
                    metaMaterializedIndexMapper.deleteByPrimaryKey(materializedIndex.getId(), partition.getId());

                }
            }

            MetaTabletMapper metaTabletMapper = session.getMapper(MetaTabletMapper.class);
            for (Partition partition : olapTable.getPartitions()) {
                for (MaterializedIndex materializedIndex : partition.getMaterializedIndices()) {
                    for (Tablet tablet : materializedIndex.getTablets()) {
                        metaTabletMapper.deleteByPrimaryKey(tablet.getId());
                    }
                }
            }

            MetaReplicaMapper metaReplicaMapper = session.getMapper(MetaReplicaMapper.class);
            for (Partition partition : olapTable.getPartitions()) {
                for (MaterializedIndex materializedIndex : partition.getMaterializedIndices()) {
                    for (Tablet tablet : materializedIndex.getTablets()) {
                        for (Replica replica : tablet.getReplicas()) {
                            metaReplicaMapper.deleteByPrimaryKey(replica.getId());
                        }
                    }
                }
            }

            session.commit();
        } catch (Exception e) {
            session.rollback();
            LOG.error("fe meta data delete error : " + e.getMessage());
        } finally {
            if (null != session) {
                session.close();
            }
        }

        LOG.info("fe meta data, delete sucessful,table[{}].", olapTable.getName());

    }

    /**
     * 主要更新两个表：OlapTable和SchemaIndex
     * @param olapTable
     */
    public void updateSchemaChangeTable(OlapTable olapTable) {
        SqlSession session = null;

        try {
            session = MybatisConfig.getInstance().getSessionFactory();
            MetaOlapTableMapper metaOlapTableMapper = session.getMapper(MetaOlapTableMapper.class);

            MetaOlapTable metaOlapTable = metaOlapTableMapper.selectByPrimaryKey(olapTable.getId());

            // 更新
            metaOlapTable.setBfColumns(JsonHelper.toJson(olapTable.getCopiedBfColumns()));
            metaOlapTable.setBfFpp(olapTable.getBfFpp());
            // 保存
            metaOlapTableMapper.updateByPrimaryKey(metaOlapTable);


            MetaSchemaIndexMapper metaSchemaIndexMapper = session.getMapper(MetaSchemaIndexMapper.class);
            List<Long> lst = JsonHelper.fromJsonArray(metaOlapTable.getSchemaIndexIdList(), Long.class);
//            List<MetaSchemaIndex> metaSchemaIndexList = metaSchemaIndexMapper.selectByIds(lst);

            // 先全部删除MetaSchemaIndex，再获取获取最新的List<MetaSchemaIndex>，然后批量更新：
            for (Long metaSchemaIndex : lst) {
                metaSchemaIndexMapper.deleteByPrimaryKey(metaSchemaIndex);
            }

            List<MetaSchemaIndex> metaSchemaIndexList = toMetaSchemaIndexList(olapTable);
            metaSchemaIndexMapper.batchInsert(metaSchemaIndexList);

            session.commit();

            LOG.info("update schema change table sucessful. ");
        } finally {

            if (null != session) {
                session.close();
            }

        }

    }


    public void updateRollupTable(OlapTable olapTable) {
        SqlSession session = null;

        try {
            session = MybatisConfig.getInstance().getSessionFactory();
            MetaOlapTableMapper metaOlapTableMapper = session.getMapper(MetaOlapTableMapper.class);

            /**
             * 获取表：
             */
            MetaOlapTable metaOlapTable = metaOlapTableMapper.selectByPrimaryKey(olapTable.getId());
            MetaSchemaIndexMapper metaSchemaIndexMapper = session.getMapper(MetaSchemaIndexMapper.class);

            List<Long> schemaIndexIds = JsonHelper.fromJsonArray(metaOlapTable.getSchemaIndexIdList(), Long.class);
            // 先全部删除MetaSchemaIndex，
            for (Long metaSchemaIndex : schemaIndexIds) {
                metaSchemaIndexMapper.deleteByPrimaryKey(metaSchemaIndex);
            }

            // 再获取获取最新的List<MetaSchemaIndex>，然后批量更新：
            List<MetaSchemaIndex> metaSchemaIndexList = toMetaSchemaIndexList(olapTable);
            metaSchemaIndexMapper.batchInsert(metaSchemaIndexList);

            // 备注：创建Rollup的时候，meta_partition部分字段有变化
            List<Long> partitionIds = JsonHelper.fromJsonArray(metaOlapTable.getPartitionIdList(), Long.class);
            MetaPartitionMapper metaPartitionMapper = session.getMapper(MetaPartitionMapper.class);
            for (Long partitionId : partitionIds) {
                metaPartitionMapper.deleteByPrimaryKey(partitionId);
            }
            List<MetaPartition> partitionList = toMetaPartitionList(olapTable);
            metaPartitionMapper.batchInsert(partitionList);


            /**
             * 原有MetaMaterializedInde，不变，新创建了MetaMaterializedInde
             */
            MetaMaterializedIndexMapper metaMaterializedIndexMapper = session.getMapper(MetaMaterializedIndexMapper.class);
            for (Partition partition : olapTable.getPartitions()) {
                for (MaterializedIndex materializedIndex : partition.getMaterializedIndices()) {
                    metaMaterializedIndexMapper.deleteByPrimaryKey(materializedIndex.getId(), partition.getId());
                }
            }
            List<MetaMaterializedIndex> metaMaterializedIndexList = toMaterializedIndex(olapTable);
            metaMaterializedIndexMapper.batchInsert(metaMaterializedIndexList);


            MetaTabletMapper metaTabletMapper = session.getMapper(MetaTabletMapper.class);
            for (Partition partition : olapTable.getPartitions()) {
                for (MaterializedIndex materializedIndex : partition.getMaterializedIndices()) {
                    for (Tablet tablet : materializedIndex.getTablets()) {
                        metaTabletMapper.deleteByPrimaryKey(tablet.getId());
                    }
                }
            }
            List<MetaTablet> metaTabletList = toMetaTabletList(olapTable);
            metaTabletMapper.batchInsert(metaTabletList);

            metaOlapTableMapper.deleteByPrimaryKey(olapTable.getId());
            MetaOlapTable newMetaOlapTable = toMetaOlapTable(olapTable);
            metaOlapTableMapper.insert(newMetaOlapTable);

            // replica的信息已经更新结束，不需要再更新了。
            session.commit();

            LOG.info("update rollup table to sucessful. ");

        } finally {

            if (null != session) {
                session.close();
            }

        }



    }



    public void saveOrUpdateMetaReplica(Replica replica) {
        SqlSession session = null;
        try {
            session = MybatisConfig.getInstance().getSessionFactory();

            MetaReplicaMapper metaReplicaMapper = session.getMapper(MetaReplicaMapper.class);

            MetaReplica metaReplica = metaReplicaMapper.selectByPrimaryKey(replica.getId());

            if (metaReplica == null) {
                metaReplica = new MetaReplica();
                metaReplica.setReplicaId(replica.getId());
                metaReplica.setBackendId(replica.getBackendId());
                metaReplica.setReplicaState(replica.getState().toString());
            }

            metaReplica.setVersion(replica.getVersion());
            metaReplica.setVersionHash(replica.getVersionHash());
            metaReplica.setDataSize(replica.getDataSize());
            metaReplica.setRowCount(replica.getRowCount());

            // 先删除，后更新：
            metaReplicaMapper.deleteByPrimaryKey(replica.getId());
            metaReplicaMapper.insert(metaReplica);

//            metaReplicaMapper.updateByPrimaryKey(metaReplica);

            session.commit();

            LOG.info("modify replica sucessful. ");
        } finally {
            if (null != session) {
                session.close();
            }

        }

    }

    /**
     * 删除Rollup时，修改mysql对应信息
     * @param olapTable
     */
    public void deleteRollupTable(OlapTable olapTable, Long rollupIndexId) {


        SqlSession session = null;
        try {
            session = MybatisConfig.getInstance().getSessionFactory();
            MetaOlapTableMapper olapTablePojoMapper = session.getMapper(MetaOlapTableMapper.class);

            // 备注，这里删除了schema index之后，还要更新olap table表的相关字段
            MetaSchemaIndexMapper metaSchemaIndexMapper = session.getMapper(MetaSchemaIndexMapper.class);
            MetaPartitionMapper metaPartitionMapper = session.getMapper(MetaPartitionMapper.class);
            MetaMaterializedIndexMapper metaMaterializedIndexMapper = session.getMapper(MetaMaterializedIndexMapper.class);
            MetaTabletMapper metaTabletMapper = session.getMapper(MetaTabletMapper.class);
            MetaReplicaMapper metaReplicaMapper = session.getMapper(MetaReplicaMapper.class);


//            long rollupIndexId = olapTable.getIndexIdByName(rollupIndexName);

            for (Partition partition : olapTable.getPartitions()) { // 需要更新materialized_index_id_list的相关字段

                MetaMaterializedIndex rollupIndex = metaMaterializedIndexMapper.selectByPrimaryKey(rollupIndexId, partition.getId());

                if (rollupIndex != null) {

                    List<Long> tabletIdList = JsonHelper.fromJsonArray(rollupIndex.getTabletIdList(), Long.class);

                    for (Long tabletId : tabletIdList) {
                        MetaTablet metaTablet = metaTabletMapper.selectByPrimaryKey(tabletId);
                        List<Long> replictIdList = JsonHelper.fromJsonArray(metaTablet.getReplicaIdList(), Long.class);

                        for (Long replicaId : replictIdList) {
                            // 删除 replica
                            metaReplicaMapper.deleteByPrimaryKey(replicaId);
                        }

                        // 删除 tablet
                        metaTabletMapper.deleteByPrimaryKey(tabletId);

                    }

                }

                // 删除 MaterializedIndex
                metaMaterializedIndexMapper.deleteByPrimaryKey(rollupIndexId, partition.getId());

                // 更新partition：
                MetaPartition metaPartition = metaPartitionMapper.selectByPrimaryKey(partition.getId());
                List<Long> metaMaterializedIndexIds = JsonHelper.fromJsonArray(metaPartition.getMaterializedIndexIdList(), Long.class);
                metaMaterializedIndexIds.remove(rollupIndexId);
                metaPartition.setMaterializedIndexIdList(JsonHelper.toJson(metaMaterializedIndexIds));
                metaPartitionMapper.updateByPrimaryKey(metaPartition);


            }

            // 删除 SchemaIndex
            metaSchemaIndexMapper.deleteByPrimaryKey(rollupIndexId);

            // 更新 olapTable
            MetaOlapTable metaOlapTable = olapTablePojoMapper.selectByPrimaryKey(olapTable.getId());
            List<Long> schemaIndexIds = JsonHelper.fromJsonArray(metaOlapTable.getSchemaIndexIdList(), Long.class);
            schemaIndexIds.remove(rollupIndexId);
            metaOlapTable.setSchemaIndexIdList(JsonHelper.toJson(schemaIndexIds));
            olapTablePojoMapper.updateByPrimaryKey(metaOlapTable);

            session.commit();

            LOG.info("delete rollup table sucessful. ");
        } finally {
            if (null != session) {
                session.close();
            }

        }

    }


    public void updatePartition(Partition partition) {
        SqlSession session = null;
        try {
            session = MybatisConfig.getInstance().getSessionFactory();

            MetaPartitionMapper metaPartitionMapper = session.getMapper(MetaPartitionMapper.class);

            MetaPartition metaPartition = metaPartitionMapper.selectByPrimaryKey(partition.getId());

            if (metaPartition != null) {
                metaPartition.setCommittedVersion(partition.getCommittedVersion());
                metaPartition.setCommittedVersionHash(partition.getCommittedVersionHash());
            }

            session.commit();
            LOG.info("update meta partition table sucessful. ");

        } finally {
            if (null != session) {
                session.close();
            }
        }
    }

    public void updateMaterializedIndex(MaterializedIndex materializedIndex, long partitiooId) {

        SqlSession session = null;
        try {
            session = MybatisConfig.getInstance().getSessionFactory();

            MetaMaterializedIndexMapper metaMaterializedIndexMapper = session.getMapper(MetaMaterializedIndexMapper.class);
            MetaMaterializedIndex metaMaterializedIndex = metaMaterializedIndexMapper.selectByPrimaryKey(materializedIndex.getId(), partitiooId);

            if (materializedIndex != null) {
                materializedIndex.setRowCount(materializedIndex.getRowCount());
            }


            session.commit();
            LOG.info("update meta MaterializedIndex table sucessful. ");

        } finally {
            if (null != session) {
                session.close();
            }
        }

    }

    public static void main(String[] args) {

        SqlSession session = null;

        try {
            session = MybatisConfig.getInstance().getSessionFactory();
            MetaReplicaMapper metaReplicaMapper = session.getMapper(MetaReplicaMapper.class);

//            MetaReplica metaReplica = metaReplicaMapper.selectByPrimaryKey(10);




        } finally {
            if (null != session) {
                session.close();
            }
        }



    }


}
