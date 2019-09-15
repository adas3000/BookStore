package com.example.mylib;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.mylib.BackPressed.IOnBackPressed;
import com.example.mylib.Data.Book;
import com.example.mylib.Data.BookEdit;
import com.example.mylib.Helpers.EditButtonEvent;
import com.example.mylib.sql.SqlManager;
import com.squareup.picasso.Picasso;


public class SingleBookFragment extends Fragment implements IOnBackPressed, BookEdit {

    private Book clicked_Book;
    private SqlManager sqlManager;
    private String setBookShelfs(){
        String readen = "On shelfs: ";

        switch(clicked_Book.getBook_reading_state()){
            case 1:
                readen = "Finished date:"+clicked_Book.getFinish_date()+"\n";
                break;
            case 2:
                readen="Reading now";
                break;
            case 3:
                readen="Wants to read";
                break;
        }
        if(clicked_Book.isBook_is_favorite() || clicked_Book.isUser_has_book())
            readen += "On shelfs:";

        if(clicked_Book.isUser_has_book()){
            readen+=" I Have ";
        }
        if(clicked_Book.isBook_is_favorite()){
            if(clicked_Book.isUser_has_book())
                readen+="and";
            readen += " Favorite ";
        }
       return readen;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.singlebook_fragment, container, false);

        sqlManager = SqlManager.getInstance();


        TextView textView_Book = view.findViewById(R.id.textView_book);
        TextView textView_Description = view.findViewById(R.id.textView_description);
        TextView textView_BookReaden = view.findViewById(R.id.textView_readen);
        ImageView imageView = view.findViewById(R.id.imageView_SingleBook);
        textView_Book.setGravity(Gravity.CENTER);
        textView_Book.setText(clicked_Book.getAuthor() + "\n" + clicked_Book.getTitle());
        textView_Description.setText(clicked_Book.getShort_description());

        Picasso.with(getContext()).load(clicked_Book.getImage_url()).placeholder(R.mipmap.ic_launcher).into(imageView);

        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();


        textView_BookReaden.setText(setBookShelfs());

        Button edit_Button = view.findViewById(R.id.button_editBook);
        Button delete_Button = view.findViewById(R.id.button_deleteBook);

        Log.d("Something","asd");


        edit_Button.setOnClickListener(view1 -> {
            EditButtonEvent.HandleEditButtonClicked(view.getContext(),clicked_Book);
            textView_BookReaden.setText(setBookShelfs());

        });


        delete_Button.setOnClickListener(view12 -> {

            DialogInterface.OnClickListener dialogClickListener = (dialogInterface, i) -> {

                switch (i) {
                    case DialogInterface.BUTTON_POSITIVE:
                        sqlManager.deleteBookFromDb(clicked_Book.getTitle(), clicked_Book.getAuthor());
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, new AllBooksFragment()).commit();
                        break;
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener).show();

        });


        return view;
    }

    public void setBook(Book b) {
        clicked_Book = b;
    }


    @Override
    public void onBackPressed() {
        getActivity().getSupportFragmentManager().popBackStack();
    }
}
