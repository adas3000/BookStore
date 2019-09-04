package com.example.mylib.Data;

import android.app.Application;
import android.content.Context;
import android.widget.SearchView;

public class AppData extends Application {

    private static  Context mContext ;



    private static SearchView searchView;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }




    public static Context getContext(){return mContext;}

}
