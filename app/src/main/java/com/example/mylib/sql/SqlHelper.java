package com.example.mylib.sql;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class SqlHelper extends SQLiteOpenHelper {

    private static final int Db_Version = 3;
    private static final String Db_Name = "Library.db";


    private static final String table_Name = "book";


    /** ColumnsNames
     *  NOTICE if boook is not added to user every value of below is null!
     *  Book_reading_state contains a current state of book
     *  1 - Book is finished by user , 2 - Book is reading by user , 3 - User wants to read book in the future
     *  Has_book contains a value whether user has book on his own
     *  1 - Yes else No
     *  Book_is_favorite contains a value whether book is one of user favorite books
     *  1 - Yes else No
     *  Date contains a user finished book date if user not finished yet is null
     */

    public static final String columnssNames[] = {"id","author","title","short_description","image_url",
            "book_reading_state","has_book","book_is_favorite", "date"};


    public static final String getTable_Name(){return table_Name;}
    public static final String getDbName(){return Db_Name;}


    public SqlHelper(Context context){
        super(context,Db_Name,null,Db_Version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table _user(" +
                "id INTEGER primary key AUTOINCREMENT ," +
                "nickname Text" + ")");


        sqLiteDatabase.execSQL("create table book(\n" +
                "\tid INTEGER primary key AUTOINCREMENT ,\n" +
                "    author Text,\n" +
                "    title Text,\n" +
                "    short_description Text,\n" +
                "    image_url Text,\n" +
                "    book_reading_state INTEGER,"+
                    "has_book Text,"+
                    "book_is_favorite Text,"+
                    "iduser INTEGER,"+
                "date Text,"+
                "foreign key(iduser) references _user(id)" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public Cursor getValues(SQLiteDatabase sqLiteDatabase){
        Cursor c = sqLiteDatabase.query(table_Name,columnssNames,null,null,
                null,null,null);
        return c;
    }

    public Cursor getValues(SQLiteDatabase sqLiteDatabase,String table_Name,String [] columns){

        Cursor c = sqLiteDatabase.query(table_Name,columns,null,null,
                null,null,null);


        return c;
    }


}