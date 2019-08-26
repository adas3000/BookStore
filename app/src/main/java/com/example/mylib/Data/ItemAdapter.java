package com.example.mylib.Data;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mylib.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

@TargetApi(Build.VERSION_CODES.N)
public class ItemAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private ArrayList<Book> bookArrayList;
    private boolean onlyReaden;


    public ItemAdapter(Context context,ArrayList<Book> bookArrayList,boolean onlyReaden){
        this.layoutInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.bookArrayList = bookArrayList;
        this.onlyReaden = onlyReaden;


        if(onlyReaden){
            bookArrayList.removeIf(b->!b.isReadenByUser());
        }
    }


    @Override
    public int getCount() {
        return bookArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return bookArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        View v =layoutInflater.inflate(R.layout.my_listview_detail,null);

        TextView textView_AuthorAndTitle =v.findViewById(R.id.textView_AuthorandTitle);
        ImageView imageView_Cover = v.findViewById(R.id.imageView_Cover);


        Book book = bookArrayList.get(i);

        textView_AuthorAndTitle.setText(book.getAuthor()+"\n"+book.getTitle());
        textView_AuthorAndTitle.setGravity(Gravity.CENTER);
        textView_AuthorAndTitle.setTextSize(18);
        Picasso.with(v.getContext()).load(book.getImage_url()).placeholder(R.mipmap.ic_launcher).into(imageView_Cover);

        return v;
    }
}
