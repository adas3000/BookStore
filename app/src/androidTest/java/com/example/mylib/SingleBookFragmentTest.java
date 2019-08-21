package com.example.mylib;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.test.InstrumentationRegistry;

import com.example.mylib.Data.Book;
import com.example.mylib.sql.SqlManager;
import com.squareup.picasso.Picasso;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static androidx.test.InstrumentationRegistry.getContext;
import static org.junit.Assert.*;

public class SingleBookFragmentTest {


    private Book clicked_Book;
    private Context context;
    private View view;
    private TextView textView_Book,textView_Description;
    private ImageView imageView;


    @Before
    public void setup(){
        context = InstrumentationRegistry.getTargetContext();
        clicked_Book = new Book("From animals to gods","Yuval Noash Harari","...", "https://image.ceneostatic.pl/data/products/9367217/49f352e8-fe40-4a0a-b2c5-f38ba07d1d3d_i-harry-potter-i-komnata-tajemnic-harry-potter-and-the-chamber-of-secrets-3dvd.jpg",
                false,0,0,0);
        view = new View(context);
        textView_Book = new TextView(context);
        textView_Description = new TextView(context);
        imageView = new ImageView(context);
    }

    @Test
    public void checkTextViewAndDownloadThroughtPicasso(){

        textView_Book.setGravity(Gravity.CENTER);
        textView_Book.setText(clicked_Book.getAuthor()+"\n"+clicked_Book.getTitle());
        textView_Description.setText(clicked_Book.getShort_description());
       // Picasso.with(context).load(clicked_Book.getImage_url()).placeholder(R.mipmap.ic_launcher).into(imageView);


        Handler uiHandler = new Handler(Looper.getMainLooper());
        uiHandler.post(new Runnable(){
            @Override
            public void run() {
                Picasso.with(context)
                        .load(clicked_Book.getImage_url()).placeholder(R.mipmap.ic_launcher)
                        .into(imageView);
            }
        });



        assertEquals(Gravity.CENTER,textView_Book.getGravity());
        assertEquals("Yuval Noash Harari"+'\n'+"From animals to gods",textView_Book.getText().toString());
        assertEquals("...",textView_Description.getText().toString());
       // assertEquals(true,imageView.getDrawable()!=null);
    }

    @After
    public void onFinish(){
        context.deleteDatabase(SqlManager.getDbName());
    }


}