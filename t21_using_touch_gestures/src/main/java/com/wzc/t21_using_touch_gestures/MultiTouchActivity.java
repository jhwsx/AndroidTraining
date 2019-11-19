package com.wzc.t21_using_touch_gestures;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.view.MotionEventCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;

/**
 * @author wzc
 * @date 2018/5/11
 */
public class MultiTouchActivity extends AppCompatActivity {
    private static final String TAG = MultiTouchActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multitouch);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        // 根据 pointerIndex 获取 pointerId
//        int activePointerId = event.getPointerId(0);
//        // 根据 pointerId 获取它在数组中的索引
//        int pointerIndex = event.findPointerIndex(activePointerId);
//
//        float x = event.getX(pointerIndex);
//        float y = event.getY(pointerIndex);
//
//        Log.d(TAG, "onTouchEvent: activePointerId = " + activePointerId
//        + ", pointerIndex = " + pointerIndex
//        + ", x = " + x
//        + ", y = " + y);

        // 总是使用这种兼容方法来获取 action。因为它支持多手指的情况。
        int action = MotionEventCompat.getActionMasked(event);
        // 获取与该动作关联的点的索引
        int index = MotionEventCompat.getActionIndex(event);

        if (event.getPointerCount() > 1) {
            Log.d(TAG, "onTouchEvent: Multitouch event");
        } else {
            Log.d(TAG, "onTouchEvent: Single touch event");
        }
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "onTouchEvent: ACTION_DOWN");
                return true;
            case MotionEvent.ACTION_POINTER_DOWN:
                Log.d(TAG, "onTouchEvent: ACTION_POINTER_DOWN" + ", actionIndex = " + MotionEventCompat.getActionIndex(event));
                return true;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "onTouchEvent: ACTION_MOVE");
                return true;
            case MotionEvent.ACTION_POINTER_UP:
                Log.d(TAG, "onTouchEvent: ACTION_POINTER_UP" + ", actionIndex = " + MotionEventCompat.getActionIndex(event));
                return true;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onTouchEvent: ACTION_UP");
                return true;
            default:
                return super.onTouchEvent(event);
        }
    }


}
