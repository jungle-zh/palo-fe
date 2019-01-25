package com.baidu.palo.metadata.pojo;

public class MetaOlapTable {
    private Long tableId;

    private String tableName;

    private String tableType;

    private String olapTableState;

    private String keysType;

    private String partitionInfo;

    private String distributionInfo;

    private String partitionIdList;

    private String schemaIndexIdList;

    private Double bfFpp;

    private String bfColumns;

    private String baseSchema;

    private String dbName;

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType == null ? null : tableType.trim();
    }

    public String getOlapTableState() {
        return olapTableState;
    }

    public void setOlapTableState(String olapTableState) {
        this.olapTableState = olapTableState == null ? null : olapTableState.trim();
    }

    public String getKeysType() {
        return keysType;
    }

    public void setKeysType(String keysType) {
        this.keysType = keysType == null ? null : keysType.trim();
    }

    public String getPartitionInfo() {
        return partitionInfo;
    }

    public void setPartitionInfo(String partitionInfo) {
        this.partitionInfo = partitionInfo == null ? null : partitionInfo.trim();
    }

    public String getDistributionInfo() {
        return distributionInfo;
    }

    public void setDistributionInfo(String distributionInfo) {
        this.distributionInfo = distributionInfo == null ? null : distributionInfo.trim();
    }

    public String getPartitionIdList() {
        return partitionIdList;
    }

    public void setPartitionIdList(String partitionIdList) {
        this.partitionIdList = partitionIdList == null ? null : partitionIdList.trim();
    }

    public String getSchemaIndexIdList() {
        return schemaIndexIdList;
    }

    public void setSchemaIndexIdList(String schemaIndexIdList) {
        this.schemaIndexIdList = schemaIndexIdList == null ? null : schemaIndexIdList.trim();
    }

    public Double getBfFpp() {
        return bfFpp;
    }

    public void setBfFpp(Double bfFpp) {
        this.bfFpp = bfFpp;
    }

    public String getBfColumns() {
        return bfColumns;
    }

    public void setBfColumns(String bfColumns) {
        this.bfColumns = bfColumns == null ? null : bfColumns.trim();
    }


    public String getBaseSchema() {
        return baseSchema;
    }

    public void setBaseSchema(String baseSchema) {
        this.baseSchema = baseSchema;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
}