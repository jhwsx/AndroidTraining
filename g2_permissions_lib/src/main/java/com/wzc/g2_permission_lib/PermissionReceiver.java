package com.wzc.g2_permission_lib;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * @author wzc
 * @date 2018/7/16
 */
public class PermissionReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if ("com.wzc.g2_permission_lib.PermissionReceiver_ACTION".equals(intent.getAction())) {
            Toast.makeText(context, "PermissionReceiver receive message",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
