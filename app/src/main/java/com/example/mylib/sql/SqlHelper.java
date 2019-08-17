package com.example.mylib.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqlHelper extends SQLiteOpenHelper {

    private static final int Db_Version = 1;
    private static final String Db_Name = "Library.db";


    public SqlHelper(Context context){
        super(context,Db_Name,null,Db_Version);

    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table book(\n" +
                "\tid int primary key,\n" +
                "    author varchar(30),\n" +
                "    title varchar(100),\n" +
                "    short_description varchar(150),\n" +
                "    image_url varchar(100),\n" +
                "    readen bool"+
                ");");
        

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
