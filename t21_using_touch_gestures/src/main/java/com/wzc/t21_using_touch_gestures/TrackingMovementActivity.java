package com.wzc.t21_using_touch_gestures;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.view.VelocityTrackerCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;

/**
 * @author wzc
 * @date 2018/5/9
 */
public class TrackingMovementActivity extends AppCompatActivity {

    private static final String TAG = TrackingMovementActivity.class.getSimpleName();
    private VelocityTracker mVelocityTracker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking_movement);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int index = event.getActionIndex();
        int action = event.getActionMasked();
        int pointerId = event.getPointerId(index);

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (mVelocityTracker == null) {
                    // 获取 VelocityTracker 的实例
                    mVelocityTracker = VelocityTracker.obtain();
                } else {
                    // 重置 VelocityTracker 回到初始状态
                    mVelocityTracker.clear();
                }
                mVelocityTracker.addMovement(event);
                break;
            case MotionEvent.ACTION_MOVE:
                // 把事件交给 VelocityTracker。
                mVelocityTracker.addMovement(event);
                // 根据已经收集的点，计算当前的速度
                mVelocityTracker.computeCurrentVelocity(1000);

                Log.d(TAG, "onTouchEvent: " +
                        "X velocity = "
                        + VelocityTrackerCompat.getXVelocity(mVelocityTracker, pointerId)
                        + ", Y velocity = "
                        + VelocityTrackerCompat.getYVelocity(mVelocityTracker, pointerId)
                        + ", pointerId = " + pointerId);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (mVelocityTracker != null) {
                    mVelocityTracker.recycle();
                    mVelocityTracker = null;
                }

                break;
            default:
        }
        return true;
    }
}
