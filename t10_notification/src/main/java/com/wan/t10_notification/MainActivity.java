package com.wan.t10_notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private NotificationManager mNotificationManager;
    int numMessages = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Button buttonNotificationSimple = (Button) findViewById(R.id.btn_notification_simple);
        buttonNotificationSimple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this)
                        .setContentTitle("My notification")
                        .setContentText("Hello world")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentIntent(pendingIntent);
                Notification notification = builder.build();
                mNotificationManager.notify(1, notification);
            }
        });

        Button buttonPreserveRegular = (Button) findViewById(R.id.btn_notification_preservce_regular);
        buttonPreserveRegular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegularActivity.class);
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(MainActivity.this);
                // 添加返回栈
                stackBuilder.addParentStack(RegularActivity.class);
                // 把intent添加到栈顶
                stackBuilder.addNextIntent(intent);
                // 获取包含整个返回栈的PendingIntent对象
                PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(2, PendingIntent.FLAG_UPDATE_CURRENT);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("RegularActivity")
                        .setContentText("Preserving Navigation when Starting an Activity")
                        .setContentIntent(resultPendingIntent);
                Notification notification = builder.build();
                mNotificationManager.notify(2, notification);
            }
        });

        Button buttonPreserveSpecial = (Button) findViewById(R.id.btn_notification_preservce_specail);
        buttonPreserveSpecial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SpecialActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent
                        = PendingIntent.getActivity(MainActivity.this, 3, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Specail Activity")
                        .setContentText("Preserving Navigation when Starting an Activity")
                        .setContentIntent(pendingIntent);
                Notification notification = builder.build();
                mNotificationManager.notify(3, notification);

            }
        });

        Button buttonUpdate = (Button) findViewById(R.id.btn_notification_update);
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this)
                        .setContentTitle("New Message")
                        .setContentText("You've received new messages")
                        .setSmallIcon(R.mipmap.ic_launcher);
                builder.setNumber(numMessages++);
                mNotificationManager.notify(4, builder.build());

            }
        });
        Button buttonBigView = (Button) findViewById(R.id.btn_big_view_style);
        buttonBigView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PingActivity.start(MainActivity.this);
            }
        });
        Button buttonProgress = (Button) findViewById(R.id.btn_progress_notification);
        buttonProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Picture Download")
                        .setContentText("Download in progress");

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i <= 100; i += 5) {
                            builder.setContentText("Download percent " + i + "%").setProgress(100, i, false);
                            mNotificationManager.notify(6, builder.build());
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        builder.setContentText("Download complete")
                                .setProgress(0, 0, false);
                        mNotificationManager.notify(6, builder.build());
                    }
                }).start();
            }
        });
        Button buttonProgress2 = (Button) findViewById(R.id.btn_progress_notification_1);
        buttonProgress2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Picture Download")
                        .setContentText("Download in progress");

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i <= 100; i += 5) {
                            builder.setProgress(0, 0, true);
                            mNotificationManager.notify(7, builder.build());
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        builder.setContentText("Download complete")
                                .setProgress(0, 0, false);
                        mNotificationManager.notify(7, builder.build());
                    }
                }).start();
            }
        });

    }
}
