package com.example.mylib;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.test.InstrumentationRegistry;
import com.example.mylib.Data.Book;
import com.example.mylib.Img.ImageDownloader;
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
    private Bitmap bitmap;

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

        new GetImageFromURL(imageView).execute(img_url);


        ImageView other_imageView = new ImageView(context);
        other_imageView.setImageResource(R.drawable.ic_action_name);
        Bitmap otherbitmap = ((BitmapDrawable)other_imageView.getDrawable()).getBitmap();

        assertEquals(true,bitmap.sameAs(otherbitmap));
    }

    @After
    public void clear() {
        context.deleteDatabase(SqlManager.getDbName());
    }

    public class GetImageFromURL extends AsyncTask<String,Void,Bitmap> {
        ImageView imgV;

        public GetImageFromURL(ImageView imgV){
            this.imgV = imgV;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String url = strings[0];
            bitmap = null;
            try{
                InputStream in = new java.net.URL(url).openStream();
                bitmap = BitmapFactory.decodeStream(in);
            }catch(Exception e){
                e.fillInStackTrace();
            }
            return bitmap;
        }

        @Override
        protected  void onPostExecute(Bitmap bitmap){
            super.onPostExecute(bitmap);
            imgV.setImageBitmap(bitmap);
        }


    }




}