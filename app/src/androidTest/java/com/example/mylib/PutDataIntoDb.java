package com.example.mylib;

import android.content.Context;
import android.util.Log;

import androidx.test.InstrumentationRegistry;

import com.example.mylib.Data.Book;
import com.example.mylib.Data.Shelv_Type;
import com.example.mylib.sql.SqlManager;

import org.junit.Test;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

public class PutDataIntoDb {

    @Test
    public void putData() {
        Context context = InstrumentationRegistry.getTargetContext();
        SqlManager.init(context);
        SqlManager sqlManager = SqlManager.getInstance();

        sqlManager.addBookToDb("harry potter i kamień filozoficzny", "J.K Rowling", "..",
                "https://s3.viva.pl/newsy/plakat-filmu-harry-potter-i-kamien-filozoficznygalapagos-films-429090-movie_cover.jpg",
                5, 1, 1, 1, new Date(Calendar.getInstance().getTimeInMillis()));

        sqlManager.addBookToDb("harry potter i komnata tajemnic", "J.K Rowling", "..",
                "https://image.ceneostatic.pl/data/products/9367217/49f352e8-fe40-4a0a-b2c5-f38ba07d1d3d_i-harry-potter-i-komnata-tajemnic-harry-potter-and-the-chamber-of-secrets-3dvd.jpg",
                5, 2, 1, 0, new Date(Calendar.getInstance().getTimeInMillis()));

        sqlManager.addBookToDb("harry potter i czara ognia", "J.K Rowling", "..",
                "das",
                5, 3, 0, 0, new Date(Calendar.getInstance().getTimeInMillis()));
        //

    }

    @Test
    public void putData2() {
        Context context = InstrumentationRegistry.getTargetContext();
        SqlManager.init(context);
        SqlManager sqlManager = SqlManager.getInstance();
        sqlManager.addBookToDb("harry potter i zakon ognia", "J.K Rowling", "..",
                "https://image.ceneostatic.pl/data/products/11477021/i-harry-potter-i-czara-ognia-harry-potter-and-the-goblet-of-fire-2dvd.jpg");
    }

    @Test
    public void checkDefaultValuesinbook() {
        Context context = InstrumentationRegistry.getTargetContext();
        SqlManager.init(context);
        SqlManager sqlManager = SqlManager.getInstance();

        List<Book> bookList = sqlManager.getValues(Shelv_Type.All);

        for(Book book : bookList){
            Log.d("Book",book.getTitle());
            Log.d("BRS",String.valueOf(book.getBook_reading_state()));
            Log.d("DATE",String.valueOf(book.getFinish_date()));

        }


    }


}
