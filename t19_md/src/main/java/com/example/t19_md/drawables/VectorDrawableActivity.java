package com.example.t19_md.drawables;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import android.view.View;
import android.widget.ImageView;

import com.example.t19_md.R;

/**
 * @author wzc
 * @date 2018/4/3
 * 介绍
 * https://blog.stylingandroid.com/vectordrawables-part-3/
 * 填坑
 * https://www.jianshu.com/p/e3614e7abc03
 */
public class VectorDrawableActivity extends AppCompatActivity {
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vector_drawable);
    }

    public void anim(View view) {

        ImageView imageView = (ImageView) view;
        Drawable drawable = imageView.getDrawable();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }
    }
}
