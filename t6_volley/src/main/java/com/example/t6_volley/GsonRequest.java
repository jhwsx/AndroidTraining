package com.example.t6_volley;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;

/**
 * Created by wzc on 2018/1/26.
 */

public class GsonRequest<T> extends Request<T> {
    public GsonRequest(int method, String url, Response.ErrorListener listener) {
        super(method, url, listener);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        return null;
    }

    @Override
    protected void deliverResponse(T response) {

    }
}
