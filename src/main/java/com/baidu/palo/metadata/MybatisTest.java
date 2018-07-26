package com.baidu.palo.metadata;

import com.baidu.palo.metadata.dao.*;
import com.baidu.palo.metadata.pojo.MetaOlapTable;
import com.baidu.palo.metadata.pojo.MetaPartition;
import com.baidu.palo.metadata.pojo.MetaReplica;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MybatisTest {


    public static void main(String[] args) throws IOException {
        MybatisTest mt = new MybatisTest();
        mt.getUser();



    }

    public void getUser() throws IOException  {
        SqlSession session = null;
        try {
            session =  MybatisConfig.getInstance().getSessionFactory();

//            List<MetaPartition> lst = new ArrayList<MetaPartition>();
//            MetaPartition p1 = new MetaPartition();
//            p1.setPartitionId(1L);
//            p1.setPartitionName("test");
//            lst.add(p1);
//            MetaPartition p2 = new MetaPartition();
//            p2.setPartitionId(2L);
//            lst.add(p2);
//
//            MetaPartitionMapper metaPartitionMapper = session.getMapper(MetaPartitionMapper.class);
//            System.out.println(metaPartitionMapper.selectByPrimaryKey(10438L).getPartitionName());
//            metaPartitionMapper.batchInsert(lst);
////            metaPartitionMapper.insert(p1);
//            session.commit();




//            MetaMaterializedIndexMapper metaMaterializedIndexMapper = session.getMapper(MetaMaterializedIndexMapper.class);
//
//            System.out.println(metaMaterializedIndexMapper.selectByPrimaryKey(10439L, 10438L).getTabletIdList());


            /**
             * 查询
             */
//            MetaOlapTableMapper metaOlapTableMapper = session.getMapper(MetaOlapTableMapper.class);
//            MetaPartitionMapper metaPartitionMapper = session.getMapper(MetaPartitionMapper.class);
//
//            MetaOlapTable metaOlapTable = metaOlapTableMapper.selectByPrimaryKey(10224L);
//            metaOlapTable.setTableName("table3");
//            metaOlapTableMapper.updateByPrimaryKey(metaOlapTable);
//
//            List<Long> partitionIds = JsonHelper.fromJsonArray(metaOlapTable.getPartitionIdList(), Long.class);
//            System.out.println(partitionIds);
//            List<MetaPartition> metaPartitionList = metaPartitionMapper.selectByIds(partitionIds);
//            System.out.println(metaPartitionList.size());

            /**
             * 更新
             */
//            MetaReplicaMapper metaReplicaMapper = session.getMapper(MetaReplicaMapper.class);
//            MetaReplica metaReplica = metaReplicaMapper.selectByPrimaryKey(10284L);
//
//            System.out.println(metaReplica == null);
//
//            System.out.println(metaReplicaMapper.deleteByPrimaryKey(1L));
//
//            System.out.println(metaReplica.toString());
//
//            metaReplica.setVersion(2L);
//            metaReplica.setVersionHash(1L);
//            metaReplica.setDataSize(0L);
//
//            metaReplicaMapper.updateByPrimaryKey(metaReplica);

            MetaTabletMapper metaTabletMapper = session.getMapper(MetaTabletMapper.class);
            System.out.println(metaTabletMapper.selectByPrimaryKey(10440L).getCheckedVersion());



            session.commit();

        } finally {

            if (null != session) {
                session.close();
            }

        }

    }

}
