package com.example.t6_volley;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by wzc on 2018/1/25.
 */

public class SetupARequestQueueActivity extends BaseActivity {

    private Button mBtnSendRequest;
    private TextView mTvContent;
    private RequestQueue mRequestQueue;

    public static void start(Context context) {
        Intent starter = new Intent(context, SetupARequestQueueActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_requestqueue);

        mBtnSendRequest = (Button) findViewById(R.id.button_send_request);
        mTvContent = (TextView) findViewById(R.id.tv_content);

        mBtnSendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Instantiate the cache
                Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024);

                // Set up the network to use HttpURLConnection as the HTTP client.
                Network network = new BasicNetwork(new HurlStack());

                // Instantiate the RequestQueue with the cache and network.
                mRequestQueue = new RequestQueue(cache, network);

                // Start the queue
                mRequestQueue.start();

                // Formulate the request and handle the response
                String url = "http://www.google.com";
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                mTvContent.setText(response.substring(0,500));
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                mTvContent.setText("Error");
                            }
                        });

                // Add the request to the RequestQueue
                mRequestQueue.add(stringRequest);
            }
        });
    }
}
