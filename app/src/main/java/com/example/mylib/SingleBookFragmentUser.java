package com.example.mylib;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mylib.BackPressed.IOnBackPressed;
import com.example.mylib.Data.Book;
import com.example.mylib.Data.BookEdit;
import com.example.mylib.sql.SqlManager;

public class SingleBookFragmentUser extends Fragment implements IOnBackPressed, BookEdit {

    private Book clicked_Book;
    private SqlManager sqlManager;

    @Override
    public void onBackPressed() {
        getActivity().getSupportFragmentManager().popBackStack();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.singlebook_fragment_user, container, false);

        sqlManager = SqlManager.getInstance();





        return view;
    }

    @Override
    public void setBook(Book b) {
        this.clicked_Book = b;
    }
}
