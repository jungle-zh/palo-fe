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
public class TCreateTabletReq implements org.apache.thrift.TBase<TCreateTabletReq, TCreateTabletReq._Fields>, java.io.Serializable, Cloneable, Comparable<TCreateTabletReq> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TCreateTabletReq");

  private static final org.apache.thrift.protocol.TField TABLET_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("tablet_id", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField TABLET_SCHEMA_FIELD_DESC = new org.apache.thrift.protocol.TField("tablet_schema", org.apache.thrift.protocol.TType.STRUCT, (short)2);
  private static final org.apache.thrift.protocol.TField VERSION_FIELD_DESC = new org.apache.thrift.protocol.TField("version", org.apache.thrift.protocol.TType.I64, (short)3);
  private static final org.apache.thrift.protocol.TField VERSION_HASH_FIELD_DESC = new org.apache.thrift.protocol.TField("version_hash", org.apache.thrift.protocol.TType.I64, (short)4);
  private static final org.apache.thrift.protocol.TField STORAGE_MEDIUM_FIELD_DESC = new org.apache.thrift.protocol.TField("storage_medium", org.apache.thrift.protocol.TType.I32, (short)5);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new TCreateTabletReqStandardSchemeFactory());
    schemes.put(TupleScheme.class, new TCreateTabletReqTupleSchemeFactory());
  }

  public long tablet_id; // required
  public TTabletSchema tablet_schema; // required
  public long version; // optional
  public long version_hash; // optional
  /**
   * 
   * @see com.baidu.palo.thrift.TStorageMedium
   */
  public com.baidu.palo.thrift.TStorageMedium storage_medium; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    TABLET_ID((short)1, "tablet_id"),
    TABLET_SCHEMA((short)2, "tablet_schema"),
    VERSION((short)3, "version"),
    VERSION_HASH((short)4, "version_hash"),
    /**
     * 
     * @see com.baidu.palo.thrift.TStorageMedium
     */
    STORAGE_MEDIUM((short)5, "storage_medium");

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
        case 1: // TABLET_ID
          return TABLET_ID;
        case 2: // TABLET_SCHEMA
          return TABLET_SCHEMA;
        case 3: // VERSION
          return VERSION;
        case 4: // VERSION_HASH
          return VERSION_HASH;
        case 5: // STORAGE_MEDIUM
          return STORAGE_MEDIUM;
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
  private static final int __VERSION_ISSET_ID = 1;
  private static final int __VERSION_HASH_ISSET_ID = 2;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.VERSION,_Fields.VERSION_HASH,_Fields.STORAGE_MEDIUM};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.TABLET_ID, new org.apache.thrift.meta_data.FieldMetaData("tablet_id", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64        , "TTabletId")));
    tmpMap.put(_Fields.TABLET_SCHEMA, new org.apache.thrift.meta_data.FieldMetaData("tablet_schema", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, TTabletSchema.class)));
    tmpMap.put(_Fields.VERSION, new org.apache.thrift.meta_data.FieldMetaData("version", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64        , "TVersion")));
    tmpMap.put(_Fields.VERSION_HASH, new org.apache.thrift.meta_data.FieldMetaData("version_hash", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64        , "TVersionHash")));
    tmpMap.put(_Fields.STORAGE_MEDIUM, new org.apache.thrift.meta_data.FieldMetaData("storage_medium", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, com.baidu.palo.thrift.TStorageMedium.class)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TCreateTabletReq.class, metaDataMap);
  }

  public TCreateTabletReq() {
  }

  public TCreateTabletReq(
    long tablet_id,
    TTabletSchema tablet_schema)
  {
    this();
    this.tablet_id = tablet_id;
    setTablet_idIsSet(true);
    this.tablet_schema = tablet_schema;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TCreateTabletReq(TCreateTabletReq other) {
    __isset_bitfield = other.__isset_bitfield;
    this.tablet_id = other.tablet_id;
    if (other.isSetTablet_schema()) {
      this.tablet_schema = new TTabletSchema(other.tablet_schema);
    }
    this.version = other.version;
    this.version_hash = other.version_hash;
    if (other.isSetStorage_medium()) {
      this.storage_medium = other.storage_medium;
    }
  }

  public TCreateTabletReq deepCopy() {
    return new TCreateTabletReq(this);
  }

  @Override
  public void clear() {
    setTablet_idIsSet(false);
    this.tablet_id = 0;
    this.tablet_schema = null;
    setVersionIsSet(false);
    this.version = 0;
    setVersion_hashIsSet(false);
    this.version_hash = 0;
    this.storage_medium = null;
  }

  public long getTablet_id() {
    return this.tablet_id;
  }

  public TCreateTabletReq setTablet_id(long tablet_id) {
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

  public TTabletSchema getTablet_schema() {
    return this.tablet_schema;
  }

  public TCreateTabletReq setTablet_schema(TTabletSchema tablet_schema) {
    this.tablet_schema = tablet_schema;
    return this;
  }

  public void unsetTablet_schema() {
    this.tablet_schema = null;
  }

  /** Returns true if field tablet_schema is set (has been assigned a value) and false otherwise */
  public boolean isSetTablet_schema() {
    return this.tablet_schema != null;
  }

  public void setTablet_schemaIsSet(boolean value) {
    if (!value) {
      this.tablet_schema = null;
    }
  }

  public long getVersion() {
    return this.version;
  }

  public TCreateTabletReq setVersion(long version) {
    this.version = version;
    setVersionIsSet(true);
    return this;
  }

  public void unsetVersion() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __VERSION_ISSET_ID);
  }

  /** Returns true if field version is set (has been assigned a value) and false otherwise */
  public boolean isSetVersion() {
    return EncodingUtils.testBit(__isset_bitfield, __VERSION_ISSET_ID);
  }

  public void setVersionIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __VERSION_ISSET_ID, value);
  }

  public long getVersion_hash() {
    return this.version_hash;
  }

  public TCreateTabletReq setVersion_hash(long version_hash) {
    this.version_hash = version_hash;
    setVersion_hashIsSet(true);
    return this;
  }

  public void unsetVersion_hash() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __VERSION_HASH_ISSET_ID);
  }

  /** Returns true if field version_hash is set (has been assigned a value) and false otherwise */
  public boolean isSetVersion_hash() {
    return EncodingUtils.testBit(__isset_bitfield, __VERSION_HASH_ISSET_ID);
  }

  public void setVersion_hashIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __VERSION_HASH_ISSET_ID, value);
  }

  /**
   * 
   * @see com.baidu.palo.thrift.TStorageMedium
   */
  public com.baidu.palo.thrift.TStorageMedium getStorage_medium() {
    return this.storage_medium;
  }

  /**
   * 
   * @see com.baidu.palo.thrift.TStorageMedium
   */
  public TCreateTabletReq setStorage_medium(com.baidu.palo.thrift.TStorageMedium storage_medium) {
    this.storage_medium = storage_medium;
    return this;
  }

  public void unsetStorage_medium() {
    this.storage_medium = null;
  }

  /** Returns true if field storage_medium is set (has been assigned a value) and false otherwise */
  public boolean isSetStorage_medium() {
    return this.storage_medium != null;
  }

  public void setStorage_mediumIsSet(boolean value) {
    if (!value) {
      this.storage_medium = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case TABLET_ID:
      if (value == null) {
        unsetTablet_id();
      } else {
        setTablet_id((Long)value);
      }
      break;

    case TABLET_SCHEMA:
      if (value == null) {
        unsetTablet_schema();
      } else {
        setTablet_schema((TTabletSchema)value);
      }
      break;

    case VERSION:
      if (value == null) {
        unsetVersion();
      } else {
        setVersion((Long)value);
      }
      break;

    case VERSION_HASH:
      if (value == null) {
        unsetVersion_hash();
      } else {
        setVersion_hash((Long)value);
      }
      break;

    case STORAGE_MEDIUM:
      if (value == null) {
        unsetStorage_medium();
      } else {
        setStorage_medium((com.baidu.palo.thrift.TStorageMedium)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case TABLET_ID:
      return getTablet_id();

    case TABLET_SCHEMA:
      return getTablet_schema();

    case VERSION:
      return getVersion();

    case VERSION_HASH:
      return getVersion_hash();

    case STORAGE_MEDIUM:
      return getStorage_medium();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case TABLET_ID:
      return isSetTablet_id();
    case TABLET_SCHEMA:
      return isSetTablet_schema();
    case VERSION:
      return isSetVersion();
    case VERSION_HASH:
      return isSetVersion_hash();
    case STORAGE_MEDIUM:
      return isSetStorage_medium();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TCreateTabletReq)
      return this.equals((TCreateTabletReq)that);
    return false;
  }

  public boolean equals(TCreateTabletReq that) {
    if (that == null)
      return false;

    boolean this_present_tablet_id = true;
    boolean that_present_tablet_id = true;
    if (this_present_tablet_id || that_present_tablet_id) {
      if (!(this_present_tablet_id && that_present_tablet_id))
        return false;
      if (this.tablet_id != that.tablet_id)
        return false;
    }

    boolean this_present_tablet_schema = true && this.isSetTablet_schema();
    boolean that_present_tablet_schema = true && that.isSetTablet_schema();
    if (this_present_tablet_schema || that_present_tablet_schema) {
      if (!(this_present_tablet_schema && that_present_tablet_schema))
        return false;
      if (!this.tablet_schema.equals(that.tablet_schema))
        return false;
    }

    boolean this_present_version = true && this.isSetVersion();
    boolean that_present_version = true && that.isSetVersion();
    if (this_present_version || that_present_version) {
      if (!(this_present_version && that_present_version))
        return false;
      if (this.version != that.version)
        return false;
    }

    boolean this_present_version_hash = true && this.isSetVersion_hash();
    boolean that_present_version_hash = true && that.isSetVersion_hash();
    if (this_present_version_hash || that_present_version_hash) {
      if (!(this_present_version_hash && that_present_version_hash))
        return false;
      if (this.version_hash != that.version_hash)
        return false;
    }

    boolean this_present_storage_medium = true && this.isSetStorage_medium();
    boolean that_present_storage_medium = true && that.isSetStorage_medium();
    if (this_present_storage_medium || that_present_storage_medium) {
      if (!(this_present_storage_medium && that_present_storage_medium))
        return false;
      if (!this.storage_medium.equals(that.storage_medium))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_tablet_id = true;
    list.add(present_tablet_id);
    if (present_tablet_id)
      list.add(tablet_id);

    boolean present_tablet_schema = true && (isSetTablet_schema());
    list.add(present_tablet_schema);
    if (present_tablet_schema)
      list.add(tablet_schema);

    boolean present_version = true && (isSetVersion());
    list.add(present_version);
    if (present_version)
      list.add(version);

    boolean present_version_hash = true && (isSetVersion_hash());
    list.add(present_version_hash);
    if (present_version_hash)
      list.add(version_hash);

    boolean present_storage_medium = true && (isSetStorage_medium());
    list.add(present_storage_medium);
    if (present_storage_medium)
      list.add(storage_medium.getValue());

    return list.hashCode();
  }

  @Override
  public int compareTo(TCreateTabletReq other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

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
    lastComparison = Boolean.valueOf(isSetTablet_schema()).compareTo(other.isSetTablet_schema());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTablet_schema()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.tablet_schema, other.tablet_schema);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetVersion()).compareTo(other.isSetVersion());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetVersion()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.version, other.version);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetVersion_hash()).compareTo(other.isSetVersion_hash());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetVersion_hash()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.version_hash, other.version_hash);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetStorage_medium()).compareTo(other.isSetStorage_medium());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetStorage_medium()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.storage_medium, other.storage_medium);
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
    StringBuilder sb = new StringBuilder("TCreateTabletReq(");
    boolean first = true;

    sb.append("tablet_id:");
    sb.append(this.tablet_id);
    first = false;
    if (!first) sb.append(", ");
    sb.append("tablet_schema:");
    if (this.tablet_schema == null) {
      sb.append("null");
    } else {
      sb.append(this.tablet_schema);
    }
    first = false;
    if (isSetVersion()) {
      if (!first) sb.append(", ");
      sb.append("version:");
      sb.append(this.version);
      first = false;
    }
    if (isSetVersion_hash()) {
      if (!first) sb.append(", ");
      sb.append("version_hash:");
      sb.append(this.version_hash);
      first = false;
    }
    if (isSetStorage_medium()) {
      if (!first) sb.append(", ");
      sb.append("storage_medium:");
      if (this.storage_medium == null) {
        sb.append("null");
      } else {
        sb.append(this.storage_medium);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // alas, we cannot check 'tablet_id' because it's a primitive and you chose the non-beans generator.
    if (tablet_schema == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'tablet_schema' was not present! Struct: " + toString());
    }
    // check for sub-struct validity
    if (tablet_schema != null) {
      tablet_schema.validate();
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

  private static class TCreateTabletReqStandardSchemeFactory implements SchemeFactory {
    public TCreateTabletReqStandardScheme getScheme() {
      return new TCreateTabletReqStandardScheme();
    }
  }

  private static class TCreateTabletReqStandardScheme extends StandardScheme<TCreateTabletReq> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TCreateTabletReq struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // TABLET_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.tablet_id = iprot.readI64();
              struct.setTablet_idIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // TABLET_SCHEMA
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.tablet_schema = new TTabletSchema();
              struct.tablet_schema.read(iprot);
              struct.setTablet_schemaIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // VERSION
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.version = iprot.readI64();
              struct.setVersionIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // VERSION_HASH
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.version_hash = iprot.readI64();
              struct.setVersion_hashIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // STORAGE_MEDIUM
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.storage_medium = com.baidu.palo.thrift.TStorageMedium.findByValue(iprot.readI32());
              struct.setStorage_mediumIsSet(true);
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
      if (!struct.isSetTablet_id()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'tablet_id' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, TCreateTabletReq struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(TABLET_ID_FIELD_DESC);
      oprot.writeI64(struct.tablet_id);
      oprot.writeFieldEnd();
      if (struct.tablet_schema != null) {
        oprot.writeFieldBegin(TABLET_SCHEMA_FIELD_DESC);
        struct.tablet_schema.write(oprot);
        oprot.writeFieldEnd();
      }
      if (struct.isSetVersion()) {
        oprot.writeFieldBegin(VERSION_FIELD_DESC);
        oprot.writeI64(struct.version);
        oprot.writeFieldEnd();
      }
      if (struct.isSetVersion_hash()) {
        oprot.writeFieldBegin(VERSION_HASH_FIELD_DESC);
        oprot.writeI64(struct.version_hash);
        oprot.writeFieldEnd();
      }
      if (struct.storage_medium != null) {
        if (struct.isSetStorage_medium()) {
          oprot.writeFieldBegin(STORAGE_MEDIUM_FIELD_DESC);
          oprot.writeI32(struct.storage_medium.getValue());
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TCreateTabletReqTupleSchemeFactory implements SchemeFactory {
    public TCreateTabletReqTupleScheme getScheme() {
      return new TCreateTabletReqTupleScheme();
    }
  }

  private static class TCreateTabletReqTupleScheme extends TupleScheme<TCreateTabletReq> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TCreateTabletReq struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeI64(struct.tablet_id);
      struct.tablet_schema.write(oprot);
      BitSet optionals = new BitSet();
      if (struct.isSetVersion()) {
        optionals.set(0);
      }
      if (struct.isSetVersion_hash()) {
        optionals.set(1);
      }
      if (struct.isSetStorage_medium()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetVersion()) {
        oprot.writeI64(struct.version);
      }
      if (struct.isSetVersion_hash()) {
        oprot.writeI64(struct.version_hash);
      }
      if (struct.isSetStorage_medium()) {
        oprot.writeI32(struct.storage_medium.getValue());
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TCreateTabletReq struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.tablet_id = iprot.readI64();
      struct.setTablet_idIsSet(true);
      struct.tablet_schema = new TTabletSchema();
      struct.tablet_schema.read(iprot);
      struct.setTablet_schemaIsSet(true);
      BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.version = iprot.readI64();
        struct.setVersionIsSet(true);
      }
      if (incoming.get(1)) {
        struct.version_hash = iprot.readI64();
        struct.setVersion_hashIsSet(true);
      }
      if (incoming.get(2)) {
        struct.storage_medium = com.baidu.palo.thrift.TStorageMedium.findByValue(iprot.readI32());
        struct.setStorage_mediumIsSet(true);
      }
    }
  }

}

