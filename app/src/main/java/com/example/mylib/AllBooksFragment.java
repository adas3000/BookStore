package com.example.mylib;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
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
    private ArrayList<TextView> tv_book = new ArrayList<>();
    private ArrayList<ImageView> iv_book = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.allbooks_activity, container, false);

        context = getContext();
        sqlManager = new SqlManager(context);
        ArrayList<Book> bookArrayList = sqlManager.getValues(false);

      //  TextView textView = view.findViewById(R.id.textView_Book);
      //  ImageView imageView = view.findViewById(R.id.imageView_Book);

      //  textView.setText(bookArrayList.get(0).getAuthor() + "\n" + bookArrayList.get(0).getTitle());

       // Picasso.with(getContext()).load(URL).into(imageView);
        RelativeLayout MainRL = view.findViewById(R.id.relativelayout);

        TextAndImageViewHelper.LoadStringAndImages(bookArrayList,MainRL,getContext());





        return view;
    }
}

