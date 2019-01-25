package com.baidu.palo.metadata.dao;

import org.apache.ibatis.annotations.*;

/**
 * Created by admin on 18/8/2.
 */
public interface MetaInstanceIdMapper {

    @Update("update meta_instanceid set " +
            "id = #{currentId,jdbcType=BIGINT}")
    int updateId(@Param("currentId") Long currentId);

    @Select("select id from meta_instanceid")
    @ResultType(Long.class)
    Long selectId();


    @Insert("insert into meta_instanceid (id) values (#{currentId,jdbcType=BIGINT})")
    int insertId(@Param("currentId") Long currentId);
}
