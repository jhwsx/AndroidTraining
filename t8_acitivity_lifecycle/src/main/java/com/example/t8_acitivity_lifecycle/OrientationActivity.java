package com.example.t8_acitivity_lifecycle;

import android.content.res.Configuration;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

/**
 * API指南: https://developer.android.google.cn/guide/topics/resources/runtime-changes.html?hl=zh-cn#RetainingAnObject
 * 自行处理配置变更
 * 阻止系统在某些配置变更期间重启 Activity，但要在配置确实发生变化时接收回调，这样，您就能够根据需要手动更新 Activity。
 * 使用这种办法时,记得在清单中添加android:configChanges="orientation|screenSize|keyboardHidden",屏幕方向改变时,不是
 * 仅添加orientation就可以了,因为从 Android 3.2（API 级别 13）开始，当设备在纵向和横向之间切换时，“屏幕尺寸”也会发生变化
 * 所以,要加上screenSize.添加以后,在进行屏幕切换时,只有onConfigurationChanged()方法会被调用
 * Created by wzc on 2018/1/29.
 */

public class OrientationActivity extends AppCompatActivity {
    private static final String TAG = OrientationActivity.class.getSimpleName();
    private int param = 1;
    private TextView mTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orientation_portrait);
        mTextView = (TextView) findViewById(R.id.textView);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("param", param);
        Log.d(TAG, "onSaveInstanceState: put param " + param);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        param = savedInstanceState.getInt("param");
        Log.d(TAG, "onRestoreInstanceState: get param " + param);
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(TAG, "onConfigurationChanged: ");
        switch (newConfig.orientation) {
            case Configuration.ORIENTATION_LANDSCAPE:
                mTextView.setText(getResources().getString(R.string.landscape));
                break;
            case Configuration.ORIENTATION_PORTRAIT:
                mTextView.setText(getResources().getString(R.string.portrait));
                break;
        }
    }
}
