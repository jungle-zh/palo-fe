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

public enum TMetricKind implements org.apache.thrift.TEnum {
  GAUGE(0),
  COUNTER(1),
  PROPERTY(2),
  STATS(3),
  SET(4),
  HISTOGRAM(5);

  private final int value;

  private TMetricKind(int value) {
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
  public static TMetricKind findByValue(int value) { 
    switch (value) {
      case 0:
        return GAUGE;
      case 1:
        return COUNTER;
      case 2:
        return PROPERTY;
      case 3:
        return STATS;
      case 4:
        return SET;
      case 5:
        return HISTOGRAM;
      default:
        return null;
    }
  }
}