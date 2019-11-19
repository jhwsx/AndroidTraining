package com.wzc.t21_using_touch_gestures;

import android.os.Bundle;
import androidx.core.view.MotionEventCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * @author wzc
 * @date 2018/5/5
 */
public class TouchActivity extends AppCompatActivity implements View.OnTouchListener {
    private static final String TAG = TouchActivity.class.getSimpleName();
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch);
        mTextView = (TextView) findViewById(R.id.tv);
        mTextView.setOnTouchListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = MotionEventCompat.getActionMasked(event);

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "onTouchEvent: Action was DOWN");
                return true;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "onTouchEvent: Action was MOVE");
                return true;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onTouchEvent: Action was UP");
                return true;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "onTouchEvent: Action was CANCEL");
                return true;
            case MotionEvent.ACTION_OUTSIDE:
                Log.d(TAG, "onTouchEvent: Action was OUTSIDE");
                return true;
            default:
                return super.onTouchEvent(event);

        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = MotionEventCompat.getActionMasked(event);

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.d("tag_view", "onTouchEvent: Action was DOWN");
                return true; // 这里返回false的话，后面的MOVE,UP就不会调用，因为DOWN是所有触摸事件的起点。
            case MotionEvent.ACTION_MOVE:
                Log.d("tag_view", "onTouchEvent: Action was MOVE");
                return true;
            case MotionEvent.ACTION_UP:
                Log.d("tag_view", "onTouchEvent: Action was UP");
                return true;
            case MotionEvent.ACTION_CANCEL:
                Log.d("tag_view", "onTouchEvent: Action was CANCEL");
                return true;
            case MotionEvent.ACTION_OUTSIDE:
                Log.d("tag_view", "onTouchEvent: Action was OUTSIDE");
                return true;
            default:
                return super.onTouchEvent(event);
        }
    }
}
