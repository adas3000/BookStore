package com.example.mylib;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mylib.Data.Book;
import com.example.mylib.sql.SqlManager;

import java.util.ArrayList;

public class LibFragment extends Fragment {

    private ArrayList<ImageView> book_imageView = new ArrayList<>();
    private ArrayList<TextView> book_textView = new ArrayList<>();
    private Context context = getContext();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mylib_activity,container,false);
    }

    public LibFragment(){
        SqlManager sqlManager = new SqlManager(context);
        ArrayList<Book>bookArrayList = sqlManager.getValues(true);

        for(Book book : bookArrayList){
           // book_textView.add(new TextView(context))
        }



    }



}
