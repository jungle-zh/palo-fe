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
public class TTypeDesc implements org.apache.thrift.TBase<TTypeDesc, TTypeDesc._Fields>, java.io.Serializable, Cloneable, Comparable<TTypeDesc> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TTypeDesc");

  private static final org.apache.thrift.protocol.TField TYPES_FIELD_DESC = new org.apache.thrift.protocol.TField("types", org.apache.thrift.protocol.TType.LIST, (short)1);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new TTypeDescStandardSchemeFactory());
    schemes.put(TupleScheme.class, new TTypeDescTupleSchemeFactory());
  }

  public List<TTypeNode> types; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    TYPES((short)1, "types");

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
        case 1: // TYPES
          return TYPES;
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
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.TYPES, new org.apache.thrift.meta_data.FieldMetaData("types", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, TTypeNode.class))));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TTypeDesc.class, metaDataMap);
  }

  public TTypeDesc() {
  }

  public TTypeDesc(
    List<TTypeNode> types)
  {
    this();
    this.types = types;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TTypeDesc(TTypeDesc other) {
    if (other.isSetTypes()) {
      List<TTypeNode> __this__types = new ArrayList<TTypeNode>(other.types.size());
      for (TTypeNode other_element : other.types) {
        __this__types.add(new TTypeNode(other_element));
      }
      this.types = __this__types;
    }
  }

  public TTypeDesc deepCopy() {
    return new TTypeDesc(this);
  }

  @Override
  public void clear() {
    this.types = null;
  }

  public int getTypesSize() {
    return (this.types == null) ? 0 : this.types.size();
  }

  public java.util.Iterator<TTypeNode> getTypesIterator() {
    return (this.types == null) ? null : this.types.iterator();
  }

  public void addToTypes(TTypeNode elem) {
    if (this.types == null) {
      this.types = new ArrayList<TTypeNode>();
    }
    this.types.add(elem);
  }

  public List<TTypeNode> getTypes() {
    return this.types;
  }

  public TTypeDesc setTypes(List<TTypeNode> types) {
    this.types = types;
    return this;
  }

  public void unsetTypes() {
    this.types = null;
  }

  /** Returns true if field types is set (has been assigned a value) and false otherwise */
  public boolean isSetTypes() {
    return this.types != null;
  }

  public void setTypesIsSet(boolean value) {
    if (!value) {
      this.types = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case TYPES:
      if (value == null) {
        unsetTypes();
      } else {
        setTypes((List<TTypeNode>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case TYPES:
      return getTypes();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case TYPES:
      return isSetTypes();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TTypeDesc)
      return this.equals((TTypeDesc)that);
    return false;
  }

  public boolean equals(TTypeDesc that) {
    if (that == null)
      return false;

    boolean this_present_types = true && this.isSetTypes();
    boolean that_present_types = true && that.isSetTypes();
    if (this_present_types || that_present_types) {
      if (!(this_present_types && that_present_types))
        return false;
      if (!this.types.equals(that.types))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_types = true && (isSetTypes());
    list.add(present_types);
    if (present_types)
      list.add(types);

    return list.hashCode();
  }

  @Override
  public int compareTo(TTypeDesc other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetTypes()).compareTo(other.isSetTypes());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTypes()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.types, other.types);
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
    StringBuilder sb = new StringBuilder("TTypeDesc(");
    boolean first = true;

    sb.append("types:");
    if (this.types == null) {
      sb.append("null");
    } else {
      sb.append(this.types);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
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

  private static class TTypeDescStandardSchemeFactory implements SchemeFactory {
    public TTypeDescStandardScheme getScheme() {
      return new TTypeDescStandardScheme();
    }
  }

  private static class TTypeDescStandardScheme extends StandardScheme<TTypeDesc> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TTypeDesc struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // TYPES
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list8 = iprot.readListBegin();
                struct.types = new ArrayList<TTypeNode>(_list8.size);
                TTypeNode _elem9;
                for (int _i10 = 0; _i10 < _list8.size; ++_i10)
                {
                  _elem9 = new TTypeNode();
                  _elem9.read(iprot);
                  struct.types.add(_elem9);
                }
                iprot.readListEnd();
              }
              struct.setTypesIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, TTypeDesc struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.types != null) {
        oprot.writeFieldBegin(TYPES_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.types.size()));
          for (TTypeNode _iter11 : struct.types)
          {
            _iter11.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TTypeDescTupleSchemeFactory implements SchemeFactory {
    public TTypeDescTupleScheme getScheme() {
      return new TTypeDescTupleScheme();
    }
  }

  private static class TTypeDescTupleScheme extends TupleScheme<TTypeDesc> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TTypeDesc struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetTypes()) {
        optionals.set(0);
      }
      oprot.writeBitSet(optionals, 1);
      if (struct.isSetTypes()) {
        {
          oprot.writeI32(struct.types.size());
          for (TTypeNode _iter12 : struct.types)
          {
            _iter12.write(oprot);
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TTypeDesc struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(1);
      if (incoming.get(0)) {
        {
          org.apache.thrift.protocol.TList _list13 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.types = new ArrayList<TTypeNode>(_list13.size);
          TTypeNode _elem14;
          for (int _i15 = 0; _i15 < _list13.size; ++_i15)
          {
            _elem14 = new TTypeNode();
            _elem14.read(iprot);
            struct.types.add(_elem14);
          }
        }
        struct.setTypesIsSet(true);
      }
    }
  }

}

