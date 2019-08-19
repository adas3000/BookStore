package com.example.mylib;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.test.InstrumentationRegistry;

import com.example.mylib.Data.Book;
import com.example.mylib.Img.ImageDownloader;
import com.example.mylib.sql.SqlManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static androidx.test.InstrumentationRegistry.getContext;
import static org.junit.Assert.*;

public class AllBooksFragmentTest {

    private Context context;
    private ArrayList<Book> bookArrayList;
    private SqlManager sqlManager;

    @Before
    public void setup() {
        context = InstrumentationRegistry.getTargetContext();
        sqlManager = new SqlManager(context);
        sqlManager.addBookToDb("Adam", "Adam Z", "One day...",
                "https://java.sogeti.nl/JavaBlog/wp-content/uploads/2009/04/android_icon_256.png", 1, 1, 1, 1);
        bookArrayList = sqlManager.getValues(false);
    }


    @Test
    public void goodImageTest() throws Exception {
        Book b = bookArrayList.get(0);
        String title = b.getTitle();
        String author = b.getAuthor();
        String img_url = "https://java.sogeti.nl/JavaBlog/wp-content/uploads/2009/04/android_icon_256.png";
        TextView textView = new TextView(context);
        textView.setText(author + "\n" + title);
        textView.setTextSize(18);
        ImageView imageView = new ImageView(context);
        Bitmap bitmap = new ImageDownloader(imageView).execute(img_url).get();


        ImageView other_imageView = new ImageView(context);
        other_imageView.setImageResource(R.drawable.ic_action_name);


        Bitmap otherbitmap = ((BitmapDrawable)other_imageView.getDrawable()).getBitmap();

//        assertEquals("Adam\n Adam Z", textView.getText().toString());
        assertEquals(true,bitmap.sameAs(otherbitmap));
    }

    @After
    public void clear() {
        context.deleteDatabase(SqlManager.getDbName());
    }


}