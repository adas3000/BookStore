package com.example.mylib.Data;

import android.content.Context;
import android.graphics.Path;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class TextAndImageViewHelper {


    public static void LoadStringAndImages(ArrayList<Book> bookArrayList, RelativeLayout MainRL, Context context) {

        String URL = "https://image.ceneostatic.pl/data/products/9367217/49f352e8-fe40-4a0a-b2c5-f38ba07d1d3d_i-harry-potter-i-komnata-tajemnic-harry-potter-and-the-chamber-of-secrets-3dvd.jpg";


        for (Book b : bookArrayList) {
            TextView text = new TextView(context);
            text.setText(b.getAuthor() + "\n" + b.getTitle());
            text.setTextSize(18);
            text.setGravity(Gravity.CENTER);

            RelativeLayout.LayoutParams rll = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            rll.setMargins(0, 100, 0, 0);

            text.setLayoutParams(rll);

            rll = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT);

            rll.setMargins(0,200,0,0);
            rll.height = 500;
            rll.width = 600;

            ImageView imageView = new ImageView(context);
            rll.setMargins(0, 200+RelativeLayout.LayoutParams.WRAP_CONTENT, 0, 0);
            imageView.setLayoutParams(rll);


            Picasso.with(context).load(URL).into(imageView);
            MainRL.addView(text);
            MainRL.addView(imageView);
        }
    }

}
