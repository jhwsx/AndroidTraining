package com.wzc.t23_background_jobs;

import android.app.IntentService;
import android.content.Intent;
import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author wzc
 * @date 2018/5/14
 */
public class BackgroundIntentService extends IntentService {
    private static final String TAG = BackgroundIntentService.class.getSimpleName();

    public BackgroundIntentService() {
        super(TAG);
    }

    public static final String ARGS_URL = "args_url";
    public static final String STATUS_START = "STATUS_START";
    public static final String STATUS_ERROR = "STATUS_ERROR";
    public static final String STATUS_SUCESS = "STATUS_SUCESS";

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        feedback(STATUS_START);
        String url = intent.getStringExtra(ARGS_URL);
        Log.d(TAG, "onHandleIntent: thread = " + Thread.currentThread().getName());
        try {
            OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
            Request request = new Request.Builder()
                    .get()
                    .url(url)
                    .build();

            Response response = okHttpClient.newCall(request).execute();
            if (response.code() != 200) {
                return;
            }
            String string = response.body().string();
            Log.d(TAG, "onHandleIntent: response = " + string);
            feedback(STATUS_SUCESS + ": " + string);
        } catch (IOException e) {
            e.printStackTrace();
            feedback(STATUS_ERROR);
        }

    }

    private void feedback(String status) {
        Intent receiverIntent = new Intent(MainActivity.ResponseReceiver.ACTION_RESPONSE_RECEIVER);
        receiverIntent.putExtra(MainActivity.ResponseReceiver.STATUS, status);
        LocalBroadcastManager.getInstance(this).sendBroadcast(receiverIntent);
    }
}
