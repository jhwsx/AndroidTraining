package com.example.t4_test_apps;

import android.content.SharedPreferences;

/**
 * Created by wzc on 2017/12/26.
 */

public class SharedPreferencesHelper {
    static final String KEY_NAME = "key_name";

    private final SharedPreferences mSharedPreferences;

    public SharedPreferencesHelper(SharedPreferences sharedPreferences) {
        mSharedPreferences = sharedPreferences;
    }

    public boolean saveName(String name) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(KEY_NAME, name);
        return editor.commit();
    }

    public String getName() {
        return mSharedPreferences.getString(KEY_NAME, "");
    }
}
