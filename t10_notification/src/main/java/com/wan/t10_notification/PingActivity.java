package com.wan.t10_notification;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * @author wzc
 * @date 2018/2/9
 */
public class PingActivity extends AppCompatActivity {

    private EditText mEtSeconds;
    private EditText mEtReminder;
    private Button mBtnPing;

    public static void start(Context context) {
        Intent starter = new Intent(context, PingActivity.class);
        starter.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ping);

        mEtSeconds = (EditText) findViewById(R.id.et_seconds);
        mEtReminder = (EditText) findViewById(R.id.et_reminder);
        mBtnPing = (Button) findViewById(R.id.btn_ping);
        mBtnPing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = mEtSeconds.getText().toString();
                int seconds;
                if (TextUtils.isEmpty(input)) {
                    seconds = R.string.app_name;
                } else {
                    seconds = Integer.parseInt(input);
                }

                String reminder = mEtReminder.getText().toString();
                Intent intent = new Intent(PingActivity.this, PingService.class);
                intent.setAction(PingService.ACTION_PING);
                intent.putExtra(PingService.EXTRA_SECONDS, seconds);
                intent.putExtra(PingService.EXTRA_REMINDER, reminder);
                startService(intent);
            }
        });

    }
}
