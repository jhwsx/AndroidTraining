package com.wzc.g2_permission_lib;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * @author wzc
 * @date 2018/7/17
 */
public class PermissionBindService extends Service {
    private static final String TAG = PermissionBindService.class.getSimpleName();
    private IBinder mBinder = new ICompute.Stub() {
        @Override
        public int add(int a, int b) throws RemoteException {
            return a + b;
        }

        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            int checkCallingPermission = checkCallingPermission("com.wzc.g2_permission_lib.PermissionBindService_ACCESS");
            if (checkCallingPermission == PackageManager.PERMISSION_DENIED) {
                Log.d(TAG, "onTransact: Well, permission denied, check first!!!");
                return false;
            }
            return super.onTransact(code, data, reply, flags);
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
