package com.example.mylib.Data;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mylib.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@TargetApi(Build.VERSION_CODES.N)
public class ItemAdapter extends BaseAdapter implements Filterable {

    private LayoutInflater layoutInflater;
    private ArrayList<Book> bookArrayList_originalData;
    private ArrayList<Book> bookArrayList_filteredData;
    private boolean onlyReaden;
    private ItemFilter itemFilter = new ItemFilter();

    public ItemAdapter(Context context, ArrayList<Book> bookArrayList, boolean onlyReaden) {
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.bookArrayList_originalData = bookArrayList;
        this.bookArrayList_filteredData = bookArrayList;
        this.onlyReaden = onlyReaden;


        if (onlyReaden) {
            bookArrayList.removeIf(b -> !b.isReadenByUser());
        }
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

        TextView textView_AuthorAndTitle = v.findViewById(R.id.textView_AuthorandTitle);
        ImageView imageView_Cover = v.findViewById(R.id.imageView_Cover);


        Book book = bookArrayList_filteredData.get(i);

        textView_AuthorAndTitle.setText(book.getAuthor() + "\n" + book.getTitle());
        textView_AuthorAndTitle.setGravity(Gravity.CENTER);
        textView_AuthorAndTitle.setTextSize(18);
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

            results.values = bookArrayList_filteredData;
            results.count = bookArrayList_filteredData.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

        }
    }


}