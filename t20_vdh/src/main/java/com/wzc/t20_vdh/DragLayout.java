package com.wzc.t20_vdh;

import android.content.Context;
import androidx.customview.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.wzc.t19_vdh.R;

/**
 * @author wzc
 * @date 2018/3/24
 */
public class DragLayout extends LinearLayout {
    private static final String TAG = DragLayout.class.getSimpleName();
    private final ViewDragHelper mDragHelper;
    private boolean mDragHorizontal;
    private boolean mDragVertical;
    private boolean mDragEdge;
    private boolean mDragCapture;
    private View mDragView1;
    private View mDragView2;

    public DragLayout(Context context) {
        this(context, null);
    }

    public DragLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 1, 初始化VDH
        // 参一 : 当前的ViewGroup对象 Parent view to monitor
        // 参二 : 灵敏度 Multiplier for how sensitive the helper should be about detecting
        // the start of a drag. Larger values are more sensitive. 1.0f is normal.
        // 参三 : 提供信息和接收事件的回调
        mDragHelper = ViewDragHelper.create(this, 1.0F, new DragHelperCallback());
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mDragView1 = findViewById(R.id.drag1);
        mDragView2 = findViewById(R.id.drag2);
    }

    // 2, 在onInterceptTouchEvent和onTouchEvent中调用VDH的方法
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // 通过使用mDragHelper.shouldInterceptTouchEvent(ev)来决定我们是否应该拦截当前的事件
        return mDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 通过mDragHelper.processTouchEvent(event)来处理事件
        mDragHelper.processTouchEvent(event);
        return true;
    }

    public void setDragHorizontal(boolean dragHorizontal) {
        mDragHorizontal = dragHorizontal;
        mDragView2.setVisibility(View.GONE);
    }

    public void setDragVertical(boolean dragVertical) {
        mDragVertical = dragVertical;
        mDragView1.setVisibility(View.GONE);
    }

    public void setDragEdge(boolean dragEdge) {
        // 设置VDH的边缘追踪 edge tracking
        mDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_ALL);
        mDragEdge = dragEdge;
        mDragView2.setVisibility(View.GONE);
    }

    public void setDragCapture(boolean dragCapture) {
        mDragCapture = dragCapture;
    }

    private class DragHelperCallback extends ViewDragHelper.Callback {

        // 返回true,表示允许捕获该子view
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            Log.d(TAG, "tryCaptureView: child = " + child.getId() + ", pointerId = " + pointerId);
            if (mDragCapture) {
                // 两个子View都在页面上, 但只有mDragView1是可拖拽的
                return child == mDragView1;
            }
            return true;
        }

        // 限制被拖拽的子view沿横轴的运动
        // 默认实现不允许横轴上的运动
        // 子类必须覆写此方法,从而提供希望的clamping
        // 参一 : child 正被拖拽的子view
        // 参二 : x轴上意图达到的位置,是child的左边在屏幕上的位置
        // 参三 : 推荐的left位置的增值
        // 使用默认的方式,拖拽时不管怎么拖拽,都会跳跃回抵消掉margins and parent padding的位置
        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            Log.d(TAG, "clampViewPositionHorizontal: child = " + child.getId() + ", child.getX() = " + child.getX() + ", left = " + left + ", dx = " + dx);
            // 水平拖拽 选择拖拽mDragView1 边缘拖拽
            if (mDragHorizontal || mDragCapture||mDragEdge) {
                // 进行边界控制,注意要take margins and parent padding into consideration
                // 左边界
                int leftBound = getPaddingLeft() + ((LayoutParams) mDragView1.getLayoutParams()).leftMargin;
                // 右边界
                int rightBound = getWidth() - mDragView1.getWidth() - getPaddingRight() - ((LayoutParams) mDragView1.getLayoutParams()).rightMargin;
                // 新的left
                int newLeft = Math.min(Math.max(leftBound, left), rightBound);

                return newLeft;
            }
            return super.clampViewPositionHorizontal(child, left, dx);
        }


        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            Log.d(TAG, "clampViewPositionVertical: child = " + child.getId() + ", child.getY() = " + child.getY() + ", top = " + top + ", dy = " + dy);
            // 竖直拖拽
            if (mDragVertical) {
                // 进行边界控制,注意要take margins and parent padding into consideration
                // 上边界
                int topBound = getPaddingTop() + ((LayoutParams) mDragView2.getLayoutParams()).topMargin;
                // 下边界
                int bottomBound = getHeight() - mDragView2.getHeight() - getPaddingBottom() - ((LayoutParams) mDragView2.getLayoutParams()).bottomMargin;
                // 新的top
                int newTop = Math.min(Math.max(topBound, top), bottomBound);

                return newTop;
            }
            return super.clampViewPositionVertical(child, top, dy);
        }

        // Called when one of the subscribed edges in the parent view has been touched
        // by the user while no child view is currently captured.
        @Override
        public void onEdgeTouched(int edgeFlags, int pointerId) {
            super.onEdgeTouched(edgeFlags, pointerId);
            Log.d(TAG, "onEdgeTouched: edgeFlags = " + edgeFlags + ", pointerId = " + pointerId);
        }

        // Called when the user has started a deliberate drag away from one
        // of the subscribed edges in the parent view while no child view is currently captured.
        // In this method, you have to capture a child view manually.
        @Override
        public void onEdgeDragStarted(int edgeFlags, int pointerId) {
            super.onEdgeDragStarted(edgeFlags, pointerId);
            Log.d(TAG, "onEdgeDragStarted: edgeFlags = " + edgeFlags + ", pointerId = " + pointerId);
            if (mDragEdge) {
                // 捕获父布局中的一个特定的子view用来拖拽
                mDragHelper.captureChildView(mDragView1, pointerId);
            }
        }

        @Override
        public int getViewHorizontalDragRange(View child) {
            int viewHorizontalDragRange = super.getViewHorizontalDragRange(child);
            Log.d(TAG, "getViewHorizontalDragRange: child = " + child.getId() + ", viewHorizontalDragRange = " + viewHorizontalDragRange);
            return viewHorizontalDragRange;
        }

        @Override
        public int getViewVerticalDragRange(View child) {
            int viewVerticalDragRange = super.getViewVerticalDragRange(child);
            Log.d(TAG, "getViewVerticalDragRange: child = " + child.getId() + ", viewVerticalDragRange = " + viewVerticalDragRange);
            return viewVerticalDragRange;
        }
    }
}
