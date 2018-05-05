package frees.boost.mobilebooster.g1_service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * @author wzc
 * @date 2018/4/4
 */
public class MyService extends Service {
    private static final String TAG = MyService.class.getSimpleName();
    private Binder mBinder = new LocalBinder();

    public class LocalBinder extends Binder {
        public  MyService getService() {
            return MyService.this;
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: intent = " + intent + ", thread name = " + Thread.currentThread().getName());
        return mBinder;
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d(TAG, "onRebind: intent = " + intent + ", thread name = " + Thread.currentThread().getName());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: thread name = " + Thread.currentThread().getName());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: intent = " + intent + ", flags = " + flags + ", startId = " + startId
        + "thread name = " + Thread.currentThread().getName());
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: intent = " + intent + ", thread name = " + Thread.currentThread().getName());
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: thread name = " + Thread.currentThread().getName());
    }
}
