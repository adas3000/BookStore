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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.allbooks_activity, container, false);

        context = getContext();
        sqlManager = new SqlManager(context);
        ArrayList<Book> bookArrayList = sqlManager.getValues(false);

       // Picasso.with(getContext()).load(URL).into(imageView);
        RelativeLayout MainRL = view.findViewById(R.id.relative_layoutinsidescrollview);

        TextAndImageViewHelper.LoadStringAndImages(bookArrayList,MainRL,getContext());





        return view;
    }
}

