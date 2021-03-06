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
public class TUserSpecification implements org.apache.thrift.TBase<TUserSpecification, TUserSpecification._Fields>, java.io.Serializable, Cloneable, Comparable<TUserSpecification> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TUserSpecification");

  private static final org.apache.thrift.protocol.TField USER_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("user_name", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField HOST_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("host_name", org.apache.thrift.protocol.TType.STRING, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new TUserSpecificationStandardSchemeFactory());
    schemes.put(TupleScheme.class, new TUserSpecificationTupleSchemeFactory());
  }

  public String user_name; // required
  public String host_name; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    USER_NAME((short)1, "user_name"),
    HOST_NAME((short)2, "host_name");

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
        case 1: // USER_NAME
          return USER_NAME;
        case 2: // HOST_NAME
          return HOST_NAME;
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
  private static final _Fields optionals[] = {_Fields.HOST_NAME};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.USER_NAME, new org.apache.thrift.meta_data.FieldMetaData("user_name", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.HOST_NAME, new org.apache.thrift.meta_data.FieldMetaData("host_name", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TUserSpecification.class, metaDataMap);
  }

  public TUserSpecification() {
  }

  public TUserSpecification(
    String user_name)
  {
    this();
    this.user_name = user_name;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TUserSpecification(TUserSpecification other) {
    if (other.isSetUser_name()) {
      this.user_name = other.user_name;
    }
    if (other.isSetHost_name()) {
      this.host_name = other.host_name;
    }
  }

  public TUserSpecification deepCopy() {
    return new TUserSpecification(this);
  }

  @Override
  public void clear() {
    this.user_name = null;
    this.host_name = null;
  }

  public String getUser_name() {
    return this.user_name;
  }

  public TUserSpecification setUser_name(String user_name) {
    this.user_name = user_name;
    return this;
  }

  public void unsetUser_name() {
    this.user_name = null;
  }

  /** Returns true if field user_name is set (has been assigned a value) and false otherwise */
  public boolean isSetUser_name() {
    return this.user_name != null;
  }

  public void setUser_nameIsSet(boolean value) {
    if (!value) {
      this.user_name = null;
    }
  }

  public String getHost_name() {
    return this.host_name;
  }

  public TUserSpecification setHost_name(String host_name) {
    this.host_name = host_name;
    return this;
  }

  public void unsetHost_name() {
    this.host_name = null;
  }

  /** Returns true if field host_name is set (has been assigned a value) and false otherwise */
  public boolean isSetHost_name() {
    return this.host_name != null;
  }

  public void setHost_nameIsSet(boolean value) {
    if (!value) {
      this.host_name = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case USER_NAME:
      if (value == null) {
        unsetUser_name();
      } else {
        setUser_name((String)value);
      }
      break;

    case HOST_NAME:
      if (value == null) {
        unsetHost_name();
      } else {
        setHost_name((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case USER_NAME:
      return getUser_name();

    case HOST_NAME:
      return getHost_name();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case USER_NAME:
      return isSetUser_name();
    case HOST_NAME:
      return isSetHost_name();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TUserSpecification)
      return this.equals((TUserSpecification)that);
    return false;
  }

  public boolean equals(TUserSpecification that) {
    if (that == null)
      return false;

    boolean this_present_user_name = true && this.isSetUser_name();
    boolean that_present_user_name = true && that.isSetUser_name();
    if (this_present_user_name || that_present_user_name) {
      if (!(this_present_user_name && that_present_user_name))
        return false;
      if (!this.user_name.equals(that.user_name))
        return false;
    }

    boolean this_present_host_name = true && this.isSetHost_name();
    boolean that_present_host_name = true && that.isSetHost_name();
    if (this_present_host_name || that_present_host_name) {
      if (!(this_present_host_name && that_present_host_name))
        return false;
      if (!this.host_name.equals(that.host_name))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_user_name = true && (isSetUser_name());
    list.add(present_user_name);
    if (present_user_name)
      list.add(user_name);

    boolean present_host_name = true && (isSetHost_name());
    list.add(present_host_name);
    if (present_host_name)
      list.add(host_name);

    return list.hashCode();
  }

  @Override
  public int compareTo(TUserSpecification other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetUser_name()).compareTo(other.isSetUser_name());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUser_name()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.user_name, other.user_name);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetHost_name()).compareTo(other.isSetHost_name());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetHost_name()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.host_name, other.host_name);
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
    StringBuilder sb = new StringBuilder("TUserSpecification(");
    boolean first = true;

    sb.append("user_name:");
    if (this.user_name == null) {
      sb.append("null");
    } else {
      sb.append(this.user_name);
    }
    first = false;
    if (isSetHost_name()) {
      if (!first) sb.append(", ");
      sb.append("host_name:");
      if (this.host_name == null) {
        sb.append("null");
      } else {
        sb.append(this.host_name);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (user_name == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'user_name' was not present! Struct: " + toString());
    }
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
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TUserSpecificationStandardSchemeFactory implements SchemeFactory {
    public TUserSpecificationStandardScheme getScheme() {
      return new TUserSpecificationStandardScheme();
    }
  }

  private static class TUserSpecificationStandardScheme extends StandardScheme<TUserSpecification> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TUserSpecification struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // USER_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.user_name = iprot.readString();
              struct.setUser_nameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // HOST_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.host_name = iprot.readString();
              struct.setHost_nameIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, TUserSpecification struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.user_name != null) {
        oprot.writeFieldBegin(USER_NAME_FIELD_DESC);
        oprot.writeString(struct.user_name);
        oprot.writeFieldEnd();
      }
      if (struct.host_name != null) {
        if (struct.isSetHost_name()) {
          oprot.writeFieldBegin(HOST_NAME_FIELD_DESC);
          oprot.writeString(struct.host_name);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TUserSpecificationTupleSchemeFactory implements SchemeFactory {
    public TUserSpecificationTupleScheme getScheme() {
      return new TUserSpecificationTupleScheme();
    }
  }

  private static class TUserSpecificationTupleScheme extends TupleScheme<TUserSpecification> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TUserSpecification struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeString(struct.user_name);
      BitSet optionals = new BitSet();
      if (struct.isSetHost_name()) {
        optionals.set(0);
      }
      oprot.writeBitSet(optionals, 1);
      if (struct.isSetHost_name()) {
        oprot.writeString(struct.host_name);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TUserSpecification struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.user_name = iprot.readString();
      struct.setUser_nameIsSet(true);
      BitSet incoming = iprot.readBitSet(1);
      if (incoming.get(0)) {
        struct.host_name = iprot.readString();
        struct.setHost_nameIsSet(true);
      }
    }
  }

}

