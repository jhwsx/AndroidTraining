package com.wan.t11_swipe_to_refresh;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.wan.t10_swipe_to_refresh.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnBasic = (Button) findViewById(R.id.btn_basic);
        Button btnListFragment = (Button) findViewById(R.id.btn_list_fragment);

        btnBasic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BasicActivity.start(MainActivity.this);
            }
        });
        btnListFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            ListFragmentActivity.start(MainActivity.this);
            }
        });
    }
}
