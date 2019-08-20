package com.example.mylib;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;
import androidx.test.InstrumentationRegistry;
import com.example.mylib.Data.Book;
import com.example.mylib.sql.SqlManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.InputStream;
import java.util.ArrayList;
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


        String img_url = "https://java.sogeti.nl/JavaBlog/wp-content/uploads/2009/04/android_icon_256.png";
        ImageView imageView = new ImageView(context);

        Bitmap bitmap = null;

        try{
            InputStream in = new java.net.URL(img_url).openStream();
            bitmap = BitmapFactory.decodeStream(in);
        }catch(Exception e){
            e.fillInStackTrace();
        }
        imageView.setImageBitmap(bitmap);
        ImageView other_imageView = new ImageView(context);
        other_imageView.setImageResource(R.drawable.ic_action_name);
        Bitmap otherbitmap = ((BitmapDrawable)other_imageView.getDrawable()).getBitmap();


        assertEquals(true,otherbitmap.sameAs(otherbitmap));
    }

    @After
    public void clear() {
        context.deleteDatabase(SqlManager.getDbName());
    }


}