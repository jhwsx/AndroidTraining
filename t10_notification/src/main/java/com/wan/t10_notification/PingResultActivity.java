package com.wan.t10_notification;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import static com.wan.t10_notification.PingService.ACTION_DISMISS;
import static com.wan.t10_notification.PingService.ACTION_SNOOZE;

/**
 * @author wzc
 * @date 2018/2/9
 */
public class PingResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ping_result);

        Button buttonDismiss = (Button) findViewById(R.id.btn_dismiss);
        Button buttonSnooze = (Button) findViewById(R.id.btn_snooze);
        buttonDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dismissIntent = new Intent(PingResultActivity.this, PingService.class);
                dismissIntent.setAction(ACTION_DISMISS);
                startService(dismissIntent);
            }
        });
        buttonSnooze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent snoozeIntent = new Intent(PingResultActivity.this, PingService.class);
                snoozeIntent.setAction(ACTION_SNOOZE);
                startService(snoozeIntent);
            }
        });
    }
}
