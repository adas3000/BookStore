package com.example.mylib;


import android.content.Context;

import androidx.test.InstrumentationRegistry;

import com.example.mylib.sql.SqlManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SqlStorePhotoTest {

    private Context context;
    private SqlManager sqlManager;


    @Before
    public void setup(){
        context = InstrumentationRegistry.getTargetContext();
        sqlManager = new SqlManager(context);
    }

    @Test
    public void storePhotoTest(){




    }


    @After
    public void done(){
        context.deleteDatabase(SqlManager.getDbName());
    }


}
