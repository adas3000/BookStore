package com.example.mylib;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.test.InstrumentationRegistry;

import com.example.mylib.Data.Book;
import com.example.mylib.sql.SqlHelper;
import com.example.mylib.sql.SqlManager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

public class SqlTestAfterDbChanges {

    private static Context context;
    private static SqlHelper sqlHelper;

    @BeforeClass
    public static void setup() {
        context = InstrumentationRegistry.getTargetContext();

        sqlHelper = new SqlHelper(context);

    }

    @Before
    public void putData() {

        try (SQLiteDatabase db = sqlHelper.getWritableDatabase()) {
            ContentValues contentValues = new ContentValues();

            contentValues.put(SqlHelper.columnssNames[1], "Rowling");
            contentValues.put(SqlHelper.columnssNames[2], "HP1");
            contentValues.put(SqlHelper.columnssNames[3], "Desc");
            contentValues.put(SqlHelper.columnssNames[4], "img");

            db.insert(SqlHelper.getTable_Name(), null, contentValues);
        }


    }


    @Test
    public void ifEqualsThenOk() {

        try (SQLiteDatabase db = sqlHelper.getReadableDatabase()) {

            Cursor cursor = sqlHelper.getValues(db);

            cursor.moveToFirst();

            String author = cursor.getString(cursor.getColumnIndex(SqlHelper.columnssNames[1]));
            String title = cursor.getString(cursor.getColumnIndex(SqlHelper.columnssNames[2]));
            String desc = cursor.getString(cursor.getColumnIndex(SqlHelper.columnssNames[3]));
            String img_url = cursor.getString(cursor.getColumnIndex(SqlHelper.columnssNames[4]));
            int mark = cursor.getInt(cursor.getColumnIndex(SqlHelper.columnssNames[5]));
            int book_reading_state = cursor.getInt(cursor.getColumnIndex(SqlHelper.columnssNames[6]));
            int has_book = cursor.getInt(cursor.getColumnIndex(SqlHelper.columnssNames[7]));
            int book_is_favorite = cursor.getInt(cursor.getColumnIndex(SqlHelper.columnssNames[8]));
            String date = cursor.getString(cursor.getColumnIndex(SqlHelper.columnssNames[9]));

            assertEquals("Rowling", author);
            assertEquals("HP1", title);
            assertEquals("Desc", desc);
            assertEquals("img", img_url);
            assertNull(date);
            assertEquals(0,mark);
            assertEquals(0,book_reading_state);
            assertEquals(0,has_book);
            assertEquals(0,book_is_favorite);
        }
    }


    @After
    public void afterEachTest() {
        try (SQLiteDatabase db = sqlHelper.getWritableDatabase()) {
            db.execSQL("DELETE  FROM " + SqlHelper.getTable_Name());
        }
    }

    @AfterClass
    public static void deleteDb() {
        context.deleteDatabase(SqlManager.getDbName());
    }


}
