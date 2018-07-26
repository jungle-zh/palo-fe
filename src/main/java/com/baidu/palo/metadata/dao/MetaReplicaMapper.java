package com.baidu.palo.metadata.dao;

import com.baidu.palo.metadata.pojo.MetaReplica;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface MetaReplicaMapper {
    @Delete("delete from meta_replica where replica_id = #{replicaId,jdbcType=BIGINT}")
    int deleteByPrimaryKey(Long replicaId);

    @Insert("insert into meta_replica (replica_id, backend_id, version, " +
            "version_hash, data_size, row_count, " +
            "replica_state)" +
            "values (#{replicaId,jdbcType=BIGINT}, #{backendId,jdbcType=BIGINT}, #{version,jdbcType=BIGINT}, " +
            "#{versionHash,jdbcType=BIGINT}, #{dataSize,jdbcType=BIGINT}, #{rowCount,jdbcType=BIGINT}, " +
            "#{replicaState,jdbcType=VARCHAR})")
    int insert(MetaReplica record);

    @Insert("<script>"  +"    insert into meta_replica (replica_id, backend_id, version," +
            "    version_hash, data_size, row_count," +
            "    replica_state)" +
            "    values" +
            "    <foreach collection=\"list\" item=\"item\" index=\"index\" separator=\",\">" +
            "      (#{item.replicaId,jdbcType=BIGINT}, #{item.backendId,jdbcType=BIGINT}, #{item.version,jdbcType=BIGINT}," +
            "      #{item.versionHash,jdbcType=BIGINT}, #{item.dataSize,jdbcType=BIGINT}, #{item.rowCount,jdbcType=BIGINT}," +
            "      #{item.replicaState,jdbcType=VARCHAR})" +
            "    </foreach>" +
            "</script>")
    int batchInsert(@Param("list") List<MetaReplica> lst);

    int insertSelective(MetaReplica record);


//    @Select("select replica_id, backend_id, version, version_hash, data_size, row_count, replica_state " +
//            "from meta_replica where replica_id = #{replicaId,jdbcType=BIGINT}")
//    @Results(value = { @Result(property = "replica_id", column = "replicaId"),
//            @Result(property = "backend_id", column = "backendId", javaType = String.class, jdbcType = JdbcType.BIGINT),
//            @Result(property = "version", column = "version"),
//            @Result(property = "version_hash", column = "versionHash"),
//            @Result(property = "data_size", column = "dataSize"),
//            @Result(property = "row_count", column = "rowCount"),
//            @Result(property = "replica_state", column = "replicaState", javaType = String.class, jdbcType = JdbcType.VARCHAR)})
    @Select("select replica_id as replicaId, backend_id as backendId, version as version, version_hash as versionHash, data_size as dataSize, row_count as rowCount, replica_state as replicaState " +
            "from meta_replica where replica_id = #{replicaId,jdbcType=BIGINT}")
    MetaReplica selectByPrimaryKey(@Param("replicaId") Long replicaId);

    int updateByPrimaryKeySelective(MetaReplica record);

    @Update("update meta_replica " +
            "set version = #{version,jdbcType=BIGINT}," +
            "version_hash = #{versionHash,jdbcType=BIGINT}," +
            "data_size = #{dataSize,jdbcType=BIGINT}," +
            "row_count = #{rowCount,jdbcType=BIGINT} " +
            "where replica_id = #{replicaId,jdbcType=BIGINT} and backend_id = #{backendId,jdbcType=BIGINT}")
    int updateByPrimaryKey(MetaReplica record);
}