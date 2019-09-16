package com.example.mylib.Helpers;

import android.annotation.TargetApi;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.mylib.Data.AppData;
import com.example.mylib.Data.Book;
import com.example.mylib.R;

import java.util.List;

@TargetApi(23)
public class SetSpinnerHelper {
    /**
     *
     *
     * @param spinner - spinner
     * @param arrayId - id to display strings from strings.xml
     * @param bookList - book to setChanges
     * @param which - number from 0 to 2 tells about action to do 0 - stars change , 1 - sort change , 2 - on shelf since change , till now
     *              2 in which is going to be hidden
     */

    public static void SetSpinner(Spinner spinner, int arrayId, List<Book> bookList,List<Book> bookList_All,int which) {

        ArrayAdapter<CharSequence> spinner_ShowAdapter = ArrayAdapter.createFromResource(AppData.getContext(), arrayId,
                android.R.layout.simple_spinner_item);

        spinner_ShowAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(spinner_ShowAdapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // since 0
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               // TODO implement item selected action
               if(which==1){
                   int index = position - 1;




               }
               else if(which == 2){

               }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

}
