package com.cmuse13.countdownapp.countdownmodule;

import android.app.Application;

public class MyApplication
        extends Application {

    private static MyApplication mInstance;

    public static MyApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
