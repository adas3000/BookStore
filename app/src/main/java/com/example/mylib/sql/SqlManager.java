package com.example.mylib.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
<<<<<<< HEAD
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
=======
>>>>>>> itemadapter_Branch

import com.example.mylib.Data.Book;
import java.sql.Statement;
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
            Map<String, String> kav = SqlHelper.getColumnMap();

            do {
                String title = cursor.getString(cursor.getColumnIndex(kav.get("title")));
                String author = cursor.getString(cursor.getColumnIndex(kav.get("author")));
                String short_desc = cursor.getString(cursor.getColumnIndex(kav.get("short_description")));
              //  String image_url = cursor.getString(cursor.getColumnIndex(kav.get("image_url")));
                byte [] image_url = cursor.getBlob(cursor.getColumnIndex("image_url"));
                Bitmap img = BitmapFactory.decodeByteArray(image_url,0,image_url.length);
                int temp_year = cursor.getInt(cursor.getColumnIndex(kav.get("year")));
                int temp_month = cursor.getInt(cursor.getColumnIndex(kav.get("month")));
                int temp_day = cursor.getInt(cursor.getColumnIndex(kav.get("day")));
                int readen = cursor.getInt(cursor.getColumnIndex(kav.get("readen")));
                boolean _readen = readen > 0 ? true : false;


                Book book = new Book.Builder(title, author, short_desc, image_url).readenByUser(_readen).
                        date(temp_year, temp_month, temp_day).build();

<<<<<<< HEAD
                Book book = new Book(title,author,short_desc,img,_readen,temp_year,temp_month,temp_day);
=======
>>>>>>> itemadapter_Branch
                bookArrayList.add(book);
            } while (cursor.moveToNext());
        }
        return bookArrayList;
    }

<<<<<<< HEAD
    public void addBookToDb(String title, String author, String short_desc, Bitmap image_url, int readen,
                            int y, int m, int d){


        byte [] img_data = BitmapHelper.getBitmapAsByteArray(image_url);


=======
    public void addBookToDb(String title, String author, String short_desc, String image_url, int readen,
                            int y, int m, int d) {
        if(context==null) throw new IllegalStateException("No context detected");
>>>>>>> itemadapter_Branch
        SQLiteDatabase db = sqlHelper.getWritableDatabase();

        Map<String, String> kav = SqlHelper.getColumnMap();
        ContentValues contentValues = new ContentValues();

<<<<<<< HEAD
        contentValues.put(kav.get("title"),title);
        contentValues.put(kav.get("author"),author);
        contentValues.put(kav.get("short_description"),short_desc);
        contentValues.put(kav.get("image_url"),img_data);
        contentValues.put(kav.get("readen"),readen);
        contentValues.put(kav.get("year"),y);
        contentValues.put(kav.get("month"),m);
        contentValues.put(kav.get("day"),d);
=======
        contentValues.put(kav.get("title"), title);
        contentValues.put(kav.get("author"), author);
        contentValues.put(kav.get("short_description"), short_desc);
        contentValues.put(kav.get("image_url"), image_url);
        contentValues.put(kav.get("readen"), readen);
        contentValues.put(kav.get("year"), y);
        contentValues.put(kav.get("month"), m);
        contentValues.put(kav.get("day"), d);

        db.insert(SqlHelper.getTable_Name(), null, contentValues);
    }


    public int deleteBookFromDb(String title, String author) {
        if(context==null) throw new IllegalStateException("No context detected");
>>>>>>> itemadapter_Branch

        SQLiteDatabase db = sqlHelper.getWritableDatabase();

        Map<String, String> columnNames = SqlHelper.getColumnMap();

        return db.delete(SqlHelper.getTable_Name(), columnNames.get("title") + "=? and "
                + columnNames.get("author") + "=?", new String[]{title, author});
    }


    public static final String getDbName() {
        return SqlHelper.getDbName();
    }
}
