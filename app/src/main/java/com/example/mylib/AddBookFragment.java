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

public class AddBookFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.addbook_activity,container,false);

        Button confirm_Button = view.findViewById(R.id.button);

        final EditText editText_Author = view.findViewById(R.id.editText_Author);
        final EditText editText_Title = view.findViewById(R.id.editText_Title);
        final EditText editText_desc = view.findViewById(R.id.editText_desc);
        final EditText editText_url = view.findViewById(R.id.editText_url);

        confirm_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"HI",Toast.LENGTH_LONG);
                String author = editText_Author.getText().toString();
                String title = editText_Title.getText().toString();
                String desc = editText_desc.getText().toString();
                String url = editText_url.getText().toString();

                if(author.isEmpty() || title.isEmpty() || desc.isEmpty() || url.isEmpty()){
                    Toast.makeText(getContext(),"Fill all editTexts",Toast.LENGTH_SHORT);
                    return ;
                }
                SqlManager sqlManager = new SqlManager(getContext());
                sqlManager.addBookToDb(title,author,desc,url,0,0,0,0);
                Toast.makeText(getContext(),"Book added successfully",Toast.LENGTH_SHORT);
                editText_Author.setText("");
                editText_Title.setText("");
                editText_desc.setText("");
                editText_url.setText("");
            }
        });





        return inflater.inflate(R.layout.addbook_activity,container,false);
    }
}
