package com.wzc.g3_intent;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;

import java.io.IOException;

/**
 * @author wzc
 * @date 2018/7/17
 */
public class ReceiveImageActivity extends AppCompatActivity {

    private ImageView mImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_image);
        mImageView = (ImageView) findViewById(R.id.imageView);
        if (getIntent() != null) {
            Uri uri = getIntent().getParcelableExtra(Intent.EXTRA_STREAM);
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                mImageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
