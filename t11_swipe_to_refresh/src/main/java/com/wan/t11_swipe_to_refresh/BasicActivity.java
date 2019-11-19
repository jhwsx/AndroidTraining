package com.wan.t11_swipe_to_refresh;

import android.content.Context;
import android.content.Intent;
import androidx.fragment.app.Fragment;

/**
 * @author wzc
 * @date 2018/2/10
 */
public class BasicActivity extends SingleFragmentActivity {
    public static void start(Context context) {
        Intent starter = new Intent(context, BasicActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected Fragment createFragment() {
        return BasicFragment.newInstance();
    }
}
