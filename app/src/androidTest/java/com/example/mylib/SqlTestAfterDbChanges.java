package com.example.mylib;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.test.InstrumentationRegistry;

import com.example.mylib.Data.Book;
import com.example.mylib.Data.Shelv_Type;
import com.example.mylib.sql.SqlHelper;
import com.example.mylib.sql.SqlManager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

public class SqlTestAfterDbChanges {

    private static Context context;
    private static SqlManager sqlManager;

    @BeforeClass
    public static void setup() {
        context = InstrumentationRegistry.getTargetContext();

        SqlManager.init(context);
        sqlManager = SqlManager.getInstance();

        sqlManager.addBookToDb("Hp1","Row","...","...",5,0,1,0,
                new Date(Calendar.getInstance().getTimeInMillis()));
        sqlManager.addBookToDb("Hp2","Row","...","...",5,1,0,1,
                new Date(Calendar.getInstance().getTimeInMillis()));
        sqlManager.addBookToDb("Hp1","Row","...","...",5,1,0,1,
                new Date(Calendar.getInstance().getTimeInMillis()));

    }



    @Test
    public void ifEqualsThenOk() {

        ArrayList<Book> bookArrayList = sqlManager.getValues(Shelv_Type.All);

        assertEquals("Hp1",bookArrayList.get(0).getTitle());
        assertEquals("Row",bookArrayList.get(0).getAuthor());

        assertEquals(5,bookArrayList.get(0).getMark());
        assertEquals(0,bookArrayList.get(0).getBook_reading_state());

    }

    @Test
    public void checkOnlyReadenBooks(){

        ArrayList<Book> bookArrayList = sqlManager.getValues(Shelv_Type.Finished);

        assertEquals(2,bookArrayList.size());
        assertEquals("Hp2",bookArrayList.get(0).getTitle());
    }




    @After
    public void afterEachTest() {

    }

    @AfterClass
    public static void deleteDb() {
        context.deleteDatabase(SqlManager.getDbName());
    }


}
