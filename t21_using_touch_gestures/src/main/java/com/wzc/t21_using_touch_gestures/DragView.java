package com.wzc.t21_using_touch_gestures;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import static android.view.MotionEvent.INVALID_POINTER_ID;

/**
 * @author wzc
 * @date 2018/5/11
 */
public class DragView extends View {
    private static final String TAG = DragView.class.getSimpleName();
    private int mActivePointerId;
    private float mDx;
    private float mDy;

    public DragView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private float mLastTouchX;
    private float mLastTouchY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        final int action = MotionEventCompat.getActionMasked(event);

        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                // 获取当前触点的 index 下标
                final int pointerIndex = MotionEventCompat.getActionIndex(event);
                // 获取指定触点的 x 坐标
                final float x = event.getX(pointerIndex);
                // 获取指定触点的 y 坐标
                final float y = event.getY(pointerIndex);

                mLastTouchX = x;
                mLastTouchY = y;
                // 获取触点唯一id，记录当前触摸点的 id（活动点的 id）
                mActivePointerId = event.getPointerId(0);
                Log.d(TAG, "onTouchEvent: ACTION_DOWN pointerIndex = " + pointerIndex
                        + ", x = " + x
                        + ", y = " + y
                        + ", mActivePointerId = " + mActivePointerId);
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                // Find the index of the active pointer and fetch its position
                // 通过触点的 id 获取 index 下标
                final int pointerIndex =
                        event.findPointerIndex(mActivePointerId);

                final float x = event.getX(pointerIndex);
                final float y = event.getY(pointerIndex);

                // Calculate the distance moved
                mDx = x - mLastTouchX;
                mDy = y - mLastTouchY;

                doMove();

                // Remember this touch position for the next move event
                mLastTouchX = x;
                mLastTouchY = y;

                Log.d(TAG, "onTouchEvent: ACTION_MOVE pointerIndex = " + pointerIndex
                        + ", x = " + x
                        + ", y = " + y
                        + ", dx = " + mDx
                        + ", dy = " + mDy);
                break;
            }

            case MotionEvent.ACTION_UP: {
                Log.d(TAG, "onTouchEvent: ACTION_UP");
                mActivePointerId = INVALID_POINTER_ID;

//                resetPosition();
                break;
            }

            case MotionEvent.ACTION_CANCEL: {
                Log.d(TAG, "onTouchEvent: ACTION_CANCEL");
                mActivePointerId = INVALID_POINTER_ID;
                break;
            }

            case MotionEvent.ACTION_POINTER_DOWN: {
                //获取再次按下的触点的ID
                final int pointerIndex = MotionEventCompat.getActionIndex(event);
                final int pointerId = MotionEventCompat.getPointerId(event, pointerIndex);
                //如果触发的点不是活动点的话，就更新mLastTouchX/mLastTouchY，并设其为活动点
                if (pointerId != mActivePointerId) {
                    mLastTouchX = event.getX(pointerIndex);
                    mLastTouchY = event.getY(pointerIndex);
                    mActivePointerId = event.getPointerId(pointerIndex);
                }
                break;
            }
            case MotionEvent.ACTION_POINTER_UP: {
                Log.d(TAG, "onTouchEvent: ACTION_POINTER_UP");
                // 获取松开的触点的 index 下标
                final int pointerIndex = MotionEventCompat.getActionIndex(event);
                // 获取这个触点的 id
                final int pointerId = event.getPointerId(pointerIndex);
                // 如果触发的点不是活动点的话，就更新mLastTouchX/mLastTouchY,并设置为活动点
                if (pointerId == mActivePointerId) {
                    // This was our active pointer going up. Choose a new
                    // active pointer and adjust accordingly.
                    final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
                    mLastTouchX = (int) event.getX(newPointerIndex);
                    mLastTouchY = (int) event.getY(newPointerIndex);
                    mActivePointerId = event.getPointerId(newPointerIndex);
                }
                break;
            }
            default:
                break;
        }
        return true;
    }

    Rect mRect = new Rect();

    //拖拽方法
    private void doMove() {
        if (mRect.isEmpty()) {
            mRect.set(getLeft(), getTop(), getRight(), getBottom());
        }
        int movedx = (int) (mDx / 2);
        int movedy = (int) (mDy / 2);
        if (movedy != 0) {
            layout(getLeft() + movedx, getTop() + movedy, getLeft() + getWidth() + movedx, getTop() + getHeight() + movedy);
        }
    }

    //复位方法
    private void resetPosition() {
        Animation animation = new TranslateAnimation(getLeft() - mRect.left, 0, getTop() - mRect.top, 0);
        animation.setDuration(200);
        animation.setFillAfter(true);
        startAnimation(animation);
        layout(mRect.left, mRect.top, mRect.right, mRect.bottom);
        mRect.setEmpty();
    }
}
