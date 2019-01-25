package com.baidu.palo.metadata.dao;

import com.baidu.palo.metadata.pojo.MetaBackend;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by admin on 18/7/31.
 */
public interface MetaBackendMapper {

    @Delete("delete from meta_backend where id = #{backendId,jdbcType=BIGINT}")
    int deleteByPrimaryKey(Long backendId);


    @Insert("insert into meta_backend (backend_id,host,heartbeat_port,be_port,http_port,be_rpc_port,last_update_ms,last_start_time,decommission_type,owner_cluster_name)" +
            " values (#{backendId,jdbcType=BIGINT},#{host,jdbcType=VARCHAR}," +
            "#{heartbeatPort,jdbcType=INTEGER},#{bePort,jdbcType=INTEGER}," +
            "#{httpPort,jdbcType=INTEGER},#{beRpcPort,jdbcType=INTEGER}," +
            "#{lastUpdateMs,jdbcType=BIGINT},#{lastStartTime,jdbcType=BIGINT}," +
            "#{decommissionType,jdbcType=VARCHAR}," +
            "#{ownerClusterName,jdbcType=VARCHAR})")
    int insert(MetaBackend backend);



    @Select("select backend_id as backendId, host as host ,heartbeat_port as heartbeatPort, http_port as httpPort ,be_rpc_port as beRpcPort ,last_update_ms as lastUpdateMs, last_start_time as lastStartTime," +
            "decommission_type as decommissionType,owner_cluster_name as ownerClusterName  " +
            "from meta_backend where backend_id = #{backendId,jdbcType=BIGINT} ")
    MetaBackend selectByPrimaryKey(@Param("backendId") Long backendId);


    @Select("select backend_id as backendId, host as host ,heartbeat_port as heartbeatPort, http_port as httpPort ,be_rpc_port as beRpcPort ,last_update_ms as lastUpdateMs, last_start_time as lastStartTime," +
            "decommission_type as decommissionType,owner_cluster_name as ownerClusterName " +
            "from meta_backend where owner_cluster_name = #{clusterName,jdbcType=VARCHAR} ")
    List<MetaBackend> selectByClusterName(@Param("clusterName") String clusterName);

    @Update("update meta_backend  set " +
            "host = #{host,jdbcType=VARCHAR}," +
            "heartbeat_port = #{heartbeatPort,jdbcType=INTEGER}, " +
            "http_port = #{httpPort ,jdbcType=INTEGER}, " +
            "be_rpc_port = #{beRpcPort ,jdbcType=INTEGER}," +
            "last_update_ms = #{lastUpdateMs,jdbcType=BIGINT}," +
            "last_start_time = #{lastStartTime,jdbcType=BIGINT}," +
            "decommission_type = #{decommissionType,jdbcType=VARCHAR}," +
            "owner_cluster_name = #{ownerClusterName,jdbcType=VARCHAR} " +
            "where backend_id = #{backendId,jdbcType=BIGINT}" )
    int updateByPrimaryKey(MetaBackend metaBackend);






}
