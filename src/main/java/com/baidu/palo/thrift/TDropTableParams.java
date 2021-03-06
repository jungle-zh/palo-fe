/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.baidu.palo.thrift;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)", date = "2018-05-01")
public class TDropTableParams implements org.apache.thrift.TBase<TDropTableParams, TDropTableParams._Fields>, java.io.Serializable, Cloneable, Comparable<TDropTableParams> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TDropTableParams");

  private static final org.apache.thrift.protocol.TField TABLE_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("table_name", org.apache.thrift.protocol.TType.STRUCT, (short)1);
  private static final org.apache.thrift.protocol.TField IF_EXISTS_FIELD_DESC = new org.apache.thrift.protocol.TField("if_exists", org.apache.thrift.protocol.TType.BOOL, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new TDropTableParamsStandardSchemeFactory());
    schemes.put(TupleScheme.class, new TDropTableParamsTupleSchemeFactory());
  }

  public TTableName table_name; // required
  public boolean if_exists; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    TABLE_NAME((short)1, "table_name"),
    IF_EXISTS((short)2, "if_exists");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // TABLE_NAME
          return TABLE_NAME;
        case 2: // IF_EXISTS
          return IF_EXISTS;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __IF_EXISTS_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.IF_EXISTS};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.TABLE_NAME, new org.apache.thrift.meta_data.FieldMetaData("table_name", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, TTableName.class)));
    tmpMap.put(_Fields.IF_EXISTS, new org.apache.thrift.meta_data.FieldMetaData("if_exists", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TDropTableParams.class, metaDataMap);
  }

  public TDropTableParams() {
  }

  public TDropTableParams(
    TTableName table_name)
  {
    this();
    this.table_name = table_name;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TDropTableParams(TDropTableParams other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetTable_name()) {
      this.table_name = new TTableName(other.table_name);
    }
    this.if_exists = other.if_exists;
  }

  public TDropTableParams deepCopy() {
    return new TDropTableParams(this);
  }

  @Override
  public void clear() {
    this.table_name = null;
    setIf_existsIsSet(false);
    this.if_exists = false;
  }

  public TTableName getTable_name() {
    return this.table_name;
  }

  public TDropTableParams setTable_name(TTableName table_name) {
    this.table_name = table_name;
    return this;
  }

  public void unsetTable_name() {
    this.table_name = null;
  }

  /** Returns true if field table_name is set (has been assigned a value) and false otherwise */
  public boolean isSetTable_name() {
    return this.table_name != null;
  }

  public void setTable_nameIsSet(boolean value) {
    if (!value) {
      this.table_name = null;
    }
  }

  public boolean isIf_exists() {
    return this.if_exists;
  }

  public TDropTableParams setIf_exists(boolean if_exists) {
    this.if_exists = if_exists;
    setIf_existsIsSet(true);
    return this;
  }

  public void unsetIf_exists() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __IF_EXISTS_ISSET_ID);
  }

  /** Returns true if field if_exists is set (has been assigned a value) and false otherwise */
  public boolean isSetIf_exists() {
    return EncodingUtils.testBit(__isset_bitfield, __IF_EXISTS_ISSET_ID);
  }

  public void setIf_existsIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __IF_EXISTS_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case TABLE_NAME:
      if (value == null) {
        unsetTable_name();
      } else {
        setTable_name((TTableName)value);
      }
      break;

    case IF_EXISTS:
      if (value == null) {
        unsetIf_exists();
      } else {
        setIf_exists((Boolean)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case TABLE_NAME:
      return getTable_name();

    case IF_EXISTS:
      return isIf_exists();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case TABLE_NAME:
      return isSetTable_name();
    case IF_EXISTS:
      return isSetIf_exists();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TDropTableParams)
      return this.equals((TDropTableParams)that);
    return false;
  }

  public boolean equals(TDropTableParams that) {
    if (that == null)
      return false;

    boolean this_present_table_name = true && this.isSetTable_name();
    boolean that_present_table_name = true && that.isSetTable_name();
    if (this_present_table_name || that_present_table_name) {
      if (!(this_present_table_name && that_present_table_name))
        return false;
      if (!this.table_name.equals(that.table_name))
        return false;
    }

    boolean this_present_if_exists = true && this.isSetIf_exists();
    boolean that_present_if_exists = true && that.isSetIf_exists();
    if (this_present_if_exists || that_present_if_exists) {
      if (!(this_present_if_exists && that_present_if_exists))
        return false;
      if (this.if_exists != that.if_exists)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_table_name = true && (isSetTable_name());
    list.add(present_table_name);
    if (present_table_name)
      list.add(table_name);

    boolean present_if_exists = true && (isSetIf_exists());
    list.add(present_if_exists);
    if (present_if_exists)
      list.add(if_exists);

    return list.hashCode();
  }

  @Override
  public int compareTo(TDropTableParams other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetTable_name()).compareTo(other.isSetTable_name());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTable_name()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.table_name, other.table_name);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetIf_exists()).compareTo(other.isSetIf_exists());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetIf_exists()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.if_exists, other.if_exists);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("TDropTableParams(");
    boolean first = true;

    sb.append("table_name:");
    if (this.table_name == null) {
      sb.append("null");
    } else {
      sb.append(this.table_name);
    }
    first = false;
    if (isSetIf_exists()) {
      if (!first) sb.append(", ");
      sb.append("if_exists:");
      sb.append(this.if_exists);
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (table_name == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'table_name' was not present! Struct: " + toString());
    }
    // check for sub-struct validity
    if (table_name != null) {
      table_name.validate();
    }
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TDropTableParamsStandardSchemeFactory implements SchemeFactory {
    public TDropTableParamsStandardScheme getScheme() {
      return new TDropTableParamsStandardScheme();
    }
  }

  private static class TDropTableParamsStandardScheme extends StandardScheme<TDropTableParams> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TDropTableParams struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // TABLE_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.table_name = new TTableName();
              struct.table_name.read(iprot);
              struct.setTable_nameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // IF_EXISTS
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.if_exists = iprot.readBool();
              struct.setIf_existsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, TDropTableParams struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.table_name != null) {
        oprot.writeFieldBegin(TABLE_NAME_FIELD_DESC);
        struct.table_name.write(oprot);
        oprot.writeFieldEnd();
      }
      if (struct.isSetIf_exists()) {
        oprot.writeFieldBegin(IF_EXISTS_FIELD_DESC);
        oprot.writeBool(struct.if_exists);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TDropTableParamsTupleSchemeFactory implements SchemeFactory {
    public TDropTableParamsTupleScheme getScheme() {
      return new TDropTableParamsTupleScheme();
    }
  }

  private static class TDropTableParamsTupleScheme extends TupleScheme<TDropTableParams> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TDropTableParams struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      struct.table_name.write(oprot);
      BitSet optionals = new BitSet();
      if (struct.isSetIf_exists()) {
        optionals.set(0);
      }
      oprot.writeBitSet(optionals, 1);
      if (struct.isSetIf_exists()) {
        oprot.writeBool(struct.if_exists);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TDropTableParams struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.table_name = new TTableName();
      struct.table_name.read(iprot);
      struct.setTable_nameIsSet(true);
      BitSet incoming = iprot.readBitSet(1);
      if (incoming.get(0)) {
        struct.if_exists = iprot.readBool();
        struct.setIf_existsIsSet(true);
      }
    }
  }

}

