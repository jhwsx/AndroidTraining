package com.example.t19_md.drawables;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.palette.graphics.Palette;
import androidx.appcompat.widget.Toolbar;
import android.view.Window;

import com.example.t19_md.R;

/**
 * @author wzc
 * @date 2018/4/3
 * https://www.jianshu.com/p/9fcf316031ba
 */
public class PaletteActivity2 extends AppCompatActivity {
    private String tabTitles[] = new String[]{"主页", "分享", "收藏", "关注", "微博"};
    private Toolbar mToolbar;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette2);

        // Set up the toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);


       // Set up the tablayout and the viewpager
        mTabLayout = (TabLayout) findViewById(R.id.tablayout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return tabTitles.length;
            }

            @Override
            public Fragment getItem(int position) {
                return PaletteFragment2.newInstance(position);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return tabTitles[position];
            }
        });
        mTabLayout.setupWithViewPager(viewPager);
        changeTopBgColor(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changeTopBgColor(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void changeTopBgColor(int pos) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), PaletteFragment2.getCurrentDrawableRes(pos));

        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
                if (vibrantSwatch == null) {
                    return;
                }
                mToolbar.setBackgroundColor(vibrantSwatch.getRgb());
                mTabLayout.setBackgroundColor(vibrantSwatch.getRgb());
                mTabLayout.setSelectedTabIndicatorColor(colorBurn(vibrantSwatch.getRgb()));
                if (android.os.Build.VERSION.SDK_INT >= 21) {
                    Window window = getWindow();
                    window.setStatusBarColor(colorBurn(vibrantSwatch.getRgb()));
                    window.setNavigationBarColor(colorBurn(vibrantSwatch.getRgb()));
                }
            }
        });
    }

    /**
     * 颜色加深处理
     *
     * @param RGBValues RGB的值，由alpha（透明度）、red（红）、green（绿）、blue（蓝）构成，
     *                  Android中我们一般使用它的16进制，
     *                  例如："#FFAABBCC",最左边到最右每两个字母就是代表alpha（透明度）、
     *                  red（红）、green（绿）、blue（蓝）。每种颜色值占一个字节(8位)，值域0~255
     *                  所以下面使用移位的方法可以得到每种颜色的值，然后每种颜色值减小一下，在合成RGB颜色，颜色就会看起来深一些了
     * @return
     */
    private int colorBurn(int RGBValues) {
        int alpha = RGBValues >> 24;
        int red = RGBValues >> 16 & 0xFF;
        int green = RGBValues >> 8 & 0xFF;
        int blue = RGBValues & 0xFF;
        red = (int) Math.floor(red * (1 - 0.1));
        green = (int) Math.floor(green * (1 - 0.1));
        blue = (int) Math.floor(blue * (1 - 0.1));
        return Color.rgb(red, green, blue);
    }
}
