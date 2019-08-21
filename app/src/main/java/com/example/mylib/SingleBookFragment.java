package com.example.mylib;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mylib.Data.Book;
import com.squareup.picasso.Picasso;

public class SingleBookFragment extends Fragment {

    private Book clicked_Book;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.singlebook_fragment,container,false);

        return view;
    }

    public void setBook(Book b){
        clicked_Book = b;
        TextView textView_Book = getView().findViewById(R.id.textView_book);
        TextView textView_Description = getView().findViewById(R.id.textView_description);
        ImageView imageView = getView().findViewById(R.id.imageView_SingleBook);
        textView_Book.setGravity(Gravity.CENTER);
        textView_Book.setText(clicked_Book.getAuthor()+"\n"+clicked_Book.getTitle());
        textView_Description.setText(clicked_Book.getShort_description());

        Picasso.with(getContext()).load(clicked_Book.getImage_url()).placeholder(R.mipmap.ic_launcher).into(imageView);
    }

}
