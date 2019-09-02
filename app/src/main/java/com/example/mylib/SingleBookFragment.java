package com.example.mylib;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mylib.BackPressed.IOnBackPressed;
import com.example.mylib.Data.Book;
import com.example.mylib.sql.SqlManager;
import com.squareup.picasso.Picasso;


public class SingleBookFragment extends Fragment implements IOnBackPressed {

    private Book clicked_Book;
    private SqlManager sqlManager;

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


        String readen = clicked_Book.isReadenByUser() ? "Readen :"+clicked_Book.getFinish_date() : "Not readen.";

        textView_BookReaden.setText(readen);

        Button edit_Button = view.findViewById(R.id.button_editBook);
        Button delete_Button = view.findViewById(R.id.button_deleteBook);


        edit_Button.setOnClickListener(view1 -> {

            AddBookFragment editBook = new AddBookFragment();
            editBook.setEditBook(clicked_Book);

         getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,editBook).addToBackStack(null).commit();
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
