package com.wzc.g6_app_resources;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

/**
 * @author wzc
 * @date 2018/10/10
 */
public class AnimationResourcesActivity extends Activity {

    private TextView mTvPropertyAnimator;
    private Context mContext = this;
    private TextView mTvTweenAnimation;

    public static void start(Context context) {
        Intent starter = new Intent(context, AnimationResourcesActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_resources);
        mTvPropertyAnimator = (TextView) findViewById(R.id.tv_propertyanimator);
        mTvPropertyAnimator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.animator.property_animator);
                set.setTarget(mTvPropertyAnimator);
                set.start();
            }
        });

        mTvTweenAnimation = (TextView) findViewById(R.id.tv_tweenanimation);
        mTvTweenAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.tween_animation);
                mTvTweenAnimation.startAnimation(animation);
            }
        });
    }


}
