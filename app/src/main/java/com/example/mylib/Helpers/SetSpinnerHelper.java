package com.example.mylib.Helpers;

import android.annotation.TargetApi;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.mylib.Data.AppData;
import com.example.mylib.R;

@TargetApi(23)
public class SetSpinnerHelper {


    public static void SetSpinner(Spinner spinner, int arrayId) {

        ArrayAdapter<CharSequence> spinner_ShowAdapter = ArrayAdapter.createFromResource(AppData.getContext(), arrayId,
                android.R.layout.simple_spinner_item);

        spinner_ShowAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(spinner_ShowAdapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               // TODO implement item selected action
               // TextView textView = (TextView) parent.getChildAt(0);
               // textView.setTextColor(AppData.getContext().getColor(R.color.colorMain));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

}
