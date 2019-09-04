package com.example.mylib.Helpers;

import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.mylib.Data.AppData;
import com.example.mylib.R;

public class SetSpinnerHelper {


    public static void SetSpinner(Spinner spinner, int arrayId){

        ArrayAdapter<CharSequence> spinner_ShowAdapter = ArrayAdapter.createFromResource(AppData.getContext(), arrayId,
                android.R.layout.simple_spinner_item);

        spinner_ShowAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(spinner_ShowAdapter);
    }


}
