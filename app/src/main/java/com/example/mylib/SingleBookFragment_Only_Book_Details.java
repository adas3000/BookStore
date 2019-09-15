package com.example.mylib;

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
import com.squareup.picasso.Picasso;

public class SingleBookFragment_Only_Book_Details extends Fragment {


    private Book clicked_Book;

    public void setClickedBook(Book b){
        clicked_Book = b;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.singlebook_fragment_only_book_details,container,false);

        TextView tv_desc = view.findViewById(R.id.textView_description_sfobd);
        TextView tv_Book = view.findViewById(R.id.textView_book_sfobd);
        ImageView imageView = view.findViewById(R.id.imageView_SingleBook_sfobd);

        Picasso.with(getContext()).load(clicked_Book.getImage_url()).placeholder(R.mipmap.ic_launcher).into(imageView);
        tv_Book.setText(clicked_Book.getAuthor() + "\n" + clicked_Book.getTitle());
        tv_desc.setText(clicked_Book.getShort_description());


        return view;
    }
}
