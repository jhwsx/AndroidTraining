package com.wan.t18_manage_system_ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_dim_bars).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View decorView = getWindow().getDecorView();
                int uiOptions = View.SYSTEM_UI_FLAG_LOW_PROFILE;
                decorView.setSystemUiVisibility(uiOptions);
            }
        });
        findViewById(R.id.btn_reveal_bars).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View decorView = getWindow().getDecorView();
                // Calling setSystemUiVisibility() with a value of 0 clears
                // all flags.
                decorView.setSystemUiVisibility(0);
            }
        });
        findViewById(R.id.btn_hide_bars).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View decorView = getWindow().getDecorView();
                // 隐藏status bar
                int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
                decorView.setSystemUiVisibility(uiOptions);
                // 把action bar也一道儿隐藏
                if (getSupportActionBar() != null) {
                    getSupportActionBar().hide();
                }
            }
        });
        findViewById(R.id.btn_show_bars).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View decorView = getWindow().getDecorView();
                // 显示status bar
                decorView.setSystemUiVisibility(0);
                // 把action bar也一道儿显示
                if (getSupportActionBar() != null) {
                    getSupportActionBar().show();
                }
            }
        });
        findViewById(R.id.btn_hide_nav_bars).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View decorView = getWindow().getDecorView();
                // 隐藏status bar和nav bar
                int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_FULLSCREEN;
                decorView.setSystemUiVisibility(uiOptions);
            }
        });
        findViewById(R.id.btn_show_nav_bars).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View decorView = getWindow().getDecorView();
                // 显示status bar和nav bar
                decorView.setSystemUiVisibility(0);
            }
        });
        findViewById(R.id.btn_use_immersive).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View decorView = getWindow().getDecorView();
                // 隐藏status bar和nav bar
                int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_FULLSCREEN;
                decorView.setSystemUiVisibility(uiOptions);
                decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
                    @Override
                    public void onSystemUiVisibilityChange(int visibility) {
                        Log.d("MainActivity", "visibility:" + visibility);
                    }
                });
            }
        });
    }
}
