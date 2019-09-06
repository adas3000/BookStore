package com.example.mylib.Helpers;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.widget.ArrayAdapter;
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

        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setMessage(clickedBook.getTitle());


        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        TextView textView_rate = new TextView(context);
        TextView textView_shelf = new TextView(context);
        TextView textView_mShelfs = new TextView(context);


        textView_rate.setTextSize(18);
        textView_mShelfs.setTextSize(18);
        textView_shelf.setTextSize(18);
        textView_mShelfs.setTextColor(AppData.getContext().getColor(R.color.colorBlack));
        textView_shelf.setTextColor(AppData.getContext().getColor(R.color.colorBlack));
        textView_rate.setTextColor(AppData.getContext().getColor(R.color.colorBlack));


        textView_shelf.setText(AppData.getContext().getString(R.string.Shelf));
        textView_mShelfs.setText(AppData.getContext().getString(R.string.mShelfs));
        textView_rate.setText(AppData.getContext().getString(R.string.rateText));



        Spinner spinner_shelf = new Spinner(context);
        Spinner spinner_mShelfs = new Spinner(context);



        ArrayAdapter<CharSequence> adapter_shelf = ArrayAdapter.createFromResource(AppData.getContext(), R.array.user_book_state, android.R.layout.simple_spinner_item);
        adapter_shelf.setDropDownViewResource(R.layout.spinner_item);

        ArrayAdapter<CharSequence> adapter_mShelfs= ArrayAdapter.createFromResource(AppData.getContext(), R.array.user_book_shelfs, android.R.layout.simple_spinner_item);
        adapter_shelf.setDropDownViewResource(R.layout.spinner_item);


        spinner_shelf.setAdapter(adapter_shelf);
        spinner_mShelfs.setAdapter(adapter_mShelfs);


        RatingBar ratingBar = new RatingBar(context);
        ratingBar.setForegroundGravity(Gravity.CENTER);
        ratingBar.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
        ratingBar.setNumStars(AppData.rateMaxNum);


        linearLayout.addView(textView_rate);
        linearLayout.addView(ratingBar);
        linearLayout.addView(textView_shelf);
        linearLayout.addView(spinner_shelf);
        linearLayout.addView(textView_mShelfs);
        linearLayout.addView(spinner_mShelfs);


        dialog.setView(linearLayout);
        dialog.show();

    }


}
