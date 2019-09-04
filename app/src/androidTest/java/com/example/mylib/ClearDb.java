package com.example.mylib;

import android.content.Context;

import androidx.test.InstrumentationRegistry;

import com.example.mylib.sql.SqlManager;

import org.junit.Test;

public class ClearDb {

    @Test
    public void deleteDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        context.deleteDatabase(SqlManager.getDbName());
    }


}
