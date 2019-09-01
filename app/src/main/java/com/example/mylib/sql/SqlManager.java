package com.example.mylib.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mylib.Data.Book;

import java.util.ArrayList;
import java.util.Map;

public class SqlManager {

    private static final SqlManager instance = new SqlManager();
    private static Context context = null;

    private static SqlHelper sqlHelper;



    public static SqlManager getInstance(){return instance;}
    private SqlManager()  {
        if(instance!=null){
            throw new IllegalStateException("Trying to create another object of singleton class.");
        }
    }


    public static void init(Context con){
        if(context==null){
            context = con;
            sqlHelper = new SqlHelper(context);
        }
        else
            throw new IllegalStateException("Data was initalized");
    }




    public ArrayList<Book> getValues() {
        if(context==null) throw new IllegalStateException("No context detected");

        SQLiteDatabase db = sqlHelper.getReadableDatabase();
        Cursor cursor = sqlHelper.getValues(db);

        ArrayList<Book> bookArrayList = new ArrayList<>();

        if (cursor.moveToFirst()) {

            do {
                String author = cursor.getString(cursor.getColumnIndex(SqlHelper.columnssNames[1]));
                String title = cursor.getString(cursor.getColumnIndex(SqlHelper.columnssNames[2]));
                String short_desc = cursor.getString(cursor.getColumnIndex(SqlHelper.columnssNames[3]));
                String image_url = cursor.getString(cursor.getColumnIndex(SqlHelper.columnssNames[4]));
                int readen = cursor.getInt(cursor.getColumnIndex(SqlHelper.columnssNames[5]));
                int temp_year = cursor.getInt(cursor.getColumnIndex(SqlHelper.columnssNames[6]));
                int temp_month = cursor.getInt(cursor.getColumnIndex(SqlHelper.columnssNames[7]));
                int temp_day = cursor.getInt(cursor.getColumnIndex(SqlHelper.columnssNames[8]));

                boolean _readen = readen > 0 ? true : false;


                Book book = new Book.Builder(title, author, short_desc, image_url).readenByUser(_readen).
                        date(temp_year, temp_month, temp_day).build();

                bookArrayList.add(book);
            } while (cursor.moveToNext());
        }
        return bookArrayList;
    }

    public void addBookToDb(String title, String author, String short_desc, String image_url, int readen,
                            int y, int m, int d) {
        if(context==null) throw new IllegalStateException("No context detected");
        try(SQLiteDatabase db = sqlHelper.getWritableDatabase()) {
            ContentValues contentValues = new ContentValues();

            contentValues.put(SqlHelper.columnssNames[1], author);
            contentValues.put(SqlHelper.columnssNames[2], title);
            contentValues.put(SqlHelper.columnssNames[3], short_desc);
            contentValues.put(SqlHelper.columnssNames[4], image_url);
            contentValues.put(SqlHelper.columnssNames[5], readen);
            contentValues.put(SqlHelper.columnssNames[6], y);
            contentValues.put(SqlHelper.columnssNames[7], m);
            contentValues.put(SqlHelper.columnssNames[8], d);

            db.insert(SqlHelper.getTable_Name(), null, contentValues);
        }
    }


    public int deleteBookFromDb(String title, String author) {
        if(context==null) throw new IllegalStateException("No context detected");

        try(SQLiteDatabase db = sqlHelper.getWritableDatabase()) {


            return db.delete(SqlHelper.getTable_Name(), SqlHelper.columnssNames[2] + "=? and "
                    + SqlHelper.columnssNames[1] + "=?", new String[]{title, author});
        }
    }



    public void editBookFromDb(String oldTitle,String oldAuthor,String newTitle,String newAuthor,
                               String newDesc,String newImageUrl,int readen,int y,int m,int d){


        try(SQLiteDatabase db = sqlHelper.getWritableDatabase()){

            db.execSQL("UPDATE "+SqlHelper.getTable_Name() + " SET "+SqlHelper.columnssNames[1] + " = '"
            +newAuthor +"',"+ SqlHelper.columnssNames[2]+" = '" + newTitle
                    +"'," + SqlHelper.columnssNames[3]+" = '" + newDesc
                    +"'," + SqlHelper.columnssNames[4]+" = '" + newImageUrl
                    +"'," + SqlHelper.columnssNames[5]+" = '" + readen
                    +"'," + SqlHelper.columnssNames[6]+" = '" + y
                    +"'," + SqlHelper.columnssNames[7]+" = '" + m
                    +"'," + SqlHelper.columnssNames[8]+" = '" + d
                    +"' WHERE " +SqlHelper.columnssNames[1]+" = '"+oldAuthor + "' AND "
                    + SqlHelper.columnssNames[2] + " = '"+ oldTitle+"'");
        }

    }


    public void deleteAllRecordsFromDb(){

        try(SQLiteDatabase db = sqlHelper.getWritableDatabase()){
            db.execSQL("DELETE  FROM "+SqlHelper.getTable_Name());
        }
    }


    public static final String getDbName() {
        return SqlHelper.getDbName();
    }

    public void onClose(){
        sqlHelper = null;
        context = null;
    }

}