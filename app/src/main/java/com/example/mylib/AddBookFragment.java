package com.example.mylib;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mylib.BackPressed.IOnBackPressed;
import com.example.mylib.sql.SqlManager;

import java.sql.Date;
import java.util.Calendar;

@TargetApi(Build.VERSION_CODES.N_MR1)
public class AddBookFragment extends Fragment implements View.OnClickListener, IOnBackPressed {

    private Date date = new Date(0, 0, 0);
    private DatePickerDialog datePickerDialog;
    private boolean editBook;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.addbook_activity, container, false);

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
                    bookReaden_Switch.setText("Readen:" + day1 + "/" + month1 + 1 + "/" + year1);
                    date = new Date(year1, month1 + 1, day1);
                }, day, month, year);
                datePickerDialog.show();
            } else {
                bookReaden_Switch.setText("Not readen");
                date = new Date(0, 0, 0);
            }
        });


        return view;
    }


    @Override
    public void onClick(View view) {

        final EditText editText_Author = getActivity().findViewById(R.id.editText_Author);
        final EditText editText_Title = getActivity().findViewById(R.id.editText_Title);
        final EditText editText_desc = getActivity().findViewById(R.id.editText_desc);
        final EditText editText_url = getActivity().findViewById(R.id.editText_url);

        final Switch bookReaden_Switch = getActivity().findViewById(R.id.switch1);

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
        sqlManager.addBookToDb(title, author, desc, url, int_readen, date.getYear(), date.getMonth(), date.getDay());


        Toast.makeText(getActivity(), "Book added successfully", Toast.LENGTH_SHORT).show();
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

    public void setEditBook(boolean b) {
        this.editBook = b;
    }

}