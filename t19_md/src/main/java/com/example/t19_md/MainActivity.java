package com.example.t19_md;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.t19_md.custom_animations.CustomAnimationsActivity;
import com.example.t19_md.drawables.DrawablesActivity;
import com.example.t19_md.shadows_clipping.ShadowsClippingActivity;

public class MainActivity extends Activity {

    private int mInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // enable transition
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_main);

        Button buttonShadow = (Button) findViewById(R.id.btn_shadow);
        buttonShadow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWindow().setExitTransition(new Explode());
                Intent intent = new Intent(MainActivity.this, ShadowsClippingActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());
            }
        });

        Button buttonDrawables = (Button) findViewById(R.id.btn_drawables);
        buttonDrawables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWindow().setExitTransition(new Explode());
                Intent intent = new Intent(MainActivity.this, DrawablesActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());
            }
        });

        Button buttonCustomAnimations = (Button) findViewById(R.id.btn_custom_animations);
        buttonCustomAnimations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWindow().setExitTransition(new Explode());
                Intent intent = new Intent(MainActivity.this, CustomAnimationsActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());
            }
        });


    }
}
