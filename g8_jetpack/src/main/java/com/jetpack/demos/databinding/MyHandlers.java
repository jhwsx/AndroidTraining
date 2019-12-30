package com.jetpack.demos.databinding;

import android.view.View;
import android.widget.Toast;

/**
 * @author wangzhichao
 * @since 2019/12/28
 */
public class MyHandlers {
    // 参数列表必须与 onClick 方法完全一致
    public void onClickFriend(View view) {
        Toast.makeText(view.getContext(), "onClickFriend", Toast.LENGTH_SHORT).show();
    }
}
