package com.wzc.g3_intent;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author wzc
 * @date 2018/7/17
 */
public class ReceiveTextActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_text);
        mTextView = (TextView) findViewById(R.id.textView);

        if (getIntent() != null) {
            String text = getIntent().getStringExtra(Intent.EXTRA_TEXT);
            mTextView.setText(text);
        }

        ImageView imageView = (ImageView) findViewById(R.id.imageView2);
        Bitmap bm = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher).copy(Bitmap.Config.ARGB_8888, true);
        addShadow(bm);
        imageView.setImageBitmap(bm);

    }
    private GradientDrawable mBackShadowDrawableLR;
    public Bitmap addShadow(Bitmap bm) {
        int[] mBackShadowColors = new int[] { 0x00000000 , 0xB0AAAAAA};
        mBackShadowDrawableLR = new GradientDrawable(
                GradientDrawable.Orientation.BL_TR, mBackShadowColors);
        mBackShadowDrawableLR.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        mBackShadowDrawableLR.setBounds(0, 0, bm.getWidth() , bm.getHeight());
        Canvas canvas = new Canvas(bm);
        mBackShadowDrawableLR.draw(canvas);
        return bm;
    }

}
