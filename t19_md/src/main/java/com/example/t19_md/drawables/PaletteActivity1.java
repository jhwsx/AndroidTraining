package com.example.t19_md.drawables;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.example.t19_md.R;

/**
 * @author wzc
 * @date 2018/4/2
 */
public class PaletteActivity1 extends AppCompatActivity {
    private ScrollView mContentSv;
    private CollapsingToolbarLayout mToolbarCtl;
    private ImageView mHeaderIv;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_palette1);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mToolbarCtl = (CollapsingToolbarLayout) findViewById(R.id.ctl_toolbar);
        mHeaderIv = (ImageView) findViewById(R.id.iv_header);
        mContentSv = (ScrollView) findViewById(R.id.sv_content);

        ViewCompat.setNestedScrollingEnabled(mContentSv, true);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_palette_00);
        if (bitmap != null && !bitmap.isRecycled()) {
            Palette.from(bitmap)
                    .generate(new Palette.PaletteAsyncListener() {
                        @Override
                        public void onGenerated(Palette palette) {
                            // 获取主题色
                            int color = palette.getDominantColor(ContextCompat.getColor(PaletteActivity1.this, R.color.colorAccent));
                            //
                            int colorDark = palette.getDarkMutedColor(color);
                            int titleTextColor = palette.getDominantSwatch().getTitleTextColor();
                            mToolbarCtl.setContentScrimColor(color);
                            mToolbarCtl.setStatusBarScrimColor(colorDark);
                            mToolbarCtl.setCollapsedTitleTextColor(titleTextColor);
                            mToolbarCtl.setExpandedTitleColor(titleTextColor);
                            ToolbarColorizeHelper.colorizeToolbar(mToolbar, titleTextColor, PaletteActivity1.this);

                            Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
                            Palette.Swatch lightVibrantSwatch = palette.getLightVibrantSwatch();
                            Palette.Swatch darkVibrantSwatch = palette.getDarkVibrantSwatch();
                            Palette.Swatch mutedSwatch = palette.getMutedSwatch();
                            Palette.Swatch lightMutedSwatch = palette.getLightMutedSwatch();
                            Palette.Swatch darkMutedSwatch = palette.getDarkMutedSwatch();
                        }
                    });
        }
        }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
