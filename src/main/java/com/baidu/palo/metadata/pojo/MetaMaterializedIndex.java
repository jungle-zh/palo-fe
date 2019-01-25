package com.baidu.palo.metadata.pojo;

public class MetaMaterializedIndex {
    private Long materializedIndexId;

    private Long partitionId;

    private String materializedIndexState;

    private long rowCount;

    private long rollupIndexId;

    private long rollupFinishedVersion;

    private String tabletIdList;

    public Long getMaterializedIndexId() {
        return materializedIndexId;
    }

    public void setMaterializedIndexId(Long materializedIndexId) {
        this.materializedIndexId = materializedIndexId;
    }

    public Long getPartitionId() {
        return partitionId;
    }

    public void setPartitionId(Long partitionId) {
        this.partitionId = partitionId;
    }

    public String getMaterializedIndexState() {
        return materializedIndexState;
    }

    public void setMaterializedIndexState(String materializedIndexState) {
        this.materializedIndexState = materializedIndexState == null ? null : materializedIndexState.trim();
    }

    public long getRowCount() {
        return rowCount;
    }

    public void setRowCount(Long rowCount) {
        this.rowCount = rowCount;
    }

    public long getRollupIndexId() {
        return rollupIndexId;
    }

    public void setRollupIndexId(Long rollupIndexId) {
        this.rollupIndexId = rollupIndexId;
    }

    public long getRollupFinishedVersion() {
        return rollupFinishedVersion;
    }

    public void setRollupFinishedVersion(Long rollupFinishedVersion) {
        this.rollupFinishedVersion = rollupFinishedVersion;
    }

    public String getTabletIdList() {
        return tabletIdList;
    }

    public void setTabletIdList(String tabletIdList) {
        this.tabletIdList = tabletIdList == null ? null : tabletIdList.trim();
    }
}