package com.baidu.palo.metadata.pojo;

public class MetaPartition {
    private Long partitionId;

    private String partitionName;

    private String partitionState;

    private Long committedVersion;

    private Long committedVersionHash;

    private String distributionInfo;

    private String materializedIndexIdList;

    public Long getPartitionId() {
        return partitionId;
    }

    public void setPartitionId(Long partitionId) {
        this.partitionId = partitionId;
    }

    public String getPartitionName() {
        return partitionName;
    }

    public void setPartitionName(String partitionName) {
        this.partitionName = partitionName == null ? null : partitionName.trim();
    }

    public String getPartitionState() {
        return partitionState;
    }

    public void setPartitionState(String partitionState) {
        this.partitionState = partitionState == null ? null : partitionState.trim();
    }

    public Long getCommittedVersion() {
        return committedVersion;
    }

    public void setCommittedVersion(Long committedVersion) {
        this.committedVersion = committedVersion;
    }

    public Long getCommittedVersionHash() {
        return committedVersionHash;
    }

    public void setCommittedVersionHash(Long committedVersionHash) {
        this.committedVersionHash = committedVersionHash;
    }

    public String getDistributionInfo() {
        return distributionInfo;
    }

    public void setDistributionInfo(String distributionInfo) {
        this.distributionInfo = distributionInfo == null ? null : distributionInfo.trim();
    }

    public String getMaterializedIndexIdList() {
        return materializedIndexIdList;
    }

    public void setMaterializedIndexIdList(String materializedIndexIdList) {
        this.materializedIndexIdList = materializedIndexIdList == null ? null : materializedIndexIdList.trim();
    }
}