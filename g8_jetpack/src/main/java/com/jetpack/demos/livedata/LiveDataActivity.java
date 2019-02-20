package com.jetpack.demos.livedata;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jetpack.demos.R;

/**
 * @author wzc
 * @date 2019/2/14
 */
public class LiveDataActivity extends AppCompatActivity {

    private static final String TAG = LiveDataActivity.class.getSimpleName();
    private NameViewModel mNameViewModel;
    private TextView mTextView;
    private Button mBtnUpdateName;

    public static void start(Context context) {
        Intent starter = new Intent(context, LiveDataActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livedata);
        mTextView = (TextView) findViewById(R.id.textView);
        mBtnUpdateName = (Button) findViewById(R.id.button);
        mNameViewModel = ViewModelProviders.of(this).get(NameViewModel.class);
        final Observer<String> nameObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable String newName) {
                Log.d(TAG, "onChanged: newName=" + newName);
                mTextView.setText(newName);
            }
        };
        mNameViewModel.getCurrentName().observe(this, nameObserver);
        mBtnUpdateName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AppExecutors().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 10000; i++) {
                            SystemClock.sleep(500);
                            final String name = "wzc" + i;
                            new AppExecutors().mainThread().execute(new Runnable() {
                                @Override
                                public void run() {
                                    Log.d(TAG, "run: name=" + name);
                                    mNameViewModel.getCurrentName().setValue(name);
                                }
                            });
                        }
                    }
                });

            }
        });
    }
}
