package com.baidu.palo.metadata.pojo;

/**
 * Created by admin on 18/7/31.
 */
public class MetaDb {

    private Long dbId;

    private String dbName;

    private String clusterName;

    private String dbState;

    private String attachDbName;

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public Long getDbId() {
        return dbId;
    }

    public void setDbId(Long dbId) {
        this.dbId = dbId;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public String getDbState() {
        return dbState;
    }

    public void setDbState(String dbState) {
        this.dbState = dbState;
    }

    public String getAttachDbName() {
        return attachDbName;
    }

    public void setAttachDbName(String attachDbName) {
        this.attachDbName = attachDbName;
    }
}
