package com.example.mylib;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mylib.sql.SqlManager;

public class AddBookFragment extends Fragment implements View.OnClickListener {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.addbook_activity, container, false);

        Button confirm_Button = view.findViewById(R.id.button);
        confirm_Button.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View view) {

        final EditText editText_Author = getActivity().findViewById(R.id.editText_Author);
        final EditText editText_Title = getActivity().findViewById(R.id.editText_Title);
        final EditText editText_desc = getActivity().findViewById(R.id.editText_desc);
        final EditText editText_url = getActivity().findViewById(R.id.editText_url);

        String author = editText_Author.getText().toString();
        String title = editText_Title.getText().toString();
        String desc = editText_desc.getText().toString();
        String url = editText_url.getText().toString();

        if (author.isEmpty() || title.isEmpty() || desc.isEmpty() || url.isEmpty()) {
            Toast.makeText(getActivity(), "Fill all fields.", Toast.LENGTH_SHORT).show();
            return;
        }


        SqlManager sqlManager = new SqlManager(getContext());
        sqlManager.addBookToDb(title, author, desc, url, 0, 0, 0, 0);
        Toast.makeText(getActivity(), "Book added successfully", Toast.LENGTH_SHORT).show();
        editText_Author.setText("");
        editText_Title.setText("");
        editText_desc.setText("");
        editText_url.setText("");
    }
}
