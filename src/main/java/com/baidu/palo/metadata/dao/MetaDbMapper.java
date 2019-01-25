package com.baidu.palo.metadata.dao;

import com.baidu.palo.metadata.pojo.MetaDb;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by admin on 18/7/31.
 */
public interface MetaDbMapper {

    @Delete("delete from meta_db where db_id = #{dbId,jdbcType=BIGINT}")
    int deleteByPrimaryKey(Long tableId);

    @Delete("delete from meta_db where db_name = #{dbName ,jdbcType=VARCHAR}")
    int deleteByName(@Param("dbName") String dbName  );


    @Insert("insert into meta_db (db_id,db_name,cluster_name,db_state,attach_db_name)" +
            " values (#{dbId,jdbcType=BIGINT},#{dbName,jdbcType=VARCHAR},#{clusterName,jdbcType=VARCHAR},#{dbState,jdbcType=VARCHAR},#{attachDbName,jdbcType=VARCHAR})")
    int insert(MetaDb db);

    @Select("select db_name as dbName from meta_db")
    @ResultType(List.class)
    List<String > selectAllDbName();

    @Select("select db_id as dbId,db_name as dbName,cluster_name as clusterName ,db_state as dbState ,attach_db_name as attachDbName from meta_db where db_name = #{dbName,jdbcType=VARCHAR} ")
    MetaDb selectByDbName(@Param("dbName") String dbName);



}
