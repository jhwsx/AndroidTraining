package com.wzc.g5_appwidgets;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Note:
 * App Widget host 会调用这个Activity, 这个配置页面应该总是返回一个结果
 * @author wzc
 * @date 2018/8/13
 */
public class AppWidgetConfigureActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mAppWidgetPrefix;
    int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
    private static final String PREFIX_KEY = "key_";
    private Context mContext = AppWidgetConfigureActivity.this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the result to CANCELED.  This will cause the widget host to cancel
        // out of the widget placement if they press the back button.
        setResult(RESULT_CANCELED);

        setContentView(R.layout.activity_configure);

        mAppWidgetPrefix = (EditText) findViewById(R.id.appwidget_prefix);

        findViewById(R.id.save_button).setOnClickListener(this);

        // 从 Intent 中取出 Widget id
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null) {
            mAppWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
        }
        // 无效的 AppWidgetId, 关闭页面
        if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
        }

        mAppWidgetPrefix.setText(loadTitleFromSp(mContext, mAppWidgetId));

    }

    static String loadTitleFromSp(Context context, int appWidgetId) {
        SharedPreferences mSp = PreferenceManager.getDefaultSharedPreferences(context);
        String key = PREFIX_KEY + appWidgetId;
        String prefix = mSp.getString(key, null);
        if (prefix != null) {
            return prefix;
        } else {
            return "Default Text";
        }

    }

    @Override
    public void onClick(View v) {

        String titlePrefix = mAppWidgetPrefix.getText().toString();
        saveTitle2Sp(mContext, titlePrefix, mAppWidgetId);

        // Push widget update to surface with newly set prefix 把文字更新到 AppWidget 中
        // 因为有配置页面时, onUpdate() 不会被调用, 所以更新需要自己处理
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(mContext);
        MyAppWidgetProvider.updateAppWidget(mContext, appWidgetManager, mAppWidgetId);

        // Make sure we pass back the original appWidgetId
        Intent result = new Intent();
        // 返回的结果要包含从 intent 里取出来的 mAppWidgetId
        result.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
        setResult(RESULT_OK, result);
        finish();
    }

    private void saveTitle2Sp(Context context, String titlePrefix, int appWidgetId) {
        SharedPreferences mSp = PreferenceManager.getDefaultSharedPreferences(context);
        String key = PREFIX_KEY + appWidgetId;
        mSp.edit().putString(key, titlePrefix).apply();
    }
}
