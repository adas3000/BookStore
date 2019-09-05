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

        sqlManager.addBookToDb("Hp1","Row","...","...");

    }



    @Test
    public void ifEqualsThenOk() {

        ArrayList<Book> bookArrayList = sqlManager.getValues(Shelv_Type.All);

        assertEquals("Hp1",bookArrayList.get(0).getTitle());
        assertEquals("Row",bookArrayList.get(0).getAuthor());

    }

    @Test
    public void ifcostamthenOk(){

    }



    @After
    public void afterEachTest() {

    }

    @AfterClass
    public static void deleteDb() {
        context.deleteDatabase(SqlManager.getDbName());
    }


}
