package com.example.mylib.Data;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mylib.AllBooksFragment;
import com.example.mylib.R;
import com.example.mylib.SingleBookFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TextAndImageViewHelper implements View.OnClickListener {


    private ArrayList<Book> bookArrayList;
    private RelativeLayout MainRL;
    private Context context;
    private AllBooksFragment allBooksFragment;
    private boolean onlyReaden;


    public static class Builder {

        private ArrayList<Book> bookArrayList;
        private RelativeLayout MainRL;
        private Context context;
        private AllBooksFragment allBooksFragment;

        private boolean onlyReaden = false;

        public Builder(ArrayList<Book> bookArrayList, RelativeLayout MainRL, AllBooksFragment allBooksFragment) {
            this.bookArrayList = bookArrayList;
            this.MainRL = MainRL;
            this.context = allBooksFragment.getContext();
            this.allBooksFragment = allBooksFragment;
        }


        public Builder onlyReaden(boolean onlyReaden) {
            this.onlyReaden = onlyReaden;
            return this;
        }

        public TextAndImageViewHelper build() {
            return new TextAndImageViewHelper(this);
        }

    }

    private TextAndImageViewHelper(Builder builder) {
        bookArrayList = builder.bookArrayList;
        MainRL = builder.MainRL;
        context = builder.context;
        onlyReaden = builder.onlyReaden;
        allBooksFragment = builder.allBooksFragment;
    }

    public void LoadStringAndImages() {

        //String URL = "https://image.ceneostatic.pl/data/products/9367217/49f352e8-fe40-4a0a-b2c5-f38ba07d1d3d_i-harry-potter-i-komnata-tajemnic-harry-potter-and-the-chamber-of-secrets-3dvd.jpg";

        final int image_widthDP = 800;
        final int image_heightDP = 500;
        int marginTop = 100, i = 0;

        for (Book b : bookArrayList) {

            if (!b.isReadenByUser() && onlyReaden) continue;

            TextView text = new TextView(context);
            text.setText(b.getAuthor() + "\n" + b.getTitle());
            text.setTextSize(18);
            text.setGravity(Gravity.CENTER);

            RelativeLayout.LayoutParams rll = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            rll.setMargins(0, marginTop, 0, 0);
            marginTop += 100;
            text.setLayoutParams(rll);

            rll = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT);

            rll.setMargins(0, marginTop, 0, 0);
            rll.height = image_heightDP;
            rll.width = image_widthDP;

            ImageView imageView = new ImageView(context);
            rll.setMargins(0, marginTop + RelativeLayout.LayoutParams.WRAP_CONTENT, 0, 0);
            imageView.setLayoutParams(rll);
            imageView.setClickable(true);
            imageView.setOnClickListener(this);
            imageView.setId(i++);
            marginTop += image_heightDP;

            Picasso.with(context).load(b.getImage_url()).placeholder(R.mipmap.ic_launcher).into(imageView);
            MainRL.addView(text);
            MainRL.addView(imageView);
        }
    }

    @Override
    public void onClick(View view) {
        //TODO add deleting and editing books by clicking on imageview -- add imageView on ClickListener


        Book clicked_Book = null;
        for (int i = 0; i < bookArrayList.size(); i++) {
            if (view.getId() == i) {
                clicked_Book = bookArrayList.get(i);
                break;
            }
        }

        SingleBookFragment nextFragment = new SingleBookFragment();
        nextFragment.setBook(clicked_Book);


        allBooksFragment.getActivity().getSupportFragmentManager().beginTransaction().
                replace(R.id.fragment_container, nextFragment).commit();


    }
}
