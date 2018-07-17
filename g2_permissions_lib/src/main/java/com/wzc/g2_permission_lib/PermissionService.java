package com.wzc.g2_permission_lib;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * @author wzc
 * @date 2018/7/16
 */
public class PermissionService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        for (int i = 10; i >= 0; i--) {
            Toast.makeText(this, "CountDown from PermissionService =====> " + i, Toast.LENGTH_SHORT).show();
        }
        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }
}
