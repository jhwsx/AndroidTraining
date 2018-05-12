package com.wzc.t21_using_touch_gestures;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewConfiguration;

/**
 * @author wzc
 * @date 2018/5/12
 */
public class ManageTouchEventInViewGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_touchevent);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(this);

        int scaledTouchSlop = viewConfiguration.getScaledTouchSlop();
        int scaledMaximumFlingVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        int scaledMinimumFlingVelocity = viewConfiguration.getScaledMinimumFlingVelocity();

    }
}
