package com.example.t5_performing_network_operations;

import android.content.Context;
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
}
