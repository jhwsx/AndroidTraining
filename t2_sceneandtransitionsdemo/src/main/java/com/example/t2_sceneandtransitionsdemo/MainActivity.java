package com.example.t2_sceneandtransitionsdemo;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
// https://www.jianshu.com/p/e497123652b5
public class MainActivity extends AppCompatActivity {

    private ViewGroup mSceneRoot;
    private Scene mAScene;
    private Scene mAnotherScene;
    private Transition mTransition;
    int i = 0;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        // Create the scene root for the scenes in this app
        mSceneRoot = (ViewGroup) findViewById(R.id.scene_root);

        // Create the scenes
        mAScene = Scene.getSceneForLayout(mSceneRoot, R.layout.a_scene, this);
        mAnotherScene = Scene.getSceneForLayout(mSceneRoot, R.layout.another_scene, this);
        mTransition = TransitionInflater.from(this).inflateTransition(R.transition.my_transition);

        mTransition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                Log.d("MainActivity", "onTransitionStart");
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                Log.d("MainActivity", "onTransitionEnd");
            }

            @Override
            public void onTransitionCancel(Transition transition) {
                Log.d("MainActivity", "onTransitionCancel");
            }

            @Override
            public void onTransitionPause(Transition transition) {
                Log.d("MainActivity", "onTransitionPause");
            }

            @Override
            public void onTransitionResume(Transition transition) {
                Log.d("MainActivity", "onTransitionResume");
            }
        });

        Button buttonChange = (Button) findViewById(R.id.button_change);

        buttonChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int remainder = i % 4;
                switch (remainder) {
                    case 0:
                        TransitionManager.go(mAnotherScene, mTransition);
                        break;
                    case 1:
                        TransitionManager.go(mAScene, mTransition);
                        break;
                    case 2:
                        // 不使用Scene来使用Transition
                        TransitionManager.beginDelayedTransition(mSceneRoot);

                        View view1 = mSceneRoot.findViewById(R.id.text_view1);
                        ViewGroup.LayoutParams layoutParams = view1.getLayoutParams();
                        int newSize = 300;
                        layoutParams.width = newSize;
                        layoutParams.height = newSize;
                        view1.setLayoutParams(layoutParams);
                        break;
                    case 3:
                        TransitionManager.beginDelayedTransition(mSceneRoot);

                        // 删除方形,添加圆形
                        View view = mSceneRoot.findViewById(R.id.text_view1);
                        mSceneRoot.removeView(view);
                        RelativeLayout.LayoutParams layoutParams1 = new RelativeLayout.LayoutParams(200, 200);
                        layoutParams1.addRule(RelativeLayout.CENTER_IN_PARENT);
                        ImageView imageView = new ImageView(mContext);
                        imageView.setLayoutParams(layoutParams1);
                        imageView.setImageResource(R.drawable.shape_circle);
                        mSceneRoot.addView(imageView);
                        break;
                }

                i++;

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_custom_transition, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_custom_transition:
                CustomTransitionActivity.start(mContext);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
