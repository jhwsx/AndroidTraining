package com.example.t5_performing_network_operations;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by wzc on 2018/1/1.
 */

public class SettingsActivity extends AppCompatActivity{


    public static void start(Context context) {
        Intent starter = new Intent(context, SettingsActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(android.R.id.content);
        if (fragment == null) {
            fragment = new SettingsFragment();
            fragmentManager.beginTransaction().replace(android.R.id.content, fragment).commit();
        }
    }

}
