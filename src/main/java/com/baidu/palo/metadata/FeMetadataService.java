package com.baidu.palo.metadata;

import com.baidu.palo.catalog.*;
import com.baidu.palo.metadata.dao.*;
import com.baidu.palo.metadata.pojo.*;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class FeMetadataService {

    private static final Logger LOG = LogManager.getLogger(FeMetadataService.class);

    public void save(OlapTable olapTable) {

//        LOG.info("########################## create olap table : "  + JsonHelper.toJson(olapTable));

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
            } else {
                LOG.info("=============     metaSchemaIndexList is 0");
            }

            if (metaPartitionList.size() > 0) {
                MetaPartitionMapper metaPartitionMapper = session.getMapper(MetaPartitionMapper.class);
                metaPartitionMapper.batchInsert(metaPartitionList);
            } else {
                LOG.info("=============     metaPartitionList is 0");
            }

            if (metaMaterializedIndexList.size()>0) {
                MetaMaterializedIndexMapper metaMaterializedIndexMapper = session.getMapper(MetaMaterializedIndexMapper.class);
                metaMaterializedIndexMapper.batchInsert(metaMaterializedIndexList);
            } else {
                LOG.info("=============     metaMaterializedIndexList is 0");
            }

            if (metaTabletList.size()>0) {
                MetaTabletMapper metaTabletMapper = session.getMapper(MetaTabletMapper.class);
                metaTabletMapper.batchInsert(metaTabletList);
            } else {
                LOG.info("=============     metaTabletList is 0");
            }

            if (metaReplicaList.size()>0) {
                MetaReplicaMapper metaReplicaMapper = session.getMapper(MetaReplicaMapper.class);
                metaReplicaMapper.batchInsert(metaReplicaList);
            } else {
                LOG.info("=============     metaReplicaList is 0");
            }

            session.commit(); // 需要考虑回滚问题
        } catch (IOException e) {
            session.rollback();
            LOG.error("fe metadata save error : " + e.getMessage());
        } finally {
            if (null != session) {
                session.close();
            }
        }


        LOG.info("fe metadata save sucessful, table[{}].", olapTable.getName());
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
                    metaMaterializedIndexMapper.deleteByPrimaryKey(materializedIndex.getId());

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
        } catch (IOException e) {
            session.rollback();
            LOG.error("fe metadata delete error : " + e.getMessage());
        } finally {
            if (null != session) {
                session.close();
            }
        }

        LOG.info("fe metadata, delete sucessful,table[{}].", olapTable.getName());

    }






}
