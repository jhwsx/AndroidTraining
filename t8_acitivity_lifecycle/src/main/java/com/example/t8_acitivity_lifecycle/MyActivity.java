package com.example.t8_acitivity_lifecycle;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

/**
 * 在配置变更期间保留对象
 * 允许 Activity 在配置变更时重启，但是要将有状态对象传递给 Activity 的新实例。
 * 当转换屏幕时,添加setRetainInstance(boolean)时,那么数据会得到保存;不添加时,那么数据不会得到保存.但是注意片段不会为null的
 * @author wzc
 * @date 2018/1/29
 */
public class MyActivity extends AppCompatActivity {

    private TextView mTextView;
    String data = "onCreate()时的初始化数据";
    private RetainedFragment mFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        mTextView = (TextView) findViewById(R.id.textView);

        mFragment = (RetainedFragment) getSupportFragmentManager().findFragmentByTag("data");
        if (BuildConfig.DEBUG)
            Log.d("MyActivity", "mFragment:" + mFragment);
        if (mFragment == null) {
            mFragment = new RetainedFragment();
            getSupportFragmentManager().beginTransaction().add(mFragment, "data").commit();
            mFragment.setData(data);
        } else {
            data = mFragment.getData();
        }
        mTextView.setText(data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFragment.setData("onDestroy()时的数据");
    }
}
