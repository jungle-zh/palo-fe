package com.baidu.palo.metadata.dao;

import com.baidu.palo.metadata.pojo.MetaSchemaIndex;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

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

    int updateByPrimaryKeySelective(MetaSchemaIndex record);

    int updateByPrimaryKeyWithBLOBs(MetaSchemaIndex record);

    int updateByPrimaryKey(MetaSchemaIndex record);
}