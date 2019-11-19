package com.wzc.t21_using_touch_gestures;

import android.content.Context;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author wzc
 * @date 2018/5/12
 */
public class MyChildView extends View {
    private static final String TAG = MyChildView.class.getSimpleName();
    public MyChildView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "onTouchEvent: ACTION_DOWN");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "onTouchEvent: ACTION_CANCEL");
                break;
            default:

        }
        return super.onTouchEvent(event);
    }
    
}
