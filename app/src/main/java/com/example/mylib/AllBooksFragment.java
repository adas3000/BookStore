package com.example.mylib;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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

        View view = inflater.inflate(R.layout.allbooks_activity, container, false);

        context = getContext();
        sqlManager = new SqlManager(context);
        ArrayList<Book> bookArrayList = sqlManager.getValues(false);

        RelativeLayout relativeLayout = view.findViewById(R.id.relativelayout);

        for(Book b : bookArrayList){
            TextView tv = new TextView(context);
            tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            tv.setTextSize(20);
            tv.setTextColor(Color.BLACK);
            tv.setText(b.getAuthor()+"\n"+b.getTitle());
            relativeLayout.addView(tv);
        }




        return view;
    }




}
