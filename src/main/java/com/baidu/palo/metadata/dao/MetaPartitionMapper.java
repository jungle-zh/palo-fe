package com.baidu.palo.metadata.dao;

import com.baidu.palo.metadata.pojo.MetaPartition;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

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

    @Select("select partition_id, partition_name, partition_state, committed_version, committed_version_hash, distribution_info, materialized_index_id_list " +
            "from meta_partition  where partition_id = #{partitionId,jdbcType=BIGINT}")
    @Results(value = {@Result(column="partition_id",property="partitionId"),
            @Result(column="partition_name",property="partitionName"),
            @Result(column="partition_state",property="partitionState"),
            @Result(column="committed_version",property="committedVersion"),
            @Result(column="committed_version_hash",property="committedVersionHash"),
            @Result(column="distribution_info",property="distributionInfo"),
            @Result(column="materialized_index_id_list",property="materializedIndexIdList",javaType = String.class, jdbcType = JdbcType.VARCHAR)})
    MetaPartition selectByPrimaryKey(Long partitionId);


    @Select("<script>"
                + "select partition_id, partition_name, partition_state, "
                + "committed_version, committed_version_hash, distribution_info, materialized_index_id_list "
                + "from meta_partition where partition_id in "
                + "<foreach item='id' index='index' collection='ids' open='(' separator=',' close=')'>"
                    + "#{id}"
                + "</foreach>"
            + "</script>")
    @Results(value = {@Result(column="partition_id",property="partitionId"),
            @Result(column="partition_name",property="partitionName"),
            @Result(column="partition_state",property="partitionState"),
            @Result(column="committed_version",property="committedVersion"),
            @Result(column="committed_version_hash",property="committedVersionHash"),
            @Result(column="distribution_info",property="distributionInfo"),
            @Result(column="materialized_index_id_list",property="materializedIndexIdList",javaType = String.class, jdbcType = JdbcType.VARCHAR)})
    //     @Results(value = { @Result(property = "userName", column = "user_name", javaType = String.class, jdbcType = JdbcType.VARCHAR) })
    List<MetaPartition> selectByIds(@Param("ids") List<Long> ids);

    int updateByPrimaryKeySelective(MetaPartition record);

    @Update("update meta_partition " +
            "set partition_name = #{partitionName,jdbcType=VARCHAR}," +
            "partition_state = #{partitionState,jdbcType=VARCHAR}," +
            "committed_version = #{committedVersion,jdbcType=BIGINT}," +
            "committed_version_hash = #{committedVersionHash,jdbcType=BIGINT}," +
            "distribution_info = #{distributionInfo,jdbcType=LONGVARCHAR}," +
            "materialized_index_id_list = #{materializedIndexIdList,jdbcType=VARCHAR}" +
            "where partition_id = #{partitionId,jdbcType=BIGINT}")
    int updateByPrimaryKey(MetaPartition record);
}