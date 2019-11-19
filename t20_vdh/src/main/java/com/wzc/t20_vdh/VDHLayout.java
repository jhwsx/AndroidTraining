package com.wzc.t20_vdh;

import android.content.Context;
import android.graphics.Point;
import androidx.annotation.Nullable;
import androidx.customview.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * https://blog.csdn.net/lmj623565791/article/details/46858663
 *
 * @author wzc
 * @date 2018/5/2
 */
public class VDHLayout extends LinearLayout {

    private final ViewDragHelper mDragHelper;
    private View mDragView;
    private View mAutobackView;
    private View mEdgeTrackerView;

    public VDHLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragCallback());

        mDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mDragView = getChildAt(0);
        mAutobackView = getChildAt(1);
        mEdgeTrackerView = getChildAt(2);

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragHelper.processTouchEvent(event);
        return true;
    }

    private class ViewDragCallback extends ViewDragHelper.Callback {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return child == mDragView || child == mAutobackView;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            return left;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return top;
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);

            if (releasedChild == mAutobackView) {
                mDragHelper.settleCapturedViewAt(mAutoBackOriginPos.x, mAutoBackOriginPos.y);
                invalidate();
            }
        }

        @Override
        public void onEdgeDragStarted(int edgeFlags, int pointerId) {
            super.onEdgeDragStarted(edgeFlags, pointerId);

            mDragHelper.captureChildView(mEdgeTrackerView, pointerId);
        }

        @Override
        public void onEdgeTouched(int edgeFlags, int pointerId) {
            super.onEdgeTouched(edgeFlags, pointerId);
        }
    }

    @Override
    public void computeScroll() {
        if (mDragHelper.continueSettling(true)) {
            invalidate();
        }
    }

    private Point mAutoBackOriginPos = new Point();

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        mAutoBackOriginPos.x = mAutobackView.getLeft();
        mAutoBackOriginPos.y = mAutobackView.getTop();
    }
}
