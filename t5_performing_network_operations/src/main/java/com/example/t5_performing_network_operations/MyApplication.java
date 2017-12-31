package com.example.t5_performing_network_operations;

import android.app.Application;

/**
 * Created by wzc on 2017/12/30.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.getInstance().init(this);
    }
}
