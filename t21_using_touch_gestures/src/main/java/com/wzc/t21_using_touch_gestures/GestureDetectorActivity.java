package com.wzc.t21_using_touch_gestures;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.view.GestureDetectorCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * @author wzc
 * @date 2018/5/5
 */
public class GestureDetectorActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
    private static final String TAG = GestureDetectorActivity.class.getSimpleName();
    private GestureDetectorCompat mDetector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesturedetector);
        // 1, 创建手势识别实例
        mDetector = new GestureDetectorCompat(this, this);

        mDetector.setOnDoubleTapListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDetector.onTouchEvent(event);
        // 记得要调用超类的实现
        return super.onTouchEvent(event);
    }
    // 用户轻触触摸屏,由一个MotionEvent.ACTION_DOWN触发
    // 所有其它事件都在它之后触发。
    // 注意：不管是使用OnGestureListener还是SimpleOnGestureListener，实现这个方法并且返回true都是最佳实践。这是
    // 因为所有的手势都是开始于onDown；返回false的话（这是SimpleOnGestureListener的默认实现），系统会认为你要忽略
    // 掉剩余的手势，这样其它的回调都不会发生了。
    @Override
    public boolean onDown(MotionEvent e) {
        Log.d(TAG, "onDown: " + e.toString());
        return true;
    }

    // 用户轻触触摸屏,但并没有移动或抬起,也是由一个down事件触发的
    // 与onDown()的异同点:
    // 同: 都是由down事件触发的
    // 异: onDown一定会回调,onShowPress却不一定会回调(比如用户迅速触摸屏幕后离开时)
    // 作用: 通常用来给用户提供视觉反馈，让他们知道他们的动作已经被识别了，例如高亮一个元素。
    @Override
    public void onShowPress(MotionEvent e) {
        Log.d(TAG, "onShowPress: "+ e.toString());
    }

    // 用户轻触触摸屏抬起时触发,由一个up事件触发的
    // 只有在轻击一下屏幕,迅速抬起来,才会触发本方法
    // 如果包含了其他的操作,就不会触发本方法,比如(多个down时,不会触发;包含了move时,不会触发)
    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.d(TAG, "onSingleTapUp: "+ e.toString());
        return true;
    }

    // 用户按下触摸屏并拖动时触发,由一个down和多个move触发
    // 这个方法会多次触发
    // 参数含义:
    // 参一: 是开启拖动时的第一个down事件
    // 参二: 是一个move事件,触发当前onScroll的那个move事件
    // 参三: 相邻两次调用onScroll时,在x方向上移动的距离,向左为正,向右为负
    // 参四: 相邻两次调用onScroll时,在y方向上移动的距离,向上为正,向下为负
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.d(TAG, "onScroll: "+ e1.toString() + ", " + e2.toString() + ", distanceX = " + distanceX + ", distanceY = " + distanceY);
        return true;
    }

    // 用户长按触摸屏,由多个down触发
    // 只会触发一次
    // 这个方法之后不再跟其它方法了。
    @Override
    public void onLongPress(MotionEvent e) {
        Log.d(TAG, "onLongPress: "+ e.toString());
    }

    // 用户按下触摸屏,快速移动后松开,一个down,多个move和一个up触发
    // 如果用户不是快速移动后松开,则不会触发
    // 只会触发一次
    // 参数含义:
    // 参一:e1 是第一个down事件
    // 参二:e2 是一个up事件
    // 参三: velocityX：X轴上的移动速度，像素/秒,左为负,右为正
    // 参四: velocityY：Y轴上的移动速度，像素/秒,上为负,下为正
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.d(TAG, "onFling: " + e1.toString() + ", " + e2.toString() + ", velocityX = " + velocityX + ", velocityY = " + velocityY);
        return true;
    }

    // 当单次点击时触发该方法，严格的单击行为
    // 与onSingleTapUp()方法的区别:
    // 在是双击时,onSingleTapUp()方法也会执行
    // 在是双击时,onSingleTapConfirmed()方法不会执行
    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        Log.d(TAG, "onSingleTapConfirmed: "+ e.toString());
        return true;
    }

    // 双击执行
    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Log.d(TAG, "onDoubleTap: "+ e.toString());
        return true;
    }

    // 双击间隔中发生的动作，双击期间，down，move和up都会触发此方法
    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        Log.d(TAG, "onDoubleTapEvent: "+ e.toString());
        return true;
    }
}
