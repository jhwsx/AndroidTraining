package com.jetpack.demos.databinding;

import android.content.Context;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jetpack.demos.R;

/**
 * @author wzc
 * @date 2019/2/21
 */
public class DatabindingIncludeActivity extends AppCompatActivity {
    public static void start(Context context) {
        Intent starter = new Intent(context, DatabindingIncludeActivity.class);
        context.startActivity(starter);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDatabindingIncludeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_databinding_include);
        binding.includeTextview.tv.setText("I am a good man.");

    }
}
