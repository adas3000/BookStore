package com.example.mylib;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mylib.Data.Book;
import com.example.mylib.Data.TextAndImageViewHelper;
import com.example.mylib.sql.SqlManager;
import java.util.ArrayList;

public class AllBooksFragment extends Fragment {

    private Context context;
    private SqlManager sqlManager;
    private boolean onlyReaden = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.allbooks_activity, container, false);

        context = getContext();
        sqlManager = SqlManager.getInstance();
        ArrayList<Book> bookArrayList = sqlManager.getValues();




        return view;
    }

    public void setOnlyReaden(boolean b){onlyReaden = b;}
}

