package com.example.t19_md.shadows_clipping;

import android.app.Activity;
import android.graphics.Outline;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.TextView;

import com.example.t19_md.R;

/**
 * @author wzc
 * @date 2018/4/13
 */
public class ShadowsClippingActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shadows);

        // 这里是使用ViewOutlineProvider来设置背景圆角
        TextView tv4 = (TextView) findViewById(R.id.tv4);
        tv4.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
//                outline.setRoundRect(0,0,view.getWidth(), view.getHeight(),10);
                outline.setOval(0, 0, view.getWidth(), view.getHeight());
            }
        });
//        tv4.setOutlineProvider(null);
        tv4.setClipToOutline(true);
    }
}
