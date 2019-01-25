package com.baidu.palo.metadata.pojo;

import com.baidu.palo.alter.DecommissionBackendJob;
import com.baidu.palo.metadata.JsonHelper;
import com.baidu.palo.system.Backend;

/**
 * Created by admin on 18/8/1.
 */
public class MetaBackend {



    private long backendId;
    private String host;

    private int heartbeatPort; // heartbeat
    private int bePort; // be

    public long getBackendId() {
        return backendId;
    }

    public void setBackendId(long backendId) {
        this.backendId = backendId;
    }

    private int httpPort; // web service
    private int beRpcPort; // be rpc port

    private long lastUpdateMs;
    private long lastStartTime;

    private String   decommissionType;
    private String  ownerClusterName;
    // to index the state in some cluster




    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getHeartbeatPort() {
        return heartbeatPort;
    }

    public void setHeartbeatPort(int heartbeatPort) {
        this.heartbeatPort = heartbeatPort;
    }

    public int getBePort() {
        return bePort;
    }

    public void setBePort(int bePort) {
        this.bePort = bePort;
    }

    public int getHttpPort() {
        return httpPort;
    }

    public void setHttpPort(int httpPort) {
        this.httpPort = httpPort;
    }

    public int getBeRpcPort() {
        return beRpcPort;
    }

    public void setBeRpcPort(int beRpcPort) {
        this.beRpcPort = beRpcPort;
    }

    public long getLastUpdateMs() {
        return lastUpdateMs;
    }

    public void setLastUpdateMs(long lastUpdateMs) {
        this.lastUpdateMs = lastUpdateMs;
    }

    public long getLastStartTime() {
        return lastStartTime;
    }

    public void setLastStartTime(long lastStartTime) {
        this.lastStartTime = lastStartTime;
    }



    public String getOwnerClusterName() {
        return ownerClusterName;
    }

    public void setOwnerClusterName(String ownerClusterName) {
        this.ownerClusterName = ownerClusterName;
    }



    public Backend toBackend(){
        Backend backend = new Backend(backendId,host,heartbeatPort);
        backend.setBePort(bePort);
        backend.setHttpPort(httpPort);
        backend.setBeRpcPort(beRpcPort);
        backend.setLastUpdateMs(lastUpdateMs);
        backend.setLastStartTime(lastStartTime);
        backend.setOwnerClusterName(ownerClusterName);
        backend.setDecommissionType(JsonHelper.fromJson(decommissionType, DecommissionBackendJob.DecommissionType.class));

        return backend;


    }


    public String getDecommissionType() {
        return decommissionType;
    }

    public void setDecommissionType(String decommissionType) {
        this.decommissionType = decommissionType;
    }
}
