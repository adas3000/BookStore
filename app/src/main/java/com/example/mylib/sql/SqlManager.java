package com.example.mylib.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mylib.Data.Book;
import com.example.mylib.Data.Shelv_Type;

import java.sql.Date;
import java.util.ArrayList;

public class SqlManager {

    private static final SqlManager instance = new SqlManager();
    private static Context context = null;

    private static SqlHelper sqlHelper;


    public static SqlManager getInstance() {
        return instance;
    }

    private SqlManager() {
        if (instance != null) {
            throw new RuntimeException("Trying to create another object of singleton class.");
        }
    }


    public static void init(Context con) {
        if (context == null) {
            context = con;
            sqlHelper = new SqlHelper(context);
        } else
            throw new RuntimeException("Data was initalized");
    }


    public ArrayList<Book> getValues(Shelv_Type shelv_type) {
        if (context == null)
            return new ArrayList<>();// throw new IllegalStateException("No context detected");

        SQLiteDatabase db = sqlHelper.getReadableDatabase();
        Cursor cursor = sqlHelper.getValues(db, shelv_type);

        ArrayList<Book> bookArrayList = new ArrayList<>();

        if (cursor.moveToFirst()) {

            do {
                String author = cursor.getString(cursor.getColumnIndex(SqlHelper.columnssNames[1]));
                String title = cursor.getString(cursor.getColumnIndex(SqlHelper.columnssNames[2]));
                String short_desc = cursor.getString(cursor.getColumnIndex(SqlHelper.columnssNames[3]));
                String image_url = cursor.getString(cursor.getColumnIndex(SqlHelper.columnssNames[4]));
                float mark = cursor.getInt(cursor.getColumnIndex(SqlHelper.columnssNames[5]));
                int book_reading_state = cursor.getInt(cursor.getColumnIndex(SqlHelper.columnssNames[6]));
                int has_book = cursor.getInt(cursor.getColumnIndex(SqlHelper.columnssNames[7]));
                int book_is_favorite = cursor.getInt(cursor.getColumnIndex(SqlHelper.columnssNames[8]));
                String date = cursor.getString(cursor.getColumnIndex(SqlHelper.columnssNames[9]));


                boolean _has_book = has_book > 0;
                boolean _book_is_favor = book_is_favorite > 0;
                Book book;

                if (book_reading_state == 1)
                    book = new Book.Builder(title, author, short_desc, image_url).Mark(mark).user_Has_Book(_has_book).date(Date.valueOf(date))
                            .book_Is_Favorite(_book_is_favor).book_Reading_State(book_reading_state).build();

                else
                    book = new Book.Builder(title, author, short_desc, image_url).Mark(mark).user_Has_Book(_has_book)
                            .book_Is_Favorite(_book_is_favor).book_Reading_State(book_reading_state).build();

                bookArrayList.add(book);
            } while (cursor.moveToNext());
        }
        return bookArrayList;
    }

    public void addBookToDb(String title, String author, String short_desc, String image_url, float mark,
                            int book_reading_state, int has_book, int book_is_favorite, Date date) {

        if (context == null) throw new IllegalStateException("No context detected");


        try (SQLiteDatabase db = sqlHelper.getWritableDatabase()) {
            ContentValues contentValues = new ContentValues();

            contentValues.put(SqlHelper.columnssNames[1], author);
            contentValues.put(SqlHelper.columnssNames[2], title);
            contentValues.put(SqlHelper.columnssNames[3], short_desc);
            contentValues.put(SqlHelper.columnssNames[4], image_url);
            contentValues.put(SqlHelper.columnssNames[5], mark);
            contentValues.put(SqlHelper.columnssNames[6], book_reading_state);
            contentValues.put(SqlHelper.columnssNames[7], has_book);
            contentValues.put(SqlHelper.columnssNames[8], book_is_favorite);
            contentValues.put(SqlHelper.columnssNames[9], date.toString());

            db.insert(SqlHelper.getTable_Name(), null, contentValues);
        }
    }

    public void addBookToDb(String title, String author, String short_desc, String image_url) {
        if (context == null) throw new IllegalStateException("No context detected");
        try (SQLiteDatabase db = sqlHelper.getWritableDatabase()) {
            ContentValues contentValues = new ContentValues();

            contentValues.put(SqlHelper.columnssNames[1], author);
            contentValues.put(SqlHelper.columnssNames[2], title);
            contentValues.put(SqlHelper.columnssNames[3], short_desc);
            contentValues.put(SqlHelper.columnssNames[4], image_url);


            db.insert(SqlHelper.getTable_Name(), null, contentValues);
        }
    }


    public int deleteBookFromDb(String title, String author) {
        if (context == null) throw new IllegalStateException("No context detected");

        try (SQLiteDatabase db = sqlHelper.getWritableDatabase()) {


            return db.delete(SqlHelper.getTable_Name(), SqlHelper.columnssNames[2] + "=? and "
                    + SqlHelper.columnssNames[1] + "=?", new String[]{title, author});
        }
    }


    public int editBookFromDb(String oldTitle, String oldAuthor, String newTitle, String newAuthor,
                              String newDesc, String newImageUrl) {

        try (SQLiteDatabase db = sqlHelper.getWritableDatabase()) {

            ContentValues cv = new ContentValues();
            cv.put(SqlHelper.columnssNames[1], newAuthor);
            cv.put(SqlHelper.columnssNames[2], newTitle);
            cv.put(SqlHelper.columnssNames[3], newDesc);
            cv.put(SqlHelper.columnssNames[4], newImageUrl);

            return db.update(SqlHelper.getTable_Name(), cv, SqlHelper.columnssNames[1] + " =?"
                    + " AND " + SqlHelper.columnssNames[2] + " =?", new String[]{oldAuthor, oldTitle});
        }
    }

    /**
     * @param date  -- if bookreadingstate != 1 date is null
     * @return db.update
     */
    public int editBookFromDb(String whereTitle, String whereAuthor, float rate, int book_reading_state, boolean hasBook, boolean isFavor, Date date) {


        if (book_reading_state != 1) date = new Date(0,0,0);


        int has = hasBook == true ? 1 : 0;
        int favor = isFavor == true ? 1 : 0;

        try(SQLiteDatabase db = sqlHelper.getWritableDatabase()){

            ContentValues cv = new ContentValues();

            cv.put(SqlHelper.columnssNames[5],rate);
            cv.put(SqlHelper.columnssNames[6],book_reading_state);
            cv.put(SqlHelper.columnssNames[7],has);
            cv.put(SqlHelper.columnssNames[8],favor);
            cv.put(SqlHelper.columnssNames[9],date.toString());

            return db.update(SqlHelper.getTable_Name(), cv, SqlHelper.columnssNames[1] + " =?"
                    + " AND " + SqlHelper.columnssNames[2] + " =?", new String[]{whereAuthor, whereTitle});

        }
    }

    public int editBookFromDb(Book book){

        return editBookFromDb(book.getTitle(),book.getAuthor(),book.getMark(),book.getBook_reading_state(),book.isUser_has_book(),book.isBook_is_favorite(),
                book.getFinish_date());

    }

    public void deleteAllRecordsFromDb() {

        try (SQLiteDatabase db = sqlHelper.getWritableDatabase()) {
            db.execSQL("DELETE  FROM " + SqlHelper.getTable_Name());
        }
    }


    public static final String getDbName() {
        return SqlHelper.getDbName();
    }

    public void onClose() {
        sqlHelper = null;
        context = null;
    }

}