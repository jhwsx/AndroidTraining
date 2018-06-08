package com.wzc.t23_background_jobs;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
private static final String TAG = MainActivity.class.getSimpleName();
    private ResponseReceiver mResponseReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mResponseReceiver = new ResponseReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ResponseReceiver.ACTION_RESPONSE_RECEIVER);
        LocalBroadcastManager.getInstance(this).registerReceiver(mResponseReceiver,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mResponseReceiver != null) {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(mResponseReceiver);
            mResponseReceiver = null;
        }

    }

    public void startIntentService(View view) {
        Intent service = new Intent(MainActivity.this, BackgroundIntentService.class);
        service.putExtra(BackgroundIntentService.ARGS_URL, "http://www.wanandroid.com/banner/json");
        startService(service);
    }

    public void loadDataCusorLoader(View view) {
        startActivity(new Intent(MainActivity.this, CursorLoaderActivity.class));
    }

    public void manageDeviceAwake(View view) {
        startActivity(new Intent(MainActivity.this, ManageDeviceAwakeStateActivity.class));
    }

    public  class ResponseReceiver extends BroadcastReceiver {
        public static final String STATUS = "STATUS";
        public static final String ACTION_RESPONSE_RECEIVER = "ACTION_RESPONSE_RECEIVER";
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "onReceive: currthread = " + Thread.currentThread().getName());
            if (ACTION_RESPONSE_RECEIVER.equals(intent.getAction())) {
                String status = intent.getStringExtra(STATUS);
                Log.d(TAG, "onReceive: "+status);
            }
        }
    }
}
