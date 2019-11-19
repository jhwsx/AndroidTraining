package com.example.t2_sceneandtransitionsdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

/**
 * Created by wzc on 2017/12/24.
 */

public class CustomTransitionActivity extends AppCompatActivity implements View.OnClickListener {

    private Scene[] mScenes;
    private Context mContext;
    private CustomTransition mCustomTransition;
    private int mCurrentScene;
    private static final String STATE_CURRENT_SCENE = "current_scene";

    public static void start(Context context) {
        Intent starter = new Intent(context, CustomTransitionActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        if (savedInstanceState != null) {
            mCurrentScene = savedInstanceState.getInt(STATE_CURRENT_SCENE);
        }
        setContentView(R.layout.activity_custom_transition);
        Button btnShowNext = (Button) findViewById(R.id.btn_show_next);
        btnShowNext.setOnClickListener(this);
        FrameLayout sceneRoot = (FrameLayout) findViewById(R.id.container);
        // 获取Scene
        mScenes = new Scene[]{
                Scene.getSceneForLayout(sceneRoot, R.layout.scene_1, mContext),
                Scene.getSceneForLayout(sceneRoot, R.layout.scene_2, mContext),
                Scene.getSceneForLayout(sceneRoot, R.layout.scene_3, mContext),
        };

        // 获取自定义的Transition
        mCustomTransition = new CustomTransition();

        // 显示初始的Scene
        TransitionManager.go(mScenes[mCurrentScene % mScenes.length]);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show_next:
                mCurrentScene = (mCurrentScene + 1) % mScenes.length;
                TransitionManager.go(mScenes[mCurrentScene % mScenes.length]);
                break;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putInt(STATE_CURRENT_SCENE, mCurrentScene);
        super.onSaveInstanceState(outState, outPersistentState);
    }
}
