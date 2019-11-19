package com.wan.t18_manage_system_ui;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

/**
 * @author wzc
 * @date 2018/3/7
 */
public class DimActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dim);

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View decorView = getWindow().getDecorView();
                decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
            }
        });

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View decorView = getWindow().getDecorView();
                // Calling setSystemUiVisibility() with a value of 0 clears
                // all flags.
                decorView.setSystemUiVisibility(0);
            }
        });
    }
}
