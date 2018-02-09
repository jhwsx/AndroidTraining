package com.wan.t10_notification;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;

/**
 * @author wzc
 * @date 2018/2/9
 */
public class PingService extends IntentService {
    private static final String TAG = PingService.class.getSimpleName();
    public static final String EXTRA_SECONDS = "extra_seconds";
    public static final String EXTRA_REMINDER = "extra_reminder";
    public static final String ACTION_PING = "ACTION_PING";
    public static final String ACTION_DISMISS = "ACTION_DISMISS";
    public static final String ACTION_SNOOZE = "ACTION_SNOOZE";
    private String mReminder;
    private int mSeconds;
    private int NOTIFICATION_ID = 0;
    public PingService() {
        super(TAG);
    }
    private Handler mHandler = new Handler(Looper.getMainLooper());
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent == null) {
            return;
        }
        String action = intent.getAction();
        mSeconds = intent.getIntExtra(EXTRA_SECONDS, 10);
        mReminder = intent.getStringExtra(EXTRA_REMINDER);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


        if (TextUtils.equals(action,ACTION_PING)) {
            issueNotification(mReminder);
        } else if (TextUtils.equals(action,ACTION_DISMISS)) {
            notificationManager.cancel(NOTIFICATION_ID);
        }else if (TextUtils.equals(action,ACTION_SNOOZE)) {
            notificationManager.cancel(NOTIFICATION_ID);
            issueNotification("Snooze zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz...");
        }
    }

    private void issueNotification(String reminder) {

        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setContentTitle("Ping Notification")
                .setContentText("Ping")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(getResultPendingIntent())
//                .setStyle(new NotificationCompat.BigTextStyle().bigText(reminder))
                .setStyle(new NotificationCompat.BigPictureStyle().setBigContentTitle(reminder).bigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.ic_big_pic)))
                .addAction(R.drawable.ic_stat_dismiss,"DISMISS",getDismissPendingIntent())
                .addAction(R.drawable.ic_stat_snooze,"SNOOZE",getSnoozePendingIntent());
        Log.d(TAG, "setting the timer");
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "timer finished");
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(NOTIFICATION_ID, builder.build());
            }
        }, mSeconds*1000);
    }

    private PendingIntent getResultPendingIntent() {
        Intent resultIntent = new Intent(this, PingResultActivity.class);
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private PendingIntent getDismissPendingIntent() {
        Intent dismissIntent = new Intent(this, PingService.class);
        dismissIntent.setAction(ACTION_DISMISS);
        return PendingIntent.getService(this, 1, dismissIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    }
    private PendingIntent getSnoozePendingIntent() {
        Intent snoozeIntent = new Intent(this, PingService.class);
        snoozeIntent.setAction(ACTION_SNOOZE);
        return PendingIntent.getService(this, 2, snoozeIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    }
}
