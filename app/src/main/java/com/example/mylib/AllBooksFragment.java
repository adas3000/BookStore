package com.example.mylib;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mylib.BackPressed.IOnBackPressed;
import com.example.mylib.Data.Book;
import com.example.mylib.Data.ItemAdapter;
import com.example.mylib.Data.TextAndImageViewHelper;
import com.example.mylib.sql.SqlManager;

import java.util.ArrayList;

public class AllBooksFragment extends Fragment implements IOnBackPressed {

    private Context context;
    private SqlManager sqlManager;
    private boolean onlyReaden = false;

    ListView listView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.allbooks_activity, container, false);

        context = getContext();
        sqlManager = SqlManager.getInstance();
        final ArrayList<Book> bookArrayList = sqlManager.getValues();

        listView = view.findViewById(R.id.myListViewEmails);
        ItemAdapter itemAdapter = new ItemAdapter(view.getContext(),bookArrayList,onlyReaden);
        listView.setAdapter(itemAdapter);
        

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Book clicked_Book = bookArrayList.get(i);

                SingleBookFragment nextFragment = new SingleBookFragment();
                nextFragment.setBook(clicked_Book);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,nextFragment).addToBackStack(null).commit();
            }
        });

        return view;
    }

    public void setOnlyReaden(boolean b) {
        onlyReaden = b;
    }

    @Override
    public void onBackPressed() {
        getActivity().getSupportFragmentManager().popBackStack();
    }
}

