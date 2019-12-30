package com.jetpack.demos;

import android.app.Application;
import android.content.Context;

/**
 * @author wangzhichao
 * @since 2019/12/28
 */
public class App extends Application {
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
