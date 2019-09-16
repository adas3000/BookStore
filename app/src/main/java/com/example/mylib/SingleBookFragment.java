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
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.mylib.BackPressed.IOnBackPressed;
import com.example.mylib.Data.Book;
import com.example.mylib.Data.BookEdit;
import com.example.mylib.Helpers.AfterEditing;
import com.example.mylib.Helpers.EditButtonEvent;
import com.example.mylib.sql.SqlManager;
import com.squareup.picasso.Picasso;


public class SingleBookFragment extends Fragment implements IOnBackPressed, BookEdit, AfterEditing {

    private Book clicked_Book;
    private SqlManager sqlManager;
    private RatingBar ratingBar_rate;

    public String setBookShelfs() {
        String readen;

        switch (clicked_Book.getBook_reading_state()) {
            case 1:
                readen = "Finished date:" + clicked_Book.getFinish_date() ;
                break;
            case 2:
                readen = "Reading now";
                break;
            case 3:
                readen = "Wants to read";
                break;
            default:
                readen = "Not added.";
                break;
        }
        if (clicked_Book.isBook_is_favorite() || clicked_Book.isUser_has_book())
            readen += "\nOn shelfs:";

        if (clicked_Book.isUser_has_book()) {
            readen += " I Have ";
        }
        if (clicked_Book.isBook_is_favorite()) {
            if (clicked_Book.isUser_has_book())
                readen += "and";
            readen += " Favorite ";
        }
        ratingBar_rate.setRating(clicked_Book.getMark());

        return readen;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.singlebook_fragment, container, false);

        sqlManager = SqlManager.getInstance();

        ratingBar_rate = view.findViewById(R.id.ratingBar);
        TextView textView_Book = view.findViewById(R.id.textView_book);
        TextView textView_BookReaden = view.findViewById(R.id.textView_readen);
        ImageView imageView = view.findViewById(R.id.imageView_SingleBook);
        textView_Book.setGravity(Gravity.CENTER);
        textView_Book.setText(clicked_Book.getAuthor() + "\n" + clicked_Book.getTitle());

        Picasso.with(getContext()).load(clicked_Book.getImage_url()).placeholder(R.mipmap.ic_launcher).into(imageView);

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();


        ratingBar_rate.setRating(clicked_Book.getMark());

        if(clicked_Book.getBook_reading_state()!=1) ratingBar_rate.setVisibility(View.INVISIBLE);

        textView_BookReaden.setText(setBookShelfs());

        Button edit_Button = view.findViewById(R.id.button_editBook);
        Button delete_Button = view.findViewById(R.id.button_deleteBook);


        edit_Button.setOnClickListener(view1 -> {
            EditButtonEvent.HandleEditButtonClicked(view.getContext(), clicked_Book, this::setBookShelfs, textView_BookReaden,ratingBar_rate);
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


        imageView.setOnClickListener(view1 -> {

            SingleBookFragment_Only_Book_Details singleBookFragment_only_book_details = new SingleBookFragment_Only_Book_Details();
            singleBookFragment_only_book_details.setClickedBook(clicked_Book);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, singleBookFragment_only_book_details).addToBackStack(null).commit();
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
