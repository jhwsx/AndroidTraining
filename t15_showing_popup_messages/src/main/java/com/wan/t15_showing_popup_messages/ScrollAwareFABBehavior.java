package com.wan.t15_showing_popup_messages;

import android.content.Context;
import android.os.Build;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorListener;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * 用于定义按钮该如何响应包含在同一CoordinatorLayout之内的其它view
 * @author wzc
 * @date 2018/3/9
 */
public class ScrollAwareFABBehavior extends FloatingActionButton.Behavior {

    public ScrollAwareFABBehavior(Context context, AttributeSet attrs) {
        super();
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout,
                                       FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
        Log.d("ScrollAwareFABBehavior", "onStartNestedScroll directTargetChild : " + directTargetChild + ", target : "
                + target + "nestedScrollAxes : " + nestedScrollAxes);
        // 响应垂直方向上的滚动事件
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL
                || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        Log.d("ScrollAwareFABBehavior", "onNestedScroll dxConsumed = " + dxConsumed + ", dyConsumed = " + dyConsumed);
//        if (dyConsumed > 0 && !this.mIsAnimatingOut && child.getVisibility() == View.VISIBLE) {
//            animateOut(child);
//        } else if (dyConsumed < 0 && child.getVisibility() != View.VISIBLE) {
//            animateIn(child);
//        }
    }

    private static final android.view.animation.Interpolator INTERPOLATOR =
            new FastOutSlowInInterpolator();
    private boolean mIsAnimatingOut = false;

    // Same animation that FloatingActionButton.Behavior uses to
    // hide the FAB when the AppBarLayout exits
    private void animateOut(final FloatingActionButton button) {
        if (Build.VERSION.SDK_INT >= 14) {
            ViewCompat.animate(button).scaleX(0.0F).scaleY(0.0F).alpha(0.0F)
                    .setInterpolator(INTERPOLATOR).withLayer()
                    .setListener(new ViewPropertyAnimatorListener() {
                        @Override
                        public void onAnimationStart(View view) {
                            ScrollAwareFABBehavior.this.mIsAnimatingOut = true;
                        }

                        @Override
                        public void onAnimationCancel(View view) {
                            ScrollAwareFABBehavior.this.mIsAnimatingOut = false;
                        }

                        @Override
                        public void onAnimationEnd(View view) {
                            ScrollAwareFABBehavior.this.mIsAnimatingOut = false;
                            // https://stackoverflow.com/questions/41153619/floating-action-button-not-visible-on-scrolling-after-updating-google-support?noredirect=1&lq=1
                            // ATTENTION: 这里需要设置为INVISIBLE而不是GONE,因为支持库有些版本的CoordinatorLayout的onNestedScroll会跳过设置为GONE的view
                            view.setVisibility(View.INVISIBLE);
                        }
                    }).start();
        } else {
            Animation anim = AnimationUtils.loadAnimation(button.getContext(), R.anim.fab_out);
            anim.setInterpolator(INTERPOLATOR);
            anim.setDuration(200L);
            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    ScrollAwareFABBehavior.this.mIsAnimatingOut = true;
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    ScrollAwareFABBehavior.this.mIsAnimatingOut = false;
                    button.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onAnimationRepeat(final Animation animation) {
                }
            });
            button.startAnimation(anim);
        }
    }

    // Same animation that FloatingActionButton.Behavior
    // uses to show the FAB when the AppBarLayout enters
    private void animateIn(FloatingActionButton button) {
        button.setVisibility(View.VISIBLE);
        if (Build.VERSION.SDK_INT >= 14) {
            ViewCompat.animate(button).scaleX(1.0F).scaleY(1.0F).alpha(1.0F)
                    .setInterpolator(INTERPOLATOR).withLayer().setListener(null)
                    .start();
        } else {
            Animation anim = AnimationUtils.loadAnimation(button.getContext(), R.anim.fab_in);
            anim.setDuration(200L);
            anim.setInterpolator(INTERPOLATOR);
            button.startAnimation(anim);
        }
    }
}
