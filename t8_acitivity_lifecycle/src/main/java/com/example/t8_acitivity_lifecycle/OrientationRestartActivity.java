package com.example.t8_acitivity_lifecycle;

import android.content.res.Configuration;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

/**
 * 因屏幕方向变化导致重启行为的页面
 * 不会调用onConfigurationChanged()方法
 * 重启应用的缺点: 重启应用并恢复大量数据不仅成本高昂，而且给用户留下糟糕的使用体验
 * @author wzc
 * @date 2018/1/29
 */
public class OrientationRestartActivity extends AppCompatActivity {
    private static final String TAG = OrientationRestartActivity.class.getSimpleName();
    private int param;
    private TextView mTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orientation);
        if (savedInstanceState != null) {
            param = savedInstanceState.getInt("param");
        }
        mTextView = (TextView) findViewById(R.id.textView);

        mTextView.setText( mTextView.getText().toString() + " "+param);
        Log.d(TAG, "onCreate: param: " + param);
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
        param++;
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
