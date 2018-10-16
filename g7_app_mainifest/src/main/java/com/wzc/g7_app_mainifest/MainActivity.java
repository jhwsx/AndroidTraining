package com.wzc.g7_app_mainifest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_activity_alias).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityAliasActvity.start(MainActivity.this);
            }
        });
    }
}
