package com.wzc.t20_vdh;

import android.content.Context;
import android.graphics.Point;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.customview.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.wzc.t19_vdh.R;

/**
 * @author wzc
 * @date 2018/5/2
 */
public class ThreeDirectionScrollUnlockLayout extends ConstraintLayout {
    private static final String TAG = ThreeDirectionScrollUnlockLayout.class.getSimpleName();
    private final ViewDragHelper mViewDragHelper;
    private View mReadView;
    private View mUnlockView;
    private View mRedbagView;
    private View mHomeView;
    private Point mHomeOriginalPoint = new Point();

    private Point mHomeCenterPoint = new Point();
    private Point mReadCenterPoint = new Point();
    private Point mUnlockCenterPoint = new Point();
    private Point mRedbagCenterPoint = new Point();
    private boolean isReachBound = false;
    private boolean isHorizontalDrag = false;
    private boolean isVerticalDrag = false;
    private int mTopBound;
    private int mLeftBound;
    private int mRightBound;
    private int mBottomBound;
    public ThreeDirectionScrollUnlockLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mViewDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragCallback());
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mReadView = findViewById(R.id.iv_read);
        mUnlockView = findViewById(R.id.iv_unlock);
        mRedbagView = findViewById(R.id.iv_redbag);
        mHomeView = findViewById(R.id.iv_home);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        mHomeOriginalPoint.x = mHomeView.getLeft();
        mHomeOriginalPoint.y = mHomeView.getTop();

        mHomeCenterPoint.x = mHomeView.getLeft() + mHomeView.getMeasuredWidth() / 2;
        mHomeCenterPoint.y = mHomeView.getTop() + mHomeView.getMeasuredHeight() / 2;

        mReadCenterPoint.x = mReadView.getLeft() + mReadView.getMeasuredWidth() / 2;
        mReadCenterPoint.y = mReadView.getTop() + mReadView.getMeasuredHeight() / 2;

        mUnlockCenterPoint.x = mUnlockView.getLeft() + mUnlockView.getMeasuredWidth() / 2;
        mUnlockCenterPoint.y = mUnlockView.getTop() + mUnlockView.getMeasuredHeight() / 2;

        mRedbagCenterPoint.x = mRedbagView.getLeft() + mRedbagView.getMeasuredWidth() / 2;
        mRedbagCenterPoint.y = mRedbagView.getTop() + mRedbagView.getMeasuredHeight() / 2;

        Log.d(TAG, "onLayout: mHomeCenterPoint = " + mHomeCenterPoint);
        Log.d(TAG, "onLayout: mReadCenterPoint = " + mReadCenterPoint);
        Log.d(TAG, "onLayout: mUnlockCenterPoint = " + mUnlockCenterPoint);
        Log.d(TAG, "onLayout: mRedbagCenterPoint = " + mRedbagCenterPoint);

        mTopBound = mRedbagCenterPoint.y - mHomeView.getMeasuredHeight() / 2;
        mBottomBound = mHomeCenterPoint.y - mHomeView.getMeasuredHeight() / 2;
        mLeftBound = mReadCenterPoint.x - mHomeView.getMeasuredWidth() / 2;
        mRightBound = mUnlockCenterPoint.x - mHomeView.getMeasuredWidth() / 2;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        resetFlags();
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    private void resetFlags() {
        isReachBound = false;
        isHorizontalDrag = false;
        isVerticalDrag = false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    private class ViewDragCallback extends ViewDragHelper.Callback {

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return child == mHomeView;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {

            if (isHorizontalDrag) {
                Log.d(TAG, "clampViewPositionVertical: isHorizontalDrag = true ");
                return mHomeView.getTop();
            }
            if (Math.abs(dy) > 0) {
                Log.d(TAG, "clampViewPositionVertical: isVerticalDrag 设置为true，dy = " + Math.abs(dy));
                isVerticalDrag = true;
            }
            final int newTop = Math.min(Math.max(mTopBound, top), mBottomBound);
            return newTop;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {

            if (isVerticalDrag) {
                Log.d(TAG, "clampViewPositionHorizontal: isVerticalDrag = true");
                return mHomeView.getLeft();
            }
            if (Math.abs(dx) > 0) {
                Log.d(TAG, "clampViewPositionHorizontal: isHorizontalDrag 设置为true， dx = " + Math.abs(dx));
                isHorizontalDrag = true;
            }
            final int newLeft = Math.min(Math.max(mLeftBound, left), mRightBound);

            return newLeft;

        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);

            if (releasedChild == mHomeView) {
                mViewDragHelper.settleCapturedViewAt(mHomeOriginalPoint.x, mHomeOriginalPoint.y);
                invalidate();
            }
        }


        // 当拖拽的View的位置发生变化的时候回调(特指capturedview)
        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
            Log.d(TAG, "onViewPositionChanged: changedView = " + changedView + ", left = " + left + ", top = " + top);
            Log.d(TAG, "onViewPositionChanged: mLeftBound = " + mLeftBound);
            Log.d(TAG, "onViewPositionChanged: mRightBound = " + mRightBound);
            Log.d(TAG, "onViewPositionChanged: mTopBound = " + mTopBound);
            if (isReachBound) {
                return;
            }
            if (left <= mLeftBound) {
                isReachBound = true;
                Log.d(TAG, "onViewPositionChanged: " + "到达左边界");
                Toast.makeText(getContext(), "到达左边界", Toast.LENGTH_SHORT).show();
            }
            if (left >= mRightBound) {
                isReachBound = true;
                Log.d(TAG, "onViewPositionChanged: " + "到达右边界");
                Toast.makeText(getContext(), "到达右边界", Toast.LENGTH_SHORT).show();
            }
            if (top <= mTopBound) {
                isReachBound = true;
                Log.d(TAG, "onViewPositionChanged: " + "到达上边界");
                Toast.makeText(getContext(), "到达上边界", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mViewDragHelper.continueSettling(true)) {
            invalidate();
        }
    }
}
