package com.wzc.t7_viewpager_fragment_lazyload;

import android.content.Context;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private Context mContext;
    List<PageModel> mPageModels = new ArrayList<>();

    {
        mPageModels.add(new PageModel(R.string.first, FirstFragment.class.getName()));
        mPageModels.add(new PageModel(R.string.second, SecondFragment.class.getName()));
        mPageModels.add(new PageModel(R.string.third, ThirdFragment.class.getName()));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                return Fragment.instantiate(mContext, mPageModels.get(position).getFragmentName());
            }

            @Override
            public int getCount() {
                return mPageModels.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return getString(mPageModels.get(position).getTitleRes());
            }
        };
        mViewPager.setAdapter(adapter);

       TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        // 设置tabs均匀地分布于在屏幕的宽度范围内
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(mViewPager, true);
    }
}
