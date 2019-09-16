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
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.mylib.BackPressed.IOnBackPressed;

import com.example.mylib.Data.AppData;
import com.example.mylib.Data.Book;
import com.example.mylib.Data.ItemAdapter;
import com.example.mylib.Data.Shelv_Type;
import com.example.mylib.sql.SqlManager;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AllBooksFragment extends Fragment implements IOnBackPressed {

    private SqlManager sqlManager;
    private ArrayList<Book> bookArrayList,allbookArrayList;
    private ListView listView;
    private ItemAdapter itemAdapter;
    private Spinner spinner_Show, spinner_sortBy, spinner_onshelvesFrom;
    private Shelv_Type shelv_type;


    public AllBooksFragment(Shelv_Type shelv_type){
        this.shelv_type = shelv_type;
    }

    public AllBooksFragment(){
        this.shelv_type = Shelv_Type.All;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
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

        AppData.getFab().show();

        if(!((AppCompatActivity)getActivity()).getSupportActionBar().isShowing())
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();

        sqlManager = SqlManager.getInstance();
        bookArrayList = sqlManager.getValues(shelv_type);
        allbookArrayList = sqlManager.getValues(shelv_type);


        listView = view.findViewById(R.id.myListViewEmails);
        itemAdapter = new ItemAdapter(view.getContext(), bookArrayList);

        LayoutInflater layoutInflater = getLayoutInflater();
        View header = layoutInflater.inflate(R.layout.listviewheader, listView, false);
        listView.addHeaderView(header, null, false);


        listView.setAdapter(itemAdapter);


        listView.setOnItemClickListener((adapterView, view1, i, l) -> {
           /// Book clicked_Book = bookArrayList.get(i - 1);
            Book clicked_Book = itemAdapter.getClickedBook(i-1);
            SingleBookFragment nextFragment = new SingleBookFragment();
            nextFragment.setBook(clicked_Book);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, nextFragment).addToBackStack(null).commit();
        });


        //spinners
        spinner_Show = view.findViewById(R.id.spinner_Show);
        spinner_sortBy = view.findViewById(R.id.spinner_Sort);

        itemAdapter.setSpinners(spinner_Show,spinner_sortBy);

        return view;
    }

    @Override
    public void onBackPressed() {
        getActivity().getSupportFragmentManager().popBackStack();
    }
}

