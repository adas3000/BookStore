package com.example.mylib;

import android.content.Context;

import androidx.test.InstrumentationRegistry;

import com.example.mylib.sql.SqlManager;

import org.junit.Test;

import java.sql.Date;
import java.util.Calendar;

public class PutDataIntoDb {

    @Test
    public void putData() {
        Context context = InstrumentationRegistry.getTargetContext();
        SqlManager.init(context);
        SqlManager sqlManager = SqlManager.getInstance();

        sqlManager.addBookToDb("harry potter i kamień filozoficzny","J.K Rowling","..",
                "https://s3.viva.pl/newsy/plakat-filmu-harry-potter-i-kamien-filozoficznygalapagos-films-429090-movie_cover.jpg",
                5,1,1,1,new Date(Calendar.getInstance().getTimeInMillis()));

        sqlManager.addBookToDb("harry potter i komnata tajemnic","J.K Rowling","..",
                "https://image.ceneostatic.pl/data/products/9367217/49f352e8-fe40-4a0a-b2c5-f38ba07d1d3d_i-harry-potter-i-komnata-tajemnic-harry-potter-and-the-chamber-of-secrets-3dvd.jpg",
                5,2,1,0,new Date(Calendar.getInstance().getTimeInMillis()));

        sqlManager.addBookToDb("harry potter i czara ognia","J.K Rowling","..",
                "        https://image.ceneostatic.pl/data/products/11477021/i-harry-potter-i-czara-ognia-harry-potter-and-the-goblet-of-fire-2dvd.jpg\n",
                5,3,0,0,new Date(Calendar.getInstance().getTimeInMillis()));


        //

    }


}
