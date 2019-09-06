package com.example.mylib;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mylib.BackPressed.IOnBackPressed;
import com.example.mylib.Data.AppData;
import com.example.mylib.Data.Book;
import com.example.mylib.sql.SqlManager;

import java.sql.Date;
import java.util.Calendar;

@TargetApi(25)
public class AddBookFragment extends Fragment implements View.OnClickListener, IOnBackPressed {


    private boolean editBook;
    private Book book_toEdit;
    private EditText editText_Author;
    private EditText editText_Title;
    private EditText editText_desc;
    private EditText editText_url;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        LinearLayout view = (LinearLayout) inflater.inflate(R.layout.addbook_activity, container, false);


        setEditTexts(view);


        if (editBook) {
            editText_Author.setText(book_toEdit.getAuthor());
            editText_Title.setText(book_toEdit.getTitle());
            editText_desc.setText(book_toEdit.getShort_description());
            editText_url.setText(book_toEdit.getImage_url());


        }


        Button confirm_Button = view.findViewById(R.id.button);
        confirm_Button.setOnClickListener(this);


        return view;
    }


    @Override
    public void onClick(View view) {


        String author = editText_Author.getText().toString();
        String title = editText_Title.getText().toString();
        String desc = editText_desc.getText().toString();
        String url = editText_url.getText().toString();

        if (author.isEmpty() || title.isEmpty()) {
            Toast.makeText(getActivity(), "Fill fields author and title.", Toast.LENGTH_SHORT).show();
            return;
        }


        SqlManager sqlManager = SqlManager.getInstance();

        String text_to_Show = "Book added successfully";
        if (!editBook)
             sqlManager.addBookToDb(title, author, desc, url);


        Toast.makeText(getActivity(), text_to_Show, Toast.LENGTH_SHORT).show();
        editText_Author.setText("");
        editText_Title.setText("");
        editText_desc.setText("");
        editText_url.setText("");
    }

    @Override
    public void onBackPressed() {
        getActivity().getSupportFragmentManager().popBackStack();
    }


    public void setEditBook(Book book) {
        this.book_toEdit = book;
        this.editBook = true;
    }


    private void setEditTexts(LinearLayout view) {
        editText_Author = view.findViewById(R.id.editText_Author);
        editText_Title = view.findViewById(R.id.editText_Title);
        editText_desc = view.findViewById(R.id.editText_desc);
        editText_url = view.findViewById(R.id.editText_url);
    }

}