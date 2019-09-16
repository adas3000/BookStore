package com.example.mylib.Helpers;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;


import com.example.mylib.Data.AppData;
import com.example.mylib.Data.Book;
import com.example.mylib.R;
import com.example.mylib.sql.SqlManager;
import com.google.android.material.chip.Chip;

import java.sql.Date;
import java.util.Calendar;

import static com.example.mylib.Data.AppData.getContext;


public class EditButtonEvent {





    @TargetApi(23)
    public static void HandleEditButtonClicked(Context context, Book clickedBook,AfterEditing afterEditing,TextView setOn,RatingBar parentRatingBar) {

        Book clickedBook_ref = new Book.Builder(clickedBook.getTitle(),clickedBook.getAuthor(),clickedBook.getShort_description(),clickedBook.getImage_url())
                .book_Is_Favorite(clickedBook.isBook_is_favorite()).book_Reading_State(clickedBook.getBook_reading_state()).user_Has_Book(clickedBook.isUser_has_book())
                .date(clickedBook.getFinish_date()).Mark(clickedBook.getMark()).build();



        if (clickedBook_ref.getFinish_date() == null) {
            clickedBook_ref.setFinish_date(new Date(Calendar.getInstance().getTimeInMillis()));
        }
        if(clickedBook_ref.getBook_reading_state()==0) clickedBook_ref.setBook_reading_state(3);



        AlertDialog.Builder builder_dialog = new AlertDialog.Builder(context);
        AlertDialog dialog = builder_dialog.create();
        dialog.setMessage(clickedBook_ref.getTitle());


        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        EditText editText_date = new EditText(context);
        editText_date.setInputType(InputType.TYPE_NULL);
        editText_date.setText(clickedBook_ref.getFinish_date().toString());

        TextView textView_rate = new TextView(context);
        TextView textView_shelf = new TextView(context);
        TextView textView_mShelfs = new TextView(context);
        TextView textView_finishDate = new TextView(context);


        textView_rate.setTextSize(18);
        textView_mShelfs.setTextSize(18);
        textView_shelf.setTextSize(18);
        textView_finishDate.setTextSize(18);



        textView_mShelfs.setTextColor(getContext().getColor(R.color.colorBlack));
        textView_shelf.setTextColor(getContext().getColor(R.color.colorBlack));
        textView_rate.setTextColor(getContext().getColor(R.color.colorBlack));
        textView_finishDate.setTextColor(getContext().getColor(R.color.colorBlack));



        textView_shelf.setText(getContext().getString(R.string.Shelf));
        textView_mShelfs.setText(getContext().getString(R.string.mShelfs));
        textView_rate.setText(getContext().getString(R.string.rateText));
        textView_finishDate.setText(getContext().getString(R.string.finish_readingtext));


        Spinner spinner_shelf = new Spinner(context);

        ArrayAdapter<CharSequence> adapter_shelf = ArrayAdapter.createFromResource(getContext(), R.array.user_book_state, android.R.layout.simple_spinner_item);
        adapter_shelf.setDropDownViewResource(R.layout.spinner_item);


        spinner_shelf.setAdapter(adapter_shelf);
        spinner_shelf.setSelection(clickedBook_ref.getBook_reading_state()-1);


        Button save_Button = new Button(context);
        save_Button.setText(getContext().getResources().getString(R.string.saveButtonText));
        save_Button.setBackgroundColor(Color.parseColor("#d4d4d4"));
        save_Button.setTextColor(Color.parseColor("#4a4a4a"));


        RatingBar ratingBar = new RatingBar(context);
        ratingBar.setForegroundGravity(Gravity.CENTER);
        ratingBar.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
        ratingBar.setNumStars(AppData.rateMaxNum);
        ratingBar.setStepSize(1.0f);
        ratingBar.setRating(clickedBook_ref.getMark());


        ratingBar.setOnRatingBarChangeListener((ratingBar1, v, b) -> {
            clickedBook_ref.setMark(v);
        });


        save_Button.setOnClickListener(view -> {
            SqlManager.getInstance().editBookFromDb(clickedBook_ref);
            clickedBook.setFinish_date(clickedBook_ref.getFinish_date());
            clickedBook.setBook_reading_state(clickedBook_ref.getBook_reading_state());
            clickedBook.setUser_has_book(clickedBook_ref.isUser_has_book());
            clickedBook.setBook_is_favorite(clickedBook_ref.isBook_is_favorite());
            clickedBook.setMark(clickedBook_ref.getMark());
            setOn.setText(afterEditing.setBookShelfs());
            if(clickedBook.getBook_reading_state()!=1) parentRatingBar.setVisibility(View.INVISIBLE);
            else parentRatingBar.setVisibility(View.VISIBLE);
            dialog.cancel();
        });

        editText_date.setOnClickListener(view -> {
            Calendar calendar = Calendar.getInstance();
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);


            DatePickerDialog datePickerDialog = new DatePickerDialog(context, (datePicker, year1, month1, day1) -> {

                Date date = new Date(year1 - 1900, month1, day1);
                editText_date.setText(date.toString());
                clickedBook_ref.setFinish_date(date);
            }, year, month, day);
            datePickerDialog.getDatePicker().setBackgroundColor(getContext().getResources().getColor(R.color.colorPrimary));
            datePickerDialog.show();

            datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", (dialogInterface, i) -> {

            });

        });


        spinner_shelf.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                String[] args = getContext().getResources().getStringArray(R.array.user_book_state);
                String selected_item = spinner_shelf.getSelectedItem().toString();


                if (selected_item.equals(args[0])) {

                    if (!(textView_rate.getParent() == null)) return;
                    ratingBar.setRating(0.f);
                    linearLayout.addView(textView_rate, 0);
                    linearLayout.addView(ratingBar, 1);
                    linearLayout.addView(textView_finishDate, linearLayout.getChildCount() - 1); // TODO idk why in two lines is -1 but it's working because of that ....
                    linearLayout.addView(editText_date, linearLayout.getChildCount() - 1);

                } else {
                    clickedBook_ref.setMark(0.f);
                    linearLayout.removeView(textView_rate);
                    linearLayout.removeView(ratingBar);
                    linearLayout.removeView(textView_finishDate);
                    linearLayout.removeView(editText_date);
                }

                clickedBook_ref.setBook_reading_state(position + 1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );

        View someView = layoutInflater.inflate(R.layout.chip,null);

        Chip chip_have = someView.findViewById(R.id.chip_has);
        Chip chip_favor = someView.findViewById(R.id.chip_favor);

        if(clickedBook_ref.isUser_has_book())
            chip_have.setChecked(true);
        if(clickedBook_ref.isBook_is_favorite())
            chip_favor.setChecked(true);

        chip_have.setOnCheckedChangeListener((compoundButton, b) -> {
            if(b)
                clickedBook_ref.setUser_has_book(true);
            else
                clickedBook_ref.setUser_has_book(false);
        });

        chip_favor.setOnCheckedChangeListener((compoundButton, b) -> {
            if(b)
                clickedBook_ref.setBook_is_favorite(true);
            else
                clickedBook_ref.setBook_is_favorite(false);
        });


        ((ViewGroup)chip_have.getParent()).removeView(chip_have);
        ((ViewGroup)chip_favor.getParent()).removeView(chip_favor);

        RelativeLayout relativeLayout = new RelativeLayout(context);
        relativeLayout.addView(chip_have);
        relativeLayout.addView(chip_favor);


        linearLayout.addView(textView_shelf);
        linearLayout.addView(spinner_shelf);
        linearLayout.addView(textView_mShelfs);
        linearLayout.addView(relativeLayout);
        linearLayout.addView(save_Button);


        if (clickedBook_ref.getBook_reading_state() == 1) {
            linearLayout.addView(textView_rate, 0);
            linearLayout.addView(ratingBar, 1);
            linearLayout.addView(textView_finishDate, linearLayout.getChildCount() - 1);
            linearLayout.addView(editText_date, linearLayout.getChildCount() - 1);
        }

        dialog.setOnDismissListener(dialogInterface -> {

           // SqlManager.getInstance().editBookFromDb(clickedBook);
        });

        dialog.setView(linearLayout);
        dialog.show();

    }


}
