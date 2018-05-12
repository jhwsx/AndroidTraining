package com.wzc.t21_using_touch_gestures;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import static android.view.MotionEvent.INVALID_POINTER_ID;

/**
 * @author wzc
 * @date 2018/5/11
 */
public class DraggingActivity extends AppCompatActivity /*implements View.OnTouchListener*/ {

    private TextView mTv;
    private int mActivePointerId = INVALID_POINTER_ID;
    private float mPosX;
    private float mPosY;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dragging);
//
//        mTv = (TextView) findViewById(R.id.tv);
//        mTv.setOnTouchListener(this);
    }

//    private float mLastTouchX;
//    private float mLastTouchY;
//
//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//        // 获取 masked action
//        int action = MotionEventCompat.getActionMasked(event);
//
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN: {
//                // 获取 pointerIndex
//                int pointerIndex = MotionEventCompat.getActionIndex(event);
//                float x = event.getRawX();
//                float y = event.getRawY();
//
//                // Remember where we started (for dragging)
//                mLastTouchX = x;
//                mLastTouchY = y;
//                // Save the ID of this pointer (for dragging)
//                mActivePointerId = event.getPointerId(0);
//
//                return true;
//            }
//            case MotionEvent.ACTION_MOVE: {
//                // Find the index of the active pointer and fetch its position
//                int pointerIndex = event.findPointerIndex(mActivePointerId);
//
//                float x = event.getRawX();
//                float y = event.getRawY();
//
//                // Calculate the distance moved
//                float dx = x - mLastTouchX;
//                float dy = y - mLastTouchY;
//
//                mPosX += dx;
//                mPosY += dy;
//
//                mTv.setY(mPosY);
////                mTv.scrollTo(((int) mPosX), ((int) mPosY));
//                mTv.invalidate();
//                // Remember this touch position for the next move event
//                mLastTouchX = event.getRawX();
//                mLastTouchY = event.getRawY();
//
//                return true;
//            }
//
//            case MotionEvent.ACTION_UP: {
//                mActivePointerId = INVALID_POINTER_ID;
//                return true;
//            }
//            case MotionEvent.ACTION_CANCEL: {
//                mActivePointerId = INVALID_POINTER_ID;
//                return true;
//            }
//            case MotionEvent.ACTION_POINTER_UP: {
//                // 获取 pointerIndex
//                int pointerIndex = MotionEventCompat.getActionIndex(event);
//                int pointerId = event.getPointerId(pointerIndex);
//                if (pointerId == mActivePointerId) {
//                    // This was our active pointer going up. Choose a new
//                    // active pointer and adjust accordingly.
//                    final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
//                    mLastTouchX = event.getX(newPointerIndex);
//                    mLastTouchY = event.getY(newPointerIndex);
//                    mActivePointerId = event.getPointerId(newPointerIndex);
//                }
//                return true;
//            }
//
//            default:
//                return false;
//        }
//    }

}
