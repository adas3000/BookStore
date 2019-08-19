package com.example.mylib;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mylib.Data.Book;
import com.example.mylib.Img.ImageDownloader;
import com.example.mylib.sql.SqlManager;

import java.util.ArrayList;

public class AllBooksFragment extends Fragment {

    private Context context;
    private SqlManager sqlManager;
    private ArrayList<TextView> tv_book = new ArrayList<>();
    private ArrayList<ImageView> iv_book = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        context = getContext();
        sqlManager = new SqlManager(context);
        ArrayList<Book> bookArrayList = sqlManager.getValues(false);

      /*  for(Book b : bookArrayList){
            String title = b.getTitle();
            String author = b.getAuthor();
            TextView tv = new TextView(context);
            tv.setTextSize(18);
            tv.setText(author+"\n"+title);
            tv_book.add(tv);
        }*/





        return inflater.inflate(R.layout.allbooks_activity,container,false);
    }




}
