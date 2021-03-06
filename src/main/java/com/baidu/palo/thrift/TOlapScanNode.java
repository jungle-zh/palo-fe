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
public class TOlapScanNode implements org.apache.thrift.TBase<TOlapScanNode, TOlapScanNode._Fields>, java.io.Serializable, Cloneable, Comparable<TOlapScanNode> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TOlapScanNode");

  private static final org.apache.thrift.protocol.TField TUPLE_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("tuple_id", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField KEY_COLUMN_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("key_column_name", org.apache.thrift.protocol.TType.LIST, (short)2);
  private static final org.apache.thrift.protocol.TField KEY_COLUMN_TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("key_column_type", org.apache.thrift.protocol.TType.LIST, (short)3);
  private static final org.apache.thrift.protocol.TField IS_PREAGGREGATION_FIELD_DESC = new org.apache.thrift.protocol.TField("is_preaggregation", org.apache.thrift.protocol.TType.BOOL, (short)4);
  private static final org.apache.thrift.protocol.TField SORT_COLUMN_FIELD_DESC = new org.apache.thrift.protocol.TField("sort_column", org.apache.thrift.protocol.TType.STRING, (short)5);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new TOlapScanNodeStandardSchemeFactory());
    schemes.put(TupleScheme.class, new TOlapScanNodeTupleSchemeFactory());
  }

  public int tuple_id; // required
  public List<String> key_column_name; // required
  public List<com.baidu.palo.thrift.TPrimitiveType> key_column_type; // required
  public boolean is_preaggregation; // required
  public String sort_column; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    TUPLE_ID((short)1, "tuple_id"),
    KEY_COLUMN_NAME((short)2, "key_column_name"),
    KEY_COLUMN_TYPE((short)3, "key_column_type"),
    IS_PREAGGREGATION((short)4, "is_preaggregation"),
    SORT_COLUMN((short)5, "sort_column");

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
        case 1: // TUPLE_ID
          return TUPLE_ID;
        case 2: // KEY_COLUMN_NAME
          return KEY_COLUMN_NAME;
        case 3: // KEY_COLUMN_TYPE
          return KEY_COLUMN_TYPE;
        case 4: // IS_PREAGGREGATION
          return IS_PREAGGREGATION;
        case 5: // SORT_COLUMN
          return SORT_COLUMN;
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
  private static final int __TUPLE_ID_ISSET_ID = 0;
  private static final int __IS_PREAGGREGATION_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.SORT_COLUMN};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.TUPLE_ID, new org.apache.thrift.meta_data.FieldMetaData("tuple_id", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32        , "TTupleId")));
    tmpMap.put(_Fields.KEY_COLUMN_NAME, new org.apache.thrift.meta_data.FieldMetaData("key_column_name", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    tmpMap.put(_Fields.KEY_COLUMN_TYPE, new org.apache.thrift.meta_data.FieldMetaData("key_column_type", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, com.baidu.palo.thrift.TPrimitiveType.class))));
    tmpMap.put(_Fields.IS_PREAGGREGATION, new org.apache.thrift.meta_data.FieldMetaData("is_preaggregation", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.SORT_COLUMN, new org.apache.thrift.meta_data.FieldMetaData("sort_column", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TOlapScanNode.class, metaDataMap);
  }

  public TOlapScanNode() {
  }

  public TOlapScanNode(
    int tuple_id,
    List<String> key_column_name,
    List<com.baidu.palo.thrift.TPrimitiveType> key_column_type,
    boolean is_preaggregation)
  {
    this();
    this.tuple_id = tuple_id;
    setTuple_idIsSet(true);
    this.key_column_name = key_column_name;
    this.key_column_type = key_column_type;
    this.is_preaggregation = is_preaggregation;
    setIs_preaggregationIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TOlapScanNode(TOlapScanNode other) {
    __isset_bitfield = other.__isset_bitfield;
    this.tuple_id = other.tuple_id;
    if (other.isSetKey_column_name()) {
      List<String> __this__key_column_name = new ArrayList<String>(other.key_column_name);
      this.key_column_name = __this__key_column_name;
    }
    if (other.isSetKey_column_type()) {
      List<com.baidu.palo.thrift.TPrimitiveType> __this__key_column_type = new ArrayList<com.baidu.palo.thrift.TPrimitiveType>(other.key_column_type.size());
      for (com.baidu.palo.thrift.TPrimitiveType other_element : other.key_column_type) {
        __this__key_column_type.add(other_element);
      }
      this.key_column_type = __this__key_column_type;
    }
    this.is_preaggregation = other.is_preaggregation;
    if (other.isSetSort_column()) {
      this.sort_column = other.sort_column;
    }
  }

  public TOlapScanNode deepCopy() {
    return new TOlapScanNode(this);
  }

  @Override
  public void clear() {
    setTuple_idIsSet(false);
    this.tuple_id = 0;
    this.key_column_name = null;
    this.key_column_type = null;
    setIs_preaggregationIsSet(false);
    this.is_preaggregation = false;
    this.sort_column = null;
  }

  public int getTuple_id() {
    return this.tuple_id;
  }

  public TOlapScanNode setTuple_id(int tuple_id) {
    this.tuple_id = tuple_id;
    setTuple_idIsSet(true);
    return this;
  }

  public void unsetTuple_id() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __TUPLE_ID_ISSET_ID);
  }

  /** Returns true if field tuple_id is set (has been assigned a value) and false otherwise */
  public boolean isSetTuple_id() {
    return EncodingUtils.testBit(__isset_bitfield, __TUPLE_ID_ISSET_ID);
  }

  public void setTuple_idIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __TUPLE_ID_ISSET_ID, value);
  }

  public int getKey_column_nameSize() {
    return (this.key_column_name == null) ? 0 : this.key_column_name.size();
  }

  public java.util.Iterator<String> getKey_column_nameIterator() {
    return (this.key_column_name == null) ? null : this.key_column_name.iterator();
  }

  public void addToKey_column_name(String elem) {
    if (this.key_column_name == null) {
      this.key_column_name = new ArrayList<String>();
    }
    this.key_column_name.add(elem);
  }

  public List<String> getKey_column_name() {
    return this.key_column_name;
  }

  public TOlapScanNode setKey_column_name(List<String> key_column_name) {
    this.key_column_name = key_column_name;
    return this;
  }

  public void unsetKey_column_name() {
    this.key_column_name = null;
  }

  /** Returns true if field key_column_name is set (has been assigned a value) and false otherwise */
  public boolean isSetKey_column_name() {
    return this.key_column_name != null;
  }

  public void setKey_column_nameIsSet(boolean value) {
    if (!value) {
      this.key_column_name = null;
    }
  }

  public int getKey_column_typeSize() {
    return (this.key_column_type == null) ? 0 : this.key_column_type.size();
  }

  public java.util.Iterator<com.baidu.palo.thrift.TPrimitiveType> getKey_column_typeIterator() {
    return (this.key_column_type == null) ? null : this.key_column_type.iterator();
  }

  public void addToKey_column_type(com.baidu.palo.thrift.TPrimitiveType elem) {
    if (this.key_column_type == null) {
      this.key_column_type = new ArrayList<com.baidu.palo.thrift.TPrimitiveType>();
    }
    this.key_column_type.add(elem);
  }

  public List<com.baidu.palo.thrift.TPrimitiveType> getKey_column_type() {
    return this.key_column_type;
  }

  public TOlapScanNode setKey_column_type(List<com.baidu.palo.thrift.TPrimitiveType> key_column_type) {
    this.key_column_type = key_column_type;
    return this;
  }

  public void unsetKey_column_type() {
    this.key_column_type = null;
  }

  /** Returns true if field key_column_type is set (has been assigned a value) and false otherwise */
  public boolean isSetKey_column_type() {
    return this.key_column_type != null;
  }

  public void setKey_column_typeIsSet(boolean value) {
    if (!value) {
      this.key_column_type = null;
    }
  }

  public boolean isIs_preaggregation() {
    return this.is_preaggregation;
  }

  public TOlapScanNode setIs_preaggregation(boolean is_preaggregation) {
    this.is_preaggregation = is_preaggregation;
    setIs_preaggregationIsSet(true);
    return this;
  }

  public void unsetIs_preaggregation() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __IS_PREAGGREGATION_ISSET_ID);
  }

  /** Returns true if field is_preaggregation is set (has been assigned a value) and false otherwise */
  public boolean isSetIs_preaggregation() {
    return EncodingUtils.testBit(__isset_bitfield, __IS_PREAGGREGATION_ISSET_ID);
  }

  public void setIs_preaggregationIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __IS_PREAGGREGATION_ISSET_ID, value);
  }

  public String getSort_column() {
    return this.sort_column;
  }

  public TOlapScanNode setSort_column(String sort_column) {
    this.sort_column = sort_column;
    return this;
  }

  public void unsetSort_column() {
    this.sort_column = null;
  }

  /** Returns true if field sort_column is set (has been assigned a value) and false otherwise */
  public boolean isSetSort_column() {
    return this.sort_column != null;
  }

  public void setSort_columnIsSet(boolean value) {
    if (!value) {
      this.sort_column = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case TUPLE_ID:
      if (value == null) {
        unsetTuple_id();
      } else {
        setTuple_id((Integer)value);
      }
      break;

    case KEY_COLUMN_NAME:
      if (value == null) {
        unsetKey_column_name();
      } else {
        setKey_column_name((List<String>)value);
      }
      break;

    case KEY_COLUMN_TYPE:
      if (value == null) {
        unsetKey_column_type();
      } else {
        setKey_column_type((List<com.baidu.palo.thrift.TPrimitiveType>)value);
      }
      break;

    case IS_PREAGGREGATION:
      if (value == null) {
        unsetIs_preaggregation();
      } else {
        setIs_preaggregation((Boolean)value);
      }
      break;

    case SORT_COLUMN:
      if (value == null) {
        unsetSort_column();
      } else {
        setSort_column((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case TUPLE_ID:
      return getTuple_id();

    case KEY_COLUMN_NAME:
      return getKey_column_name();

    case KEY_COLUMN_TYPE:
      return getKey_column_type();

    case IS_PREAGGREGATION:
      return isIs_preaggregation();

    case SORT_COLUMN:
      return getSort_column();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case TUPLE_ID:
      return isSetTuple_id();
    case KEY_COLUMN_NAME:
      return isSetKey_column_name();
    case KEY_COLUMN_TYPE:
      return isSetKey_column_type();
    case IS_PREAGGREGATION:
      return isSetIs_preaggregation();
    case SORT_COLUMN:
      return isSetSort_column();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TOlapScanNode)
      return this.equals((TOlapScanNode)that);
    return false;
  }

  public boolean equals(TOlapScanNode that) {
    if (that == null)
      return false;

    boolean this_present_tuple_id = true;
    boolean that_present_tuple_id = true;
    if (this_present_tuple_id || that_present_tuple_id) {
      if (!(this_present_tuple_id && that_present_tuple_id))
        return false;
      if (this.tuple_id != that.tuple_id)
        return false;
    }

    boolean this_present_key_column_name = true && this.isSetKey_column_name();
    boolean that_present_key_column_name = true && that.isSetKey_column_name();
    if (this_present_key_column_name || that_present_key_column_name) {
      if (!(this_present_key_column_name && that_present_key_column_name))
        return false;
      if (!this.key_column_name.equals(that.key_column_name))
        return false;
    }

    boolean this_present_key_column_type = true && this.isSetKey_column_type();
    boolean that_present_key_column_type = true && that.isSetKey_column_type();
    if (this_present_key_column_type || that_present_key_column_type) {
      if (!(this_present_key_column_type && that_present_key_column_type))
        return false;
      if (!this.key_column_type.equals(that.key_column_type))
        return false;
    }

    boolean this_present_is_preaggregation = true;
    boolean that_present_is_preaggregation = true;
    if (this_present_is_preaggregation || that_present_is_preaggregation) {
      if (!(this_present_is_preaggregation && that_present_is_preaggregation))
        return false;
      if (this.is_preaggregation != that.is_preaggregation)
        return false;
    }

    boolean this_present_sort_column = true && this.isSetSort_column();
    boolean that_present_sort_column = true && that.isSetSort_column();
    if (this_present_sort_column || that_present_sort_column) {
      if (!(this_present_sort_column && that_present_sort_column))
        return false;
      if (!this.sort_column.equals(that.sort_column))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_tuple_id = true;
    list.add(present_tuple_id);
    if (present_tuple_id)
      list.add(tuple_id);

    boolean present_key_column_name = true && (isSetKey_column_name());
    list.add(present_key_column_name);
    if (present_key_column_name)
      list.add(key_column_name);

    boolean present_key_column_type = true && (isSetKey_column_type());
    list.add(present_key_column_type);
    if (present_key_column_type)
      list.add(key_column_type);

    boolean present_is_preaggregation = true;
    list.add(present_is_preaggregation);
    if (present_is_preaggregation)
      list.add(is_preaggregation);

    boolean present_sort_column = true && (isSetSort_column());
    list.add(present_sort_column);
    if (present_sort_column)
      list.add(sort_column);

    return list.hashCode();
  }

  @Override
  public int compareTo(TOlapScanNode other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetTuple_id()).compareTo(other.isSetTuple_id());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTuple_id()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.tuple_id, other.tuple_id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetKey_column_name()).compareTo(other.isSetKey_column_name());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetKey_column_name()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.key_column_name, other.key_column_name);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetKey_column_type()).compareTo(other.isSetKey_column_type());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetKey_column_type()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.key_column_type, other.key_column_type);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetIs_preaggregation()).compareTo(other.isSetIs_preaggregation());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetIs_preaggregation()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.is_preaggregation, other.is_preaggregation);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetSort_column()).compareTo(other.isSetSort_column());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSort_column()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.sort_column, other.sort_column);
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
    StringBuilder sb = new StringBuilder("TOlapScanNode(");
    boolean first = true;

    sb.append("tuple_id:");
    sb.append(this.tuple_id);
    first = false;
    if (!first) sb.append(", ");
    sb.append("key_column_name:");
    if (this.key_column_name == null) {
      sb.append("null");
    } else {
      sb.append(this.key_column_name);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("key_column_type:");
    if (this.key_column_type == null) {
      sb.append("null");
    } else {
      sb.append(this.key_column_type);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("is_preaggregation:");
    sb.append(this.is_preaggregation);
    first = false;
    if (isSetSort_column()) {
      if (!first) sb.append(", ");
      sb.append("sort_column:");
      if (this.sort_column == null) {
        sb.append("null");
      } else {
        sb.append(this.sort_column);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // alas, we cannot check 'tuple_id' because it's a primitive and you chose the non-beans generator.
    if (key_column_name == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'key_column_name' was not present! Struct: " + toString());
    }
    if (key_column_type == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'key_column_type' was not present! Struct: " + toString());
    }
    // alas, we cannot check 'is_preaggregation' because it's a primitive and you chose the non-beans generator.
    // check for sub-struct validity
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

  private static class TOlapScanNodeStandardSchemeFactory implements SchemeFactory {
    public TOlapScanNodeStandardScheme getScheme() {
      return new TOlapScanNodeStandardScheme();
    }
  }

  private static class TOlapScanNodeStandardScheme extends StandardScheme<TOlapScanNode> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TOlapScanNode struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // TUPLE_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.tuple_id = iprot.readI32();
              struct.setTuple_idIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // KEY_COLUMN_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list152 = iprot.readListBegin();
                struct.key_column_name = new ArrayList<String>(_list152.size);
                String _elem153;
                for (int _i154 = 0; _i154 < _list152.size; ++_i154)
                {
                  _elem153 = iprot.readString();
                  struct.key_column_name.add(_elem153);
                }
                iprot.readListEnd();
              }
              struct.setKey_column_nameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // KEY_COLUMN_TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list155 = iprot.readListBegin();
                struct.key_column_type = new ArrayList<com.baidu.palo.thrift.TPrimitiveType>(_list155.size);
                com.baidu.palo.thrift.TPrimitiveType _elem156;
                for (int _i157 = 0; _i157 < _list155.size; ++_i157)
                {
                  _elem156 = com.baidu.palo.thrift.TPrimitiveType.findByValue(iprot.readI32());
                  struct.key_column_type.add(_elem156);
                }
                iprot.readListEnd();
              }
              struct.setKey_column_typeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // IS_PREAGGREGATION
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.is_preaggregation = iprot.readBool();
              struct.setIs_preaggregationIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // SORT_COLUMN
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.sort_column = iprot.readString();
              struct.setSort_columnIsSet(true);
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
      if (!struct.isSetTuple_id()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'tuple_id' was not found in serialized data! Struct: " + toString());
      }
      if (!struct.isSetIs_preaggregation()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'is_preaggregation' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, TOlapScanNode struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(TUPLE_ID_FIELD_DESC);
      oprot.writeI32(struct.tuple_id);
      oprot.writeFieldEnd();
      if (struct.key_column_name != null) {
        oprot.writeFieldBegin(KEY_COLUMN_NAME_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, struct.key_column_name.size()));
          for (String _iter158 : struct.key_column_name)
          {
            oprot.writeString(_iter158);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      if (struct.key_column_type != null) {
        oprot.writeFieldBegin(KEY_COLUMN_TYPE_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.I32, struct.key_column_type.size()));
          for (com.baidu.palo.thrift.TPrimitiveType _iter159 : struct.key_column_type)
          {
            oprot.writeI32(_iter159.getValue());
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(IS_PREAGGREGATION_FIELD_DESC);
      oprot.writeBool(struct.is_preaggregation);
      oprot.writeFieldEnd();
      if (struct.sort_column != null) {
        if (struct.isSetSort_column()) {
          oprot.writeFieldBegin(SORT_COLUMN_FIELD_DESC);
          oprot.writeString(struct.sort_column);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TOlapScanNodeTupleSchemeFactory implements SchemeFactory {
    public TOlapScanNodeTupleScheme getScheme() {
      return new TOlapScanNodeTupleScheme();
    }
  }

  private static class TOlapScanNodeTupleScheme extends TupleScheme<TOlapScanNode> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TOlapScanNode struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeI32(struct.tuple_id);
      {
        oprot.writeI32(struct.key_column_name.size());
        for (String _iter160 : struct.key_column_name)
        {
          oprot.writeString(_iter160);
        }
      }
      {
        oprot.writeI32(struct.key_column_type.size());
        for (com.baidu.palo.thrift.TPrimitiveType _iter161 : struct.key_column_type)
        {
          oprot.writeI32(_iter161.getValue());
        }
      }
      oprot.writeBool(struct.is_preaggregation);
      BitSet optionals = new BitSet();
      if (struct.isSetSort_column()) {
        optionals.set(0);
      }
      oprot.writeBitSet(optionals, 1);
      if (struct.isSetSort_column()) {
        oprot.writeString(struct.sort_column);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TOlapScanNode struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.tuple_id = iprot.readI32();
      struct.setTuple_idIsSet(true);
      {
        org.apache.thrift.protocol.TList _list162 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, iprot.readI32());
        struct.key_column_name = new ArrayList<String>(_list162.size);
        String _elem163;
        for (int _i164 = 0; _i164 < _list162.size; ++_i164)
        {
          _elem163 = iprot.readString();
          struct.key_column_name.add(_elem163);
        }
      }
      struct.setKey_column_nameIsSet(true);
      {
        org.apache.thrift.protocol.TList _list165 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.I32, iprot.readI32());
        struct.key_column_type = new ArrayList<com.baidu.palo.thrift.TPrimitiveType>(_list165.size);
        com.baidu.palo.thrift.TPrimitiveType _elem166;
        for (int _i167 = 0; _i167 < _list165.size; ++_i167)
        {
          _elem166 = com.baidu.palo.thrift.TPrimitiveType.findByValue(iprot.readI32());
          struct.key_column_type.add(_elem166);
        }
      }
      struct.setKey_column_typeIsSet(true);
      struct.is_preaggregation = iprot.readBool();
      struct.setIs_preaggregationIsSet(true);
      BitSet incoming = iprot.readBitSet(1);
      if (incoming.get(0)) {
        struct.sort_column = iprot.readString();
        struct.setSort_columnIsSet(true);
      }
    }
  }

}

