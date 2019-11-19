package com.wzc.g6_app_resources;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wzc
 * @date 2018/9/4
 */
public class RetainedFragmentActivity extends AppCompatActivity {
    private RetainedFragment mFragment;

    public static void start(Context context) {
        Intent starter = new Intent(context, RetainedFragmentActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retained_fragment);
        FragmentManager fm = getSupportFragmentManager();
        mFragment = (RetainedFragment) fm.findFragmentByTag(RetainedFragment.TAG);
        if (mFragment == null) {
            mFragment = RetainedFragment.newInstance();
            fm.beginTransaction()
                    .add(mFragment, RetainedFragment.TAG)
                    .commit();
        }
        List<LargeData> data = mFragment.loadData();
        Toast.makeText(this, "restore data size: " + data.size(), Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onDestroy() {
        List<LargeData> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            final LargeData wzc = new LargeData("wzc-" + i, i);
            list.add(wzc);
        }
        mFragment.setData(list);
        Toast.makeText(this, "store data size: " + list.size(), Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

}
