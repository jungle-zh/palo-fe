/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.baidu.palo.thrift;


import java.util.Map;
import java.util.HashMap;
import org.apache.thrift.TEnum;

public enum TPlanNodeType implements org.apache.thrift.TEnum {
  OLAP_SCAN_NODE(0),
  MYSQL_SCAN_NODE(1),
  CSV_SCAN_NODE(2),
  SCHEMA_SCAN_NODE(3),
  HASH_JOIN_NODE(4),
  MERGE_JOIN_NODE(5),
  AGGREGATION_NODE(6),
  PRE_AGGREGATION_NODE(7),
  SORT_NODE(8),
  EXCHANGE_NODE(9),
  MERGE_NODE(10),
  SELECT_NODE(11),
  CROSS_JOIN_NODE(12),
  META_SCAN_NODE(13),
  ANALYTIC_EVAL_NODE(14),
  OLAP_REWRITE_NODE(15),
  KUDU_SCAN_NODE(16),
  BROKER_SCAN_NODE(17),
  EMPTY_SET_NODE(18),
  UNION_NODE(19);

  private final int value;

  private TPlanNodeType(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  public static TPlanNodeType findByValue(int value) { 
    switch (value) {
      case 0:
        return OLAP_SCAN_NODE;
      case 1:
        return MYSQL_SCAN_NODE;
      case 2:
        return CSV_SCAN_NODE;
      case 3:
        return SCHEMA_SCAN_NODE;
      case 4:
        return HASH_JOIN_NODE;
      case 5:
        return MERGE_JOIN_NODE;
      case 6:
        return AGGREGATION_NODE;
      case 7:
        return PRE_AGGREGATION_NODE;
      case 8:
        return SORT_NODE;
      case 9:
        return EXCHANGE_NODE;
      case 10:
        return MERGE_NODE;
      case 11:
        return SELECT_NODE;
      case 12:
        return CROSS_JOIN_NODE;
      case 13:
        return META_SCAN_NODE;
      case 14:
        return ANALYTIC_EVAL_NODE;
      case 15:
        return OLAP_REWRITE_NODE;
      case 16:
        return KUDU_SCAN_NODE;
      case 17:
        return BROKER_SCAN_NODE;
      case 18:
        return EMPTY_SET_NODE;
      case 19:
        return UNION_NODE;
      default:
        return null;
    }
  }
}