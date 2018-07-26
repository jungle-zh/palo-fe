package com.baidu.palo.metadata.pojo;

public class MetaTablet {
    private Long tabletId;

    private Long checkedVersion;

    private Long checkedVersionHash;

    private Integer isConsistent;

    private String replicaIdList;

    public Long getTabletId() {
        return tabletId;
    }

    public void setTabletId(Long tabletId) {
        this.tabletId = tabletId;
    }

    public Long getCheckedVersion() {
        return checkedVersion;
    }

    public void setCheckedVersion(Long checkedVersion) {
        this.checkedVersion = checkedVersion;
    }

    public Long getCheckedVersionHash() {
        return checkedVersionHash;
    }

    public void setCheckedVersionHash(Long checkedVersionHash) {
        this.checkedVersionHash = checkedVersionHash;
    }

    public Integer getIsConsistent() {
        return isConsistent;
    }

    public void setIsConsistent(Integer isConsistent) {
        this.isConsistent = isConsistent;
    }

    public String getReplicaIdList() {
        return replicaIdList;
    }

    public void setReplicaIdList(String replicaIdList) {
        this.replicaIdList = replicaIdList == null ? null : replicaIdList.trim();
    }
}