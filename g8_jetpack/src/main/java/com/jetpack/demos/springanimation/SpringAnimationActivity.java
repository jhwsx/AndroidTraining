package com.jetpack.demos.springanimation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.PathInterpolator;
import android.widget.TextView;

import com.jetpack.demos.R;

/**
 * @author wzc
 * @date 2019/2/20
 */
public class SpringAnimationActivity extends AppCompatActivity {

    private TextView mViewById;
    private TextView mViewById2;

    public static void start(Context context) {
        Intent starter = new Intent(context, SpringAnimationActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spring_animation);
        mViewById = (TextView) findViewById(R.id.textView2);
        mViewById2 = (TextView) findViewById(R.id.textView3);
        startAlphaBreathAnimation(mViewById,0);
        startAlphaBreathAnimation(mViewById2,50);
    }

    /**
     * 开启透明度渐变呼吸动画
     */
    private void startAlphaBreathAnimation(TextView view, long delay) {
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(view, "scaleX", 1F, 1.2F);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(view, "scaleY", 1F, 1.2F);
        objectAnimator1.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator2.setRepeatCount(ValueAnimator.INFINITE);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimator1, objectAnimator2);
        animatorSet.setStartDelay(delay);
        animatorSet.setDuration(4000);
        animatorSet.setInterpolator(new PathInterpolator(0.21F, 0.0F, 0.12F, 1.0F));//使用自定义的插值器
        animatorSet.start();
    }

    public class BraetheInterpolator implements TimeInterpolator {
        @Override
        public float getInterpolation(float input) {

            float x = 6 * input;
            float k = 1.0f / 3;
            int t = 6;
            int n = 1;//控制函数周期，这里取此函数的第一个周期
            float PI = 3.1416f;
            float output = 0;

            if (x >= ((n - 1) * t) && x < ((n - (1 - k)) * t)) {
                output = (float) (0.5 * Math.sin((PI / (k * t)) * ((x - k * t / 2) - (n - 1) * t)) + 0.5);

            } else if (x >= (n - (1 - k)) * t && x < n * t) {
                output = (float) Math.pow((0.5 * Math.sin((PI / ((1 - k) * t)) * ((x - (3 - k) * t / 2) - (n - 1) * t)) + 0.5), 2);
            }

            return output;
        }
    }
}
