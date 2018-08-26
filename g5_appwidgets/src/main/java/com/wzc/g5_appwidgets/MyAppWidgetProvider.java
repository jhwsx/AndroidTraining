package com.wzc.g5_appwidgets;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.RemoteViews;

/**
 * @author wzc
 * @date 2018/8/13
 */
public class MyAppWidgetProvider extends AppWidgetProvider {
    private static final String TAG = MyAppWidgetProvider.class.getSimpleName();

    // 这是最重要的回调, 在更新时调用, 当一个 App Widget 被添加到一个 host 里时被调用(除了有配置页面时)
    // 如果 AppWidget 的设置过程要耗费几秒的时间, 考虑在这里开启一个 Service.
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Log.d(TAG, "onUpdate: ");
        final int N = appWidgetIds.length;
        // 遍历属于这个 Provider 的所有的 app Widget
        for (int i = 0; i < N; i++) {
            int appWidgetId = appWidgetIds[i];

            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    // 当 App Widget 第一次被放置, 或者它的尺寸改变时调用
    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);
        Bundle appWidgetOptions = appWidgetManager.getAppWidgetOptions(appWidgetId);
        int minWidth = appWidgetOptions.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH);
        int minHeight = appWidgetOptions.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_HEIGHT);
        int maxWidth = appWidgetOptions.getInt(AppWidgetManager.OPTION_APPWIDGET_MAX_WIDTH);
        int maxHeight = appWidgetOptions.getInt(AppWidgetManager.OPTION_APPWIDGET_MAX_HEIGHT);
        Log.d(TAG, "onAppWidgetOptionsChanged: minWidth="+minWidth+", minHeight="+minHeight
                +", maxWidth="+maxWidth+", maxHeight="+maxHeight);

    }

    // App Widget 第一次创建时调用
    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        Log.d(TAG, "onEnabled: ");
    }

    // 当 App Widget 的 最后一个实例从 App Widget Host里删除时调用
    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        Log.d(TAG, "onDisabled: ");
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        Log.d(TAG, "onDeleted");
    }

    // 当接收广播时调用, 在 onUpdate onEnabled onDisabled 之前调用
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: ");
        super.onReceive(context, intent);
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        String text = context.getString(R.string.appwidget_text_format,
                AppWidgetConfigureActivity.loadTitleFromSp(context, appWidgetId),
                "0x" + Long.toHexString(SystemClock.elapsedRealtime()));

        // 创建启动 MyActivity 的 intent
        Intent intent = new Intent(context, MyActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        // Get the layout for the App Widget and attach an on-click listener
        // to the button
        // 创建 RemoteViews 对象
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.my_appwidget);
        remoteViews.setOnClickPendingIntent(R.id.button, pendingIntent);
        remoteViews.setTextViewText(R.id.text, text);

        // 更新
        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
    }

}
