//  Modifications copyright (C) 2017, Baidu.com, Inc. 
//  Copyright 2017 The Apache Software Foundation 
// 
// Licensed to the Apache Software Foundation (ASF) under one 
// or more contributor license agreements.  See the NOTICE file 
// distributed with this work for additional information 
// regarding copyright ownership.  The ASF licenses this file 
// to you under the Apache License, Version 2.0 (the 
// "License"); you may not use this file except in compliance 
// with the License.  You may obtain a copy of the License at 
// 
//  http://www.apache.org/licenses/LICENSE-2.0
// 
//  Unless required by applicable law or agreed to in writing, software
//  distributed under the License is distributed on an "AS IS" BASIS,
//  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//  See the License for the specific language governing permissions and
//  limitations under the License.

// This is a generated file, DO NOT EDIT.
// To add new functions, see the generator at
// common/function-registry/gen_builtins_catalog.py or the function list at
// common/function-registry/palo_builtins_functions.py.

package com.baidu.palo.builtins;

import com.baidu.palo.catalog.PrimitiveType;
import com.baidu.palo.catalog.FunctionSet;

public class ScalarBuiltins { 
    public static void initBuiltins(FunctionSet functionSet) { 
    functionSet.addScalarBuiltin("bitand", "_ZN4palo9Operators32bitand_tiny_int_val_tiny_int_valEPN8palo_udf15FunctionContextERKNS1_10TinyIntValES6_", true, false, PrimitiveType.TINYINT, PrimitiveType.TINYINT, PrimitiveType.TINYINT);
    functionSet.addScalarBuiltin("bitand", "_ZN4palo9Operators34bitand_small_int_val_small_int_valEPN8palo_udf15FunctionContextERKNS1_11SmallIntValES6_", true, false, PrimitiveType.SMALLINT, PrimitiveType.SMALLINT, PrimitiveType.SMALLINT);
    functionSet.addScalarBuiltin("bitand", "_ZN4palo9Operators22bitand_int_val_int_valEPN8palo_udf15FunctionContextERKNS1_6IntValES6_", true, false, PrimitiveType.INT, PrimitiveType.INT, PrimitiveType.INT);
    functionSet.addScalarBuiltin("bitand", "_ZN4palo9Operators30bitand_big_int_val_big_int_valEPN8palo_udf15FunctionContextERKNS1_9BigIntValES6_", true, false, PrimitiveType.BIGINT, PrimitiveType.BIGINT, PrimitiveType.BIGINT);
    functionSet.addScalarBuiltin("bitand", "_ZN4palo9Operators34bitand_large_int_val_large_int_valEPN8palo_udf15FunctionContextERKNS1_11LargeIntValES6_", true, false, PrimitiveType.LARGEINT, PrimitiveType.LARGEINT, PrimitiveType.LARGEINT);
    functionSet.addScalarBuiltin("bitor", "_ZN4palo9Operators31bitor_tiny_int_val_tiny_int_valEPN8palo_udf15FunctionContextERKNS1_10TinyIntValES6_", true, false, PrimitiveType.TINYINT, PrimitiveType.TINYINT, PrimitiveType.TINYINT);
    functionSet.addScalarBuiltin("bitor", "_ZN4palo9Operators33bitor_small_int_val_small_int_valEPN8palo_udf15FunctionContextERKNS1_11SmallIntValES6_", true, false, PrimitiveType.SMALLINT, PrimitiveType.SMALLINT, PrimitiveType.SMALLINT);
    functionSet.addScalarBuiltin("bitor", "_ZN4palo9Operators21bitor_int_val_int_valEPN8palo_udf15FunctionContextERKNS1_6IntValES6_", true, false, PrimitiveType.INT, PrimitiveType.INT, PrimitiveType.INT);
    functionSet.addScalarBuiltin("bitor", "_ZN4palo9Operators29bitor_big_int_val_big_int_valEPN8palo_udf15FunctionContextERKNS1_9BigIntValES6_", true, false, PrimitiveType.BIGINT, PrimitiveType.BIGINT, PrimitiveType.BIGINT);
    functionSet.addScalarBuiltin("bitor", "_ZN4palo9Operators33bitor_large_int_val_large_int_valEPN8palo_udf15FunctionContextERKNS1_11LargeIntValES6_", true, false, PrimitiveType.LARGEINT, PrimitiveType.LARGEINT, PrimitiveType.LARGEINT);
    functionSet.addScalarBuiltin("bitxor", "_ZN4palo9Operators32bitxor_tiny_int_val_tiny_int_valEPN8palo_udf15FunctionContextERKNS1_10TinyIntValES6_", true, false, PrimitiveType.TINYINT, PrimitiveType.TINYINT, PrimitiveType.TINYINT);
    functionSet.addScalarBuiltin("bitxor", "_ZN4palo9Operators34bitxor_small_int_val_small_int_valEPN8palo_udf15FunctionContextERKNS1_11SmallIntValES6_", true, false, PrimitiveType.SMALLINT, PrimitiveType.SMALLINT, PrimitiveType.SMALLINT);
    functionSet.addScalarBuiltin("bitxor", "_ZN4palo9Operators22bitxor_int_val_int_valEPN8palo_udf15FunctionContextERKNS1_6IntValES6_", true, false, PrimitiveType.INT, PrimitiveType.INT, PrimitiveType.INT);
    functionSet.addScalarBuiltin("bitxor", "_ZN4palo9Operators30bitxor_big_int_val_big_int_valEPN8palo_udf15FunctionContextERKNS1_9BigIntValES6_", true, false, PrimitiveType.BIGINT, PrimitiveType.BIGINT, PrimitiveType.BIGINT);
    functionSet.addScalarBuiltin("bitxor", "_ZN4palo9Operators34bitxor_large_int_val_large_int_valEPN8palo_udf15FunctionContextERKNS1_11LargeIntValES6_", true, false, PrimitiveType.LARGEINT, PrimitiveType.LARGEINT, PrimitiveType.LARGEINT);
    functionSet.addScalarBuiltin("bitnot", "_ZN4palo9Operators19bitnot_tiny_int_valEPN8palo_udf15FunctionContextERKNS1_10TinyIntValE", true, false, PrimitiveType.TINYINT, PrimitiveType.TINYINT);
    functionSet.addScalarBuiltin("bitnot", "_ZN4palo9Operators20bitnot_small_int_valEPN8palo_udf15FunctionContextERKNS1_11SmallIntValE", true, false, PrimitiveType.SMALLINT, PrimitiveType.SMALLINT);
    functionSet.addScalarBuiltin("bitnot", "_ZN4palo9Operators14bitnot_int_valEPN8palo_udf15FunctionContextERKNS1_6IntValE", true, false, PrimitiveType.INT, PrimitiveType.INT);
    functionSet.addScalarBuiltin("bitnot", "_ZN4palo9Operators18bitnot_big_int_valEPN8palo_udf15FunctionContextERKNS1_9BigIntValE", true, false, PrimitiveType.BIGINT, PrimitiveType.BIGINT);
    functionSet.addScalarBuiltin("bitnot", "_ZN4palo9Operators20bitnot_large_int_valEPN8palo_udf15FunctionContextERKNS1_11LargeIntValE", true, false, PrimitiveType.LARGEINT, PrimitiveType.LARGEINT);
    functionSet.addScalarBuiltin("unix_timestamp", "_ZN4palo18TimestampFunctions7to_unixEPN8palo_udf15FunctionContextE", true, false, PrimitiveType.INT);
    functionSet.addScalarBuiltin("unix_timestamp", "_ZN4palo18TimestampFunctions7to_unixEPN8palo_udf15FunctionContextERKNS1_11DateTimeValE", true, false, PrimitiveType.INT, PrimitiveType.DATETIME);
    functionSet.addScalarBuiltin("unix_timestamp", "_ZN4palo18TimestampFunctions7to_unixEPN8palo_udf15FunctionContextERKNS1_9StringValES6_", true, false, PrimitiveType.INT, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR);
    functionSet.addScalarBuiltin("from_unixtime", "_ZN4palo18TimestampFunctions9from_unixEPN8palo_udf15FunctionContextERKNS1_6IntValE", true, false, PrimitiveType.VARCHAR, PrimitiveType.INT);
    functionSet.addScalarBuiltin("from_unixtime", "_ZN4palo18TimestampFunctions9from_unixEPN8palo_udf15FunctionContextERKNS1_6IntValERKNS1_9StringValE", true, false, PrimitiveType.VARCHAR, PrimitiveType.INT, PrimitiveType.VARCHAR);
    functionSet.addScalarBuiltin("now", "_ZN4palo18TimestampFunctions3nowEPN8palo_udf15FunctionContextE", true, false, PrimitiveType.DATETIME);
    functionSet.addScalarBuiltin("current_timestamp", "_ZN4palo18TimestampFunctions3nowEPN8palo_udf15FunctionContextE", true, false, PrimitiveType.DATETIME);
    functionSet.addScalarBuiltin("curtime", "_ZN4palo18TimestampFunctions7curtimeEPN8palo_udf15FunctionContextE", true, false, PrimitiveType.DATETIME);
    functionSet.addScalarBuiltin("current_time", "_ZN4palo18TimestampFunctions7curtimeEPN8palo_udf15FunctionContextE", true, false, PrimitiveType.DATETIME);
    functionSet.addScalarBuiltin("timestamp", "_ZN4palo18TimestampFunctions9timestampEPN8palo_udf15FunctionContextERKNS1_11DateTimeValE", true, false, PrimitiveType.DATETIME, PrimitiveType.DATETIME);
    functionSet.addScalarBuiltin("from_days", "_ZN4palo18TimestampFunctions9from_daysEPN8palo_udf15FunctionContextERKNS1_6IntValE", true, false, PrimitiveType.DATE, PrimitiveType.INT);
    functionSet.addScalarBuiltin("to_days", "_ZN4palo18TimestampFunctions7to_daysEPN8palo_udf15FunctionContextERKNS1_11DateTimeValE", true, false, PrimitiveType.INT, PrimitiveType.DATE);
    functionSet.addScalarBuiltin("year", "_ZN4palo18TimestampFunctions4yearEPN8palo_udf15FunctionContextERKNS1_11DateTimeValE", true, false, PrimitiveType.INT, PrimitiveType.DATETIME);
    functionSet.addScalarBuiltin("month", "_ZN4palo18TimestampFunctions5monthEPN8palo_udf15FunctionContextERKNS1_11DateTimeValE", true, false, PrimitiveType.INT, PrimitiveType.DATETIME);
    functionSet.addScalarBuiltin("quarter", "_ZN4palo18TimestampFunctions7quarterEPN8palo_udf15FunctionContextERKNS1_11DateTimeValE", true, false, PrimitiveType.INT, PrimitiveType.DATETIME);
    functionSet.addScalarBuiltin("day", "_ZN4palo18TimestampFunctions12day_of_monthEPN8palo_udf15FunctionContextERKNS1_11DateTimeValE", true, false, PrimitiveType.INT, PrimitiveType.DATETIME);
    functionSet.addScalarBuiltin("dayofmonth", "_ZN4palo18TimestampFunctions12day_of_monthEPN8palo_udf15FunctionContextERKNS1_11DateTimeValE", true, false, PrimitiveType.INT, PrimitiveType.DATETIME);
    functionSet.addScalarBuiltin("dayofyear", "_ZN4palo18TimestampFunctions11day_of_yearEPN8palo_udf15FunctionContextERKNS1_11DateTimeValE", true, false, PrimitiveType.INT, PrimitiveType.DATETIME);
    functionSet.addScalarBuiltin("weekofyear", "_ZN4palo18TimestampFunctions12week_of_yearEPN8palo_udf15FunctionContextERKNS1_11DateTimeValE", true, false, PrimitiveType.INT, PrimitiveType.DATETIME);
    functionSet.addScalarBuiltin("hour", "_ZN4palo18TimestampFunctions4hourEPN8palo_udf15FunctionContextERKNS1_11DateTimeValE", true, false, PrimitiveType.INT, PrimitiveType.DATETIME);
    functionSet.addScalarBuiltin("minute", "_ZN4palo18TimestampFunctions6minuteEPN8palo_udf15FunctionContextERKNS1_11DateTimeValE", true, false, PrimitiveType.INT, PrimitiveType.DATETIME);
    functionSet.addScalarBuiltin("second", "_ZN4palo18TimestampFunctions6secondEPN8palo_udf15FunctionContextERKNS1_11DateTimeValE", true, false, PrimitiveType.INT, PrimitiveType.DATETIME);
    functionSet.addScalarBuiltin("years_add", "_ZN4palo18TimestampFunctions9years_addEPN8palo_udf15FunctionContextERKNS1_11DateTimeValERKNS1_6IntValE", true, false, PrimitiveType.DATETIME, PrimitiveType.DATETIME, PrimitiveType.INT);
    functionSet.addScalarBuiltin("years_sub", "_ZN4palo18TimestampFunctions9years_subEPN8palo_udf15FunctionContextERKNS1_11DateTimeValERKNS1_6IntValE", true, false, PrimitiveType.DATETIME, PrimitiveType.DATETIME, PrimitiveType.INT);
    functionSet.addScalarBuiltin("months_add", "_ZN4palo18TimestampFunctions10months_addEPN8palo_udf15FunctionContextERKNS1_11DateTimeValERKNS1_6IntValE", true, false, PrimitiveType.DATETIME, PrimitiveType.DATETIME, PrimitiveType.INT);
    functionSet.addScalarBuiltin("add_months", "_ZN4palo18TimestampFunctions10months_addEPN8palo_udf15FunctionContextERKNS1_11DateTimeValERKNS1_6IntValE", true, false, PrimitiveType.DATETIME, PrimitiveType.DATETIME, PrimitiveType.INT);
    functionSet.addScalarBuiltin("months_sub", "_ZN4palo18TimestampFunctions10months_subEPN8palo_udf15FunctionContextERKNS1_11DateTimeValERKNS1_6IntValE", true, false, PrimitiveType.DATETIME, PrimitiveType.DATETIME, PrimitiveType.INT);
    functionSet.addScalarBuiltin("weeks_add", "_ZN4palo18TimestampFunctions9weeks_addEPN8palo_udf15FunctionContextERKNS1_11DateTimeValERKNS1_6IntValE", true, false, PrimitiveType.DATETIME, PrimitiveType.DATETIME, PrimitiveType.INT);
    functionSet.addScalarBuiltin("weeks_sub", "_ZN4palo18TimestampFunctions9weeks_subEPN8palo_udf15FunctionContextERKNS1_11DateTimeValERKNS1_6IntValE", true, false, PrimitiveType.DATETIME, PrimitiveType.DATETIME, PrimitiveType.INT);
    functionSet.addScalarBuiltin("days_add", "_ZN4palo18TimestampFunctions8days_addEPN8palo_udf15FunctionContextERKNS1_11DateTimeValERKNS1_6IntValE", true, false, PrimitiveType.DATETIME, PrimitiveType.DATETIME, PrimitiveType.INT);
    functionSet.addScalarBuiltin("date_add", "_ZN4palo18TimestampFunctions8days_addEPN8palo_udf15FunctionContextERKNS1_11DateTimeValERKNS1_6IntValE", true, false, PrimitiveType.DATETIME, PrimitiveType.DATETIME, PrimitiveType.INT);
    functionSet.addScalarBuiltin("adddate", "_ZN4palo18TimestampFunctions8days_addEPN8palo_udf15FunctionContextERKNS1_11DateTimeValERKNS1_6IntValE", true, false, PrimitiveType.DATETIME, PrimitiveType.DATETIME, PrimitiveType.INT);
    functionSet.addScalarBuiltin("days_sub", "_ZN4palo18TimestampFunctions8days_subEPN8palo_udf15FunctionContextERKNS1_11DateTimeValERKNS1_6IntValE", true, false, PrimitiveType.DATETIME, PrimitiveType.DATETIME, PrimitiveType.INT);
    functionSet.addScalarBuiltin("date_sub", "_ZN4palo18TimestampFunctions8days_subEPN8palo_udf15FunctionContextERKNS1_11DateTimeValERKNS1_6IntValE", true, false, PrimitiveType.DATETIME, PrimitiveType.DATETIME, PrimitiveType.INT);
    functionSet.addScalarBuiltin("subdate", "_ZN4palo18TimestampFunctions8days_subEPN8palo_udf15FunctionContextERKNS1_11DateTimeValERKNS1_6IntValE", true, false, PrimitiveType.DATETIME, PrimitiveType.DATETIME, PrimitiveType.INT);
    functionSet.addScalarBuiltin("hours_add", "_ZN4palo18TimestampFunctions9hours_addEPN8palo_udf15FunctionContextERKNS1_11DateTimeValERKNS1_6IntValE", true, false, PrimitiveType.DATETIME, PrimitiveType.DATETIME, PrimitiveType.INT);
    functionSet.addScalarBuiltin("hours_sub", "_ZN4palo18TimestampFunctions9hours_subEPN8palo_udf15FunctionContextERKNS1_11DateTimeValERKNS1_6IntValE", true, false, PrimitiveType.DATETIME, PrimitiveType.DATETIME, PrimitiveType.INT);
    functionSet.addScalarBuiltin("minutes_add", "_ZN4palo18TimestampFunctions11minutes_addEPN8palo_udf15FunctionContextERKNS1_11DateTimeValERKNS1_6IntValE", true, false, PrimitiveType.DATETIME, PrimitiveType.DATETIME, PrimitiveType.INT);
    functionSet.addScalarBuiltin("minutes_sub", "_ZN4palo18TimestampFunctions11minutes_subEPN8palo_udf15FunctionContextERKNS1_11DateTimeValERKNS1_6IntValE", true, false, PrimitiveType.DATETIME, PrimitiveType.DATETIME, PrimitiveType.INT);
    functionSet.addScalarBuiltin("seconds_add", "_ZN4palo18TimestampFunctions11seconds_addEPN8palo_udf15FunctionContextERKNS1_11DateTimeValERKNS1_6IntValE", true, false, PrimitiveType.DATETIME, PrimitiveType.DATETIME, PrimitiveType.INT);
    functionSet.addScalarBuiltin("seconds_sub", "_ZN4palo18TimestampFunctions11seconds_subEPN8palo_udf15FunctionContextERKNS1_11DateTimeValERKNS1_6IntValE", true, false, PrimitiveType.DATETIME, PrimitiveType.DATETIME, PrimitiveType.INT);
    functionSet.addScalarBuiltin("microseconds_add", "_ZN4palo18TimestampFunctions10micros_addEPN8palo_udf15FunctionContextERKNS1_11DateTimeValERKNS1_6IntValE", true, false, PrimitiveType.DATETIME, PrimitiveType.DATETIME, PrimitiveType.INT);
    functionSet.addScalarBuiltin("microseconds_sub", "_ZN4palo18TimestampFunctions10micros_subEPN8palo_udf15FunctionContextERKNS1_11DateTimeValERKNS1_6IntValE", true, false, PrimitiveType.DATETIME, PrimitiveType.DATETIME, PrimitiveType.INT);
    functionSet.addScalarBuiltin("datediff", "_ZN4palo18TimestampFunctions9date_diffEPN8palo_udf15FunctionContextERKNS1_11DateTimeValES6_", true, false, PrimitiveType.INT, PrimitiveType.DATETIME, PrimitiveType.DATETIME);
    functionSet.addScalarBuiltin("timediff", "_ZN4palo18TimestampFunctions9time_diffEPN8palo_udf15FunctionContextERKNS1_11DateTimeValES6_", true, false, PrimitiveType.DATETIME, PrimitiveType.DATETIME, PrimitiveType.DATETIME);
    functionSet.addScalarBuiltin("str_to_date", "_ZN4palo18TimestampFunctions11str_to_dateEPN8palo_udf15FunctionContextERKNS1_9StringValES6_", true, false, PrimitiveType.DATETIME, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR);
    functionSet.addScalarBuiltin("date_format", "_ZN4palo18TimestampFunctions11date_formatEPN8palo_udf15FunctionContextERKNS1_11DateTimeValERKNS1_9StringValE", true, false, PrimitiveType.VARCHAR, PrimitiveType.DATETIME, PrimitiveType.VARCHAR);
    functionSet.addScalarBuiltin("date", "_ZN4palo18TimestampFunctions7to_dateEPN8palo_udf15FunctionContextERKNS1_11DateTimeValE", true, false, PrimitiveType.DATE, PrimitiveType.DATETIME);
    functionSet.addScalarBuiltin("to_date", "_ZN4palo18TimestampFunctions7to_dateEPN8palo_udf15FunctionContextERKNS1_11DateTimeValE", true, false, PrimitiveType.DATE, PrimitiveType.DATETIME);
    functionSet.addScalarBuiltin("dayname", "_ZN4palo18TimestampFunctions8day_nameEPN8palo_udf15FunctionContextERKNS1_11DateTimeValE", true, false, PrimitiveType.VARCHAR, PrimitiveType.DATETIME);
    functionSet.addScalarBuiltin("monthname", "_ZN4palo18TimestampFunctions10month_nameEPN8palo_udf15FunctionContextERKNS1_11DateTimeValE", true, false, PrimitiveType.VARCHAR, PrimitiveType.DATETIME);
    functionSet.addScalarBuiltin("pi", "_ZN4palo13MathFunctions2piEPN8palo_udf15FunctionContextE", true, false, PrimitiveType.DOUBLE);
    functionSet.addScalarBuiltin("e", "_ZN4palo13MathFunctions1eEPN8palo_udf15FunctionContextE", true, false, PrimitiveType.DOUBLE);
    functionSet.addScalarBuiltin("abs", "_ZN4palo13MathFunctions3absEPN8palo_udf15FunctionContextERKNS1_9DoubleValE", true, false, PrimitiveType.DOUBLE, PrimitiveType.DOUBLE);
    functionSet.addScalarBuiltin("sign", "_ZN4palo13MathFunctions4signEPN8palo_udf15FunctionContextERKNS1_9DoubleValE", true, false, PrimitiveType.FLOAT, PrimitiveType.DOUBLE);
    functionSet.addScalarBuiltin("sin", "_ZN4palo13MathFunctions3sinEPN8palo_udf15FunctionContextERKNS1_9DoubleValE", true, false, PrimitiveType.DOUBLE, PrimitiveType.DOUBLE);
    functionSet.addScalarBuiltin("asin", "_ZN4palo13MathFunctions4asinEPN8palo_udf15FunctionContextERKNS1_9DoubleValE", true, false, PrimitiveType.DOUBLE, PrimitiveType.DOUBLE);
    functionSet.addScalarBuiltin("cos", "_ZN4palo13MathFunctions3cosEPN8palo_udf15FunctionContextERKNS1_9DoubleValE", true, false, PrimitiveType.DOUBLE, PrimitiveType.DOUBLE);
    functionSet.addScalarBuiltin("acos", "_ZN4palo13MathFunctions4acosEPN8palo_udf15FunctionContextERKNS1_9DoubleValE", true, false, PrimitiveType.DOUBLE, PrimitiveType.DOUBLE);
    functionSet.addScalarBuiltin("tan", "_ZN4palo13MathFunctions3tanEPN8palo_udf15FunctionContextERKNS1_9DoubleValE", true, false, PrimitiveType.DOUBLE, PrimitiveType.DOUBLE);
    functionSet.addScalarBuiltin("atan", "_ZN4palo13MathFunctions4atanEPN8palo_udf15FunctionContextERKNS1_9DoubleValE", true, false, PrimitiveType.DOUBLE, PrimitiveType.DOUBLE);
    functionSet.addScalarBuiltin("ceil", "_ZN4palo13MathFunctions4ceilEPN8palo_udf15FunctionContextERKNS1_9DoubleValE", true, false, PrimitiveType.BIGINT, PrimitiveType.DOUBLE);
    functionSet.addScalarBuiltin("ceiling", "_ZN4palo13MathFunctions4ceilEPN8palo_udf15FunctionContextERKNS1_9DoubleValE", true, false, PrimitiveType.BIGINT, PrimitiveType.DOUBLE);
    functionSet.addScalarBuiltin("dceil", "_ZN4palo13MathFunctions4ceilEPN8palo_udf15FunctionContextERKNS1_9DoubleValE", true, false, PrimitiveType.BIGINT, PrimitiveType.DOUBLE);
    functionSet.addScalarBuiltin("floor", "_ZN4palo13MathFunctions5floorEPN8palo_udf15FunctionContextERKNS1_9DoubleValE", true, false, PrimitiveType.BIGINT, PrimitiveType.DOUBLE);
    functionSet.addScalarBuiltin("dfloor", "_ZN4palo13MathFunctions5floorEPN8palo_udf15FunctionContextERKNS1_9DoubleValE", true, false, PrimitiveType.BIGINT, PrimitiveType.DOUBLE);
    functionSet.addScalarBuiltin("round", "_ZN4palo13MathFunctions5roundEPN8palo_udf15FunctionContextERKNS1_9DoubleValE", true, false, PrimitiveType.BIGINT, PrimitiveType.DOUBLE);
    functionSet.addScalarBuiltin("dround", "_ZN4palo13MathFunctions5roundEPN8palo_udf15FunctionContextERKNS1_9DoubleValE", true, false, PrimitiveType.BIGINT, PrimitiveType.DOUBLE);
    functionSet.addScalarBuiltin("round", "_ZN4palo13MathFunctions11round_up_toEPN8palo_udf15FunctionContextERKNS1_9DoubleValERKNS1_6IntValE", true, false, PrimitiveType.DOUBLE, PrimitiveType.DOUBLE, PrimitiveType.INT);
    functionSet.addScalarBuiltin("dround", "_ZN4palo13MathFunctions11round_up_toEPN8palo_udf15FunctionContextERKNS1_9DoubleValERKNS1_6IntValE", true, false, PrimitiveType.DOUBLE, PrimitiveType.DOUBLE, PrimitiveType.INT);
    functionSet.addScalarBuiltin("truncate", "_ZN4palo13MathFunctions8truncateEPN8palo_udf15FunctionContextERKNS1_9DoubleValERKNS1_6IntValE", true, false, PrimitiveType.DOUBLE, PrimitiveType.DOUBLE, PrimitiveType.INT);
    functionSet.addScalarBuiltin("ln", "_ZN4palo13MathFunctions2lnEPN8palo_udf15FunctionContextERKNS1_9DoubleValE", true, false, PrimitiveType.DOUBLE, PrimitiveType.DOUBLE);
    functionSet.addScalarBuiltin("dlog1", "_ZN4palo13MathFunctions2lnEPN8palo_udf15FunctionContextERKNS1_9DoubleValE", true, false, PrimitiveType.DOUBLE, PrimitiveType.DOUBLE);
    functionSet.addScalarBuiltin("log", "_ZN4palo13MathFunctions3logEPN8palo_udf15FunctionContextERKNS1_9DoubleValES6_", true, false, PrimitiveType.DOUBLE, PrimitiveType.DOUBLE, PrimitiveType.DOUBLE);
    functionSet.addScalarBuiltin("log2", "_ZN4palo13MathFunctions4log2EPN8palo_udf15FunctionContextERKNS1_9DoubleValE", true, false, PrimitiveType.DOUBLE, PrimitiveType.DOUBLE);
    functionSet.addScalarBuiltin("log10", "_ZN4palo13MathFunctions5log10EPN8palo_udf15FunctionContextERKNS1_9DoubleValE", true, false, PrimitiveType.DOUBLE, PrimitiveType.DOUBLE);
    functionSet.addScalarBuiltin("dlog10", "_ZN4palo13MathFunctions5log10EPN8palo_udf15FunctionContextERKNS1_9DoubleValE", true, false, PrimitiveType.DOUBLE, PrimitiveType.DOUBLE);
    functionSet.addScalarBuiltin("exp", "_ZN4palo13MathFunctions3expEPN8palo_udf15FunctionContextERKNS1_9DoubleValE", true, false, PrimitiveType.DOUBLE, PrimitiveType.DOUBLE);
    functionSet.addScalarBuiltin("dexp", "_ZN4palo13MathFunctions3expEPN8palo_udf15FunctionContextERKNS1_9DoubleValE", true, false, PrimitiveType.DOUBLE, PrimitiveType.DOUBLE);
    functionSet.addScalarBuiltin("radians", "_ZN4palo13MathFunctions7radiansEPN8palo_udf15FunctionContextERKNS1_9DoubleValE", true, false, PrimitiveType.DOUBLE, PrimitiveType.DOUBLE);
    functionSet.addScalarBuiltin("degrees", "_ZN4palo13MathFunctions7degreesEPN8palo_udf15FunctionContextERKNS1_9DoubleValE", true, false, PrimitiveType.DOUBLE, PrimitiveType.DOUBLE);
    functionSet.addScalarBuiltin("sqrt", "_ZN4palo13MathFunctions4sqrtEPN8palo_udf15FunctionContextERKNS1_9DoubleValE", true, false, PrimitiveType.DOUBLE, PrimitiveType.DOUBLE);
    functionSet.addScalarBuiltin("dsqrt", "_ZN4palo13MathFunctions4sqrtEPN8palo_udf15FunctionContextERKNS1_9DoubleValE", true, false, PrimitiveType.DOUBLE, PrimitiveType.DOUBLE);
    functionSet.addScalarBuiltin("pow", "_ZN4palo13MathFunctions3powEPN8palo_udf15FunctionContextERKNS1_9DoubleValES6_", true, false, PrimitiveType.DOUBLE, PrimitiveType.DOUBLE, PrimitiveType.DOUBLE);
    functionSet.addScalarBuiltin("power", "_ZN4palo13MathFunctions3powEPN8palo_udf15FunctionContextERKNS1_9DoubleValES6_", true, false, PrimitiveType.DOUBLE, PrimitiveType.DOUBLE, PrimitiveType.DOUBLE);
    functionSet.addScalarBuiltin("dpow", "_ZN4palo13MathFunctions3powEPN8palo_udf15FunctionContextERKNS1_9DoubleValES6_", true, false, PrimitiveType.DOUBLE, PrimitiveType.DOUBLE, PrimitiveType.DOUBLE);
    functionSet.addScalarBuiltin("fpow", "_ZN4palo13MathFunctions3powEPN8palo_udf15FunctionContextERKNS1_9DoubleValES6_", true, false, PrimitiveType.DOUBLE, PrimitiveType.DOUBLE, PrimitiveType.DOUBLE);
    functionSet.addScalarBuiltin("rand", "_ZN4palo13MathFunctions4randEPN8palo_udf15FunctionContextE", true, "_ZN4palo13MathFunctions12rand_prepareEPN8palo_udf15FunctionContextENS2_18FunctionStateScopeE", null, false, PrimitiveType.DOUBLE);
    functionSet.addScalarBuiltin("random", "_ZN4palo13MathFunctions4randEPN8palo_udf15FunctionContextE", true, "_ZN4palo13MathFunctions12rand_prepareEPN8palo_udf15FunctionContextENS2_18FunctionStateScopeE", null, false, PrimitiveType.DOUBLE);
    functionSet.addScalarBuiltin("rand", "_ZN4palo13MathFunctions9rand_seedEPN8palo_udf15FunctionContextERKNS1_9BigIntValE", true, "_ZN4palo13MathFunctions12rand_prepareEPN8palo_udf15FunctionContextENS2_18FunctionStateScopeE", null, false, PrimitiveType.DOUBLE, PrimitiveType.BIGINT);
    functionSet.addScalarBuiltin("random", "_ZN4palo13MathFunctions9rand_seedEPN8palo_udf15FunctionContextERKNS1_9BigIntValE", true, "_ZN4palo13MathFunctions12rand_prepareEPN8palo_udf15FunctionContextENS2_18FunctionStateScopeE", null, false, PrimitiveType.DOUBLE, PrimitiveType.BIGINT);
    functionSet.addScalarBuiltin("bin", "_ZN4palo13MathFunctions3binEPN8palo_udf15FunctionContextERKNS1_9BigIntValE", true, false, PrimitiveType.VARCHAR, PrimitiveType.BIGINT);
    functionSet.addScalarBuiltin("hex", "_ZN4palo13MathFunctions7hex_intEPN8palo_udf15FunctionContextERKNS1_9BigIntValE", true, false, PrimitiveType.VARCHAR, PrimitiveType.BIGINT);
    functionSet.addScalarBuiltin("hex", "_ZN4palo13MathFunctions10hex_stringEPN8palo_udf15FunctionContextERKNS1_9StringValE", true, false, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR);
    functionSet.addScalarBuiltin("unhex", "_ZN4palo13MathFunctions5unhexEPN8palo_udf15FunctionContextERKNS1_9StringValE", true, false, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR);
    functionSet.addScalarBuiltin("conv", "_ZN4palo13MathFunctions8conv_intEPN8palo_udf15FunctionContextERKNS1_9BigIntValERKNS1_10TinyIntValES9_", true, false, PrimitiveType.VARCHAR, PrimitiveType.BIGINT, PrimitiveType.TINYINT, PrimitiveType.TINYINT);
    functionSet.addScalarBuiltin("conv", "_ZN4palo13MathFunctions11conv_stringEPN8palo_udf15FunctionContextERKNS1_9StringValERKNS1_10TinyIntValES9_", true, false, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR, PrimitiveType.TINYINT, PrimitiveType.TINYINT);
    functionSet.addScalarBuiltin("pmod", "_ZN4palo13MathFunctions11pmod_bigintEPN8palo_udf15FunctionContextERKNS1_9BigIntValES6_", true, false, PrimitiveType.BIGINT, PrimitiveType.BIGINT, PrimitiveType.BIGINT);
    functionSet.addScalarBuiltin("pmod", "_ZN4palo13MathFunctions11pmod_doubleEPN8palo_udf15FunctionContextERKNS1_9DoubleValES6_", true, false, PrimitiveType.DOUBLE, PrimitiveType.DOUBLE, PrimitiveType.DOUBLE);
    functionSet.addScalarBuiltin("mod", "_ZN4palo9Operators29mod_tiny_int_val_tiny_int_valEPN8palo_udf15FunctionContextERKNS1_10TinyIntValES6_", true, false, PrimitiveType.TINYINT, PrimitiveType.TINYINT, PrimitiveType.TINYINT);
    functionSet.addScalarBuiltin("mod", "_ZN4palo9Operators31mod_small_int_val_small_int_valEPN8palo_udf15FunctionContextERKNS1_11SmallIntValES6_", true, false, PrimitiveType.SMALLINT, PrimitiveType.SMALLINT, PrimitiveType.SMALLINT);
    functionSet.addScalarBuiltin("mod", "_ZN4palo9Operators19mod_int_val_int_valEPN8palo_udf15FunctionContextERKNS1_6IntValES6_", true, false, PrimitiveType.INT, PrimitiveType.INT, PrimitiveType.INT);
    functionSet.addScalarBuiltin("mod", "_ZN4palo9Operators27mod_big_int_val_big_int_valEPN8palo_udf15FunctionContextERKNS1_9BigIntValES6_", true, false, PrimitiveType.BIGINT, PrimitiveType.BIGINT, PrimitiveType.BIGINT);
    functionSet.addScalarBuiltin("mod", "_ZN4palo9Operators31mod_large_int_val_large_int_valEPN8palo_udf15FunctionContextERKNS1_11LargeIntValES6_", true, false, PrimitiveType.LARGEINT, PrimitiveType.LARGEINT, PrimitiveType.LARGEINT);
    functionSet.addScalarBuiltin("mod", "_ZN4palo16DecimalOperators27mod_decimal_val_decimal_valEPN8palo_udf15FunctionContextERKNS1_10DecimalValES6_", true, false, PrimitiveType.DECIMAL, PrimitiveType.DECIMAL, PrimitiveType.DECIMAL);
    functionSet.addScalarBuiltin("mod", "_ZN4palo13MathFunctions10fmod_floatEPN8palo_udf15FunctionContextERKNS1_8FloatValES6_", true, false, PrimitiveType.FLOAT, PrimitiveType.FLOAT, PrimitiveType.FLOAT);
    functionSet.addScalarBuiltin("fmod", "_ZN4palo13MathFunctions10fmod_floatEPN8palo_udf15FunctionContextERKNS1_8FloatValES6_", true, false, PrimitiveType.FLOAT, PrimitiveType.FLOAT, PrimitiveType.FLOAT);
    functionSet.addScalarBuiltin("mod", "_ZN4palo13MathFunctions11fmod_doubleEPN8palo_udf15FunctionContextERKNS1_9DoubleValES6_", true, false, PrimitiveType.DOUBLE, PrimitiveType.DOUBLE, PrimitiveType.DOUBLE);
    functionSet.addScalarBuiltin("fmod", "_ZN4palo13MathFunctions11fmod_doubleEPN8palo_udf15FunctionContextERKNS1_9DoubleValES6_", true, false, PrimitiveType.DOUBLE, PrimitiveType.DOUBLE, PrimitiveType.DOUBLE);
    functionSet.addScalarBuiltin("positive", "_ZN4palo13MathFunctions15positive_bigintEPN8palo_udf15FunctionContextERKNS1_9BigIntValE", true, false, PrimitiveType.BIGINT, PrimitiveType.BIGINT);
    functionSet.addScalarBuiltin("positive", "_ZN4palo13MathFunctions15positive_doubleEPN8palo_udf15FunctionContextERKNS1_9DoubleValE", true, false, PrimitiveType.DOUBLE, PrimitiveType.DOUBLE);
    functionSet.addScalarBuiltin("positive", "_ZN4palo13MathFunctions16positive_decimalEPN8palo_udf15FunctionContextERKNS1_10DecimalValE", true, false, PrimitiveType.DECIMAL, PrimitiveType.DECIMAL);
    functionSet.addScalarBuiltin("negative", "_ZN4palo13MathFunctions15negative_bigintEPN8palo_udf15FunctionContextERKNS1_9BigIntValE", true, false, PrimitiveType.BIGINT, PrimitiveType.BIGINT);
    functionSet.addScalarBuiltin("negative", "_ZN4palo13MathFunctions15negative_doubleEPN8palo_udf15FunctionContextERKNS1_9DoubleValE", true, false, PrimitiveType.DOUBLE, PrimitiveType.DOUBLE);
    functionSet.addScalarBuiltin("negative", "_ZN4palo13MathFunctions16negative_decimalEPN8palo_udf15FunctionContextERKNS1_10DecimalValE", true, false, PrimitiveType.DECIMAL, PrimitiveType.DECIMAL);
    functionSet.addScalarBuiltin("least", "_ZN4palo13MathFunctions5leastEPN8palo_udf15FunctionContextEiPKNS1_10TinyIntValE", true, true, PrimitiveType.TINYINT, PrimitiveType.TINYINT);
    functionSet.addScalarBuiltin("least", "_ZN4palo13MathFunctions5leastEPN8palo_udf15FunctionContextEiPKNS1_11SmallIntValE", true, true, PrimitiveType.SMALLINT, PrimitiveType.SMALLINT);
    functionSet.addScalarBuiltin("least", "_ZN4palo13MathFunctions5leastEPN8palo_udf15FunctionContextEiPKNS1_6IntValE", true, true, PrimitiveType.INT, PrimitiveType.INT);
    functionSet.addScalarBuiltin("least", "_ZN4palo13MathFunctions5leastEPN8palo_udf15FunctionContextEiPKNS1_9BigIntValE", true, true, PrimitiveType.BIGINT, PrimitiveType.BIGINT);
    functionSet.addScalarBuiltin("least", "_ZN4palo13MathFunctions5leastEPN8palo_udf15FunctionContextEiPKNS1_11LargeIntValE", true, true, PrimitiveType.LARGEINT, PrimitiveType.LARGEINT);
    functionSet.addScalarBuiltin("least", "_ZN4palo13MathFunctions5leastEPN8palo_udf15FunctionContextEiPKNS1_8FloatValE", true, true, PrimitiveType.FLOAT, PrimitiveType.FLOAT);
    functionSet.addScalarBuiltin("least", "_ZN4palo13MathFunctions5leastEPN8palo_udf15FunctionContextEiPKNS1_9DoubleValE", true, true, PrimitiveType.DOUBLE, PrimitiveType.DOUBLE);
    functionSet.addScalarBuiltin("least", "_ZN4palo13MathFunctions5leastEPN8palo_udf15FunctionContextEiPKNS1_9StringValE", true, true, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR);
    functionSet.addScalarBuiltin("least", "_ZN4palo13MathFunctions5leastEPN8palo_udf15FunctionContextEiPKNS1_11DateTimeValE", true, true, PrimitiveType.DATETIME, PrimitiveType.DATETIME);
    functionSet.addScalarBuiltin("least", "_ZN4palo13MathFunctions5leastEPN8palo_udf15FunctionContextEiPKNS1_10DecimalValE", true, true, PrimitiveType.DECIMAL, PrimitiveType.DECIMAL);
    functionSet.addScalarBuiltin("greatest", "_ZN4palo13MathFunctions8greatestEPN8palo_udf15FunctionContextEiPKNS1_10TinyIntValE", true, true, PrimitiveType.TINYINT, PrimitiveType.TINYINT);
    functionSet.addScalarBuiltin("greatest", "_ZN4palo13MathFunctions8greatestEPN8palo_udf15FunctionContextEiPKNS1_11SmallIntValE", true, true, PrimitiveType.SMALLINT, PrimitiveType.SMALLINT);
    functionSet.addScalarBuiltin("greatest", "_ZN4palo13MathFunctions8greatestEPN8palo_udf15FunctionContextEiPKNS1_6IntValE", true, true, PrimitiveType.INT, PrimitiveType.INT);
    functionSet.addScalarBuiltin("greatest", "_ZN4palo13MathFunctions8greatestEPN8palo_udf15FunctionContextEiPKNS1_9BigIntValE", true, true, PrimitiveType.BIGINT, PrimitiveType.BIGINT);
    functionSet.addScalarBuiltin("greatest", "_ZN4palo13MathFunctions8greatestEPN8palo_udf15FunctionContextEiPKNS1_11LargeIntValE", true, true, PrimitiveType.LARGEINT, PrimitiveType.LARGEINT);
    functionSet.addScalarBuiltin("greatest", "_ZN4palo13MathFunctions8greatestEPN8palo_udf15FunctionContextEiPKNS1_8FloatValE", true, true, PrimitiveType.FLOAT, PrimitiveType.FLOAT);
    functionSet.addScalarBuiltin("greatest", "_ZN4palo13MathFunctions8greatestEPN8palo_udf15FunctionContextEiPKNS1_9DoubleValE", true, true, PrimitiveType.DOUBLE, PrimitiveType.DOUBLE);
    functionSet.addScalarBuiltin("greatest", "_ZN4palo13MathFunctions8greatestEPN8palo_udf15FunctionContextEiPKNS1_9StringValE", true, true, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR);
    functionSet.addScalarBuiltin("greatest", "_ZN4palo13MathFunctions8greatestEPN8palo_udf15FunctionContextEiPKNS1_11DateTimeValE", true, true, PrimitiveType.DATETIME, PrimitiveType.DATETIME);
    functionSet.addScalarBuiltin("greatest", "_ZN4palo13MathFunctions8greatestEPN8palo_udf15FunctionContextEiPKNS1_10DecimalValE", true, true, PrimitiveType.DECIMAL, PrimitiveType.DECIMAL);
    functionSet.addScalarBuiltin("if", "", true, false, PrimitiveType.BOOLEAN, PrimitiveType.BOOLEAN, PrimitiveType.BOOLEAN, PrimitiveType.BOOLEAN);
    functionSet.addScalarBuiltin("if", "", true, false, PrimitiveType.TINYINT, PrimitiveType.BOOLEAN, PrimitiveType.TINYINT, PrimitiveType.TINYINT);
    functionSet.addScalarBuiltin("if", "", true, false, PrimitiveType.SMALLINT, PrimitiveType.BOOLEAN, PrimitiveType.SMALLINT, PrimitiveType.SMALLINT);
    functionSet.addScalarBuiltin("if", "", true, false, PrimitiveType.INT, PrimitiveType.BOOLEAN, PrimitiveType.INT, PrimitiveType.INT);
    functionSet.addScalarBuiltin("if", "", true, false, PrimitiveType.BIGINT, PrimitiveType.BOOLEAN, PrimitiveType.BIGINT, PrimitiveType.BIGINT);
    functionSet.addScalarBuiltin("if", "", true, false, PrimitiveType.LARGEINT, PrimitiveType.BOOLEAN, PrimitiveType.LARGEINT, PrimitiveType.LARGEINT);
    functionSet.addScalarBuiltin("if", "", true, false, PrimitiveType.FLOAT, PrimitiveType.BOOLEAN, PrimitiveType.FLOAT, PrimitiveType.FLOAT);
    functionSet.addScalarBuiltin("if", "", true, false, PrimitiveType.DOUBLE, PrimitiveType.BOOLEAN, PrimitiveType.DOUBLE, PrimitiveType.DOUBLE);
    functionSet.addScalarBuiltin("if", "", true, false, PrimitiveType.VARCHAR, PrimitiveType.BOOLEAN, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR);
    functionSet.addScalarBuiltin("if", "", true, false, PrimitiveType.DATETIME, PrimitiveType.BOOLEAN, PrimitiveType.DATETIME, PrimitiveType.DATETIME);
    functionSet.addScalarBuiltin("if", "", true, false, PrimitiveType.DECIMAL, PrimitiveType.BOOLEAN, PrimitiveType.DECIMAL, PrimitiveType.DECIMAL);
    functionSet.addScalarBuiltin("nullif", "", true, false, PrimitiveType.BOOLEAN, PrimitiveType.BOOLEAN, PrimitiveType.BOOLEAN);
    functionSet.addScalarBuiltin("nullif", "", true, false, PrimitiveType.TINYINT, PrimitiveType.TINYINT, PrimitiveType.TINYINT);
    functionSet.addScalarBuiltin("nullif", "", true, false, PrimitiveType.SMALLINT, PrimitiveType.SMALLINT, PrimitiveType.SMALLINT);
    functionSet.addScalarBuiltin("nullif", "", true, false, PrimitiveType.INT, PrimitiveType.INT, PrimitiveType.INT);
    functionSet.addScalarBuiltin("nullif", "", true, false, PrimitiveType.BIGINT, PrimitiveType.BIGINT, PrimitiveType.BIGINT);
    functionSet.addScalarBuiltin("nullif", "", true, false, PrimitiveType.LARGEINT, PrimitiveType.LARGEINT, PrimitiveType.LARGEINT);
    functionSet.addScalarBuiltin("nullif", "", true, false, PrimitiveType.FLOAT, PrimitiveType.FLOAT, PrimitiveType.FLOAT);
    functionSet.addScalarBuiltin("nullif", "", true, false, PrimitiveType.DOUBLE, PrimitiveType.DOUBLE, PrimitiveType.DOUBLE);
    functionSet.addScalarBuiltin("nullif", "", true, false, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR);
    functionSet.addScalarBuiltin("nullif", "", true, false, PrimitiveType.DATETIME, PrimitiveType.DATETIME, PrimitiveType.DATETIME);
    functionSet.addScalarBuiltin("nullif", "", true, false, PrimitiveType.DECIMAL, PrimitiveType.DECIMAL, PrimitiveType.DECIMAL);
    functionSet.addScalarBuiltin("ifnull", "", true, false, PrimitiveType.BOOLEAN, PrimitiveType.BOOLEAN, PrimitiveType.BOOLEAN);
    functionSet.addScalarBuiltin("ifnull", "", true, false, PrimitiveType.TINYINT, PrimitiveType.TINYINT, PrimitiveType.TINYINT);
    functionSet.addScalarBuiltin("ifnull", "", true, false, PrimitiveType.SMALLINT, PrimitiveType.SMALLINT, PrimitiveType.SMALLINT);
    functionSet.addScalarBuiltin("ifnull", "", true, false, PrimitiveType.INT, PrimitiveType.INT, PrimitiveType.INT);
    functionSet.addScalarBuiltin("ifnull", "", true, false, PrimitiveType.BIGINT, PrimitiveType.BIGINT, PrimitiveType.BIGINT);
    functionSet.addScalarBuiltin("ifnull", "", true, false, PrimitiveType.LARGEINT, PrimitiveType.LARGEINT, PrimitiveType.LARGEINT);
    functionSet.addScalarBuiltin("ifnull", "", true, false, PrimitiveType.FLOAT, PrimitiveType.FLOAT, PrimitiveType.FLOAT);
    functionSet.addScalarBuiltin("ifnull", "", true, false, PrimitiveType.DOUBLE, PrimitiveType.DOUBLE, PrimitiveType.DOUBLE);
    functionSet.addScalarBuiltin("ifnull", "", true, false, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR);
    functionSet.addScalarBuiltin("ifnull", "", true, false, PrimitiveType.DATETIME, PrimitiveType.DATETIME, PrimitiveType.DATETIME);
    functionSet.addScalarBuiltin("ifnull", "", true, false, PrimitiveType.DECIMAL, PrimitiveType.DECIMAL, PrimitiveType.DECIMAL);
    functionSet.addScalarBuiltin("coalesce", "", true, true, PrimitiveType.BOOLEAN, PrimitiveType.BOOLEAN);
    functionSet.addScalarBuiltin("coalesce", "", true, true, PrimitiveType.TINYINT, PrimitiveType.TINYINT);
    functionSet.addScalarBuiltin("coalesce", "", true, true, PrimitiveType.SMALLINT, PrimitiveType.SMALLINT);
    functionSet.addScalarBuiltin("coalesce", "", true, true, PrimitiveType.INT, PrimitiveType.INT);
    functionSet.addScalarBuiltin("coalesce", "", true, true, PrimitiveType.BIGINT, PrimitiveType.BIGINT);
    functionSet.addScalarBuiltin("coalesce", "", true, true, PrimitiveType.LARGEINT, PrimitiveType.LARGEINT);
    functionSet.addScalarBuiltin("coalesce", "", true, true, PrimitiveType.FLOAT, PrimitiveType.FLOAT);
    functionSet.addScalarBuiltin("coalesce", "", true, true, PrimitiveType.DOUBLE, PrimitiveType.DOUBLE);
    functionSet.addScalarBuiltin("coalesce", "", true, true, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR);
    functionSet.addScalarBuiltin("coalesce", "", true, true, PrimitiveType.DATETIME, PrimitiveType.DATETIME);
    functionSet.addScalarBuiltin("coalesce", "", true, true, PrimitiveType.DECIMAL, PrimitiveType.DECIMAL);
    functionSet.addScalarBuiltin("substr", "_ZN4palo15StringFunctions9substringEPN8palo_udf15FunctionContextERKNS1_9StringValERKNS1_6IntValE", true, false, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR, PrimitiveType.INT);
    functionSet.addScalarBuiltin("substring", "_ZN4palo15StringFunctions9substringEPN8palo_udf15FunctionContextERKNS1_9StringValERKNS1_6IntValE", true, false, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR, PrimitiveType.INT);
    functionSet.addScalarBuiltin("substr", "_ZN4palo15StringFunctions9substringEPN8palo_udf15FunctionContextERKNS1_9StringValERKNS1_6IntValES9_", true, false, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR, PrimitiveType.INT, PrimitiveType.INT);
    functionSet.addScalarBuiltin("substring", "_ZN4palo15StringFunctions9substringEPN8palo_udf15FunctionContextERKNS1_9StringValERKNS1_6IntValES9_", true, false, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR, PrimitiveType.INT, PrimitiveType.INT);
    functionSet.addScalarBuiltin("strleft", "_ZN4palo15StringFunctions4leftEPN8palo_udf15FunctionContextERKNS1_9StringValERKNS1_6IntValE", true, false, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR, PrimitiveType.INT);
    functionSet.addScalarBuiltin("strright", "_ZN4palo15StringFunctions5rightEPN8palo_udf15FunctionContextERKNS1_9StringValERKNS1_6IntValE", true, false, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR, PrimitiveType.INT);
    functionSet.addScalarBuiltin("space", "_ZN4palo15StringFunctions5spaceEPN8palo_udf15FunctionContextERKNS1_6IntValE", true, false, PrimitiveType.VARCHAR, PrimitiveType.INT);
    functionSet.addScalarBuiltin("repeat", "_ZN4palo15StringFunctions6repeatEPN8palo_udf15FunctionContextERKNS1_9StringValERKNS1_6IntValE", true, false, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR, PrimitiveType.INT);
    functionSet.addScalarBuiltin("lpad", "_ZN4palo15StringFunctions4lpadEPN8palo_udf15FunctionContextERKNS1_9StringValERKNS1_6IntValES6_", true, false, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR, PrimitiveType.INT, PrimitiveType.VARCHAR);
    functionSet.addScalarBuiltin("rpad", "_ZN4palo15StringFunctions4rpadEPN8palo_udf15FunctionContextERKNS1_9StringValERKNS1_6IntValES6_", true, false, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR, PrimitiveType.INT, PrimitiveType.VARCHAR);
    functionSet.addScalarBuiltin("length", "_ZN4palo15StringFunctions6lengthEPN8palo_udf15FunctionContextERKNS1_9StringValE", true, false, PrimitiveType.INT, PrimitiveType.VARCHAR);
    functionSet.addScalarBuiltin("lower", "_ZN4palo15StringFunctions5lowerEPN8palo_udf15FunctionContextERKNS1_9StringValE", true, false, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR);
    functionSet.addScalarBuiltin("lcase", "_ZN4palo15StringFunctions5lowerEPN8palo_udf15FunctionContextERKNS1_9StringValE", true, false, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR);
    functionSet.addScalarBuiltin("upper", "_ZN4palo15StringFunctions5upperEPN8palo_udf15FunctionContextERKNS1_9StringValE", true, false, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR);
    functionSet.addScalarBuiltin("ucase", "_ZN4palo15StringFunctions5upperEPN8palo_udf15FunctionContextERKNS1_9StringValE", true, false, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR);
    functionSet.addScalarBuiltin("reverse", "_ZN4palo15StringFunctions7reverseEPN8palo_udf15FunctionContextERKNS1_9StringValE", true, false, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR);
    functionSet.addScalarBuiltin("trim", "_ZN4palo15StringFunctions4trimEPN8palo_udf15FunctionContextERKNS1_9StringValE", true, false, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR);
    functionSet.addScalarBuiltin("ltrim", "_ZN4palo15StringFunctions5ltrimEPN8palo_udf15FunctionContextERKNS1_9StringValE", true, false, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR);
    functionSet.addScalarBuiltin("rtrim", "_ZN4palo15StringFunctions5rtrimEPN8palo_udf15FunctionContextERKNS1_9StringValE", true, false, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR);
    functionSet.addScalarBuiltin("ascii", "_ZN4palo15StringFunctions5asciiEPN8palo_udf15FunctionContextERKNS1_9StringValE", true, false, PrimitiveType.INT, PrimitiveType.VARCHAR);
    functionSet.addScalarBuiltin("instr", "_ZN4palo15StringFunctions5instrEPN8palo_udf15FunctionContextERKNS1_9StringValES6_", true, false, PrimitiveType.INT, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR);
    functionSet.addScalarBuiltin("locate", "_ZN4palo15StringFunctions6locateEPN8palo_udf15FunctionContextERKNS1_9StringValES6_", true, false, PrimitiveType.INT, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR);
    functionSet.addScalarBuiltin("locate", "_ZN4palo15StringFunctions10locate_posEPN8palo_udf15FunctionContextERKNS1_9StringValES6_RKNS1_6IntValE", true, false, PrimitiveType.INT, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR, PrimitiveType.INT);
    functionSet.addScalarBuiltin("regexp_extract", "_ZN4palo15StringFunctions14regexp_extractEPN8palo_udf15FunctionContextERKNS1_9StringValES6_RKNS1_9BigIntValE", true, "_ZN4palo15StringFunctions14regexp_prepareEPN8palo_udf15FunctionContextENS2_18FunctionStateScopeE", "_ZN4palo15StringFunctions12regexp_closeEPN8palo_udf15FunctionContextENS2_18FunctionStateScopeE", false, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR, PrimitiveType.BIGINT);
    functionSet.addScalarBuiltin("regexp_replace", "_ZN4palo15StringFunctions14regexp_replaceEPN8palo_udf15FunctionContextERKNS1_9StringValES6_S6_", true, "_ZN4palo15StringFunctions14regexp_prepareEPN8palo_udf15FunctionContextENS2_18FunctionStateScopeE", "_ZN4palo15StringFunctions12regexp_closeEPN8palo_udf15FunctionContextENS2_18FunctionStateScopeE", false, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR);
    functionSet.addScalarBuiltin("concat", "_ZN4palo15StringFunctions6concatEPN8palo_udf15FunctionContextEiPKNS1_9StringValE", true, true, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR);
    functionSet.addScalarBuiltin("concat_ws", "_ZN4palo15StringFunctions9concat_wsEPN8palo_udf15FunctionContextERKNS1_9StringValEiPS5_", true, true, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR);
    functionSet.addScalarBuiltin("find_in_set", "_ZN4palo15StringFunctions11find_in_setEPN8palo_udf15FunctionContextERKNS1_9StringValES6_", true, false, PrimitiveType.INT, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR);
    functionSet.addScalarBuiltin("parse_url", "_ZN4palo15StringFunctions9parse_urlEPN8palo_udf15FunctionContextERKNS1_9StringValES6_", true, "_ZN4palo15StringFunctions17parse_url_prepareEPN8palo_udf15FunctionContextENS2_18FunctionStateScopeE", "_ZN4palo15StringFunctions15parse_url_closeEPN8palo_udf15FunctionContextENS2_18FunctionStateScopeE", false, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR);
    functionSet.addScalarBuiltin("parse_url", "_ZN4palo15StringFunctions13parse_url_keyEPN8palo_udf15FunctionContextERKNS1_9StringValES6_S6_", true, "_ZN4palo15StringFunctions17parse_url_prepareEPN8palo_udf15FunctionContextENS2_18FunctionStateScopeE", "_ZN4palo15StringFunctions15parse_url_closeEPN8palo_udf15FunctionContextENS2_18FunctionStateScopeE", false, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR);
    functionSet.addScalarBuiltin("sleep", "_ZN4palo16UtilityFunctions5sleepEPN8palo_udf15FunctionContextERKNS1_6IntValE", true, false, PrimitiveType.BOOLEAN, PrimitiveType.INT);
    functionSet.addScalarBuiltin("version", "_ZN4palo16UtilityFunctions7versionEPN8palo_udf15FunctionContextE", true, false, PrimitiveType.VARCHAR);
    functionSet.addScalarBuiltin("get_json_int", "_ZN4palo13JsonFunctions12get_json_intEPN8palo_udf15FunctionContextERKNS1_9StringValES6_", true, false, PrimitiveType.INT, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR);
    functionSet.addScalarBuiltin("get_json_double", "_ZN4palo13JsonFunctions15get_json_doubleEPN8palo_udf15FunctionContextERKNS1_9StringValES6_", true, false, PrimitiveType.DOUBLE, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR);
    functionSet.addScalarBuiltin("get_json_string", "_ZN4palo13JsonFunctions15get_json_stringEPN8palo_udf15FunctionContextERKNS1_9StringValES6_", true, false, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR);
    functionSet.addScalarBuiltin("hll_cardinality", "_ZN4palo16HllHashFunctions15hll_cardinalityEPN8palo_udf15FunctionContextERKNS1_9StringValE", true, false, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR);
    functionSet.addScalarBuiltin("hll_hash", "_ZN4palo16HllHashFunctions8hll_hashEPN8palo_udf15FunctionContextERKNS1_9StringValE", true, false, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR);
    functionSet.addScalarBuiltin("aes_encrypt", "_ZN4palo19EncryptionFunctions11aes_encryptEPN8palo_udf15FunctionContextERKNS1_9StringValES6_", true, false, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR);
    functionSet.addScalarBuiltin("aes_decrypt", "_ZN4palo19EncryptionFunctions11aes_decryptEPN8palo_udf15FunctionContextERKNS1_9StringValES6_", true, false, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR);
    functionSet.addScalarBuiltin("from_base64", "_ZN4palo19EncryptionFunctions11from_base64EPN8palo_udf15FunctionContextERKNS1_9StringValE", true, false, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR);
    functionSet.addScalarBuiltin("to_base64", "_ZN4palo19EncryptionFunctions9to_base64EPN8palo_udf15FunctionContextERKNS1_9StringValE", true, false, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR);
    functionSet.addScalarBuiltin("md5", "_ZN4palo19EncryptionFunctions3md5EPN8palo_udf15FunctionContextERKNS1_9StringValE", true, false, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR);
    functionSet.addScalarBuiltin("md5sum", "_ZN4palo19EncryptionFunctions6md5sumEPN8palo_udf15FunctionContextEiPKNS1_9StringValE", true, true, PrimitiveType.VARCHAR, PrimitiveType.VARCHAR);

  }
}
