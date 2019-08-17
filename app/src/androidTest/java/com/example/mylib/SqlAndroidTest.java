package com.example.mylib;

import android.content.Context;

import androidx.test.InstrumentationRegistry;

import com.example.mylib.Data.Book;
import com.example.mylib.sql.SqlManager;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class SqlAndroidTest {

    private Context context;
    private SqlManager sqlManager;

    @Before
    public void setup(){
        context = InstrumentationRegistry.getTargetContext();
        sqlManager = new SqlManager(context);
    }
    @Test
    public void packageTest(){
        assertEquals("com.example.mylib", context.getPackageName());
    }

    @Test
    public void sqlTest(){

        sqlManager.addBookToDb("Hobbit","Tolkien","Two hobbits went somewhere",
              "drawable/photos/Hobbit1.png",0,2013,10,15 );

        ArrayList<Book> bookArrayList = sqlManager.getValues(true);

        assertEquals(0,bookArrayList.size());
        bookArrayList = sqlManager.getValues(false);

        assertEquals(1,bookArrayList.size());
    }


}
