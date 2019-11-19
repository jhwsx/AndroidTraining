package com.wzc.t21_using_touch_gestures;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.wzc.t21_using_touch_gestures.interactivechart.InteractiveChartActivity;

public class MainActivity extends AppCompatActivity  {
    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView mTextView;
    private Context mContext = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_touch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, TouchActivity.class));
            }
        });
        findViewById(R.id.btn_gesturedetector_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, GestureDetectorActivity.class));
            }
        });
        findViewById(R.id.btn_tracking_movement).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, TrackingMovementActivity.class));
            }
        });
        findViewById(R.id.btn_scroll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, ScrollActivity.class));
            }
        });
        findViewById(R.id.btn_interactivechart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, InteractiveChartActivity.class));
            }
        });
        findViewById(R.id.btn_multitouch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, MultiTouchActivity.class));
            }
        });
        findViewById(R.id.btn_dragging).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, DraggingActivity.class));
            }
        });
        findViewById(R.id.btn_manage_touchevent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, ManageTouchEventInViewGroupActivity.class));
            }
        });
        findViewById(R.id.btn_extend_touchable_area).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, ExtendTouchableAreaActivity.class));
            }
        });
    }

}


