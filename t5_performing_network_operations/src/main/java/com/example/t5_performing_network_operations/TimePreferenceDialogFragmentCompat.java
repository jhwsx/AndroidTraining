package com.example.t5_performing_network_operations;

import android.os.Bundle;
import android.support.v7.preference.PreferenceDialogFragmentCompat;
import android.view.View;
import android.widget.TimePicker;

/**
 * Created by wzc on 2018/1/3.
 */

public class TimePreferenceDialogFragmentCompat extends PreferenceDialogFragmentCompat {

    private TimePicker mTimePicker;

    public static TimePreferenceDialogFragmentCompat newInstance(String key) {
        final TimePreferenceDialogFragmentCompat
                fragment = new TimePreferenceDialogFragmentCompat();
        final Bundle b = new Bundle(1);
        b.putString(ARG_KEY, key);
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);
        // 希望它显示的总是存储在SharedPreference中的时间,在onBindDialogView方法中通过布局得到TimePicker。
        // 然后用getPreference方法得到打开对话框的preference
        mTimePicker = (TimePicker) view.findViewById(R.id.edit);

        // Exception when there is no TimePicker
        if (mTimePicker == null) {
            throw new IllegalStateException("Dialog view must contain" +
                    " a TimePicker with id 'edit'");
        }


    }

    @Override
    public void onDialogClosed(boolean positiveResult) {

    }
}
