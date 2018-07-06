package com.baidu.palo.metadata.dao;

import com.baidu.palo.metadata.pojo.MetaPartition;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MetaPartitionMapper {

    @Delete("delete from meta_partition where partition_id = #{partitionId,jdbcType=BIGINT}")
    int deleteByPrimaryKey(Long partitionId);

    @Insert("insert into meta_partition (partition_id, partition_name, partition_state," +
            "committed_version, committed_version_hash, distribution_info,materialized_index_id_list)" +
            "values (#{partitionId,jdbcType=BIGINT}, #{partitionName,jdbcType=VARCHAR}, #{partitionState,jdbcType=VARCHAR}," +
            "#{committedVersion,jdbcType=BIGINT}, #{committedVersionHash,jdbcType=BIGINT}, #{distributionInfo,jdbcType=LONGVARCHAR}," +
            "#{materializedIndexIdList,jdbcType=VARCHAR})")
    int insert(MetaPartition record);

    @Insert("<script>"  +
            "insert into meta_partition (partition_id, partition_name, partition_state," +
            "committed_version, committed_version_hash, distribution_info," +
            "materialized_index_id_list)" +
            "values" +
            "<foreach collection=\"list\" item=\"item\" index=\"index\" separator=\",\">" +
                "(#{item.partitionId,jdbcType=BIGINT}, #{item.partitionName,jdbcType=VARCHAR}, #{item.partitionState,jdbcType=VARCHAR}," +
                "#{item.committedVersion,jdbcType=BIGINT}, #{item.committedVersionHash,jdbcType=BIGINT}, #{item.distributionInfo,jdbcType=LONGVARCHAR}," +
                "#{item.materializedIndexIdList,jdbcType=VARCHAR})" +
            "</foreach>" +
            "</script>")
    int batchInsert(@Param("list") List<MetaPartition> lst);

    int insertSelective(MetaPartition record);

    MetaPartition selectByPrimaryKey(Long partitionId);

    int updateByPrimaryKeySelective(MetaPartition record);

    int updateByPrimaryKey(MetaPartition record);
}