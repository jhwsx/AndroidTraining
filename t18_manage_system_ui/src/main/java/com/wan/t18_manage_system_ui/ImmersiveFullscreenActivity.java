package com.wan.t18_manage_system_ui;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * @author wzc
 * @date 2018/3/7
 */
public class ImmersiveFullscreenActivity extends AppCompatActivity implements View.OnTouchListener{

    private GestureDetector mGestureDetector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immersive_fullscreen);
        final View decorView = getWindow().getDecorView();
        setImmersiveMode(decorView);
        FrameLayout frameLayout = (FrameLayout) findViewById(android.R.id.content);
        frameLayout.setOnTouchListener(this);
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                Log.d("MainActivity", "visibility:" + visibility);
                if (visibility == 0) {
                    decorView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setImmersiveMode(decorView);
                        }
                    }, 3000);
                }
            }
        });

        mGestureDetector = new GestureDetector(this, new MyGestrueDetectorListener());

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        return mGestureDetector.onTouchEvent(event);
    }

    private class MyGestrueDetectorListener implements GestureDetector.OnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }
        // 用户轻触触摸屏抬起时触发,由一个up事件触发的
        // 只有在轻击一下屏幕,迅速抬起来,才会触发本方法
        // 如果包含了其他的操作,就不会触发本方法,比如(多个down时,不会触发;包含了move时,不会触发)
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            show();
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }
    }

    private void setImmersiveMode(View decorView) {
        // 隐藏status bar和nav bar
        final int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        getSupportActionBar().hide();
    }

    public void show() {
        View decorView = getWindow().getDecorView();
        // 显示status bar
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        // 把action bar也一道儿显示
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().show();
//        }
    }
}
