package com.wan.t16_creating_custom_views.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.wan.t16_creating_custom_views.R;

/**
 * @author wzc
 * @date 2018/2/28
 */
public class MyView extends View {

    private Paint mPaint;

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        if (Build.VERSION.SDK_INT > 11) {
            setLayerType(LAYER_TYPE_SOFTWARE, null);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Bitmap src = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        mPaint.setColor(Color.RED);
        Bitmap shadowBitmap = src.extractAlpha();
        mPaint.setMaskFilter(new BlurMaskFilter(10, BlurMaskFilter.Blur.NORMAL));
        canvas.drawBitmap(shadowBitmap, 100, 100, mPaint);
        canvas.drawBitmap(src, 100, 100, null);
    }
}
