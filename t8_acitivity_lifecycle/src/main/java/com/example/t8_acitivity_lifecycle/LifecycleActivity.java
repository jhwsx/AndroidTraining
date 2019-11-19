package com.example.t8_acitivity_lifecycle;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class LifecycleActivity extends AppCompatActivity {

    private static final String TAG = LifecycleActivity.class.getSimpleName();
    private Context mContext = this;
    private int param = 1;
    private Button mButton;
    // 创建时调用
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);
        Log.d(TAG, "onCreate() called");
        mButton = (Button) findViewById(R.id.btn);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, TargetActivity.class);
                startActivity(intent);
            }
        });
    }

    // Activity创建或者从后台重新回到前台时被调用
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    // Activity从后台重新回到前台时被调用
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart() called");
    }

    // Activity创建或者从被覆盖、后台重新回到前台时被调用
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    // Activity窗口获得或者失去焦点时被调用，在onResume之后或者onPause之后
    // 首次创建呈现在用户面前，在onResume之后，为true
    // 从后台回到用户面前，在onResume之后，为true
    // 当前Activity被其他Activity覆盖，在onPause之后，为false
    // 按Home键回到主屏，在onResume之后，为false
    // 当前Activity退出，在onResume之后，在onResume之后，为false
    // 当前Activity在用户面前按下recent键，在onPause之后，为false，再按下recent键，再onResume之后，为true
    // 用处：程序启动时想要获取视特定视图组件的尺寸大小，在onCreate中可能无法取到，因为窗口Window对象还没创建完成，这个时候我们就需要在onWindowFocusChanged里获取
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.d(TAG, "onWindowFocusChanged() called hasFocus: " + hasFocus);
    }

    // Activity被部分覆盖或者被透明全屏覆盖时被调用
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    // Activity被完全覆盖不可见时调用，如退出当前Activity，跳转到新的Activity
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    // Activity销毁时调用，如退出当前Activity
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    /**
     * Activity被系统杀死时调用
     * 当屏幕方向改变时，Activity被销毁再重建时调用,这时会继续调用onRestoreInstanceState()
     * 当Activity处于后台，系统资源紧张将其杀死
     * 当按下Home键，或者跳转到其他Activity时会被调用，系统是为了保存当前View的状态，这时不会继续调用onRestoreInstanceState()
     * 在onPause()之后，onStop()之前调用
     *
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("param", param);
        Log.d(TAG, "onSaveInstanceState() called put param: " + param);
        super.onSaveInstanceState(outState);
    }
    /**
     * Activity被系统杀死后再重建时调用
     * 当屏幕方向改变时，Activity被销毁再重建时调用
     * 当Activity处于后台，系统资源紧张将其杀死，用户又启动该Activity
     * 这两种情况下，onRestoreInstanceState()都会被调用，再onStart()之后
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        param = savedInstanceState.getInt("param");
        Log.d(TAG, "onRestoreInstanceState() called get param: " + param);
        super.onRestoreInstanceState(savedInstanceState);
    }
}
