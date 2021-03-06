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
public class TRowBatch implements org.apache.thrift.TBase<TRowBatch, TRowBatch._Fields>, java.io.Serializable, Cloneable, Comparable<TRowBatch> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TRowBatch");

  private static final org.apache.thrift.protocol.TField NUM_ROWS_FIELD_DESC = new org.apache.thrift.protocol.TField("num_rows", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField ROW_TUPLES_FIELD_DESC = new org.apache.thrift.protocol.TField("row_tuples", org.apache.thrift.protocol.TType.LIST, (short)2);
  private static final org.apache.thrift.protocol.TField TUPLE_OFFSETS_FIELD_DESC = new org.apache.thrift.protocol.TField("tuple_offsets", org.apache.thrift.protocol.TType.LIST, (short)3);
  private static final org.apache.thrift.protocol.TField TUPLE_DATA_FIELD_DESC = new org.apache.thrift.protocol.TField("tuple_data", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField IS_COMPRESSED_FIELD_DESC = new org.apache.thrift.protocol.TField("is_compressed", org.apache.thrift.protocol.TType.BOOL, (short)5);
  private static final org.apache.thrift.protocol.TField BE_NUMBER_FIELD_DESC = new org.apache.thrift.protocol.TField("be_number", org.apache.thrift.protocol.TType.I32, (short)6);
  private static final org.apache.thrift.protocol.TField PACKET_SEQ_FIELD_DESC = new org.apache.thrift.protocol.TField("packet_seq", org.apache.thrift.protocol.TType.I64, (short)7);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new TRowBatchStandardSchemeFactory());
    schemes.put(TupleScheme.class, new TRowBatchTupleSchemeFactory());
  }

  public int num_rows; // required
  public List<Integer> row_tuples; // required
  public List<Integer> tuple_offsets; // required
  public String tuple_data; // required
  public boolean is_compressed; // required
  public int be_number; // required
  public long packet_seq; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    NUM_ROWS((short)1, "num_rows"),
    ROW_TUPLES((short)2, "row_tuples"),
    TUPLE_OFFSETS((short)3, "tuple_offsets"),
    TUPLE_DATA((short)4, "tuple_data"),
    IS_COMPRESSED((short)5, "is_compressed"),
    BE_NUMBER((short)6, "be_number"),
    PACKET_SEQ((short)7, "packet_seq");

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
        case 1: // NUM_ROWS
          return NUM_ROWS;
        case 2: // ROW_TUPLES
          return ROW_TUPLES;
        case 3: // TUPLE_OFFSETS
          return TUPLE_OFFSETS;
        case 4: // TUPLE_DATA
          return TUPLE_DATA;
        case 5: // IS_COMPRESSED
          return IS_COMPRESSED;
        case 6: // BE_NUMBER
          return BE_NUMBER;
        case 7: // PACKET_SEQ
          return PACKET_SEQ;
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
  private static final int __NUM_ROWS_ISSET_ID = 0;
  private static final int __IS_COMPRESSED_ISSET_ID = 1;
  private static final int __BE_NUMBER_ISSET_ID = 2;
  private static final int __PACKET_SEQ_ISSET_ID = 3;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.NUM_ROWS, new org.apache.thrift.meta_data.FieldMetaData("num_rows", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.ROW_TUPLES, new org.apache.thrift.meta_data.FieldMetaData("row_tuples", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32            , "TTupleId"))));
    tmpMap.put(_Fields.TUPLE_OFFSETS, new org.apache.thrift.meta_data.FieldMetaData("tuple_offsets", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32))));
    tmpMap.put(_Fields.TUPLE_DATA, new org.apache.thrift.meta_data.FieldMetaData("tuple_data", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.IS_COMPRESSED, new org.apache.thrift.meta_data.FieldMetaData("is_compressed", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.BE_NUMBER, new org.apache.thrift.meta_data.FieldMetaData("be_number", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.PACKET_SEQ, new org.apache.thrift.meta_data.FieldMetaData("packet_seq", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TRowBatch.class, metaDataMap);
  }

  public TRowBatch() {
  }

  public TRowBatch(
    int num_rows,
    List<Integer> row_tuples,
    List<Integer> tuple_offsets,
    String tuple_data,
    boolean is_compressed,
    int be_number,
    long packet_seq)
  {
    this();
    this.num_rows = num_rows;
    setNum_rowsIsSet(true);
    this.row_tuples = row_tuples;
    this.tuple_offsets = tuple_offsets;
    this.tuple_data = tuple_data;
    this.is_compressed = is_compressed;
    setIs_compressedIsSet(true);
    this.be_number = be_number;
    setBe_numberIsSet(true);
    this.packet_seq = packet_seq;
    setPacket_seqIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TRowBatch(TRowBatch other) {
    __isset_bitfield = other.__isset_bitfield;
    this.num_rows = other.num_rows;
    if (other.isSetRow_tuples()) {
      List<Integer> __this__row_tuples = new ArrayList<Integer>(other.row_tuples.size());
      for (Integer other_element : other.row_tuples) {
        __this__row_tuples.add(other_element);
      }
      this.row_tuples = __this__row_tuples;
    }
    if (other.isSetTuple_offsets()) {
      List<Integer> __this__tuple_offsets = new ArrayList<Integer>(other.tuple_offsets);
      this.tuple_offsets = __this__tuple_offsets;
    }
    if (other.isSetTuple_data()) {
      this.tuple_data = other.tuple_data;
    }
    this.is_compressed = other.is_compressed;
    this.be_number = other.be_number;
    this.packet_seq = other.packet_seq;
  }

  public TRowBatch deepCopy() {
    return new TRowBatch(this);
  }

  @Override
  public void clear() {
    setNum_rowsIsSet(false);
    this.num_rows = 0;
    this.row_tuples = null;
    this.tuple_offsets = null;
    this.tuple_data = null;
    setIs_compressedIsSet(false);
    this.is_compressed = false;
    setBe_numberIsSet(false);
    this.be_number = 0;
    setPacket_seqIsSet(false);
    this.packet_seq = 0;
  }

  public int getNum_rows() {
    return this.num_rows;
  }

  public TRowBatch setNum_rows(int num_rows) {
    this.num_rows = num_rows;
    setNum_rowsIsSet(true);
    return this;
  }

  public void unsetNum_rows() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __NUM_ROWS_ISSET_ID);
  }

  /** Returns true if field num_rows is set (has been assigned a value) and false otherwise */
  public boolean isSetNum_rows() {
    return EncodingUtils.testBit(__isset_bitfield, __NUM_ROWS_ISSET_ID);
  }

  public void setNum_rowsIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __NUM_ROWS_ISSET_ID, value);
  }

  public int getRow_tuplesSize() {
    return (this.row_tuples == null) ? 0 : this.row_tuples.size();
  }

  public java.util.Iterator<Integer> getRow_tuplesIterator() {
    return (this.row_tuples == null) ? null : this.row_tuples.iterator();
  }

  public void addToRow_tuples(int elem) {
    if (this.row_tuples == null) {
      this.row_tuples = new ArrayList<Integer>();
    }
    this.row_tuples.add(elem);
  }

  public List<Integer> getRow_tuples() {
    return this.row_tuples;
  }

  public TRowBatch setRow_tuples(List<Integer> row_tuples) {
    this.row_tuples = row_tuples;
    return this;
  }

  public void unsetRow_tuples() {
    this.row_tuples = null;
  }

  /** Returns true if field row_tuples is set (has been assigned a value) and false otherwise */
  public boolean isSetRow_tuples() {
    return this.row_tuples != null;
  }

  public void setRow_tuplesIsSet(boolean value) {
    if (!value) {
      this.row_tuples = null;
    }
  }

  public int getTuple_offsetsSize() {
    return (this.tuple_offsets == null) ? 0 : this.tuple_offsets.size();
  }

  public java.util.Iterator<Integer> getTuple_offsetsIterator() {
    return (this.tuple_offsets == null) ? null : this.tuple_offsets.iterator();
  }

  public void addToTuple_offsets(int elem) {
    if (this.tuple_offsets == null) {
      this.tuple_offsets = new ArrayList<Integer>();
    }
    this.tuple_offsets.add(elem);
  }

  public List<Integer> getTuple_offsets() {
    return this.tuple_offsets;
  }

  public TRowBatch setTuple_offsets(List<Integer> tuple_offsets) {
    this.tuple_offsets = tuple_offsets;
    return this;
  }

  public void unsetTuple_offsets() {
    this.tuple_offsets = null;
  }

  /** Returns true if field tuple_offsets is set (has been assigned a value) and false otherwise */
  public boolean isSetTuple_offsets() {
    return this.tuple_offsets != null;
  }

  public void setTuple_offsetsIsSet(boolean value) {
    if (!value) {
      this.tuple_offsets = null;
    }
  }

  public String getTuple_data() {
    return this.tuple_data;
  }

  public TRowBatch setTuple_data(String tuple_data) {
    this.tuple_data = tuple_data;
    return this;
  }

  public void unsetTuple_data() {
    this.tuple_data = null;
  }

  /** Returns true if field tuple_data is set (has been assigned a value) and false otherwise */
  public boolean isSetTuple_data() {
    return this.tuple_data != null;
  }

  public void setTuple_dataIsSet(boolean value) {
    if (!value) {
      this.tuple_data = null;
    }
  }

  public boolean isIs_compressed() {
    return this.is_compressed;
  }

  public TRowBatch setIs_compressed(boolean is_compressed) {
    this.is_compressed = is_compressed;
    setIs_compressedIsSet(true);
    return this;
  }

  public void unsetIs_compressed() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __IS_COMPRESSED_ISSET_ID);
  }

  /** Returns true if field is_compressed is set (has been assigned a value) and false otherwise */
  public boolean isSetIs_compressed() {
    return EncodingUtils.testBit(__isset_bitfield, __IS_COMPRESSED_ISSET_ID);
  }

  public void setIs_compressedIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __IS_COMPRESSED_ISSET_ID, value);
  }

  public int getBe_number() {
    return this.be_number;
  }

  public TRowBatch setBe_number(int be_number) {
    this.be_number = be_number;
    setBe_numberIsSet(true);
    return this;
  }

  public void unsetBe_number() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __BE_NUMBER_ISSET_ID);
  }

  /** Returns true if field be_number is set (has been assigned a value) and false otherwise */
  public boolean isSetBe_number() {
    return EncodingUtils.testBit(__isset_bitfield, __BE_NUMBER_ISSET_ID);
  }

  public void setBe_numberIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __BE_NUMBER_ISSET_ID, value);
  }

  public long getPacket_seq() {
    return this.packet_seq;
  }

  public TRowBatch setPacket_seq(long packet_seq) {
    this.packet_seq = packet_seq;
    setPacket_seqIsSet(true);
    return this;
  }

  public void unsetPacket_seq() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __PACKET_SEQ_ISSET_ID);
  }

  /** Returns true if field packet_seq is set (has been assigned a value) and false otherwise */
  public boolean isSetPacket_seq() {
    return EncodingUtils.testBit(__isset_bitfield, __PACKET_SEQ_ISSET_ID);
  }

  public void setPacket_seqIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __PACKET_SEQ_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case NUM_ROWS:
      if (value == null) {
        unsetNum_rows();
      } else {
        setNum_rows((Integer)value);
      }
      break;

    case ROW_TUPLES:
      if (value == null) {
        unsetRow_tuples();
      } else {
        setRow_tuples((List<Integer>)value);
      }
      break;

    case TUPLE_OFFSETS:
      if (value == null) {
        unsetTuple_offsets();
      } else {
        setTuple_offsets((List<Integer>)value);
      }
      break;

    case TUPLE_DATA:
      if (value == null) {
        unsetTuple_data();
      } else {
        setTuple_data((String)value);
      }
      break;

    case IS_COMPRESSED:
      if (value == null) {
        unsetIs_compressed();
      } else {
        setIs_compressed((Boolean)value);
      }
      break;

    case BE_NUMBER:
      if (value == null) {
        unsetBe_number();
      } else {
        setBe_number((Integer)value);
      }
      break;

    case PACKET_SEQ:
      if (value == null) {
        unsetPacket_seq();
      } else {
        setPacket_seq((Long)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case NUM_ROWS:
      return getNum_rows();

    case ROW_TUPLES:
      return getRow_tuples();

    case TUPLE_OFFSETS:
      return getTuple_offsets();

    case TUPLE_DATA:
      return getTuple_data();

    case IS_COMPRESSED:
      return isIs_compressed();

    case BE_NUMBER:
      return getBe_number();

    case PACKET_SEQ:
      return getPacket_seq();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case NUM_ROWS:
      return isSetNum_rows();
    case ROW_TUPLES:
      return isSetRow_tuples();
    case TUPLE_OFFSETS:
      return isSetTuple_offsets();
    case TUPLE_DATA:
      return isSetTuple_data();
    case IS_COMPRESSED:
      return isSetIs_compressed();
    case BE_NUMBER:
      return isSetBe_number();
    case PACKET_SEQ:
      return isSetPacket_seq();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TRowBatch)
      return this.equals((TRowBatch)that);
    return false;
  }

  public boolean equals(TRowBatch that) {
    if (that == null)
      return false;

    boolean this_present_num_rows = true;
    boolean that_present_num_rows = true;
    if (this_present_num_rows || that_present_num_rows) {
      if (!(this_present_num_rows && that_present_num_rows))
        return false;
      if (this.num_rows != that.num_rows)
        return false;
    }

    boolean this_present_row_tuples = true && this.isSetRow_tuples();
    boolean that_present_row_tuples = true && that.isSetRow_tuples();
    if (this_present_row_tuples || that_present_row_tuples) {
      if (!(this_present_row_tuples && that_present_row_tuples))
        return false;
      if (!this.row_tuples.equals(that.row_tuples))
        return false;
    }

    boolean this_present_tuple_offsets = true && this.isSetTuple_offsets();
    boolean that_present_tuple_offsets = true && that.isSetTuple_offsets();
    if (this_present_tuple_offsets || that_present_tuple_offsets) {
      if (!(this_present_tuple_offsets && that_present_tuple_offsets))
        return false;
      if (!this.tuple_offsets.equals(that.tuple_offsets))
        return false;
    }

    boolean this_present_tuple_data = true && this.isSetTuple_data();
    boolean that_present_tuple_data = true && that.isSetTuple_data();
    if (this_present_tuple_data || that_present_tuple_data) {
      if (!(this_present_tuple_data && that_present_tuple_data))
        return false;
      if (!this.tuple_data.equals(that.tuple_data))
        return false;
    }

    boolean this_present_is_compressed = true;
    boolean that_present_is_compressed = true;
    if (this_present_is_compressed || that_present_is_compressed) {
      if (!(this_present_is_compressed && that_present_is_compressed))
        return false;
      if (this.is_compressed != that.is_compressed)
        return false;
    }

    boolean this_present_be_number = true;
    boolean that_present_be_number = true;
    if (this_present_be_number || that_present_be_number) {
      if (!(this_present_be_number && that_present_be_number))
        return false;
      if (this.be_number != that.be_number)
        return false;
    }

    boolean this_present_packet_seq = true;
    boolean that_present_packet_seq = true;
    if (this_present_packet_seq || that_present_packet_seq) {
      if (!(this_present_packet_seq && that_present_packet_seq))
        return false;
      if (this.packet_seq != that.packet_seq)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_num_rows = true;
    list.add(present_num_rows);
    if (present_num_rows)
      list.add(num_rows);

    boolean present_row_tuples = true && (isSetRow_tuples());
    list.add(present_row_tuples);
    if (present_row_tuples)
      list.add(row_tuples);

    boolean present_tuple_offsets = true && (isSetTuple_offsets());
    list.add(present_tuple_offsets);
    if (present_tuple_offsets)
      list.add(tuple_offsets);

    boolean present_tuple_data = true && (isSetTuple_data());
    list.add(present_tuple_data);
    if (present_tuple_data)
      list.add(tuple_data);

    boolean present_is_compressed = true;
    list.add(present_is_compressed);
    if (present_is_compressed)
      list.add(is_compressed);

    boolean present_be_number = true;
    list.add(present_be_number);
    if (present_be_number)
      list.add(be_number);

    boolean present_packet_seq = true;
    list.add(present_packet_seq);
    if (present_packet_seq)
      list.add(packet_seq);

    return list.hashCode();
  }

  @Override
  public int compareTo(TRowBatch other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetNum_rows()).compareTo(other.isSetNum_rows());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetNum_rows()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.num_rows, other.num_rows);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetRow_tuples()).compareTo(other.isSetRow_tuples());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRow_tuples()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.row_tuples, other.row_tuples);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTuple_offsets()).compareTo(other.isSetTuple_offsets());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTuple_offsets()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.tuple_offsets, other.tuple_offsets);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTuple_data()).compareTo(other.isSetTuple_data());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTuple_data()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.tuple_data, other.tuple_data);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetIs_compressed()).compareTo(other.isSetIs_compressed());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetIs_compressed()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.is_compressed, other.is_compressed);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetBe_number()).compareTo(other.isSetBe_number());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetBe_number()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.be_number, other.be_number);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetPacket_seq()).compareTo(other.isSetPacket_seq());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPacket_seq()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.packet_seq, other.packet_seq);
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
    StringBuilder sb = new StringBuilder("TRowBatch(");
    boolean first = true;

    sb.append("num_rows:");
    sb.append(this.num_rows);
    first = false;
    if (!first) sb.append(", ");
    sb.append("row_tuples:");
    if (this.row_tuples == null) {
      sb.append("null");
    } else {
      sb.append(this.row_tuples);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("tuple_offsets:");
    if (this.tuple_offsets == null) {
      sb.append("null");
    } else {
      sb.append(this.tuple_offsets);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("tuple_data:");
    if (this.tuple_data == null) {
      sb.append("null");
    } else {
      sb.append(this.tuple_data);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("is_compressed:");
    sb.append(this.is_compressed);
    first = false;
    if (!first) sb.append(", ");
    sb.append("be_number:");
    sb.append(this.be_number);
    first = false;
    if (!first) sb.append(", ");
    sb.append("packet_seq:");
    sb.append(this.packet_seq);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // alas, we cannot check 'num_rows' because it's a primitive and you chose the non-beans generator.
    if (row_tuples == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'row_tuples' was not present! Struct: " + toString());
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

  private static class TRowBatchStandardSchemeFactory implements SchemeFactory {
    public TRowBatchStandardScheme getScheme() {
      return new TRowBatchStandardScheme();
    }
  }

  private static class TRowBatchStandardScheme extends StandardScheme<TRowBatch> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TRowBatch struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // NUM_ROWS
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.num_rows = iprot.readI32();
              struct.setNum_rowsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // ROW_TUPLES
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list0 = iprot.readListBegin();
                struct.row_tuples = new ArrayList<Integer>(_list0.size);
                int _elem1;
                for (int _i2 = 0; _i2 < _list0.size; ++_i2)
                {
                  _elem1 = iprot.readI32();
                  struct.row_tuples.add(_elem1);
                }
                iprot.readListEnd();
              }
              struct.setRow_tuplesIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // TUPLE_OFFSETS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list3 = iprot.readListBegin();
                struct.tuple_offsets = new ArrayList<Integer>(_list3.size);
                int _elem4;
                for (int _i5 = 0; _i5 < _list3.size; ++_i5)
                {
                  _elem4 = iprot.readI32();
                  struct.tuple_offsets.add(_elem4);
                }
                iprot.readListEnd();
              }
              struct.setTuple_offsetsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // TUPLE_DATA
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.tuple_data = iprot.readString();
              struct.setTuple_dataIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // IS_COMPRESSED
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.is_compressed = iprot.readBool();
              struct.setIs_compressedIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // BE_NUMBER
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.be_number = iprot.readI32();
              struct.setBe_numberIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 7: // PACKET_SEQ
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.packet_seq = iprot.readI64();
              struct.setPacket_seqIsSet(true);
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
      if (!struct.isSetNum_rows()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'num_rows' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, TRowBatch struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(NUM_ROWS_FIELD_DESC);
      oprot.writeI32(struct.num_rows);
      oprot.writeFieldEnd();
      if (struct.row_tuples != null) {
        oprot.writeFieldBegin(ROW_TUPLES_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.I32, struct.row_tuples.size()));
          for (int _iter6 : struct.row_tuples)
          {
            oprot.writeI32(_iter6);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      if (struct.tuple_offsets != null) {
        oprot.writeFieldBegin(TUPLE_OFFSETS_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.I32, struct.tuple_offsets.size()));
          for (int _iter7 : struct.tuple_offsets)
          {
            oprot.writeI32(_iter7);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      if (struct.tuple_data != null) {
        oprot.writeFieldBegin(TUPLE_DATA_FIELD_DESC);
        oprot.writeString(struct.tuple_data);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(IS_COMPRESSED_FIELD_DESC);
      oprot.writeBool(struct.is_compressed);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(BE_NUMBER_FIELD_DESC);
      oprot.writeI32(struct.be_number);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(PACKET_SEQ_FIELD_DESC);
      oprot.writeI64(struct.packet_seq);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TRowBatchTupleSchemeFactory implements SchemeFactory {
    public TRowBatchTupleScheme getScheme() {
      return new TRowBatchTupleScheme();
    }
  }

  private static class TRowBatchTupleScheme extends TupleScheme<TRowBatch> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TRowBatch struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeI32(struct.num_rows);
      {
        oprot.writeI32(struct.row_tuples.size());
        for (int _iter8 : struct.row_tuples)
        {
          oprot.writeI32(_iter8);
        }
      }
      BitSet optionals = new BitSet();
      if (struct.isSetTuple_offsets()) {
        optionals.set(0);
      }
      if (struct.isSetTuple_data()) {
        optionals.set(1);
      }
      if (struct.isSetIs_compressed()) {
        optionals.set(2);
      }
      if (struct.isSetBe_number()) {
        optionals.set(3);
      }
      if (struct.isSetPacket_seq()) {
        optionals.set(4);
      }
      oprot.writeBitSet(optionals, 5);
      if (struct.isSetTuple_offsets()) {
        {
          oprot.writeI32(struct.tuple_offsets.size());
          for (int _iter9 : struct.tuple_offsets)
          {
            oprot.writeI32(_iter9);
          }
        }
      }
      if (struct.isSetTuple_data()) {
        oprot.writeString(struct.tuple_data);
      }
      if (struct.isSetIs_compressed()) {
        oprot.writeBool(struct.is_compressed);
      }
      if (struct.isSetBe_number()) {
        oprot.writeI32(struct.be_number);
      }
      if (struct.isSetPacket_seq()) {
        oprot.writeI64(struct.packet_seq);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TRowBatch struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.num_rows = iprot.readI32();
      struct.setNum_rowsIsSet(true);
      {
        org.apache.thrift.protocol.TList _list10 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.I32, iprot.readI32());
        struct.row_tuples = new ArrayList<Integer>(_list10.size);
        int _elem11;
        for (int _i12 = 0; _i12 < _list10.size; ++_i12)
        {
          _elem11 = iprot.readI32();
          struct.row_tuples.add(_elem11);
        }
      }
      struct.setRow_tuplesIsSet(true);
      BitSet incoming = iprot.readBitSet(5);
      if (incoming.get(0)) {
        {
          org.apache.thrift.protocol.TList _list13 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.I32, iprot.readI32());
          struct.tuple_offsets = new ArrayList<Integer>(_list13.size);
          int _elem14;
          for (int _i15 = 0; _i15 < _list13.size; ++_i15)
          {
            _elem14 = iprot.readI32();
            struct.tuple_offsets.add(_elem14);
          }
        }
        struct.setTuple_offsetsIsSet(true);
      }
      if (incoming.get(1)) {
        struct.tuple_data = iprot.readString();
        struct.setTuple_dataIsSet(true);
      }
      if (incoming.get(2)) {
        struct.is_compressed = iprot.readBool();
        struct.setIs_compressedIsSet(true);
      }
      if (incoming.get(3)) {
        struct.be_number = iprot.readI32();
        struct.setBe_numberIsSet(true);
      }
      if (incoming.get(4)) {
        struct.packet_seq = iprot.readI64();
        struct.setPacket_seqIsSet(true);
      }
    }
  }

}

