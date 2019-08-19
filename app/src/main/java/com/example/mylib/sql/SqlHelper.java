package com.example.mylib.sql;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Map;
import java.util.TreeMap;

public class SqlHelper extends SQLiteOpenHelper {

    private static final int Db_Version = 1;
    private static final String Db_Name = "Library.db";


    private static final String table_Name = "book";
    private static final String columnssNames[] = {"id","author","title","short_description","image_url",
            "readen", "year", "month","day"};


    private static Map<String,String> columnMap = new TreeMap<>();

    public static final Map<String,String> getColumnMap(){return columnMap;}
    public static final String getTable_Name(){return table_Name;}
    public static final String getDbName(){return Db_Name;}


    public SqlHelper(Context context){
        super(context,Db_Name,null,Db_Version);

        for(int i=0 ; i<columnssNames.length ; i++)
            columnMap.put(columnssNames[i],columnssNames[i]);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table book(\n" +
                "\tid INTEGER primary key,\n" +
                "    author Text,\n" +
                "    title Text,\n" +
                "    short_description Text,\n" +
                "    image_url Blob,\n" +
                "    readen INTEGER,"+
                "year INTEGER,"+
                "month INTEGER,"+
                "day INTEGER"+
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public Cursor getValues(SQLiteDatabase sqLiteDatabase){
        Cursor c = sqLiteDatabase.query(table_Name,columnssNames,null,null,
                null,null,null);
        return c;
    }


}
