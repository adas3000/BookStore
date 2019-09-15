package com.example.mylib;

import android.content.Context;
import androidx.test.InstrumentationRegistry;

import com.example.mylib.Data.Book;
import com.example.mylib.Data.Shelv_Type;
import com.example.mylib.sql.SqlManager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotSame;
import static junit.framework.TestCase.assertTrue;

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

    @Test
    public void getBookAfterEditing(){

        ArrayList<Book> bookArrayList = sqlManager.getValues(Shelv_Type.All);

        assertFalse(2==bookArrayList.get(0).getMark());

        bookArrayList.get(0).setMark(2);

        Book b = sqlManager.getBookByTitleAndAuthor(bookArrayList.get(0).getTitle(),bookArrayList.get(0).getAuthor());

        assertTrue(2==bookArrayList.get(0).getMark());
    }


    @After
    public void afterEachTest() {

    }

    @AfterClass
    public static void deleteDb() {
        context.deleteDatabase(SqlManager.getDbName());
    }


}
