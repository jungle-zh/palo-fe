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
public class TUploadReq implements org.apache.thrift.TBase<TUploadReq, TUploadReq._Fields>, java.io.Serializable, Cloneable, Comparable<TUploadReq> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TUploadReq");

  private static final org.apache.thrift.protocol.TField LOCAL_FILE_PATH_FIELD_DESC = new org.apache.thrift.protocol.TField("local_file_path", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField REMOTE_FILE_PATH_FIELD_DESC = new org.apache.thrift.protocol.TField("remote_file_path", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField REMOTE_SOURCE_PROPERTIES_FIELD_DESC = new org.apache.thrift.protocol.TField("remote_source_properties", org.apache.thrift.protocol.TType.MAP, (short)3);
  private static final org.apache.thrift.protocol.TField TABLET_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("tablet_id", org.apache.thrift.protocol.TType.I64, (short)4);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new TUploadReqStandardSchemeFactory());
    schemes.put(TupleScheme.class, new TUploadReqTupleSchemeFactory());
  }

  public String local_file_path; // required
  public String remote_file_path; // required
  public Map<String,String> remote_source_properties; // required
  public long tablet_id; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    LOCAL_FILE_PATH((short)1, "local_file_path"),
    REMOTE_FILE_PATH((short)2, "remote_file_path"),
    REMOTE_SOURCE_PROPERTIES((short)3, "remote_source_properties"),
    TABLET_ID((short)4, "tablet_id");

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
        case 1: // LOCAL_FILE_PATH
          return LOCAL_FILE_PATH;
        case 2: // REMOTE_FILE_PATH
          return REMOTE_FILE_PATH;
        case 3: // REMOTE_SOURCE_PROPERTIES
          return REMOTE_SOURCE_PROPERTIES;
        case 4: // TABLET_ID
          return TABLET_ID;
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
  private static final int __TABLET_ID_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.TABLET_ID};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.LOCAL_FILE_PATH, new org.apache.thrift.meta_data.FieldMetaData("local_file_path", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.REMOTE_FILE_PATH, new org.apache.thrift.meta_data.FieldMetaData("remote_file_path", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.REMOTE_SOURCE_PROPERTIES, new org.apache.thrift.meta_data.FieldMetaData("remote_source_properties", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.MapMetaData(org.apache.thrift.protocol.TType.MAP, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING), 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    tmpMap.put(_Fields.TABLET_ID, new org.apache.thrift.meta_data.FieldMetaData("tablet_id", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64        , "TTabletId")));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TUploadReq.class, metaDataMap);
  }

  public TUploadReq() {
  }

  public TUploadReq(
    String local_file_path,
    String remote_file_path,
    Map<String,String> remote_source_properties)
  {
    this();
    this.local_file_path = local_file_path;
    this.remote_file_path = remote_file_path;
    this.remote_source_properties = remote_source_properties;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TUploadReq(TUploadReq other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetLocal_file_path()) {
      this.local_file_path = other.local_file_path;
    }
    if (other.isSetRemote_file_path()) {
      this.remote_file_path = other.remote_file_path;
    }
    if (other.isSetRemote_source_properties()) {
      Map<String,String> __this__remote_source_properties = new HashMap<String,String>(other.remote_source_properties);
      this.remote_source_properties = __this__remote_source_properties;
    }
    this.tablet_id = other.tablet_id;
  }

  public TUploadReq deepCopy() {
    return new TUploadReq(this);
  }

  @Override
  public void clear() {
    this.local_file_path = null;
    this.remote_file_path = null;
    this.remote_source_properties = null;
    setTablet_idIsSet(false);
    this.tablet_id = 0;
  }

  public String getLocal_file_path() {
    return this.local_file_path;
  }

  public TUploadReq setLocal_file_path(String local_file_path) {
    this.local_file_path = local_file_path;
    return this;
  }

  public void unsetLocal_file_path() {
    this.local_file_path = null;
  }

  /** Returns true if field local_file_path is set (has been assigned a value) and false otherwise */
  public boolean isSetLocal_file_path() {
    return this.local_file_path != null;
  }

  public void setLocal_file_pathIsSet(boolean value) {
    if (!value) {
      this.local_file_path = null;
    }
  }

  public String getRemote_file_path() {
    return this.remote_file_path;
  }

  public TUploadReq setRemote_file_path(String remote_file_path) {
    this.remote_file_path = remote_file_path;
    return this;
  }

  public void unsetRemote_file_path() {
    this.remote_file_path = null;
  }

  /** Returns true if field remote_file_path is set (has been assigned a value) and false otherwise */
  public boolean isSetRemote_file_path() {
    return this.remote_file_path != null;
  }

  public void setRemote_file_pathIsSet(boolean value) {
    if (!value) {
      this.remote_file_path = null;
    }
  }

  public int getRemote_source_propertiesSize() {
    return (this.remote_source_properties == null) ? 0 : this.remote_source_properties.size();
  }

  public void putToRemote_source_properties(String key, String val) {
    if (this.remote_source_properties == null) {
      this.remote_source_properties = new HashMap<String,String>();
    }
    this.remote_source_properties.put(key, val);
  }

  public Map<String,String> getRemote_source_properties() {
    return this.remote_source_properties;
  }

  public TUploadReq setRemote_source_properties(Map<String,String> remote_source_properties) {
    this.remote_source_properties = remote_source_properties;
    return this;
  }

  public void unsetRemote_source_properties() {
    this.remote_source_properties = null;
  }

  /** Returns true if field remote_source_properties is set (has been assigned a value) and false otherwise */
  public boolean isSetRemote_source_properties() {
    return this.remote_source_properties != null;
  }

  public void setRemote_source_propertiesIsSet(boolean value) {
    if (!value) {
      this.remote_source_properties = null;
    }
  }

  public long getTablet_id() {
    return this.tablet_id;
  }

  public TUploadReq setTablet_id(long tablet_id) {
    this.tablet_id = tablet_id;
    setTablet_idIsSet(true);
    return this;
  }

  public void unsetTablet_id() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __TABLET_ID_ISSET_ID);
  }

  /** Returns true if field tablet_id is set (has been assigned a value) and false otherwise */
  public boolean isSetTablet_id() {
    return EncodingUtils.testBit(__isset_bitfield, __TABLET_ID_ISSET_ID);
  }

  public void setTablet_idIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __TABLET_ID_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case LOCAL_FILE_PATH:
      if (value == null) {
        unsetLocal_file_path();
      } else {
        setLocal_file_path((String)value);
      }
      break;

    case REMOTE_FILE_PATH:
      if (value == null) {
        unsetRemote_file_path();
      } else {
        setRemote_file_path((String)value);
      }
      break;

    case REMOTE_SOURCE_PROPERTIES:
      if (value == null) {
        unsetRemote_source_properties();
      } else {
        setRemote_source_properties((Map<String,String>)value);
      }
      break;

    case TABLET_ID:
      if (value == null) {
        unsetTablet_id();
      } else {
        setTablet_id((Long)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case LOCAL_FILE_PATH:
      return getLocal_file_path();

    case REMOTE_FILE_PATH:
      return getRemote_file_path();

    case REMOTE_SOURCE_PROPERTIES:
      return getRemote_source_properties();

    case TABLET_ID:
      return getTablet_id();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case LOCAL_FILE_PATH:
      return isSetLocal_file_path();
    case REMOTE_FILE_PATH:
      return isSetRemote_file_path();
    case REMOTE_SOURCE_PROPERTIES:
      return isSetRemote_source_properties();
    case TABLET_ID:
      return isSetTablet_id();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TUploadReq)
      return this.equals((TUploadReq)that);
    return false;
  }

  public boolean equals(TUploadReq that) {
    if (that == null)
      return false;

    boolean this_present_local_file_path = true && this.isSetLocal_file_path();
    boolean that_present_local_file_path = true && that.isSetLocal_file_path();
    if (this_present_local_file_path || that_present_local_file_path) {
      if (!(this_present_local_file_path && that_present_local_file_path))
        return false;
      if (!this.local_file_path.equals(that.local_file_path))
        return false;
    }

    boolean this_present_remote_file_path = true && this.isSetRemote_file_path();
    boolean that_present_remote_file_path = true && that.isSetRemote_file_path();
    if (this_present_remote_file_path || that_present_remote_file_path) {
      if (!(this_present_remote_file_path && that_present_remote_file_path))
        return false;
      if (!this.remote_file_path.equals(that.remote_file_path))
        return false;
    }

    boolean this_present_remote_source_properties = true && this.isSetRemote_source_properties();
    boolean that_present_remote_source_properties = true && that.isSetRemote_source_properties();
    if (this_present_remote_source_properties || that_present_remote_source_properties) {
      if (!(this_present_remote_source_properties && that_present_remote_source_properties))
        return false;
      if (!this.remote_source_properties.equals(that.remote_source_properties))
        return false;
    }

    boolean this_present_tablet_id = true && this.isSetTablet_id();
    boolean that_present_tablet_id = true && that.isSetTablet_id();
    if (this_present_tablet_id || that_present_tablet_id) {
      if (!(this_present_tablet_id && that_present_tablet_id))
        return false;
      if (this.tablet_id != that.tablet_id)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_local_file_path = true && (isSetLocal_file_path());
    list.add(present_local_file_path);
    if (present_local_file_path)
      list.add(local_file_path);

    boolean present_remote_file_path = true && (isSetRemote_file_path());
    list.add(present_remote_file_path);
    if (present_remote_file_path)
      list.add(remote_file_path);

    boolean present_remote_source_properties = true && (isSetRemote_source_properties());
    list.add(present_remote_source_properties);
    if (present_remote_source_properties)
      list.add(remote_source_properties);

    boolean present_tablet_id = true && (isSetTablet_id());
    list.add(present_tablet_id);
    if (present_tablet_id)
      list.add(tablet_id);

    return list.hashCode();
  }

  @Override
  public int compareTo(TUploadReq other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetLocal_file_path()).compareTo(other.isSetLocal_file_path());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLocal_file_path()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.local_file_path, other.local_file_path);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetRemote_file_path()).compareTo(other.isSetRemote_file_path());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRemote_file_path()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.remote_file_path, other.remote_file_path);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetRemote_source_properties()).compareTo(other.isSetRemote_source_properties());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRemote_source_properties()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.remote_source_properties, other.remote_source_properties);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTablet_id()).compareTo(other.isSetTablet_id());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTablet_id()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.tablet_id, other.tablet_id);
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
    StringBuilder sb = new StringBuilder("TUploadReq(");
    boolean first = true;

    sb.append("local_file_path:");
    if (this.local_file_path == null) {
      sb.append("null");
    } else {
      sb.append(this.local_file_path);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("remote_file_path:");
    if (this.remote_file_path == null) {
      sb.append("null");
    } else {
      sb.append(this.remote_file_path);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("remote_source_properties:");
    if (this.remote_source_properties == null) {
      sb.append("null");
    } else {
      sb.append(this.remote_source_properties);
    }
    first = false;
    if (isSetTablet_id()) {
      if (!first) sb.append(", ");
      sb.append("tablet_id:");
      sb.append(this.tablet_id);
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (local_file_path == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'local_file_path' was not present! Struct: " + toString());
    }
    if (remote_file_path == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'remote_file_path' was not present! Struct: " + toString());
    }
    if (remote_source_properties == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'remote_source_properties' was not present! Struct: " + toString());
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
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TUploadReqStandardSchemeFactory implements SchemeFactory {
    public TUploadReqStandardScheme getScheme() {
      return new TUploadReqStandardScheme();
    }
  }

  private static class TUploadReqStandardScheme extends StandardScheme<TUploadReq> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TUploadReq struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // LOCAL_FILE_PATH
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.local_file_path = iprot.readString();
              struct.setLocal_file_pathIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // REMOTE_FILE_PATH
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.remote_file_path = iprot.readString();
              struct.setRemote_file_pathIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // REMOTE_SOURCE_PROPERTIES
            if (schemeField.type == org.apache.thrift.protocol.TType.MAP) {
              {
                org.apache.thrift.protocol.TMap _map24 = iprot.readMapBegin();
                struct.remote_source_properties = new HashMap<String,String>(2*_map24.size);
                String _key25;
                String _val26;
                for (int _i27 = 0; _i27 < _map24.size; ++_i27)
                {
                  _key25 = iprot.readString();
                  _val26 = iprot.readString();
                  struct.remote_source_properties.put(_key25, _val26);
                }
                iprot.readMapEnd();
              }
              struct.setRemote_source_propertiesIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // TABLET_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.tablet_id = iprot.readI64();
              struct.setTablet_idIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, TUploadReq struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.local_file_path != null) {
        oprot.writeFieldBegin(LOCAL_FILE_PATH_FIELD_DESC);
        oprot.writeString(struct.local_file_path);
        oprot.writeFieldEnd();
      }
      if (struct.remote_file_path != null) {
        oprot.writeFieldBegin(REMOTE_FILE_PATH_FIELD_DESC);
        oprot.writeString(struct.remote_file_path);
        oprot.writeFieldEnd();
      }
      if (struct.remote_source_properties != null) {
        oprot.writeFieldBegin(REMOTE_SOURCE_PROPERTIES_FIELD_DESC);
        {
          oprot.writeMapBegin(new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.STRING, struct.remote_source_properties.size()));
          for (Map.Entry<String, String> _iter28 : struct.remote_source_properties.entrySet())
          {
            oprot.writeString(_iter28.getKey());
            oprot.writeString(_iter28.getValue());
          }
          oprot.writeMapEnd();
        }
        oprot.writeFieldEnd();
      }
      if (struct.isSetTablet_id()) {
        oprot.writeFieldBegin(TABLET_ID_FIELD_DESC);
        oprot.writeI64(struct.tablet_id);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TUploadReqTupleSchemeFactory implements SchemeFactory {
    public TUploadReqTupleScheme getScheme() {
      return new TUploadReqTupleScheme();
    }
  }

  private static class TUploadReqTupleScheme extends TupleScheme<TUploadReq> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TUploadReq struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeString(struct.local_file_path);
      oprot.writeString(struct.remote_file_path);
      {
        oprot.writeI32(struct.remote_source_properties.size());
        for (Map.Entry<String, String> _iter29 : struct.remote_source_properties.entrySet())
        {
          oprot.writeString(_iter29.getKey());
          oprot.writeString(_iter29.getValue());
        }
      }
      BitSet optionals = new BitSet();
      if (struct.isSetTablet_id()) {
        optionals.set(0);
      }
      oprot.writeBitSet(optionals, 1);
      if (struct.isSetTablet_id()) {
        oprot.writeI64(struct.tablet_id);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TUploadReq struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.local_file_path = iprot.readString();
      struct.setLocal_file_pathIsSet(true);
      struct.remote_file_path = iprot.readString();
      struct.setRemote_file_pathIsSet(true);
      {
        org.apache.thrift.protocol.TMap _map30 = new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.STRING, iprot.readI32());
        struct.remote_source_properties = new HashMap<String,String>(2*_map30.size);
        String _key31;
        String _val32;
        for (int _i33 = 0; _i33 < _map30.size; ++_i33)
        {
          _key31 = iprot.readString();
          _val32 = iprot.readString();
          struct.remote_source_properties.put(_key31, _val32);
        }
      }
      struct.setRemote_source_propertiesIsSet(true);
      BitSet incoming = iprot.readBitSet(1);
      if (incoming.get(0)) {
        struct.tablet_id = iprot.readI64();
        struct.setTablet_idIsSet(true);
      }
    }
  }

}

