package com.example.t6_volley;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by wzc on 2018/1/25.
 */

public class SendASimpleRequestActivity extends BaseActivity {
    private Button mBtnSendASimpleRequest;
    private TextView mTextView;
    public static final String TAG = "MyTag";
    private RequestQueue mRequestQueue;
    private ImageView mImageView;

    public static void start(Context context) {
        Intent starter = new Intent(context, SendASimpleRequestActivity.class);
        context.startActivity(starter);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_request);
        mBtnSendASimpleRequest = (Button) findViewById(R.id.button_sending_a_simple_request);
        mTextView = (TextView) findViewById(R.id.tv_content);
        mImageView = (ImageView) findViewById(R.id.imageView);
        mBtnSendASimpleRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Instantiate the RequestQueue.
                mRequestQueue = Volley.newRequestQueue(mContext);
                String url = "http://www.google.com";

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            // Volley always delivers parsed responses on the main thread
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                                mTextView.setText("Response is: " + response.substring(0, 500));
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                mTextView.setText("That didn't work!");

                            }
                        });
                // Set tag on the request
                stringRequest.setTag(TAG);
                // Add the request to the RequestQueue.
//                mRequestQueue.add(stringRequest);

                // 使用单例来请求
                MySingleton.getInstance(mContext.getApplicationContext()).addToRequestQueue(stringRequest);
                // 加载公司logo
                MySingleton.getInstance(mContext.getApplicationContext()).getImageLoader()
                        .get("http://www.adups.com/template/fota_en/img/logo.png",
                        new ImageLoader.ImageListener() {
                            @Override
                            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                                Bitmap bitmap = response.getBitmap();
                                mImageView.setImageBitmap(bitmap);
                            }

                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(TAG);
        }
    }
}
