package com.baidu.palo.metadata.dao;

import com.baidu.palo.metadata.pojo.MetaMaterializedIndex;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MetaMaterializedIndexMapper {

    @Delete("delete from meta_materialized_index where materialized_index_id = #{materializedIndexId,jdbcType=BIGINT}")
    int deleteByPrimaryKey(Long materializedIndexId);

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

    MetaMaterializedIndex selectByPrimaryKey(Long materializedIndexId);

    int updateByPrimaryKeySelective(MetaMaterializedIndex record);

    int updateByPrimaryKey(MetaMaterializedIndex record);
}