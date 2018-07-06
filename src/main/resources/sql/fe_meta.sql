/*
Navicat MySQL Data Transfer

Source Server         : root_local
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : fe_meta

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-07-06 16:04:55
*/

SET FOREIGN_KEY_CHECKS=0;

CREATE DATABASE IF NOT EXISTS fe_meta;
use fe_meta;

-- ----------------------------
-- Table structure for meta_materialized_index
-- ----------------------------
DROP TABLE IF EXISTS `meta_materialized_index`;
CREATE TABLE `meta_materialized_index` (
  `materialized_index_id` bigint(20) NOT NULL,
  `partition_id` bigint(20) NOT NULL,
  `materialized_index_state` varchar(255) DEFAULT NULL,
  `row_count` bigint(20) DEFAULT NULL,
  `rollup_index_id` bigint(20) DEFAULT NULL,
  `rollup_finished_version` bigint(20) DEFAULT NULL,
  `tablet_id_list` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`materialized_index_id`,`partition_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of meta_materialized_index
-- ----------------------------
INSERT INTO `meta_materialized_index` VALUES ('10136', '10135', 'NORMAL', '0', '-1', null, '[10137,10139,10141,10143,10145,10147,10149,10151,10153,10155]');
INSERT INTO `meta_materialized_index` VALUES ('10224', '10221', 'NORMAL', '0', '-1', null, '[10225,10227,10229,10231,10233,10235,10237,10239,10241,10243]');
INSERT INTO `meta_materialized_index` VALUES ('10224', '10222', 'NORMAL', '0', '-1', null, '[10245,10247,10249,10251,10253,10255,10257,10259,10261,10263]');
INSERT INTO `meta_materialized_index` VALUES ('10224', '10223', 'NORMAL', '0', '-1', null, '[10265,10267,10269,10271,10273,10275,10277,10279,10281,10283]');

-- ----------------------------
-- Table structure for meta_olap_table
-- ----------------------------
DROP TABLE IF EXISTS `meta_olap_table`;
CREATE TABLE `meta_olap_table` (
  `table_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `table_name` varchar(255) DEFAULT NULL,
  `table_type` varchar(32) DEFAULT NULL,
  `olap_table_state` varchar(32) DEFAULT NULL,
  `keys_type` varchar(32) DEFAULT NULL,
  `partition_info` text,
  `distribution_info` text,
  `partition_id_list` varchar(255) DEFAULT NULL,
  `schema_index_id_list` varchar(255) DEFAULT NULL,
  `bf_columns` text,
  `bf_fpp` double DEFAULT NULL,
  PRIMARY KEY (`table_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10225 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of meta_olap_table
-- ----------------------------
INSERT INTO `meta_olap_table` VALUES ('10136', 'table1', 'OLAP', 'NORMAL', 'AGG_KEYS', '{\"type\":\"UNPARTITIONED\",\"idToDataProperty\":{\"10135\":{\"storageMedium\":\"HDD\",\"cooldownTimeMs\":253402271999000}},\"idToReplicationNum\":{\"10135\":1}}', '{\"distributionColumns\":[{\"name\":\"siteid\",\"columnType\":{\"type\":\"INT\",\"len\":-1,\"precision\":-1,\"scale\":-1,\"varcharLimit\":true},\"isAggregationTypeImplicit\":false,\"isKey\":true,\"isAllowNull\":true,\"defaultValue\":\"10\",\"comment\":\"\",\"stats\":{\"avgSerializedSize\":-1.0,\"maxSize\":-1,\"numDistinctValues\":-1,\"numNulls\":-1}}],\"bucketNum\":10,\"type\":\"HASH\"}', '[10135]', '[10136]', 'null', '0');
INSERT INTO `meta_olap_table` VALUES ('10224', 'table2', 'OLAP', 'NORMAL', 'AGG_KEYS', '{\"partitionColumns\":[{\"name\":\"event_day\",\"columnType\":{\"type\":\"DATE\",\"len\":-1,\"precision\":-1,\"scale\":-1,\"varcharLimit\":true},\"isAggregationTypeImplicit\":false,\"isKey\":true,\"isAllowNull\":true,\"comment\":\"\",\"stats\":{\"avgSerializedSize\":-1.0,\"maxSize\":-1,\"numDistinctValues\":-1,\"numNulls\":-1}}],\"idToRange\":{\"10221\":{\"lowerBound\":{\"endpoint\":{\"keys\":[{\"date\":\"Dec 31, 1899 4:00:00 PM\",\"isAuxExpr\":false,\"type\":{\"type\":\"DATE\",\"len\":0,\"precision\":0,\"scale\":0},\"isOnClauseConjunct_\":false,\"isAnalyzed\":false,\"opcode\":\"INVALID_OPCODE\",\"vectorOpcode\":\"INVALID_OPCODE\",\"selectivity\":-1.0,\"numDistinctValues\":1,\"outputScale\":-1,\"outputColumn\":-1,\"isFilter\":false,\"isConstant_\":false,\"children\":[]}],\"types\":[\"DATE\"]}},\"upperBound\":{\"endpoint\":{\"keys\":[{\"date\":\"Jun 29, 2017 4:00:00 PM\",\"isAuxExpr\":false,\"type\":{\"type\":\"DATE\",\"len\":0,\"precision\":0,\"scale\":0},\"isOnClauseConjunct_\":false,\"isAnalyzed\":true,\"opcode\":\"INVALID_OPCODE\",\"vectorOpcode\":\"INVALID_OPCODE\",\"selectivity\":-1.0,\"numDistinctValues\":1,\"outputScale\":-1,\"outputColumn\":-1,\"isFilter\":false,\"isConstant_\":true,\"children\":[]}],\"types\":[\"DATE\"]}}},\"10222\":{\"lowerBound\":{\"endpoint\":{\"keys\":[{\"date\":\"Jun 29, 2017 4:00:00 PM\",\"isAuxExpr\":false,\"type\":{\"type\":\"DATE\",\"len\":0,\"precision\":0,\"scale\":0},\"isOnClauseConjunct_\":false,\"isAnalyzed\":true,\"opcode\":\"INVALID_OPCODE\",\"vectorOpcode\":\"INVALID_OPCODE\",\"selectivity\":-1.0,\"numDistinctValues\":1,\"outputScale\":-1,\"outputColumn\":-1,\"isFilter\":false,\"isConstant_\":true,\"children\":[]}],\"types\":[\"DATE\"]}},\"upperBound\":{\"endpoint\":{\"keys\":[{\"date\":\"Jul 30, 2017 4:00:00 PM\",\"isAuxExpr\":false,\"type\":{\"type\":\"DATE\",\"len\":0,\"precision\":0,\"scale\":0},\"isOnClauseConjunct_\":false,\"isAnalyzed\":true,\"opcode\":\"INVALID_OPCODE\",\"vectorOpcode\":\"INVALID_OPCODE\",\"selectivity\":-1.0,\"numDistinctValues\":1,\"outputScale\":-1,\"outputColumn\":-1,\"isFilter\":false,\"isConstant_\":true,\"children\":[]}],\"types\":[\"DATE\"]}}},\"10223\":{\"lowerBound\":{\"endpoint\":{\"keys\":[{\"date\":\"Jul 30, 2017 4:00:00 PM\",\"isAuxExpr\":false,\"type\":{\"type\":\"DATE\",\"len\":0,\"precision\":0,\"scale\":0},\"isOnClauseConjunct_\":false,\"isAnalyzed\":true,\"opcode\":\"INVALID_OPCODE\",\"vectorOpcode\":\"INVALID_OPCODE\",\"selectivity\":-1.0,\"numDistinctValues\":1,\"outputScale\":-1,\"outputColumn\":-1,\"isFilter\":false,\"isConstant_\":true,\"children\":[]}],\"types\":[\"DATE\"]}},\"upperBound\":{\"endpoint\":{\"keys\":[{\"date\":\"Aug 30, 2017 4:00:00 PM\",\"isAuxExpr\":false,\"type\":{\"type\":\"DATE\",\"len\":0,\"precision\":0,\"scale\":0},\"isOnClauseConjunct_\":false,\"isAnalyzed\":true,\"opcode\":\"INVALID_OPCODE\",\"vectorOpcode\":\"INVALID_OPCODE\",\"selectivity\":-1.0,\"numDistinctValues\":1,\"outputScale\":-1,\"outputColumn\":-1,\"isFilter\":false,\"isConstant_\":true,\"children\":[]}],\"types\":[\"DATE\"]}}}},\"type\":\"RANGE\",\"idToDataProperty\":{\"10221\":{\"storageMedium\":\"HDD\",\"cooldownTimeMs\":253402271999000},\"10222\":{\"storageMedium\":\"HDD\",\"cooldownTimeMs\":253402271999000},\"10223\":{\"storageMedium\":\"HDD\",\"cooldownTimeMs\":253402271999000}},\"idToReplicationNum\":{\"10221\":1,\"10222\":1,\"10223\":1}}', '{\"distributionColumns\":[{\"name\":\"siteid\",\"columnType\":{\"type\":\"INT\",\"len\":-1,\"precision\":-1,\"scale\":-1,\"varcharLimit\":true},\"isAggregationTypeImplicit\":false,\"isKey\":true,\"isAllowNull\":true,\"defaultValue\":\"10\",\"comment\":\"\",\"stats\":{\"avgSerializedSize\":-1.0,\"maxSize\":-1,\"numDistinctValues\":-1,\"numNulls\":-1}}],\"bucketNum\":10,\"type\":\"HASH\"}', '[10221,10222,10223]', '[10224]', 'null', '0');

-- ----------------------------
-- Table structure for meta_partition
-- ----------------------------
DROP TABLE IF EXISTS `meta_partition`;
CREATE TABLE `meta_partition` (
  `partition_id` bigint(20) NOT NULL,
  `partition_name` varchar(255) DEFAULT NULL,
  `partition_state` varchar(255) DEFAULT NULL,
  `committed_version` bigint(20) DEFAULT NULL,
  `committed_version_hash` bigint(20) DEFAULT NULL,
  `distribution_info` text,
  `materialized_index_id_list` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`partition_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of meta_partition
-- ----------------------------
INSERT INTO `meta_partition` VALUES ('10135', 'table1', 'NORMAL', '1', null, '{\"distributionColumns\":[{\"name\":\"siteid\",\"columnType\":{\"type\":\"INT\",\"len\":-1,\"precision\":-1,\"scale\":-1,\"varcharLimit\":true},\"isAggregationTypeImplicit\":false,\"isKey\":true,\"isAllowNull\":true,\"defaultValue\":\"10\",\"comment\":\"\",\"stats\":{\"avgSerializedSize\":-1.0,\"maxSize\":-1,\"numDistinctValues\":-1,\"numNulls\":-1}}],\"bucketNum\":10,\"type\":\"HASH\"}', '[10136]');
INSERT INTO `meta_partition` VALUES ('10221', 'p1', 'NORMAL', '1', null, '{\"distributionColumns\":[{\"name\":\"siteid\",\"columnType\":{\"type\":\"INT\",\"len\":-1,\"precision\":-1,\"scale\":-1,\"varcharLimit\":true},\"isAggregationTypeImplicit\":false,\"isKey\":true,\"isAllowNull\":true,\"defaultValue\":\"10\",\"comment\":\"\",\"stats\":{\"avgSerializedSize\":-1.0,\"maxSize\":-1,\"numDistinctValues\":-1,\"numNulls\":-1}}],\"bucketNum\":10,\"type\":\"HASH\"}', '[10224]');
INSERT INTO `meta_partition` VALUES ('10222', 'p2', 'NORMAL', '1', null, '{\"distributionColumns\":[{\"name\":\"siteid\",\"columnType\":{\"type\":\"INT\",\"len\":-1,\"precision\":-1,\"scale\":-1,\"varcharLimit\":true},\"isAggregationTypeImplicit\":false,\"isKey\":true,\"isAllowNull\":true,\"defaultValue\":\"10\",\"comment\":\"\",\"stats\":{\"avgSerializedSize\":-1.0,\"maxSize\":-1,\"numDistinctValues\":-1,\"numNulls\":-1}}],\"bucketNum\":10,\"type\":\"HASH\"}', '[10224]');
INSERT INTO `meta_partition` VALUES ('10223', 'p3', 'NORMAL', '1', null, '{\"distributionColumns\":[{\"name\":\"siteid\",\"columnType\":{\"type\":\"INT\",\"len\":-1,\"precision\":-1,\"scale\":-1,\"varcharLimit\":true},\"isAggregationTypeImplicit\":false,\"isKey\":true,\"isAllowNull\":true,\"defaultValue\":\"10\",\"comment\":\"\",\"stats\":{\"avgSerializedSize\":-1.0,\"maxSize\":-1,\"numDistinctValues\":-1,\"numNulls\":-1}}],\"bucketNum\":10,\"type\":\"HASH\"}', '[10224]');

-- ----------------------------
-- Table structure for meta_replica
-- ----------------------------
DROP TABLE IF EXISTS `meta_replica`;
CREATE TABLE `meta_replica` (
  `replica_id` bigint(20) NOT NULL,
  `backend_id` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `version_hash` bigint(20) DEFAULT NULL,
  `data_size` bigint(20) DEFAULT NULL,
  `row_count` bigint(20) DEFAULT NULL,
  `replica_state` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`replica_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of meta_replica
-- ----------------------------
INSERT INTO `meta_replica` VALUES ('10138', '10001', '1', '0', '-1', null, 'NORMAL');
INSERT INTO `meta_replica` VALUES ('10140', '10001', '1', '0', '-1', null, 'NORMAL');
INSERT INTO `meta_replica` VALUES ('10142', '10001', '1', '0', '-1', null, 'NORMAL');
INSERT INTO `meta_replica` VALUES ('10144', '10001', '1', '0', '-1', null, 'NORMAL');
INSERT INTO `meta_replica` VALUES ('10146', '10001', '1', '0', '-1', null, 'NORMAL');
INSERT INTO `meta_replica` VALUES ('10148', '10001', '1', '0', '-1', null, 'NORMAL');
INSERT INTO `meta_replica` VALUES ('10150', '10001', '1', '0', '-1', null, 'NORMAL');
INSERT INTO `meta_replica` VALUES ('10152', '10001', '1', '0', '-1', null, 'NORMAL');
INSERT INTO `meta_replica` VALUES ('10154', '10001', '1', '0', '-1', null, 'NORMAL');
INSERT INTO `meta_replica` VALUES ('10156', '10001', '1', '0', '-1', null, 'NORMAL');
INSERT INTO `meta_replica` VALUES ('10226', '10001', '1', '0', '-1', null, 'NORMAL');
INSERT INTO `meta_replica` VALUES ('10228', '10001', '1', '0', '-1', null, 'NORMAL');
INSERT INTO `meta_replica` VALUES ('10230', '10001', '1', '0', '-1', null, 'NORMAL');
INSERT INTO `meta_replica` VALUES ('10232', '10001', '1', '0', '-1', null, 'NORMAL');
INSERT INTO `meta_replica` VALUES ('10234', '10001', '1', '0', '-1', null, 'NORMAL');
INSERT INTO `meta_replica` VALUES ('10236', '10001', '1', '0', '-1', null, 'NORMAL');
INSERT INTO `meta_replica` VALUES ('10238', '10001', '1', '0', '-1', null, 'NORMAL');
INSERT INTO `meta_replica` VALUES ('10240', '10001', '1', '0', '-1', null, 'NORMAL');
INSERT INTO `meta_replica` VALUES ('10242', '10001', '1', '0', '-1', null, 'NORMAL');
INSERT INTO `meta_replica` VALUES ('10244', '10001', '1', '0', '-1', null, 'NORMAL');
INSERT INTO `meta_replica` VALUES ('10246', '10001', '1', '0', '-1', null, 'NORMAL');
INSERT INTO `meta_replica` VALUES ('10248', '10001', '1', '0', '-1', null, 'NORMAL');
INSERT INTO `meta_replica` VALUES ('10250', '10001', '1', '0', '-1', null, 'NORMAL');
INSERT INTO `meta_replica` VALUES ('10252', '10001', '1', '0', '-1', null, 'NORMAL');
INSERT INTO `meta_replica` VALUES ('10254', '10001', '1', '0', '-1', null, 'NORMAL');
INSERT INTO `meta_replica` VALUES ('10256', '10001', '1', '0', '-1', null, 'NORMAL');
INSERT INTO `meta_replica` VALUES ('10258', '10001', '1', '0', '-1', null, 'NORMAL');
INSERT INTO `meta_replica` VALUES ('10260', '10001', '1', '0', '-1', null, 'NORMAL');
INSERT INTO `meta_replica` VALUES ('10262', '10001', '1', '0', '-1', null, 'NORMAL');
INSERT INTO `meta_replica` VALUES ('10264', '10001', '1', '0', '-1', null, 'NORMAL');
INSERT INTO `meta_replica` VALUES ('10266', '10001', '1', '0', '-1', null, 'NORMAL');
INSERT INTO `meta_replica` VALUES ('10268', '10001', '1', '0', '-1', null, 'NORMAL');
INSERT INTO `meta_replica` VALUES ('10270', '10001', '1', '0', '-1', null, 'NORMAL');
INSERT INTO `meta_replica` VALUES ('10272', '10001', '1', '0', '-1', null, 'NORMAL');
INSERT INTO `meta_replica` VALUES ('10274', '10001', '1', '0', '-1', null, 'NORMAL');
INSERT INTO `meta_replica` VALUES ('10276', '10001', '1', '0', '-1', null, 'NORMAL');
INSERT INTO `meta_replica` VALUES ('10278', '10001', '1', '0', '-1', null, 'NORMAL');
INSERT INTO `meta_replica` VALUES ('10280', '10001', '1', '0', '-1', null, 'NORMAL');
INSERT INTO `meta_replica` VALUES ('10282', '10001', '1', '0', '-1', null, 'NORMAL');
INSERT INTO `meta_replica` VALUES ('10284', '10001', '1', '0', '-1', null, 'NORMAL');

-- ----------------------------
-- Table structure for meta_schema_index
-- ----------------------------
DROP TABLE IF EXISTS `meta_schema_index`;
CREATE TABLE `meta_schema_index` (
  `id` bigint(20) NOT NULL,
  `schema_column` text,
  `schema_version` int(11) DEFAULT NULL,
  `schema_hash` int(11) DEFAULT NULL,
  `short_key_column_count` smallint(5) DEFAULT NULL,
  `storage_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of meta_schema_index
-- ----------------------------
INSERT INTO `meta_schema_index` VALUES ('10136', '[{\"name\":\"siteid\",\"columnType\":{\"type\":\"INT\",\"len\":-1,\"precision\":-1,\"scale\":-1,\"varcharLimit\":true},\"isAggregationTypeImplicit\":false,\"isKey\":true,\"isAllowNull\":true,\"defaultValue\":\"10\",\"comment\":\"\",\"stats\":{\"avgSerializedSize\":-1.0,\"maxSize\":-1,\"numDistinctValues\":-1,\"numNulls\":-1}},{\"name\":\"citycode\",\"columnType\":{\"type\":\"SMALLINT\",\"len\":-1,\"precision\":-1,\"scale\":-1,\"varcharLimit\":true},\"isAggregationTypeImplicit\":false,\"isKey\":true,\"isAllowNull\":true,\"comment\":\"\",\"stats\":{\"avgSerializedSize\":-1.0,\"maxSize\":-1,\"numDistinctValues\":-1,\"numNulls\":-1}},{\"name\":\"username\",\"columnType\":{\"type\":\"VARCHAR\",\"len\":32,\"precision\":-1,\"scale\":-1,\"varcharLimit\":true},\"isAggregationTypeImplicit\":false,\"isKey\":true,\"isAllowNull\":true,\"defaultValue\":\"\",\"comment\":\"\",\"stats\":{\"avgSerializedSize\":-1.0,\"maxSize\":-1,\"numDistinctValues\":-1,\"numNulls\":-1}},{\"name\":\"pv\",\"columnType\":{\"type\":\"BIGINT\",\"len\":-1,\"precision\":-1,\"scale\":-1,\"varcharLimit\":true},\"aggregationType\":\"SUM\",\"isAggregationTypeImplicit\":false,\"isKey\":false,\"isAllowNull\":true,\"defaultValue\":\"0\",\"comment\":\"\",\"stats\":{\"avgSerializedSize\":-1.0,\"maxSize\":-1,\"numDistinctValues\":-1,\"numNulls\":-1}}]', '0', '1421156361', '3', '1');
INSERT INTO `meta_schema_index` VALUES ('10224', '[{\"name\":\"event_day\",\"columnType\":{\"type\":\"DATE\",\"len\":-1,\"precision\":-1,\"scale\":-1,\"varcharLimit\":true},\"isAggregationTypeImplicit\":false,\"isKey\":true,\"isAllowNull\":true,\"comment\":\"\",\"stats\":{\"avgSerializedSize\":-1.0,\"maxSize\":-1,\"numDistinctValues\":-1,\"numNulls\":-1}},{\"name\":\"siteid\",\"columnType\":{\"type\":\"INT\",\"len\":-1,\"precision\":-1,\"scale\":-1,\"varcharLimit\":true},\"isAggregationTypeImplicit\":false,\"isKey\":true,\"isAllowNull\":true,\"defaultValue\":\"10\",\"comment\":\"\",\"stats\":{\"avgSerializedSize\":-1.0,\"maxSize\":-1,\"numDistinctValues\":-1,\"numNulls\":-1}},{\"name\":\"citycode\",\"columnType\":{\"type\":\"SMALLINT\",\"len\":-1,\"precision\":-1,\"scale\":-1,\"varcharLimit\":true},\"isAggregationTypeImplicit\":false,\"isKey\":true,\"isAllowNull\":true,\"comment\":\"\",\"stats\":{\"avgSerializedSize\":-1.0,\"maxSize\":-1,\"numDistinctValues\":-1,\"numNulls\":-1}},{\"name\":\"username\",\"columnType\":{\"type\":\"VARCHAR\",\"len\":32,\"precision\":-1,\"scale\":-1,\"varcharLimit\":true},\"isAggregationTypeImplicit\":false,\"isKey\":true,\"isAllowNull\":true,\"defaultValue\":\"\",\"comment\":\"\",\"stats\":{\"avgSerializedSize\":-1.0,\"maxSize\":-1,\"numDistinctValues\":-1,\"numNulls\":-1}},{\"name\":\"pv\",\"columnType\":{\"type\":\"BIGINT\",\"len\":-1,\"precision\":-1,\"scale\":-1,\"varcharLimit\":true},\"aggregationType\":\"SUM\",\"isAggregationTypeImplicit\":false,\"isKey\":false,\"isAllowNull\":true,\"defaultValue\":\"0\",\"comment\":\"\",\"stats\":{\"avgSerializedSize\":-1.0,\"maxSize\":-1,\"numDistinctValues\":-1,\"numNulls\":-1}}]', '0', '1520686811', '4', '1');

-- ----------------------------
-- Table structure for meta_tablet
-- ----------------------------
DROP TABLE IF EXISTS `meta_tablet`;
CREATE TABLE `meta_tablet` (
  `tablet_id` bigint(20) NOT NULL,
  `checked_version` bigint(20) DEFAULT NULL,
  `checked_version_hash` bigint(20) DEFAULT NULL,
  `is_consistent` int(11) DEFAULT NULL COMMENT '1 true 0 false',
  `replica_id_list` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tablet_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of meta_tablet
-- ----------------------------
INSERT INTO `meta_tablet` VALUES ('10137', '-1', '-1', '1', '[10138]');
INSERT INTO `meta_tablet` VALUES ('10139', '-1', '-1', '1', '[10140]');
INSERT INTO `meta_tablet` VALUES ('10141', '-1', '-1', '1', '[10142]');
INSERT INTO `meta_tablet` VALUES ('10143', '-1', '-1', '1', '[10144]');
INSERT INTO `meta_tablet` VALUES ('10145', '-1', '-1', '1', '[10146]');
INSERT INTO `meta_tablet` VALUES ('10147', '-1', '-1', '1', '[10148]');
INSERT INTO `meta_tablet` VALUES ('10149', '-1', '-1', '1', '[10150]');
INSERT INTO `meta_tablet` VALUES ('10151', '-1', '-1', '1', '[10152]');
INSERT INTO `meta_tablet` VALUES ('10153', '-1', '-1', '1', '[10154]');
INSERT INTO `meta_tablet` VALUES ('10155', '-1', '-1', '1', '[10156]');
INSERT INTO `meta_tablet` VALUES ('10225', '-1', '-1', '1', '[10226]');
INSERT INTO `meta_tablet` VALUES ('10227', '-1', '-1', '1', '[10228]');
INSERT INTO `meta_tablet` VALUES ('10229', '-1', '-1', '1', '[10230]');
INSERT INTO `meta_tablet` VALUES ('10231', '-1', '-1', '1', '[10232]');
INSERT INTO `meta_tablet` VALUES ('10233', '-1', '-1', '1', '[10234]');
INSERT INTO `meta_tablet` VALUES ('10235', '-1', '-1', '1', '[10236]');
INSERT INTO `meta_tablet` VALUES ('10237', '-1', '-1', '1', '[10238]');
INSERT INTO `meta_tablet` VALUES ('10239', '-1', '-1', '1', '[10240]');
INSERT INTO `meta_tablet` VALUES ('10241', '-1', '-1', '1', '[10242]');
INSERT INTO `meta_tablet` VALUES ('10243', '-1', '-1', '1', '[10244]');
INSERT INTO `meta_tablet` VALUES ('10245', '-1', '-1', '1', '[10246]');
INSERT INTO `meta_tablet` VALUES ('10247', '-1', '-1', '1', '[10248]');
INSERT INTO `meta_tablet` VALUES ('10249', '-1', '-1', '1', '[10250]');
INSERT INTO `meta_tablet` VALUES ('10251', '-1', '-1', '1', '[10252]');
INSERT INTO `meta_tablet` VALUES ('10253', '-1', '-1', '1', '[10254]');
INSERT INTO `meta_tablet` VALUES ('10255', '-1', '-1', '1', '[10256]');
INSERT INTO `meta_tablet` VALUES ('10257', '-1', '-1', '1', '[10258]');
INSERT INTO `meta_tablet` VALUES ('10259', '-1', '-1', '1', '[10260]');
INSERT INTO `meta_tablet` VALUES ('10261', '-1', '-1', '1', '[10262]');
INSERT INTO `meta_tablet` VALUES ('10263', '-1', '-1', '1', '[10264]');
INSERT INTO `meta_tablet` VALUES ('10265', '-1', '-1', '1', '[10266]');
INSERT INTO `meta_tablet` VALUES ('10267', '-1', '-1', '1', '[10268]');
INSERT INTO `meta_tablet` VALUES ('10269', '-1', '-1', '1', '[10270]');
INSERT INTO `meta_tablet` VALUES ('10271', '-1', '-1', '1', '[10272]');
INSERT INTO `meta_tablet` VALUES ('10273', '-1', '-1', '1', '[10274]');
INSERT INTO `meta_tablet` VALUES ('10275', '-1', '-1', '1', '[10276]');
INSERT INTO `meta_tablet` VALUES ('10277', '-1', '-1', '1', '[10278]');
INSERT INTO `meta_tablet` VALUES ('10279', '-1', '-1', '1', '[10280]');
INSERT INTO `meta_tablet` VALUES ('10281', '-1', '-1', '1', '[10282]');
INSERT INTO `meta_tablet` VALUES ('10283', '-1', '-1', '1', '[10284]');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user
-- ----------------------------
