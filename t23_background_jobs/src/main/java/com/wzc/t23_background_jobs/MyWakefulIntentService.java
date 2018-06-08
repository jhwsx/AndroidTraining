package com.wzc.t23_background_jobs;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author wzc
 * @date 2018/5/14
 */
public class MyWakefulIntentService extends IntentService {
    private static final String TAG = MyWakefulIntentService.class.getSimpleName();

    public MyWakefulIntentService() {
        super(TAG);
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG, "onHandleIntent: thread = " + Thread.currentThread().getName());
        try {
            OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
            Request request = new Request.Builder()
                    .get()
                    .url("http://www.wanandroid.com/banner/json")
                    .build();

            Response response = okHttpClient.newCall(request).execute();
            if (response.code() != 200) {
                return;
            }
            String string = response.body().string();
            Log.d(TAG, "onHandleIntent: response = " + string);
        } catch (IOException e) {
            e.printStackTrace();
        }

        MyWakefulReceiver.completeWakefulIntent(intent);

    }


}
