package com.wan.t18_manage_system_ui;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

/**
 * @author wzc
 * @date 2018/3/8
 */
public class HideStatusbarActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hide_statusbar);

    }

    boolean show;

    public void show(View view) {
        if (show) {
            View decorView = getWindow().getDecorView();
            // 显示status bar
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//        // 把action bar也一道儿显示
            if (getSupportActionBar() != null) {
                getSupportActionBar().show();
            }
        } else {
            View decorView = getWindow().getDecorView();
            int options = View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(options);
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getSupportActionBar().hide();
        }
        show = !show;
    }
}
