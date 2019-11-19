package com.example.t4_test_apps;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private static final String EXTRA_STRING = "EXTRA_STRING";
    public static void start(Context context,String text) {
        Intent starter = new Intent(context, SecondActivity.class);
        starter.putExtra(EXTRA_STRING, text);
        context.startActivity(starter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        String stringExtra = getIntent().getStringExtra(EXTRA_STRING);

        TextView viewById = (TextView) findViewById(R.id.textView);

        viewById.setText(stringExtra);
    }
}
