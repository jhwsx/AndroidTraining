package com.jetpack.demos.databinding;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.jetpack.demos.R;

/**
 * @author wangzhichao
 * @since 2019/12/28
 */
public class GenerateBindingActivity extends AppCompatActivity {

    private ActivityGenerateBindingBinding binding;

    public static void start(Context context) {
        Intent starter = new Intent(context, GenerateBindingActivity.class);
        context.startActivity(starter);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_generate_binding);
//        binding = ActivityGenerateBindingBinding.inflate(getLayoutInflater());

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, GenerateBindingFragment.newInstance())
                .commit();
    }
}
