package com.jetpack.demos.databinding;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jetpack.demos.App;

/**
 * @author wangzhichao
 * @since 2019/12/28
 */
public class Presenter {
    public void onSaveClick(User user) {
        Toast.makeText(App.context, "onSaveClick:" + user, Toast.LENGTH_SHORT).show();
    }
    public void onSaveClick(View view, User user) {
        Toast.makeText(App.context, "onSaveClick: view="+view +", user=" + user, Toast.LENGTH_SHORT).show();
    }
    // 返回值必须与方法一致
    public boolean onLongClick(View view, User user) {
        Toast.makeText(App.context, "onLongClick: view="+view +", user=" + user, Toast.LENGTH_SHORT).show();
        return true;
    }
}
