package com.jetpack.demos.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jetpack.demos.R;

/**
 * @author wzc
 * @date 2019/2/16
 */
public class NewsActivity extends AppCompatActivity {
    public static void start(Context context) {
        Intent starter = new Intent(context, NewsActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
    }
}
