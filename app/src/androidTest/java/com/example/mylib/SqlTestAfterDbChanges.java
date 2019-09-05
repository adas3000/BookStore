package com.example.mylib;

import android.content.ContentValues;
import android.content.Context;

import androidx.test.InstrumentationRegistry;

import com.example.mylib.Data.Book;
import com.example.mylib.sql.SqlManager;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

public class SqlTestAfterDbChanges {

    private static Context context;
    private static SqlManager sqlManager;

    @BeforeClass
    public static void setup(){
        context = InstrumentationRegistry.getTargetContext();


        sqlManager.addBookToDb("Hp1","Row","...","...");

    }

    @Test
    public void isEqualsAndRestNull(){

        ArrayList<Book>bookArrayList = sqlManager.getValues();



    }




}
