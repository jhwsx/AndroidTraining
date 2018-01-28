package com.example.t6_volley;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

public class RequestJsonActivity extends BaseActivity {

    private Button mBtnRequestJson;
    private TextView mTextView;

    public static void start(Context context) {
        Intent starter = new Intent(context, RequestJsonActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_json);

        mBtnRequestJson = (Button) findViewById(R.id.button_request_json);
        mTextView = (TextView) findViewById(R.id.tv_content);
        mBtnRequestJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://www.wanandroid.com/article/list/1/json";
                JsonObjectRequest jsonObjectRequest
                        = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                mTextView.setText(response.toString());
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });
                MySingleton.getInstance(mContext).getRequestQueue().add(jsonObjectRequest);
            }
        });
    }
}
