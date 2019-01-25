package com.baidu.palo.metadata;

import com.baidu.palo.metadata.dao.*;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;

public class MybatisConfig {

    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://172.31.8.1:3306/palo";
    private static String username="palo";
    private static String password="n7xkmDU9KXj2Q";


    private static class SingletonHolder {
        private static final MybatisConfig INSTANCE = new MybatisConfig();
    }

    private MybatisConfig(){

    }

    public static final MybatisConfig getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public SqlSession getSessionFactory() {

        // java api版本
        DataSource dataSource = new PooledDataSource(driver, url, username, password);
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        // 注册mapper
        configuration.addMapper(MetaPartitionMapper.class);
        configuration.addMapper(MetaMaterializedIndexMapper.class);
        configuration.addMapper(MetaOlapTableMapper.class);
        configuration.addMapper(MetaSchemaIndexMapper.class);
        configuration.addMapper(MetaReplicaMapper.class);
        configuration.addMapper(MetaTabletMapper.class);

        configuration.addMapper(MetaDbMapper.class);
        configuration.addMapper(MetaBackendMapper.class);

        configuration.addMapper(MetaInstanceIdMapper.class);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        return sqlSessionFactory.openSession();

        // 读取xml的版本
//        String xmlFile = System.getenv("PALO_HOME") + "/conf/mybatis-config.xml";
////        String xmlFile = "D:\\personal\\company_project\\palo_fe\\mybatis-config.xml";
//        File file = new File(xmlFile);
//        InputStream inputStream = null;
////        Reader reader = null;
//        try {
//            // test:
////            reader = Resources.getResourceAsReader(xmlFile);
////            reader = Resources.getResourceAsReader("mybatis-config.xml");
////            inputStream = Resources.getResourceAsStream(xmlFile);
//
//            inputStream = new FileInputStream(file);
//            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//            return sqlSessionFactory.openSession();
//        } finally {
////            if (null != reader) {
////                reader.close();
////            }
//            if (null != inputStream) {
//                inputStream.close();
//            }
//        }

    }

}
