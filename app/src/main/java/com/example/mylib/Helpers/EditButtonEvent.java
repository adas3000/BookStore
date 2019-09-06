package com.example.mylib.Helpers;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;


import com.example.mylib.Data.AppData;
import com.example.mylib.Data.Book;
import com.example.mylib.R;


public class EditButtonEvent {

    private EditButtonEvent() {
    }

    @TargetApi(23)
    public static void HandleEditButtonClicked(Context context, Book clickedBook) {

        float rate = clickedBook.getMark();
        boolean has_book_on_own = clickedBook.isUser_has_book();
        boolean book_is_favor = clickedBook.isBook_is_favorite();
        int on_shelv = clickedBook.getBook_reading_state();


        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setMessage(clickedBook.getTitle());


        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        TextView textView_rate = new TextView(context);
        TextView textView_shelf = new TextView(context);
        TextView textView_mShelfs = new TextView(context);
        TextView textView_finishDate = new TextView(context);


        textView_rate.setTextSize(18);
        textView_mShelfs.setTextSize(18);
        textView_shelf.setTextSize(18);
        textView_finishDate.setTextSize(18);
        textView_mShelfs.setTextColor(AppData.getContext().getColor(R.color.colorBlack));
        textView_shelf.setTextColor(AppData.getContext().getColor(R.color.colorBlack));
        textView_rate.setTextColor(AppData.getContext().getColor(R.color.colorBlack));
        textView_finishDate.setTextColor(AppData.getContext().getColor(R.color.colorBlack));


        textView_shelf.setText(AppData.getContext().getString(R.string.Shelf));
        textView_mShelfs.setText(AppData.getContext().getString(R.string.mShelfs));
        textView_rate.setText(AppData.getContext().getString(R.string.rateText));
        textView_finishDate.setText(AppData.getContext().getString(R.string.finish_readingtext));



        Spinner spinner_shelf = new Spinner(context);
        Spinner spinner_mShelfs = new Spinner(context);


        ArrayAdapter<CharSequence> adapter_shelf = ArrayAdapter.createFromResource(AppData.getContext(), R.array.user_book_state, android.R.layout.simple_spinner_item);
        adapter_shelf.setDropDownViewResource(R.layout.spinner_item);

        ArrayAdapter<CharSequence> adapter_mShelfs = ArrayAdapter.createFromResource(AppData.getContext(), R.array.user_book_shelfs, android.R.layout.simple_spinner_item);
        adapter_shelf.setDropDownViewResource(R.layout.spinner_item);


        spinner_shelf.setAdapter(adapter_shelf);
        spinner_mShelfs.setAdapter(adapter_mShelfs);


        Button save_Button = new Button(context);
        save_Button.setText(AppData.getContext().getResources().getString(R.string.saveButtonText));
        save_Button.setBackgroundColor(Color.parseColor("#d4d4d4"));
        save_Button.setTextColor(Color.parseColor("#4a4a4a"));


        RatingBar ratingBar = new RatingBar(context);
        ratingBar.setForegroundGravity(Gravity.CENTER);
        ratingBar.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
        ratingBar.setNumStars(AppData.rateMaxNum);
        ratingBar.setRating(rate);


        ratingBar.setOnRatingBarChangeListener((ratingBar1, v, b) -> {

        });

        save_Button.setOnClickListener(view -> {

        });

        spinner_shelf.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                String[] args = AppData.getContext().getResources().getStringArray(R.array.user_book_state);
                String selected_item = spinner_shelf.getSelectedItem().toString();


                if (selected_item.equals(args[0])) {

                    if(!(textView_rate.getParent()==null)) return ;

                    linearLayout.addView(textView_rate,0);
                    linearLayout.addView(ratingBar,1);
                    linearLayout.addView(textView_finishDate,linearLayout.getChildCount()-1);
                } else {
                    linearLayout.removeView(textView_rate);
                    linearLayout.removeView(ratingBar);
                    linearLayout.removeView(textView_finishDate);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });



        linearLayout.addView(textView_shelf);
        linearLayout.addView(spinner_shelf);
        linearLayout.addView(textView_mShelfs);
        linearLayout.addView(spinner_mShelfs);
        linearLayout.addView(save_Button);

        if(on_shelv == 1) {
            linearLayout.addView(textView_rate,0);
            linearLayout.addView(ratingBar,1);
            linearLayout.addView(textView_finishDate,linearLayout.getChildCount()-1);
        }




        dialog.setView(linearLayout);
        dialog.show();

    }


}
