package com.wzc.t21_using_touch_gestures;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author wzc
 * @date 2018/5/12
 */
public class DragView2 extends View {
    public DragView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private int lastX;
    private int lastY;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = rawX;
                lastY = rawY;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = rawX - lastX;
                int offsetY = rawY - lastY;

                ((View) getParent()).layout(getLeft() + offsetX,
                        getTop() + offsetY,
                        getRight() + offsetX,
                        getBottom()+ offsetY);

                lastX = rawX;
                lastY = rawY;
                break;
            default:
                break;
        }
        return true;
    }
}
