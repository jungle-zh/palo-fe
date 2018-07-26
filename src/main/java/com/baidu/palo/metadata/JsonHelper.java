package com.baidu.palo.metadata;

import com.baidu.palo.catalog.AggregateType;
import com.baidu.palo.catalog.Column;
import com.baidu.palo.catalog.ColumnType;
import com.baidu.palo.catalog.PrimitiveType;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

public class JsonHelper {

    public static String toJson(Object src){
        return new Gson().toJson(src);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return new Gson().fromJson(json, clazz);
    }

    public static <T> List<T> fromJsonArray(String json, Class<T> clazz) {
        List<T> lst =  new ArrayList<T>();

        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for(final JsonElement elem : array){
            lst.add(new Gson().fromJson(elem, clazz));
        }

        return lst;
    }

    public static void main(String[] args) {
        Column column4 = new Column("age", new ColumnType(PrimitiveType.INT, 30, 2, 1), false, AggregateType.REPLACE, "20", "");
        String result = JsonHelper.toJson(column4);
        System.out.println(result);
        Column c = JsonHelper.fromJson(result, Column.class);
        System.out.println(c);

        List<Column> columns = new ArrayList<Column>();
        columns.add(new Column("column8", ColumnType.createChar(10), true, null, "", ""));
        columns.add(new Column("column9", ColumnType.createVarchar(10), true, null, "", ""));
        columns.add(new Column("column10", ColumnType.createType(PrimitiveType.DATE), true, null, "", ""));
        columns.add(new Column("column11", ColumnType.createType(PrimitiveType.DATETIME), true, null, "", ""));

        columns.add(new Column("column2", ColumnType.createType(PrimitiveType.TINYINT), false, AggregateType.MIN, "", ""));
        columns.add(new Column("column3", ColumnType.createType(PrimitiveType.SMALLINT), false, AggregateType.SUM, "", ""));
        columns.add(new Column("column4", ColumnType.createType(PrimitiveType.INT), false, AggregateType.REPLACE, "", ""));
        columns.add(new Column("column5", ColumnType.createType(PrimitiveType.BIGINT), false, AggregateType.REPLACE, "", ""));
        columns.add(new Column("column6",  ColumnType.createType(PrimitiveType.FLOAT), false, AggregateType.REPLACE, "", ""));
        columns.add(new Column("column7", ColumnType.createType(PrimitiveType.DOUBLE), false, AggregateType.REPLACE, "", ""));

        String rL = JsonHelper.toJson(columns);
        System.out.println(rL);

        List<Column> from = JsonHelper.fromJsonArray(rL, Column.class);
        System.out.println(from);
    }

}
