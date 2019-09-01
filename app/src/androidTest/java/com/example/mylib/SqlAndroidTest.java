package com.example.mylib;

import android.content.Context;

import androidx.test.InstrumentationRegistry;

import com.example.mylib.Data.Book;
import com.example.mylib.sql.SqlManager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class SqlAndroidTest {

    private static Context context;
    private static SqlManager sqlManager;

    @BeforeClass
    public static void setup() {
        context = InstrumentationRegistry.getTargetContext();
        SqlManager.init(context);
        sqlManager = SqlManager.getInstance();
    }

    @Before
    public void putData(){
        sqlManager.addBookToDb("Hobbit", "Tolkien", "Two hobbits went somewhere",
                "drawable/photos/Hobbit1.png", 0, 2013, 10, 15);
        sqlManager.addBookToDb("Harry Potter", "Rowling", "Story of Big Wizard",
                "drawable/photos/Harry1.png", 1, 2019, 8, 17);
    }

    @Test
    public void packageTest() {
        assertEquals("com.example.mylib", context.getPackageName());
    }

    @Test
    public void sqlTest() {


        ArrayList<Book> bookArrayList = sqlManager.getValues();
        assertEquals(2, bookArrayList.size());
        assertEquals("Hobbit",bookArrayList.get(0).getTitle());
        assertEquals("Tolkien",bookArrayList.get(0).getAuthor());
    }


    @Test
    public void areValuesGoodTest() {


        ArrayList<Book> bookArrayList = sqlManager.getValues();

        assertEquals(2, bookArrayList.size());

        //book 1
        assertEquals("Tolkien", bookArrayList.get(0).getAuthor());
        assertEquals("Hobbit", bookArrayList.get(0).getTitle());
        assertEquals("Two hobbits went somewhere", bookArrayList.get(0).getShort_description());
        assertEquals("drawable/photos/Hobbit1.png", bookArrayList.get(0).getImage_url());
        assertEquals(false, bookArrayList.get(0).isReadenByUser());
        //book 2
        assertEquals("Rowling", bookArrayList.get(1).getAuthor());
        assertEquals("Harry Potter", bookArrayList.get(1).getTitle());
        assertEquals("Story of Big Wizard", bookArrayList.get(1).getShort_description());
        assertEquals("drawable/photos/Harry1.png", bookArrayList.get(1).getImage_url());
        assertEquals(true, bookArrayList.get(1).isReadenByUser());
    }

    @Test
    public void deleteFromDbTest() {
        ArrayList<Book> bookArrayList = sqlManager.getValues();
        String deleteTitle = bookArrayList.get(0).getTitle();
        String deleteAuthor = bookArrayList.get(0).getAuthor();

        int how = sqlManager.deleteBookFromDb(deleteTitle,deleteAuthor);
        bookArrayList = sqlManager.getValues();
        for(Book b : bookArrayList){
            String title = b.getTitle();
            String author = b.getAuthor();
            boolean equals = title.equals(deleteTitle) && author.equals(deleteAuthor);
            assertEquals(false,equals);
        }


        assertEquals(1,how);
    }


    @Test
    public void editRecordsInDbIfEqualsThenOk(){
        ArrayList<Book> bookArrayList = sqlManager.getValues();

        sqlManager.editBookFromDb("Hobbit","Tolkien","Lord of the Rings","Son of Tolkien"
        ,"LORDS OF THE RINGS ","url",-1,0,0,0);

        assertEquals("Lord of the Rings",bookArrayList.get(0).getTitle());
        assertEquals("Son of Tolkien",bookArrayList.get(0).getAuthor());

    }


    @After
    public void afterEveryTest(){
        sqlManager.deleteAllRecordsFromDb();
    }

    @AfterClass
    public static void deleteDb(){
        context.deleteDatabase(SqlManager.getDbName());
    }


}
