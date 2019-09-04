package com.example.mylib;

import android.annotation.TargetApi;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;

import com.example.mylib.BackPressed.IOnBackPressed;
import com.example.mylib.Data.Book;
import com.example.mylib.Data.ItemAdapter;
import com.example.mylib.sql.SqlManager;

import java.util.ArrayList;

public class AllBooksFragment extends Fragment implements IOnBackPressed {

    private SqlManager sqlManager;
    private ArrayList<Book> bookArrayList;
    private ListView listView;
    private ItemAdapter itemAdapter;


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchManager searchManager =(SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                itemAdapter.getFilter().filter(s);
                return false;
            }
        });


        super.onCreateOptionsMenu(menu, inflater);
    }


    @TargetApi(25)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        setHasOptionsMenu(true);


        View view = inflater.inflate(R.layout.allbooks_activity, container, false);


        sqlManager = SqlManager.getInstance();
        bookArrayList = sqlManager.getValues();

        listView = view.findViewById(R.id.myListViewEmails);
        itemAdapter = new ItemAdapter(view.getContext(), bookArrayList);



        listView.setAdapter(itemAdapter);


        listView.setOnItemClickListener((adapterView, view1, i, l) -> {
            Book clicked_Book = bookArrayList.get(i);

            SingleBookFragment nextFragment = new SingleBookFragment();
            nextFragment.setBook(clicked_Book);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, nextFragment).addToBackStack(null).commit();
        });


     /*   EditText editText_Search = view.findViewById(R.id.editText_Search);
        editText_Search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                itemAdapter.getFilter().filter(charSequence);


            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });
*/


        return view;
    }

    @Override
    public void onBackPressed() {
        getActivity().getSupportFragmentManager().popBackStack();
    }
}

