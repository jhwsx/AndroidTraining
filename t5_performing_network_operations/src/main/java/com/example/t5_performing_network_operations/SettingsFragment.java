package com.example.t5_performing_network_operations;

import android.os.Bundle;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceDialogFragmentCompat;
import android.support.v7.preference.PreferenceFragmentCompat;

/**
 * Created by wzc on 2018/1/1.
 */

public class SettingsFragment extends PreferenceFragmentCompat {
    

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.app_preferences);
    }

    @Override
    public void onDisplayPreferenceDialog(Preference preference) {
        // Try if the preference is one of our custom Preferences
        PreferenceDialogFragmentCompat dialogFragment = null;
        if (preference instanceof TimePreference) {
            // 使用对应的key,创建一个TimePreferenceDialogFragmentCompat的实例
            dialogFragment = TimePreferenceDialogFragmentCompat.newInstance(preference.getKey());
        }

        // 若是自定义的Preferences,就显示出它的dialog
        if (dialogFragment != null) {
            dialogFragment.setTargetFragment(this, 0);
            dialogFragment.show(getFragmentManager(),"android.support.v7.preference" +
                    ".PreferenceFragment.DIALOG");
        } else {
            // 处理预定义的DialogPreference
            super.onDisplayPreferenceDialog(preference);
        }
    }
}
