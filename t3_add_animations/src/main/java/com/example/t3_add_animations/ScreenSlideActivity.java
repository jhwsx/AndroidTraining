package com.example.t3_add_animations;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

/**
 * Created by wzc on 2017/12/24.
 */

public class ScreenSlideActivity extends FragmentActivity implements View.OnClickListener {

    private ViewPager mViewPager;
    private static final int NUM_PAGES = 5;
    private TextView mPre;
    private TextView mNext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mPre = (TextView) findViewById(R.id.tv_pre);
        mNext = (TextView) findViewById(R.id.tv_next);
        mPre.setOnClickListener(this);
        mNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int currentItem = mViewPager.getCurrentItem();
        switch (v.getId()) {
            case R.id.tv_pre:

                if (currentItem>0) {
                    currentItem--;
                    mNext.setText("Next");
                    mViewPager.setCurrentItem(currentItem);
                } else {
                    mPre.setEnabled(false);
                    mPre.setTextColor(getResources().getColor(android.R.color.holo_blue_light));
                }

                break;
            case R.id.tv_next:
                if (currentItem >= NUM_PAGES-1) {
                    mNext.setText("Finish");
                } else {
                    mPre.setEnabled(true);
                    mPre.setTextColor(getResources().getColor(android.R.color.darker_gray));
                    currentItem++;
                    mViewPager.setCurrentItem(currentItem);
                }
                break;
        }
    }

    private class MyPagerAdapter extends FragmentStatePagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ScreenSlidePageFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
