package com.example.mylib;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.widget.ImageView;

import androidx.test.InstrumentationRegistry;

import com.example.mylib.Data.Book;
import com.example.mylib.sql.SqlManager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class AllBooksFragmentTest {

    private static Context context;
    private static ArrayList<Book> bookArrayList;
    private static SqlManager sqlManager;

    @BeforeClass
    public static void setup() {
        context = InstrumentationRegistry.getTargetContext();
        SqlManager.init(context);
        sqlManager = SqlManager.getInstance();
        sqlManager.addBookToDb("Hobbit", "Tolkien", "Two hobbits went somewhere",
                "drawable/photos/Hobbit1.png", 0, 2013, 10, 15);
        sqlManager.addBookToDb("Harry Potter", "Rowling", "Story of Big Wizard",
                "drawable/photos/Harry1.png", 1, 2019, 8, 17);
        bookArrayList = sqlManager.getValues();
    }


    @Test
    public void filterBooks() {

        final String str = "tol";

        Stream<Book> bookStreamFilter = bookArrayList.stream();


        List<Book> result = bookStreamFilter.filter(g -> g.getTitle().toLowerCase().contains(str) || g.getAuthor().toLowerCase().contains(str))
                .collect(Collectors.toCollection(ArrayList::new));


        assertEquals(1, result.size());

    }

    @AfterClass
    public static void clear() {
        context.deleteDatabase(SqlManager.getDbName());
    }


}