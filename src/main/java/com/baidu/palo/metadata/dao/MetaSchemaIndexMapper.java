package com.baidu.palo.metadata.dao;

import com.baidu.palo.metadata.pojo.MetaSchemaIndex;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface MetaSchemaIndexMapper {

    @Delete("delete from meta_schema_index where id = #{id,jdbcType=BIGINT}")
    int deleteByPrimaryKey(Long id);

    @Insert("insert into meta_schema_index (id, schema_version, schema_hash," +
            "short_key_column_count, storage_type, schema_column)" +
            "values (#{id,jdbcType=BIGINT}, #{schemaVersion,jdbcType=INTEGER}, #{schemaHash,jdbcType=INTEGER}," +
            "#{shortKeyColumnCount,jdbcType=SMALLINT}, #{storageType,jdbcType=INTEGER}, #{schemaColumn,jdbcType=LONGVARCHAR})")
    int insert(MetaSchemaIndex record);

    @Insert("<script>"  +
            "insert into meta_schema_index (id, schema_version, schema_hash," +
            "short_key_column_count, storage_type, schema_column)" +
            "values" +
            "<foreach collection=\"list\" item=\"item\" index=\"index\" separator=\",\">" +
            "      (#{item.id,jdbcType=BIGINT}, #{item.schemaVersion,jdbcType=INTEGER}, #{item.schemaHash,jdbcType=INTEGER}," +
            "      #{item.shortKeyColumnCount,jdbcType=SMALLINT}, #{item.storageType,jdbcType=INTEGER}, #{item.schemaColumn,jdbcType=LONGVARCHAR}" +
            "      )" +
            "</foreach>" +
            "</script>")
    int batchInsert(@Param("list") List<MetaSchemaIndex> lst);

    int insertSelective(MetaSchemaIndex record);

    MetaSchemaIndex selectByPrimaryKey(Long id);

    @Select("<script>"
            + "select id, schema_version, schema_hash, short_key_column_count, storage_type, schema_column "
            + "from meta_schema_index where id in "
            + "<foreach item='id' index='index' collection='ids' open='(' separator=',' close=')'>"
                + "#{id}"
            + "</foreach>"
            + "</script>")
    @Results(value = {@Result(column="id",property="id"),
            @Result(column="schema_version",property="schemaVersion"),
            @Result(column="schema_hash",property="schemaHash"),
            @Result(column="short_key_column_count",property="shortKeyColumnCount"),
            @Result(column="storage_type",property="storageType"),
            @Result(column="schema_column",property="schemaColumn")})
    List<MetaSchemaIndex> selectByIds(@Param("ids") List<Integer> ids);

    int updateByPrimaryKeySelective(MetaSchemaIndex record);

    int updateByPrimaryKeyWithBLOBs(MetaSchemaIndex record);

    int updateByPrimaryKey(MetaSchemaIndex record);
}