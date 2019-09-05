package com.example.mylib;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mylib.BackPressed.IOnBackPressed;
import com.example.mylib.Data.Book;
import com.example.mylib.sql.SqlManager;

import java.sql.Date;
import java.util.Calendar;

@TargetApi(25)
public class AddBookFragment extends Fragment implements View.OnClickListener, IOnBackPressed {


    private Date date = new Date(Calendar.getInstance().getTimeInMillis());
    private DatePickerDialog datePickerDialog;
    private boolean editBook;
    private Book book_toEdit;
    private EditText editText_Author;
    private EditText editText_Title;
    private EditText editText_desc;
    private EditText editText_url;
    private Switch bookReaden_Switch;

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


        final Button confirm_Button = view.findViewById(R.id.button);
        confirm_Button.setOnClickListener(this);

        final Switch bookReaden_Switch = view.findViewById(R.id.switch1);


        bookReaden_Switch.setOnCheckedChangeListener((compoundButton, b) -> {
            Calendar calendar = Calendar.getInstance();
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);



            if (compoundButton.isChecked()) {
                datePickerDialog = new DatePickerDialog(getContext(), (datePicker, year1, month1, day1) -> {
                    bookReaden_Switch.setText("Readen:" + day1 + "/" + month1  + "/" + year1);
                    date = new Date(year1-1900, month1 , day1);
                    },year,month,day);
                datePickerDialog.show();

                datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", (dialogInterface, i) -> {
                    compoundButton.setChecked(false);
                });

            } else {
                bookReaden_Switch.setText("Not readen");
            }


        });


        return view;
    }


    @Override
    public void onClick(View view) {

        int int_readen;
        boolean readen = bookReaden_Switch.isChecked();

        if (readen)
            int_readen = 1;
        else
            int_readen = -1;


        String author = editText_Author.getText().toString();
        String title = editText_Title.getText().toString();
        String desc = editText_desc.getText().toString();
        String url = editText_url.getText().toString();

        if (author.isEmpty() || title.isEmpty() || url.isEmpty()) {
            Toast.makeText(getActivity(), "Fill all fields.", Toast.LENGTH_SHORT).show();
            return;
        }


        SqlManager sqlManager = SqlManager.getInstance();

        String text_to_Show = "Book added successfully";
        if (!editBook);
           // sqlManager.addBookToDb(title, author, desc, url, int_readen, date);

        else {
            sqlManager.editBookFromDb(book_toEdit.getTitle(), book_toEdit.getAuthor(),
                    title, author, desc, url, int_readen,date);
            text_to_Show = text_to_Show.replace("added", "edited");
        }

        Toast.makeText(getActivity(), text_to_Show, Toast.LENGTH_SHORT).show();
        editText_Author.setText("");
        editText_Title.setText("");
        editText_desc.setText("");
        editText_url.setText("");
        bookReaden_Switch.setChecked(false);
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
        bookReaden_Switch = view.findViewById(R.id.switch1);
    }

}