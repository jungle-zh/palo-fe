package com.baidu.palo.metadata.dao;

import com.baidu.palo.metadata.pojo.MetaOlapTable;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

public interface MetaOlapTableMapper {

    @Delete("delete from meta_olap_table where table_id = #{tableId,jdbcType=BIGINT}")
    int deleteByPrimaryKey(Long tableId);

    @Insert("insert into meta_olap_table (table_id, table_name, table_type, " +
            "olap_table_state, keys_type, partition_info," +
            "distribution_info, partition_id_list, schema_index_id_list," +
            "bf_fpp, bf_columns)" +
            "values (#{tableId,jdbcType=BIGINT}, #{tableName,jdbcType=VARCHAR}, #{tableType,jdbcType=VARCHAR}," +
            "#{olapTableState,jdbcType=VARCHAR}, #{keysType,jdbcType=VARCHAR}, #{partitionInfo,jdbcType=LONGVARCHAR}," +
            "#{distributionInfo,jdbcType=LONGVARCHAR}, #{partitionIdList,jdbcType=VARCHAR}, #{schemaIndexIdList,jdbcType=VARCHAR}," +
            "#{bfFpp,jdbcType=DOUBLE}, #{bfColumns,jdbcType=LONGVARCHAR})")
    int insert(MetaOlapTable record);

    int insertSelective(MetaOlapTable record);

    MetaOlapTable selectByPrimaryKey(Long tableId);

    int updateByPrimaryKeySelective(MetaOlapTable record);

    int updateByPrimaryKeyWithBLOBs(MetaOlapTable record);

    int updateByPrimaryKey(MetaOlapTable record);
}