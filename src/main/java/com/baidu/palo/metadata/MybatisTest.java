package com.baidu.palo.metadata;

import com.baidu.palo.metadata.dao.MetaPartitionMapper;
import com.baidu.palo.metadata.pojo.MetaPartition;
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
            List<MetaPartition> lst = new ArrayList<MetaPartition>();
            MetaPartition p1 = new MetaPartition();
            p1.setPartitionId(1L);
            p1.setPartitionName("test");
            lst.add(p1);
            MetaPartition p2 = new MetaPartition();
            p2.setPartitionId(2L);
            lst.add(p2);

            MetaPartitionMapper metaPartitionMapper = session.getMapper(MetaPartitionMapper.class);
            metaPartitionMapper.batchInsert(lst);
//            metaPartitionMapper.insert(p1);
            session.commit();

        } finally {
            if (null != session) {
                session.close();
            }
        }

    }

}
