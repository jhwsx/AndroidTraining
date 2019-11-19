package com.jetpack.demos.viewmodel;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.jetpack.demos.R;
import com.jetpack.demos.databinding.ActivityViewmodelBinding;

import java.util.List;

/**
 * @author wzc
 * @date 2019/2/16
 */
public class ViewModelActivity extends AppCompatActivity {

    public static final String TAG = ViewModelActivity.class.getSimpleName();
    private ActivityViewmodelBinding mBinding;

    public static void start(Context context) {
        Intent starter = new Intent(context, ViewModelActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_viewmodel);
        Log.d(TAG, "onCreate: ");
        MyViewModel myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        myViewModel.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                Log.d(TAG, "onChanged: " + users.hashCode() + users);
            }
        });

        mBinding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsActivity.start(ViewModelActivity.this);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}
