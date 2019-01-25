package com.baidu.palo.metadata;

import com.baidu.palo.catalog.*;
import com.baidu.palo.metadata.dao.*;
import com.baidu.palo.metadata.pojo.*;
import com.baidu.palo.system.Backend;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class FeMetadataService {

    public FeMetadataService(){

    }

    private static final Logger LOG = LogManager.getLogger(FeMetadataService.class);


    public Replica loadReplica(Long replicaId,SqlSession session){
        MetaReplicaMapper metaReplicaMapper = session.getMapper(MetaReplicaMapper.class);
        MetaReplica metaReplica = metaReplicaMapper.selectByPrimaryKey(replicaId);
        LOG.info("load replica:" + metaReplica.getReplicaId() + " ,version :" + metaReplica.getVersion());
        Replica replica = new Replica(metaReplica.getReplicaId(),metaReplica.getBackendId(),
                metaReplica.getVersion(),metaReplica.getVersionHash(),metaReplica.getDataSize(),
                metaReplica.getRowCount(),JsonHelper.fromJson(metaReplica.getReplicaState(), Replica.ReplicaState.class));

        return  replica;

    }


    public Tablet loadTablet(Long tabletId ,SqlSession session){
        MetaTabletMapper metaTabletMapper = session.getMapper(MetaTabletMapper.class);
        MetaTablet metaTablet = metaTabletMapper.selectByPrimaryKey(tabletId);

        Tablet tablet = new Tablet(metaTablet.getTabletId());
        tablet.setCheckedVersion(metaTablet.getCheckedVersion(),metaTablet.getCheckedVersionHash());
        LOG.info("load tablet:" + tablet.getId() );
        List<Long> replicaIdList =  JsonHelper.fromJsonArray(metaTablet.getReplicaIdList(),Long.class);
        for(Long replicaId : replicaIdList){
            Replica replica = loadReplica(replicaId,session);
            tablet.addReplicaWhenLoad(replica);
        }
        return  tablet;

    }

    public MaterializedIndex loadMaterializedIndex(Long partitionId,Long key ,SqlSession session){

        MetaMaterializedIndexMapper  metaMaterializedIndexMapper = session.getMapper(MetaMaterializedIndexMapper.class);
        LOG.info("loadMaterializedIndex : materializedIndexId:" + key  + " ,partitionId :" + partitionId);
        MetaMaterializedIndex metaMaterializedIndex = metaMaterializedIndexMapper.selectByPrimaryKey(key,partitionId);
        MaterializedIndex  materializedIndex = new MaterializedIndex(metaMaterializedIndex.getMaterializedIndexId(),
                JsonHelper.fromJson(metaMaterializedIndex.getMaterializedIndexState(), MaterializedIndex.IndexState.class));


        materializedIndex.setRollupIndexInfo(metaMaterializedIndex.getRollupIndexId(),metaMaterializedIndex.getRollupFinishedVersion());
        materializedIndex.setRowCount(metaMaterializedIndex.getRowCount());


        List<Long>  tabletIdList = JsonHelper.fromJsonArray(metaMaterializedIndex.getTabletIdList(),Long.class);

        for(Long tabletId : tabletIdList){

            Tablet tablet = loadTablet(tabletId,session);
            materializedIndex.addTablet(tablet);

        }

        return materializedIndex;

    }

    public Partition loadPartition(Long partitionId,SqlSession session){

        MetaPartitionMapper metaPartitionMapper =  session.getMapper(MetaPartitionMapper.class);
        MetaPartition metaPartition = metaPartitionMapper.selectByPrimaryKey(partitionId);

        //todo
        long baseIndexId = metaPartition.getBaseIndexId();

        LOG.info("loadPartition ,partitionId :" + partitionId);
        MaterializedIndex  materializedIndex = loadMaterializedIndex(metaPartition.getPartitionId(),baseIndexId,session);
        LOG.info("load partition baseIndexId  :" + baseIndexId);
        Partition partition = new Partition(metaPartition.getPartitionId(),
                metaPartition.getPartitionName(),
                materializedIndex,
                metaPartition.getDistributionInfo().contains("HASH") ?
                JsonHelper.fromJson(metaPartition.getDistributionInfo(),HashDistributionInfo.class):
                JsonHelper.fromJson(metaPartition.getDistributionInfo(),RandomDistributionInfo.class));

        partition.setName(metaPartition.getPartitionName());
        partition.setCommittedVersion(metaPartition.getCommittedVersion());
        partition.setCommittedVersionHash(metaPartition.getCommittedVersionHash());
        partition.setState(JsonHelper.fromJson(metaPartition.getPartitionState(), Partition.PartitionState.class));
        List<Long> mIndexIdList  = JsonHelper.fromJsonArray(metaPartition.getMaterializedIndexIdList(),Long.class);

        for(long mIndexId : mIndexIdList){
            if(mIndexId == baseIndexId){
                LOG.info("mIndexId == baseIndexId :" + mIndexId);
                continue;
            }
            LOG.info("create mIndexId: " + mIndexId ) ;
            MaterializedIndex  mIndex = loadMaterializedIndex(metaPartition.getPartitionId(),mIndexId,session);
            partition.createRollupIndex(mIndex);
        }

        return partition;

    }



    public OlapTable loadTable(Long tableId,SqlSession session){


            //session =  MybatisConfig.getInstance().getSessionFactory();
            MetaOlapTableMapper olapTablePojoMapper = session.getMapper(MetaOlapTableMapper.class);

            MetaOlapTable metaOlapTable = olapTablePojoMapper.selectByPrimaryKey(tableId);

            //todo add baseScheme and tableName
            OlapTable olapTable = new OlapTable(metaOlapTable.getTableId(),metaOlapTable.getTableName(),JsonHelper.fromJsonArray(metaOlapTable.getBaseSchema(),Column.class),
                    JsonHelper.fromJson(metaOlapTable.getKeysType(),KeysType.class),
                    JsonHelper.fromJson(metaOlapTable.getPartitionInfo(),PartitionInfo.class),
                    metaOlapTable.getDistributionInfo().contains("HASH") ?
                    JsonHelper.fromJson(metaOlapTable.getDistributionInfo(), HashDistributionInfo.class ):
                    JsonHelper.fromJson(metaOlapTable.getDistributionInfo(), RandomDistributionInfo.class ));

            List<Long> indexs = JsonHelper.fromJsonArray(metaOlapTable.getSchemaIndexIdList(), Long.class);
            MetaSchemaIndexMapper metaSchemaIndexMapper = session.getMapper(MetaSchemaIndexMapper.class);


            for(Long index : indexs){
                MetaSchemaIndex metaSchemaIndex = metaSchemaIndexMapper.selectByPrimaryKey(index);
                List<Column> columns = JsonHelper.fromJsonArray(metaSchemaIndex.getSchemaColumn(),Column.class);
                //todo add indexNmae
                olapTable.setIndexSchemaInfo(metaSchemaIndex.getId(),metaSchemaIndex.getName(),columns, metaSchemaIndex.getSchemaVersion(),
                metaSchemaIndex.getSchemaHash(),metaSchemaIndex.getShortKeyColumnCount());

                LOG.info("load table schema index :" + metaSchemaIndex.getId() + " ,schema version :" + metaSchemaIndex.getSchemaVersion() + " ,schema hash:" + metaSchemaIndex.getSchemaHash());
            }

            List<Long> partitioIdList = JsonHelper.fromJsonArray(metaOlapTable.getPartitionIdList(),Long.class);

            for(Long partitionId : partitioIdList){
                Partition partition  =  loadPartition(partitionId,session);
                olapTable.addPartition(partition);
            }



            return  olapTable;
    }

    public List<Backend> loadBackends(){

        List<Backend> res = new ArrayList<>();
        SqlSession session = null;
        try {

            session = MybatisConfig.getInstance().getSessionFactory();
            MetaBackendMapper metaBackendMapper = session.getMapper(MetaBackendMapper.class);
            List<MetaBackend> backends = metaBackendMapper.selectByClusterName("default_cluster");

            for(MetaBackend metaBackend:backends){
                Backend backend = metaBackend.toBackend();
                res.add(backend);
            }



        }catch (Exception e){
            session.rollback();
        } finally {
            if (null != session) {
                session.close();
            }
        }
        return  res;

    }
    public List<Database> loaddb(){


        List<Database> res = new ArrayList<>();
        SqlSession session = null;
        try {

            session = MybatisConfig.getInstance().getSessionFactory();

            MetaDbMapper dbmapper = session.getMapper(MetaDbMapper.class);
            List<String> dbNames = dbmapper.selectAllDbName();

            MetaOlapTableMapper olapTablePojoMapper = session.getMapper(MetaOlapTableMapper.class);

            for(String dbName :dbNames ){


                MetaDb metaDb = dbmapper.selectByDbName(dbName);
                Database db = new Database(metaDb.getDbId(),metaDb.getDbName());
                db.setClusterName(metaDb.getClusterName());
                db.setDbState(JsonHelper.fromJson(metaDb.getDbState(), Database.DbState.class));
                db.setAttachDb(metaDb.getAttachDbName());

                List<Long> ids  =  olapTablePojoMapper.selectTableIdsByDbName(dbName);
                for(Long id : ids){
                    OlapTable table =  loadTable(id,session);
                    db.createTable(table);
                }
                res.add(db);
            }

        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        } finally {
            if (null != session) {
                session.close();
            }
        }

        return  res;

    }

    public Long loadNextId(){

        Long id = -1L;

        SqlSession session = null;
        try {

            session = MybatisConfig.getInstance().getSessionFactory();
            MetaInstanceIdMapper metaInstanceIdMapper = session.getMapper(MetaInstanceIdMapper.class);

            id = metaInstanceIdMapper.selectId();



        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        } finally {
            if (null != session) {
                session.close();
            }
        }

        return  id ;


    }
    public void saveBackEnd(Backend backend){
        LOG.info("savaBackEnd");

      MetaBackend metaBackend =  toMetaBackend(backend);

        SqlSession session = null;
        try {
            session = MybatisConfig.getInstance().getSessionFactory();
            MetaBackendMapper metaBackendMapper = session.getMapper(MetaBackendMapper.class);
            MetaBackend metaBackend1 = metaBackendMapper.selectByPrimaryKey(metaBackend.getBackendId());
            if(metaBackend1 == null){
                metaBackendMapper.insert(metaBackend);
            }else {
                metaBackendMapper.updateByPrimaryKey(metaBackend);
            }

            session.commit();

        }catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            if (null != session) {
                session.close();
            }
        }



    }
    public void saveDb(Database db){

        LOG.info("saveDb ");
        MetaDb metaDb = toMetaDb(db);

        SqlSession session = null;
        try {
            session = MybatisConfig.getInstance().getSessionFactory();
            MetaDbMapper metaDbMapper = session.getMapper(MetaDbMapper.class);
            LOG.info(" dbName:" + metaDb.getDbName());
            metaDbMapper.insert(metaDb);


            session.commit();
        }catch (Exception e) {
            e.printStackTrace();
            session.rollback();
            LOG.error("fe meta data save to mysql error : " + e.getMessage());
        } finally {
            if (null != session) {
                session.close();
            }
        }


    }
    public void saveTable(OlapTable olapTable,Database db) {

        MetaOlapTable metaOlapTable = toMetaOlapTable(olapTable,db);
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


    private MetaBackend toMetaBackend(Backend backend){
        MetaBackend metaBackend = new MetaBackend();
        metaBackend.setOwnerClusterName(backend.getOwnerClusterName());
        metaBackend.setBePort(backend.getBePort());
        metaBackend.setBeRpcPort(backend.getBeRpcPort());
        metaBackend.setDecommissionType(JsonHelper.toJson(backend.getDecommissionType()));
        metaBackend.setHeartbeatPort(backend.getHeartbeatPort());
        metaBackend.setHost(backend.getHost());
        metaBackend.setHttpPort(backend.getHttpPort());
        metaBackend.setBackendId(backend.getId());
        metaBackend.setLastStartTime(backend.getLastStartTime());
        metaBackend.setLastUpdateMs(backend.getLastUpdateMs());

        return metaBackend;

    }
    private MetaDb toMetaDb(Database db){
        MetaDb metaDb = new MetaDb() ;
        metaDb.setDbId(db.getId());
        metaDb.setDbName(db.getFullName());
        metaDb.setDbState(JsonHelper.toJson(db.getDbState()));
        metaDb.setClusterName(db.getClusterName());
        metaDb.setAttachDbName(db.getAttachDb());

        return  metaDb;


    }
    private MetaOlapTable toMetaOlapTable(OlapTable olapTable,Database db) {
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

        metaOlapTable.setBaseSchema(JsonHelper.toJson(olapTable.getBaseSchema()));
        metaOlapTable.setDbName(db.getFullName());

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
            metaSchemaIndex.setName(olapTable.getIndexNameById(indexId));

            LOG.info("metaSchemaIndexList add metaSchemaIndex:" + indexId);
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
            metaPartition.setCommittedVersionHash(partition.getCommittedVersionHash());
            metaPartition.setBaseIndexId(partition.getBaseIndex().getId());


            // 类转换
            metaPartition.setDistributionInfo(JsonHelper.toJson(partition.getDistributionInfo()));

            //和rollup 的对应
            List<Long> materializedIndexIdList = new ArrayList<Long>();
            for (MaterializedIndex materializedIndex : partition.getMaterializedIndices()) {
                materializedIndexIdList.add(materializedIndex.getId());
            }
            metaPartition.setMaterializedIndexIdList(JsonHelper.toJson(materializedIndexIdList));

            LOG.info("metaPartitionList add metaPartition:" + metaPartition.getPartitionId());
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

                LOG.info("materializedIndexList add metaMaterializedIndex,materializedIndexId:partitionId " + metaMaterializedIndex.getMaterializedIndexId() +":"+ metaMaterializedIndex.getPartitionId() );
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
                    LOG.info("metaTabletList add metaTablet: " + metaTablet.getTabletId());
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
                        LOG.info("metaReplicaList add metaReplica:" + metaReplica.getReplicaId());
                        metaReplicaList.add(metaReplica);
                    }
                }
            }
        }

        return metaReplicaList;
    }
    public void deleteBackend(Backend backend){

        SqlSession session = null;
        try {

            session =  MybatisConfig.getInstance().getSessionFactory();
            MetaBackendMapper metaBackendMapper = session.getMapper(MetaBackendMapper.class);

            metaBackendMapper.deleteByPrimaryKey(backend.getId());

            session.commit();
        } catch (Exception e) {
            session.rollback();
            LOG.error("fe meta data delete error : " + e.getMessage());
        } finally {
            if (null != session) {
                session.close();
            }
        }


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


    public void updateRollupTable(OlapTable olapTable,Database db) {
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
            MetaOlapTable newMetaOlapTable = toMetaOlapTable(olapTable,db);
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

            boolean isNew = false;
            if (metaReplica == null) {
                isNew = true;
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
            //metaReplicaMapper.deleteByPrimaryKey(replica.getId());
            if(isNew){
                metaReplicaMapper.insert(metaReplica);
            }else {
                metaReplicaMapper.updateByPrimaryKey(metaReplica);
            }


         //  metaReplicaMapper.updateByPrimaryKey(metaReplica);

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
        LOG.info("updatePartition");
        SqlSession session = null;
        try {
            session = MybatisConfig.getInstance().getSessionFactory();

            MetaPartitionMapper metaPartitionMapper = session.getMapper(MetaPartitionMapper.class);


            MetaPartition metaPartition = metaPartitionMapper.selectByPrimaryKey(partition.getId());

            if (metaPartition != null) {
                metaPartition.setCommittedVersion(partition.getCommittedVersion());
                metaPartition.setCommittedVersionHash(partition.getCommittedVersionHash());
                LOG.info("setCommittedVersion  :" + partition.getCommittedVersion());
            }

            metaPartitionMapper.updateByPrimaryKey(metaPartition);

            session.commit();
            LOG.info("update meta partition table sucessful, version:  {} ,version hash : {}" , metaPartition.getCommittedVersion(),metaPartition.getCommittedVersionHash());

        }
        catch (Exception e){
            session.rollback();
            e.printStackTrace();
        }
        finally {
            if (null != session) {
                session.close();
            }
        }
    }

    public void updateMaterializedIndex(MaterializedIndex materializedIndex,Long partitionId) {

        SqlSession session = null;
        try {
            session = MybatisConfig.getInstance().getSessionFactory();

            MetaMaterializedIndexMapper metaMaterializedIndexMapper = session.getMapper(MetaMaterializedIndexMapper.class);
            MetaMaterializedIndex metaMaterializedIndex = metaMaterializedIndexMapper.selectByPrimaryKey(materializedIndex.getId(), partitionId);

            if (metaMaterializedIndex != null) {
                metaMaterializedIndex.setRowCount(materializedIndex.getRowCount());
                metaMaterializedIndexMapper.updateCountByPrimaryKey(metaMaterializedIndex);
            }

            session.commit();
            LOG.info("update meta MaterializedIndex table sucessful. ");

        } catch (Exception e){
            session.rollback();
            e.printStackTrace();
        }finally {
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


    public void saveNextId(Long nextId) {


        SqlSession session = null;

        try {
            session = MybatisConfig.getInstance().getSessionFactory();
            MetaInstanceIdMapper metaInstanceIdMapper = session.getMapper(MetaInstanceIdMapper.class);

            Long currentId = metaInstanceIdMapper.selectId();
            if(currentId == null || currentId < 10000){
                metaInstanceIdMapper.insertId(nextId);
            }else {
                metaInstanceIdMapper.updateId(nextId);
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


    }
}
