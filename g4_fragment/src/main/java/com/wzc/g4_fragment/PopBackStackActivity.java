package com.wzc.g4_fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;

public class PopBackStackActivity extends AppCompatActivity implements PopBackStackCallback {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_back_stack);
        if (savedInstanceState == null) {
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction()
                    .add(R.id.fl_container, FragmentA.newInstance())
                    .commit();
        }
    }

    @Override
    public void onFragmentAButtonClicked() {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.fl_container, FragmentB.newInstance())
                .commit();
    }

    @Override
    public void onFragmentBButtonClicked() {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .add(R.id.fl_container, FragmentC.newInstance())
                .addToBackStack("b")
                .commit();
    }

    @Override
    public void onFragmentCButtonClicked() {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .add(R.id.fl_container, FragmentD.newInstance())
                .addToBackStack("c")
                .commit();
    }

    @Override
    public void onBackPressed() {
        getSupportFragmentManager().popBackStackImmediate("b", 0);
//        getSupportFragmentManager().popBackStack();
        super.onBackPressed();
    }
}
