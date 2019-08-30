package com.example.mylib.Data;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.test.InstrumentationRegistry;

import com.example.mylib.AllBooksFragment;
import com.example.mylib.sql.SqlManager;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TextAndImageViewHelperTest {


    private ArrayList<Book> bookArrayList;
    private Context context ;
    private SqlManager sqlManager;
    private ArrayList<ImageView> imageViewArrayList = new ArrayList<>();

    @Before
    public void setup(){
        
        context = InstrumentationRegistry.getTargetContext();
        SqlManager.init(context);
        sqlManager = SqlManager.getInstance();
        bookArrayList = sqlManager.getValues();
        int i=0;
        for(Book b : bookArrayList){
            ImageView imageView = new ImageView(context);
            imageView.setId(i++);
            imageViewArrayList.add(imageView);
        }

    }



    @Test
    public void checkId(){

        for(int i=0 ; i<bookArrayList.size() ; i++){
            assertEquals(true,i==imageViewArrayList.get(i).getId());
        }


        
    }


}