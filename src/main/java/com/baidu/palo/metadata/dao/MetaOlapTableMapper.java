package com.baidu.palo.metadata.dao;

import com.baidu.palo.metadata.pojo.MetaOlapTable;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface MetaOlapTableMapper {

    @Delete("delete from meta_olap_table where table_id = #{tableId,jdbcType=BIGINT}")
    int deleteByPrimaryKey(Long tableId);

    @Insert("insert into meta_olap_table (table_id, table_name, table_type, " +
            "olap_table_state, keys_type, partition_info," +
            "distribution_info, partition_id_list, schema_index_id_list," +
            "bf_fpp, bf_columns ,base_schema ,db_name)" +
            "values (#{tableId,jdbcType=BIGINT}, #{tableName,jdbcType=VARCHAR}, #{tableType,jdbcType=VARCHAR}," +
            "#{olapTableState,jdbcType=VARCHAR}, #{keysType,jdbcType=VARCHAR}, #{partitionInfo,jdbcType=LONGVARCHAR}," +
            "#{distributionInfo,jdbcType=LONGVARCHAR}, #{partitionIdList,jdbcType=VARCHAR}, #{schemaIndexIdList,jdbcType=VARCHAR}," +
            "#{bfFpp,jdbcType=DOUBLE}, #{bfColumns,jdbcType=LONGVARCHAR}, #{baseSchema,jdbcType=LONGVARCHAR} ,#{dbName,jdbcType=VARCHAR})")
    int insert(MetaOlapTable record);

    int insertSelective(MetaOlapTable record);

    @Select("select table_id as tableId, table_name as tableName, table_type as tableType, olap_table_state as olapTableState, keys_type as keysType, partition_info as partitionInfo, distribution_info as distributionInfo, " +
            "partition_id_list as partitionIdList, schema_index_id_list as schemaIndexIdList, bf_fpp as bfFpp, bf_columns as bfColumns , base_schema as baseSchema ,db_name as dbName from meta_olap_table where table_id = #{tableId,jdbcType=BIGINT}")
    MetaOlapTable selectByPrimaryKey(@Param("tableId") Long tableId);



    @Select("select table_id as tableId from meta_olap_table")
    //@Results(value = { @Result(column = "table_id",javaType = Long.class) } )
    @ResultType(List.class)
    List<Long> selectAllId();


    @Select("select table_id as tableId  from meta_olap_table where db_name = #{dbName,jdbcType=VARCHAR}")
    List<Long> selectTableIdsByDbName(@Param("dbName") String dbName);

    int updateByPrimaryKeySelective(MetaOlapTable record);

    int updateByPrimaryKeyWithBLOBs(MetaOlapTable record);

    @Update("update meta_olap_table " +
            "set table_name = #{tableName,jdbcType=VARCHAR}," +
            "table_type = #{tableType,jdbcType=VARCHAR}," +
            "olap_table_state = #{olapTableState,jdbcType=VARCHAR}," +
            "keys_type = #{keysType,jdbcType=VARCHAR}," +
            "partition_info = #{partitionInfo,jdbcType=LONGVARCHAR}," +
            "distribution_info = #{distributionInfo,jdbcType=LONGVARCHAR}," +
            "partition_id_list = #{partitionIdList,jdbcType=VARCHAR}," +
            "schema_index_id_list = #{schemaIndexIdList,jdbcType=VARCHAR}," +
            "bf_fpp = #{bfFpp,jdbcType=DOUBLE}," +
            "base_schema = #{baseSchema,jdbcType=LONGVARCHAR}, " +
            "db_name = #{dbName,jdbcType=VARCHAR} " +
            "where table_id = #{tableId,jdbcType=BIGINT}")
    int updateByPrimaryKey(MetaOlapTable record);
}