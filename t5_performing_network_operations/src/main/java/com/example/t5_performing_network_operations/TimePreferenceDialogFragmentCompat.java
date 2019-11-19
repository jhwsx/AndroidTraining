package com.example.t5_performing_network_operations;

import android.os.Bundle;
import androidx.preference.DialogPreference;
import androidx.preference.PreferenceDialogFragmentCompat;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.TimePicker;

/**
 * Created by wzc on 2018/1/3.
 * 用于自定义的preference dialog
 */

public class TimePreferenceDialogFragmentCompat extends PreferenceDialogFragmentCompat {

    private TimePicker mTimePicker;

    // 创建TimePreferenceFragmentCompat实例的静态方法
    public static TimePreferenceDialogFragmentCompat newInstance(String key) {
        final TimePreferenceDialogFragmentCompat
                fragment = new TimePreferenceDialogFragmentCompat();
        final Bundle b = new Bundle(1);
        // 添加了一个String参数表示preference的key，然后通过Bundle传递给dialog, 为了知道对话框属于哪个preference
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

        // 从相关的Preference里获取time
        Integer minuteAfterMidnight = null;
        DialogPreference preference = getPreference();
        if (preference instanceof TimePreference) {
            minuteAfterMidnight = ((TimePreference) preference).getTime();
        }

        // 把获取到的time设置给mTimePicker
        if (minuteAfterMidnight != null) {
            int hours = minuteAfterMidnight / 60;
            int minutes = minuteAfterMidnight % 60;
            boolean is24hour = DateFormat.is24HourFormat(getContext());

            mTimePicker.setIs24HourView(is24hour);
            mTimePicker.setCurrentHour(hours);
            mTimePicker.setCurrentMinute(minutes);
        }
    }

    @Override
    public void onDialogClosed(boolean positiveResult) {
        // 点击ok按钮保存选择的时间
        if (positiveResult) {
            // 产生要保存的值
            int hours = mTimePicker.getCurrentHour();
            int minutes = mTimePicker.getCurrentMinute();
            int minutesAfterMidnight = hours * 60 + minutes;

            // 获取相关的Preference,并且保存值
            DialogPreference preference = getPreference();
            if (preference instanceof TimePreference) {
                TimePreference timePreference = (TimePreference) preference;
                // This allows the client to ignore the user value.
                if (timePreference.callChangeListener(
                        minutesAfterMidnight)) {
                    // Save the value
                    timePreference.setTime(minutesAfterMidnight);
                }
            }
        }
    }
}
