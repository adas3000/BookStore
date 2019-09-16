package com.example.mylib.Data;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.mylib.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@TargetApi(Build.VERSION_CODES.N)
public class ItemAdapter extends BaseAdapter implements Filterable {

    private LayoutInflater layoutInflater;
    private List<Book> bookArrayList_originalData;
    private List<Book> bookArrayList_filteredData;
    private ItemFilter itemFilter = new ItemFilter();

    public ItemAdapter(Context context, ArrayList<Book> bookArrayList) {
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.bookArrayList_originalData = bookArrayList;
        this.bookArrayList_filteredData = bookArrayList;

        //handle spinners
    }

    public Book getClickedBook(int i){
        return bookArrayList_filteredData.get(i);
    }

    public void setSpinners(Spinner spinner_Show,Spinner spinner_sortBy,Shelv_Type shelv_type){


        ArrayAdapter<CharSequence> spinner_ShowAdapter = ArrayAdapter.createFromResource(AppData.getContext(), R.array.showArgs,
                android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> spinner_sortAdapter = ArrayAdapter.createFromResource(AppData.getContext(), R.array.sortByArgs,
                android.R.layout.simple_spinner_item);

        spinner_ShowAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner_sortAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner_Show.setAdapter(spinner_ShowAdapter);
        spinner_sortBy.setAdapter(spinner_sortAdapter);




        spinner_Show.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                float rate = i-1;

                if(rate==0 || rate==-1)
                    bookArrayList_filteredData = bookArrayList_originalData;

                else {
                    Stream<Book> bookStream = bookArrayList_originalData.stream();
                    bookArrayList_filteredData = bookStream.filter(b -> b.getMark() == rate).collect(Collectors.toCollection(ArrayList::new));
                }
                notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner_sortBy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {



                notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



    }


    @Override
    public int getCount() {
        return bookArrayList_filteredData.size();
    }

    @Override
    public Object getItem(int i) {
        return bookArrayList_filteredData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = layoutInflater.inflate(R.layout.my_listview_detail, null);

        ImageView imageView_Cover = v.findViewById(R.id.imageView_Cover);

        TextView textView_Author = v.findViewById(R.id.textViewAuthor);
        TextView textView_Title = v.findViewById(R.id.textViewTitle);


        Book book = bookArrayList_filteredData.get(i);

        textView_Title.setText(book.getTitle());
        textView_Author.setText(book.getAuthor());




        Picasso.with(v.getContext()).load(book.getImage_url()).placeholder(R.mipmap.ic_launcher).into(imageView_Cover);

        return v;
    }

    @Override
    public Filter getFilter() {
        return itemFilter;
    }


    private class ItemFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence str) {
            String find = str.toString().toLowerCase();

            FilterResults results = new FilterResults();

            Stream<Book> bookStreamFilter = bookArrayList_originalData.stream();

            bookArrayList_filteredData = bookStreamFilter.filter(g -> g.getTitle().toLowerCase().contains(find) || g.getAuthor().toLowerCase().contains(find))
                    .collect(Collectors.toCollection(ArrayList::new));


            if (bookArrayList_filteredData.isEmpty()) {
                bookArrayList_filteredData = bookArrayList_originalData;
            }


            results.values = bookArrayList_filteredData;
            results.count = bookArrayList_filteredData.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            notifyDataSetChanged();
        }
    }


}