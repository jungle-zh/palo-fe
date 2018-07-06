package com.baidu.palo.metadata.dao;

import com.baidu.palo.metadata.pojo.MetaReplica;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

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

    MetaReplica selectByPrimaryKey(Long replicaId);

    int updateByPrimaryKeySelective(MetaReplica record);

    int updateByPrimaryKey(MetaReplica record);
}