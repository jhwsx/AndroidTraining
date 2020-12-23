package com.wzc.g4_fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author wzc
 * @date 2018/7/23
 */
public class ArticleActivity extends AppCompatActivity {
    public static final String EXTRA_HEADLINE = "extra_headline";
    private String mHeadline;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent() != null) {
            mHeadline = getIntent().getStringExtra(EXTRA_HEADLINE);
        }
        boolean hasTwopanes = getResources().getBoolean(R.bool.has_two_panes);
        if (hasTwopanes) {
            finish();
            return;
        }
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(android.R.id.content, ArticleFragment.newInstance(mHeadline))
                    .commit();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
