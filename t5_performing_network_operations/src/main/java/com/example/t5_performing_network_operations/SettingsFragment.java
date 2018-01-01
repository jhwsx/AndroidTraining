package com.example.t5_performing_network_operations;

import android.os.Bundle;
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
}
