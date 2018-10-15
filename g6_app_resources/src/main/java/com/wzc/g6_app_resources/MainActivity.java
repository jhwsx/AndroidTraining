package com.wzc.g6_app_resources;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void retainedFragment(View view) {
        RetainedFragmentActivity.start(MainActivity.this);
    }

    public void animationResourcesActivity(View view) {
        AnimationResourcesActivity.start(MainActivity.this);
    }
}
