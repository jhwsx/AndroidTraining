package com.wzc.t23_background_jobs;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.NotificationCompat;
import android.view.View;
import android.view.WindowManager;

/**
 * @author wzc
 * @date 2018/6/8
 */
public class ManageDeviceAwakeStateActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_device_awake);
         findViewById(R.id.btn_keep_screen_on).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
             }
         });
         findViewById(R.id.btn_clear_screen_on).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
             }
         });

         findViewById(R.id.btn_send_wakeful_broadcast).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 // 使用 PendingIntent 发送 WakefulBroadcastReceiver 广播可以
                 // 使用 sendBroadcast 发送 WakefulBroadcastReceiver 广播不可以, 无法接收到
                 Intent intent = new Intent("com.wzc.WAKEFUL_ACTION");
                 sendBroadcast(intent);
                 NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                 Intent receiver = new Intent("com.wzc.WAKEFUL_ACTION");
                 PendingIntent broadcast = PendingIntent.getBroadcast(ManageDeviceAwakeStateActivity.this, 1, receiver, PendingIntent.FLAG_UPDATE_CURRENT);
                 Notification notification = new NotificationCompat.Builder(ManageDeviceAwakeStateActivity.this)
                         .setSmallIcon(R.mipmap.ic_launcher)
                         .setContentTitle("Notification")
                         .setContentIntent(broadcast)
                         .build();
                 manager.notify(1000,notification);
             }
         });

//        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
//        PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "MyWakeLockTag");
//        wakeLock.acquire();
//        wakeLock.release();
    }


}
