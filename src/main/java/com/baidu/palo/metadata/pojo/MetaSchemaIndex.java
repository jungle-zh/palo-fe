package com.baidu.palo.metadata.pojo;

public class MetaSchemaIndex {
    private Long id;

    private Integer schemaVersion;

    private Integer schemaHash;

    private Short shortKeyColumnCount;

    private Integer storageType;

    private String schemaColumn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSchemaVersion() {
        return schemaVersion;
    }

    public void setSchemaVersion(Integer schemaVersion) {
        this.schemaVersion = schemaVersion;
    }

    public Integer getSchemaHash() {
        return schemaHash;
    }

    public void setSchemaHash(Integer schemaHash) {
        this.schemaHash = schemaHash;
    }

    public Short getShortKeyColumnCount() {
        return shortKeyColumnCount;
    }

    public void setShortKeyColumnCount(Short shortKeyColumnCount) {
        this.shortKeyColumnCount = shortKeyColumnCount;
    }

    public Integer getStorageType() {
        return storageType;
    }

    public void setStorageType(Integer storageType) {
        this.storageType = storageType;
    }

    public String getSchemaColumn() {
        return schemaColumn;
    }

    public void setSchemaColumn(String schemaColumn) {
        this.schemaColumn = schemaColumn == null ? null : schemaColumn.trim();
    }
}