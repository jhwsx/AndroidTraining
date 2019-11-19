package com.wzc.t20_vdh;

import android.content.Context;
import android.graphics.Point;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.customview.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.wzc.t19_vdh.R;

/**
 * 固定向拖动 ViewGroup
 *
 * @author wzc
 * @date 2018/5/6
 */
public class DirectionDragLayout extends ConstraintLayout {

    private final ViewDragHelper mViewDragHelper;
    /**
     * 左边的View
     */
    private View mReadView;
    /**
     * 右边的View
     */
    private View mUnlockView;
    /**
     * 上边的View
     */
    private View mRedbagView;
    /**
     * 中间的View，就是要拖动的View
     */
    private View mHomeView;

    public DirectionDragLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 1, 创建 ViewDragHelper 的实例
        // 参一 : 当前的ViewGroup对象 Parent view to monitor
        // 参二 : 灵敏度 Multiplier for how sensitive the helper should be about detecting
        // the start of a drag. Larger values are more sensitive. 1.0f is normal.
        // 参三 : 提供信息和接收事件的回调
        mViewDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragCallback());
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        // 3，获取View
        mReadView = findViewById(R.id.iv_read);
        mUnlockView = findViewById(R.id.iv_unlock);
        mRedbagView = findViewById(R.id.iv_redbag);
        mHomeView = findViewById(R.id.iv_home);
    }

    Point mHomeCenterPoint = new Point();
    Point mReadCenterPoint = new Point();
    Point mUnlockCenterPoint = new Point();
    Point mRedbagCenterPoint = new Point();
    Point mHomeOriginalPoint = new Point();

    private int mTopBound;
    private int mBottomBound;
    private int mLeftBound;
    private int mRightBound;
    // 7, 计算边界
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


        mTopBound = mRedbagCenterPoint.y - mHomeView.getMeasuredHeight() / 2;
        mBottomBound = mHomeCenterPoint.y - mHomeView.getMeasuredHeight() / 2;
        mLeftBound = mReadCenterPoint.x - mHomeView.getMeasuredWidth() / 2;
        mRightBound = mUnlockCenterPoint.x - mHomeView.getMeasuredWidth() / 2;
    }

    // 2, 在onInterceptTouchEvent和onTouchEvent中调用VDH的方法
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // 在 VDH 拦截事件前，记得重置拖拽标记
        resetFlags();
        // 通过使用mDragHelper.shouldInterceptTouchEvent(ev)来决定我们是否应该拦截当前的事件
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 通过mDragHelper.processTouchEvent(event)来处理事件
        mViewDragHelper.processTouchEvent(event);
        return true; // 返回 true，表示事件被处理了。
    }

    private void resetFlags() {
        mIsHorizontalDrag = false;
        mIsVerticalDrag = false;
        mIsReachBound = false;
    }
    /**
     * 当前正在水平拖拽的标记
     */
    private boolean mIsHorizontalDrag = false;
    /**
     * 当前正在竖直拖拽的标记
     */
    private boolean mIsVerticalDrag = false;
    /**
     * 是否到达边界的标记
     */
    private boolean mIsReachBound;
    class ViewDragCallback extends ViewDragHelper.Callback {
        // 4, 这个是必须重写的方法，
        // 返回true,表示允许捕获该子view
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            // 当 child 是 mHomeView 时，才允许捕获。
            return child == mHomeView;
        }

        // 5, 限制被拖拽的子view沿纵轴的运动
        // 如果不重写，就不能实现纵向的拖动
        // 参一：child 表示正在拖拽的 view
        // 参二：top Attempted motion along the Y axis 理解为拖动的那个view想要到达位置的top值
        // 参三：增量，变化量
        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            // 11, 固定向拖拽
            if (mIsHorizontalDrag) {
                return mHomeView.getTop();
            }
            if (Math.abs(dy) > 0) {
                mIsVerticalDrag = true;
            }
            // 8, 上下边界控制
            final int newTop = Math.min(Math.max(mTopBound, top), mBottomBound);
            return newTop;
        }

        // 6, 限制被拖拽的子view沿横轴的运动
        // 如果不重写，就不能实现横向的拖动
        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            // 11, 固定向拖拽
            if (mIsVerticalDrag) {
                return mHomeView.getLeft();
            }
            if (Math.abs(dx) > 0) {
                mIsHorizontalDrag = true;
            }
            // 9, 左右边界控制
            final int newLeft = Math.min(Math.max(mLeftBound, left), mRightBound);
            return newLeft;
        }
        // 10, 松手返回起始点
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            // 判断释放的 view 是不是 mHomeView
            if (releasedChild == mHomeView) {
                // 让释放的 view 停在给定的位置
                mViewDragHelper.settleCapturedViewAt(mHomeOriginalPoint.x, mHomeOriginalPoint.y);
                invalidate();
            }
        }

        // 当拖拽的View的位置发生变化的时候回调(特指capturedview)
        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
            // 12, 到达边界时触发操作
            if (mIsReachBound) {
                return;
            }
            if (left <= mLeftBound) {
                mIsReachBound = true;
                Toast.makeText(getContext(), "到达左边界", Toast.LENGTH_SHORT).show();
            }
            if (left >= mRightBound) {
                mIsReachBound = true;
                Toast.makeText(getContext(), "到达右边界", Toast.LENGTH_SHORT).show();
            }
            if (top <= mTopBound) {
                mIsReachBound = true;
                Toast.makeText(getContext(), "到达上边界", Toast.LENGTH_SHORT).show();
            }
        }
    }
    // 10, 松手返回起始点
    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mViewDragHelper.continueSettling(true)) {
            invalidate();
        }
    }



}
