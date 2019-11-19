package com.example.t19_md.drawables;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.transition.Explode;
import android.view.View;
import android.widget.TextView;

import com.example.t19_md.R;

/**
 * @author wzc
 * @date 2018/4/13
 */
public class DrawablesActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawables);
        final TextView textView = (TextView) findViewById(R.id.tv);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWindow().setExitTransition(new Explode());
                Intent intent = new Intent(DrawablesActivity.this, TintActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(DrawablesActivity.this).toBundle());
            }
        });
        final TextView textView1 = (TextView) findViewById(R.id.tv1);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWindow().setExitTransition(new Explode());
                Intent intent = new Intent(DrawablesActivity.this, PaletteActivity1.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(DrawablesActivity.this).toBundle());
            }
        });
        final TextView textView0 = (TextView) findViewById(R.id.tv0);
        textView0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWindow().setExitTransition(new Explode());
                Intent intent = new Intent(DrawablesActivity.this, PaletteActivity2.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(DrawablesActivity.this).toBundle());
            }
        });
        final TextView textView00 = (TextView) findViewById(R.id.tv00);
        textView00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWindow().setExitTransition(new Explode());
                Intent intent = new Intent(DrawablesActivity.this, VectorDrawableActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(DrawablesActivity.this).toBundle());
            }
        });
    }
}
