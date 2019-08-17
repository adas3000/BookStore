package com.example.mylib;

import android.content.Context;
import android.widget.TextView;

import androidx.test.InstrumentationRegistry;

import com.example.mylib.Data.Book;
import com.example.mylib.sql.SqlManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MyLibActivityTest {

    private ArrayList<TextView> book_title = new ArrayList<>();//error because of some of these variables
    private ArrayList<TextView> book_author = new ArrayList<>();
    private ArrayList<TextView> book_description = new ArrayList<>();
    private SqlManager sqlManager;
    private ArrayList<Book> bookArrayList;
    private Context context = InstrumentationRegistry.getTargetContext();


    @Before
    public void setup(){
        sqlManager = new SqlManager(context);
        bookArrayList = sqlManager.getValues(false);
    }

    @Test
    public void noErrorTest(){
        assertEquals(0,bookArrayList.size());
    }

    @After
    public void deleteDb(){
        context.deleteDatabase(SqlManager.getDbName());
    }



}