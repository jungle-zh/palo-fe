package com.baidu.palo.metadata.dao;

import com.baidu.palo.metadata.pojo.MetaMaterializedIndex;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface MetaMaterializedIndexMapper {

    @Delete("delete from meta_materialized_index where materialized_index_id = #{materializedIndexId,jdbcType=BIGINT} and partition_id = #{partitionId,jdbcType=BIGINT}")
    int deleteByPrimaryKey(@Param("materializedIndexId") Long materializedIndexId, @Param("partitionId") Long partitionId);

    @Insert("insert into meta_materialized_index (materialized_index_id, partition_id, materialized_index_state," +
            "row_count, rollup_index_id, rollup_finished_version," +
            "tablet_id_list)" +
            "values (#{materializedIndexId,jdbcType=BIGINT}, #{partitionId,jdbcType=BIGINT}, #{materializedIndexState,jdbcType=VARCHAR}," +
            "#{rowCount,jdbcType=BIGINT}, #{rollupIndexId,jdbcType=BIGINT}, #{rollupFinishedVersion,jdbcType=BIGINT}," +
            "#{tabletIdList,jdbcType=VARCHAR})")
    int insert(MetaMaterializedIndex record);

    @Insert("<script>"  +
            "insert into meta_materialized_index (materialized_index_id, partition_id, materialized_index_state," +
            "row_count, rollup_index_id, rollup_finished_version," +
            "tablet_id_list)" +
            "values" +
            "<foreach collection=\"list\" item=\"item\" index=\"index\" separator=\",\">" +
            "      (#{item.materializedIndexId,jdbcType=BIGINT}, #{item.partitionId,jdbcType=BIGINT}, #{item.materializedIndexState,jdbcType=VARCHAR}," +
            "      #{item.rowCount,jdbcType=BIGINT}, #{item.rollupIndexId,jdbcType=BIGINT}, #{item.rollupFinishedVersion,jdbcType=BIGINT}," +
            "      #{item.tabletIdList,jdbcType=VARCHAR})" +
            "</foreach>" +
            "</script>")
    int batchInsert(@Param("list") List<MetaMaterializedIndex> lst);

    int insertSelective(MetaMaterializedIndex record);

    @Select("select materialized_index_id, partition_id, materialized_index_state, row_count, rollup_index_id, rollup_finished_version, tablet_id_list " +
            "from meta_materialized_index where materialized_index_id = #{materializedIndexId,jdbcType=BIGINT} and partition_id = #{partitionId,jdbcType=BIGINT}")
    @Results(value = {@Result(column="materialized_index_id",property="materializedIndexId"),
            @Result(column="partition_id",property="partitionId"),
            @Result(column="materialized_index_state",property="materializedIndexState"),
            @Result(column="row_count",property="rowCount"),
            @Result(column="rollup_index_id",property="rollupIndexId"),
            @Result(column="rollup_finished_version",property="rollupFinishedVersion"),
            @Result(column="tablet_id_list",property="tabletIdList",javaType = String.class, jdbcType = JdbcType.VARCHAR)})
    MetaMaterializedIndex selectByPrimaryKey(@Param("materializedIndexId") Long materializedIndexId, @Param("partitionId") Long partitionId);

    int updateByPrimaryKeySelective(MetaMaterializedIndex record);

    int updateByPrimaryKey(MetaMaterializedIndex record);
}