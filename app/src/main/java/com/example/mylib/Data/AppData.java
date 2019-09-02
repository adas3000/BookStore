package com.example.mylib.Data;

import android.app.Application;
import android.content.Context;

public class AppData extends Application {

    private static  Context mContext ;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }


    public static Context getContext(){return mContext;}

}
