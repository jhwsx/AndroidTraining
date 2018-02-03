package com.example.t8_acitivity_lifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author wzc
 * @date 2018/1/30
 */
public class FirstActivity extends AppCompatActivity {
    private static final String TAG = FirstActivity.class.getSimpleName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Log.d(TAG, "onCreate: "+this.toString());
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(this.toString() + ", taskId: " + getTaskId());
        Button button = (Button) findViewById(R.id.btn_first_activity);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstActivity.this, SecondActivity.class));
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "onNewIntent: "+this.toString());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: "+this.toString());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: "+this.toString());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: "+this.toString());
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: "+this.toString());
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: "+this.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: "+this.toString());
    }
}
