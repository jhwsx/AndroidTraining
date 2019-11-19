package com.example.t19_md.drawables;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.t19_md.R;

/**
 * Created by wzc on 2018/4/1.
 * http://yifeng.studio/2017/03/30/android-tint/
 */

public class TintActivity extends Activity {

    private ImageView mIv3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tint);
        mIv3 = (ImageView) findViewById(R.id.iv3);
        // 这里使用tint来实现一个selector的效果,可以少加一张图片
        Drawable originalDrawable =  ContextCompat.getDrawable(this, R.drawable.download);
        Drawable tintDrawable = DrawableCompat.wrap(originalDrawable).mutate();
        DrawableCompat.setTintList(tintDrawable,ContextCompat.getColorStateList(this, R.color.selector_download));
        mIv3.setImageDrawable(tintDrawable);
        mIv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TintActivity.this, "onClick", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
