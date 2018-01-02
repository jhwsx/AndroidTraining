package com.example.t5_performing_network_operations;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.preference.DialogPreference;
import android.util.AttributeSet;

/**
 * Created by wzc on 2018/1/2.
 */

public class TimePreference extends DialogPreference {

    private int mTime;
    private int mDialogLayoutResId = R.layout.pref_dialog_time;

    public TimePreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public TimePreference(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, defStyleAttr);
    }

    public TimePreference(Context context, AttributeSet attrs) {
        this(context, attrs,0 );
    }

    public TimePreference(Context context) {
        this(context, null);
    }

    public int getTime() {
        return mTime;
    }

    public void setTime(int time) {
        mTime = time;
        // Save to Shared Preferences
        persistInt(time);
    }

    // 读取默认的值（我们可以在 xml/app_preferences.xml中使用android:defaultValue属性来定义默认值）
    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        // Default value from attribute. Fallback value is set to 0.
        return a.getInt(index, 0);
    }

    // 从SharedPreference读取存储的值并保存到mTime变量
    @Override
    protected void onSetInitialValue(boolean restorePersistedValue, Object defaultValue) {
        super.onSetInitialValue(restorePersistedValue, defaultValue);
        setTime(restorePersistedValue ? getPersistedInt(mTime) : (Integer) defaultValue);
    }

    @Override
    public int getDialogLayoutResource() {
        return mDialogLayoutResId;
    }
}
