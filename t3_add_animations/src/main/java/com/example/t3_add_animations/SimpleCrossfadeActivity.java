package com.example.t3_add_animations;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;

/**
 * Created by wzc on 2017/12/24.
 */

public class SimpleCrossfadeActivity extends Activity {

    private ScrollView mContentView;
    private ProgressBar mLoadingView;
    private int mShortAnimationDuration;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_crossfade);
        // ###1, Set up the Animation###
        // 1, Create member variables for the views that you want to crossfade
        mContentView = (ScrollView) findViewById(R.id.content);
        mLoadingView = (ProgressBar) findViewById(R.id.loading_spinner);

        // 2, For the view that is being faded in, set its visibility to GONE
        mContentView.setVisibility(View.GONE);

        // 3, Cache the config_shortAnimTime system property in a member variable
        mShortAnimationDuration = getResources().getInteger(
                android.R.integer.config_shortAnimTime);
    }
    // ###2, Crossfade the Views###
    private void crossfade() {
        // 1, For the view that is fading in, set the alpha value to 0 and the visibility to VISIBLE
        mContentView.setAlpha(0);
        mContentView.setVisibility(View.VISIBLE);

        // 2, For the view that is fading in, animate its alpha value from 0 to 1. At the same time,
        // for the view that is fading out, animate the alpha value from 1 to 0.
        mContentView.animate()
                .setDuration(mShortAnimationDuration)
                .alpha(1)
                .setListener(null);

        mLoadingView.animate()
                .setDuration(mShortAnimationDuration)
                .alpha(0)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        mLoadingView.setVisibility(View.GONE);
                    }
                });

    }

    private void reset() {
        mContentView.setAlpha(0);
        mContentView.setVisibility(View.GONE);
        mLoadingView.setVisibility(View.VISIBLE);
        mLoadingView.setAlpha(1);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toggle_indicator, menu);
        return true;
    }

    int count = 0;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_toggle_indicator:
                int remainder = count % 2;
                switch (remainder) {
                    case 0:
                        crossfade();
                        break;
                    case 1:
                        reset();
                        break;
                }
                count++;

                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
