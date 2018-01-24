package com.example.t6_volley;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseActivity {

    private Button mBtnSendSimpleRequest;
    private Button mBtnSetupRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_main);
        mBtnSendSimpleRequest = (Button) findViewById(R.id.button_sending_a_simple_request);
        mBtnSendSimpleRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendASimpleRequestActivity.start(mContext);
            }
        });
        mBtnSetupRequestQueue = (Button) findViewById(R.id.button_setup_a_requestqueue);
        mBtnSetupRequestQueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetupARequestQueueActivity.start(mContext);
            }
        });
    }

}
