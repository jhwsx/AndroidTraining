package com.wzc.g4_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

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

        getSupportFragmentManager().beginTransaction()
                .add(android.R.id.content, ArticleFragment.newInstance(mHeadline))
                .commit();
    }
}
