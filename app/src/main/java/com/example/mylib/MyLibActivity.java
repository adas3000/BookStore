package com.example.mylib;

import android.os.Bundle;

import com.example.mylib.Data.Book;
import com.example.mylib.sql.SqlManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MyLibActivity extends AppCompatActivity {

    private ArrayList<TextView> book_title = new ArrayList<>();
    private ArrayList<TextView> book_author = new ArrayList<>();
    private ArrayList<TextView> book_description = new ArrayList<>();
    private SqlManager sqlManager = new SqlManager(getApplicationContext());
    private ArrayList<Book> bookArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mylib);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        bookArrayList = sqlManager.getValues(false); // get all values


        





    }

}
