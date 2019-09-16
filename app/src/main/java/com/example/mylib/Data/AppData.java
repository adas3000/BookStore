package com.example.mylib.Data;

import android.app.Application;
import android.content.Context;
import android.widget.SearchView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AppData extends Application {

    private static  Context mContext ;

    private static FloatingActionButton fab;

    public static void setFab(FloatingActionButton fab1){fab = fab1;}

    public static final int rateMaxNum = 5;
    private static SearchView searchView;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static FloatingActionButton getFab(){return fab;}




    public static Context getContext(){return mContext;}

}
