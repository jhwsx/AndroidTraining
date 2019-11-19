package com.example.t5_performing_network_operations;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.preference.Preference;
import androidx.preference.PreferenceDialogFragmentCompat;
import androidx.preference.PreferenceFragmentCompat;

/**
 * Created by wzc on 2018/1/1.
 */

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
    

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.app_preferences);
    }

    @Override
    public void onResume() {
        super.onResume();
        // Registers a listener whenever a key changes
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        // Unregisters the listener set in onResume().
        // It's best practice to unregister listeners when your app isn't using them to cut down on
        // unnecessary system overhead. You do this in onPause().
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
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

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if ("key5".equals(key)) {
            MainActivity.sRefreshDisplay = true;
        }
    }
}
