package com.wzc.g6_app_resources;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void retainedFragment(View view) {
//        RetainedFragmentActivity.start(MainActivity.this);
        String locale = getResources().getConfiguration().locale.getDisplayName();
        Log.d("MainActivity", locale);
    }
}
