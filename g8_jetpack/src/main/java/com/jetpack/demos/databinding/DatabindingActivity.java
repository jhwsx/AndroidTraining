package com.jetpack.demos.databinding;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jetpack.demos.R;

/**
 * @author wzc
 * @date 2019/2/21
 */
public class DatabindingActivity extends AppCompatActivity {
    public static void start(Context context) {
        Intent starter = new Intent(context, DatabindingActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDatabindingBinding databinding = DataBindingUtil.setContentView(this, R.layout.activity_databinding);
        databinding.buttonDatabindingInclude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabindingIncludeActivity.start(DatabindingActivity.this);
            }
        });
    }
}
