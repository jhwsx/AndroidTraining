package com.example.t4_test_apps;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by wzc on 2017/12/29.
 */

public class MyService extends Service {

    private MyBinder mBinder = new MyBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent,  int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public boolean doSomethingToReturnTrue() {
        return true;
    }

    public class MyBinder extends Binder {
        public void startDownload() {
            Log.d("MyBinder", "startDownload() executed");
        }

        public MyService getService() {
            return MyService.this;
        }
    }
}
