package com.jetpack.demos.lifecycles;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * @author wzc
 * @date 2019/2/13
 */
public class LifecyclesActivity extends AppCompatActivity {

    private MyLifeListener mMyLifeListener;

    public static void start(Context context) {
        Intent starter = new Intent(context, LifecyclesActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMyLifeListener = new MyLifeListener();
        getLifecycle().addObserver(mMyLifeListener);
    }
}
