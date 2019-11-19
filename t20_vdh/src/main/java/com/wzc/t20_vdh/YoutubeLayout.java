package com.wzc.t20_vdh;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import androidx.customview.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.wzc.t19_vdh.R;

/**
 * Key points:
 * <p>
 *  - tryCaptureView returns true only for the header view
 *  - drag range is calculated onLayout
 *  - use VDH’s methods in onInterceptTouchEvent and onTouchEvent
 *  - call continueSettling in computeScroll (because VDH uses a scroller)
 *  - use smoothSlideViewTo to finish the drag motion
 *
 * @author wzc
 * @date 2018/3/24
 */
public class YoutubeLayout extends ViewGroup {

    private final ViewDragHelper mDragHelper;
    private View mHeaderView;
    private View mDescView;
    private int mDragRange;
    private int mTop;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private float mDragOffset;

    public YoutubeLayout(Context context) {
        this(context, null);
    }

    public YoutubeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 1, 初始化VDH
        mDragHelper = ViewDragHelper.create(this, 1.0F, new DragHelperCallback());
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        // 获取两个子view
        mHeaderView = findViewById(R.id.header);
        mDescView = findViewById(R.id.desc);
    }
    // 计算内部布局
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int maxWidth = MeasureSpec.getSize(widthMeasureSpec);
        int maxHeight = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(resolveSizeAndState(maxWidth, widthMeasureSpec, 0),
                resolveSizeAndState(maxHeight, heightMeasureSpec, 0));
    }
    // 摆放子View
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mDragRange = getHeight() - mHeaderView.getHeight();

        mHeaderView.layout(
                0,
                mTop,
                r,
                mTop + mHeaderView.getMeasuredHeight()
        );

        mDescView.layout(
                0,
                mTop + mHeaderView.getMeasuredHeight(),
                r,
                mTop + b
        );
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action = MotionEventCompat.getActionMasked(ev);
        if (action != MotionEvent.ACTION_DOWN) {
            mDragHelper.cancel();
            return super.onInterceptTouchEvent(ev);
        }
        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            mDragHelper.cancel();
            return false;
        }

        final float x = ev.getX();
        final float y = ev.getY();
        boolean interceptTap = false;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mInitialMotionX = x;
                mInitialMotionY = y;
                // Determine if the supplied view is under the given point in the
                // parent view's coordinate system.
                interceptTap = mDragHelper.isViewUnder(mHeaderView, (int) x, (int) y);
                break;
            case MotionEvent.ACTION_MOVE:
                final float adx = Math.abs(x - mInitialMotionX);
                final float ady = Math.abs(y - mInitialMotionY);
                final int slop = mDragHelper.getTouchSlop();
                if (ady > slop && adx > ady) {
                    mDragHelper.cancel();
                    return false;
                }
                break;
            default:
                break;
        }
        return super.onInterceptTouchEvent(ev) || interceptTap;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragHelper.processTouchEvent(event);
        final int action = event.getAction();
        final float x = event.getX();
        final float y = event.getY();

        boolean isHeaderViewUnder = mDragHelper.isViewUnder(mHeaderView, (int) x, (int) y);
        switch (action & MotionEventCompat.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                mInitialMotionX = x;
                mInitialMotionY = y;
                break;
            case MotionEvent.ACTION_UP:
                final float dx = x - mInitialMotionX;
                final float dy = y - mInitialMotionY;
                final int slop = mDragHelper.getTouchSlop();
                if (dx * dx + dy * dy < slop * slop && isHeaderViewUnder) {
                    if (mDragOffset == 0) {
                        smoothSlideTo(1f);
                    } else {
                        smoothSlideTo(0f);
                    }
                }
                break;
            default:
                break;
        }
        return isHeaderViewUnder && isViewHit(mHeaderView, (int) x, (int) y) || isViewHit(mDescView, (int) x, (int) y);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void maximize() {
        smoothSlideTo(0f);
    }

    public void minimize() {
        smoothSlideTo(1f);
    }

    private boolean smoothSlideTo(float slideOffset) {
        final int topBound = getPaddingTop();
        int y = (int) (topBound + slideOffset * mDragRange);

        if (mDragHelper.smoothSlideViewTo(mHeaderView, mHeaderView.getLeft(), y)) {
            ViewCompat.postInvalidateOnAnimation(this);
            return true;
        }
        return false;
    }

    private boolean isViewHit(View view, int x, int y) {
        int[] viewLocation = new int[2];
        view.getLocationOnScreen(viewLocation);
        int[] parentLocation = new int[2];
        this.getLocationOnScreen(parentLocation);
        int screenX = parentLocation[0] + x;
        int screenY = parentLocation[1] + y;
        return screenX >= viewLocation[0] && screenX <= viewLocation[0] + view.getWidth()
                && screenY >= viewLocation[1] && screenY <= viewLocation[1] + view.getHeight();
    }

    private class DragHelperCallback extends ViewDragHelper.Callback {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            // 只有mHeaderView可以被拖拽
            return child == mHeaderView;
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
            mTop = top;

            mDragOffset = (float) top / mDragRange;

            mHeaderView.setPivotX(mHeaderView.getWidth());
            mHeaderView.setPivotY(mHeaderView.getHeight());
            mHeaderView.setScaleX(1 - mDragOffset / 2);
            mHeaderView.setScaleY(1 - mDragOffset / 2);

            mDescView.setAlpha(1 - mDragOffset);

            requestLayout();
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            int top = getPaddingTop();
            if (yvel > 0 || (yvel == 0 && mDragOffset > 0.5F)) {
                top += mDragRange;
            }
            mDragHelper.settleCapturedViewAt(releasedChild.getLeft(), top);
            invalidate();
        }

        @Override
        public int getViewVerticalDragRange(View child) {
            return mDragRange;
//            return super.getViewVerticalDragRange(child);
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
//            return super.clampViewPositionVertical(child, top, dy);
            final int topBound = getPaddingTop();
            final int bottomBound = getHeight() - mHeaderView.getHeight() - mHeaderView.getPaddingBottom();

            int newTop = Math.min(Math.max(top, topBound), bottomBound);
            return newTop;
        }
    }

}
