package com.baidu.palo.metadata.pojo;

public class MetaReplica {
    private Long replicaId;

    private Long backendId;

    private Long version;

    private Long versionHash;

    private Long dataSize;

    private Long rowCount;

    private String replicaState;

    public Long getReplicaId() {
        return replicaId;
    }

    public void setReplicaId(Long replicaId) {
        this.replicaId = replicaId;
    }

    public Long getBackendId() {
        return backendId;
    }

    public void setBackendId(Long backendId) {
        this.backendId = backendId;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getVersionHash() {
        return versionHash;
    }

    public void setVersionHash(Long versionHash) {
        this.versionHash = versionHash;
    }

    public Long getDataSize() {
        return dataSize;
    }

    public void setDataSize(Long dataSize) {
        this.dataSize = dataSize;
    }

    public Long getRowCount() {
        return rowCount;
    }

    public void setRowCount(Long rowCount) {
        this.rowCount = rowCount;
    }

    public String getReplicaState() {
        return replicaState;
    }

    public void setReplicaState(String replicaState) {
        this.replicaState = replicaState == null ? null : replicaState.trim();
    }
}