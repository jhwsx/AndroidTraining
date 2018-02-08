package com.wan.t9_mvp_demo.mvp.model;

import android.util.Log;

import com.wan.t9_mvp_demo.client.LoginClient;
import com.wan.t9_mvp_demo.response.Data;
import com.wan.t9_mvp_demo.response.LoginResponse;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 管理登录的类
 * 单例模式
 *
 * @author wzc
 * @date 2018/2/3
 */
public class LoginManager {
    private static LoginManager sInstance;

    private LoginManager() {
        //no instance
    }

    public static LoginManager getInstance() {
        if (sInstance == null) {
            sInstance = new LoginManager();
        }
        return sInstance;
    }

    public interface OnLoginListener {
        void onLoginSuccess(Data data);

        void onLoginFail(String errorMessage);
    }

    public void login(String name, String pwd, final OnLoginListener listener) {
        String BASE_API_URL = "http://wanandroid.com/";
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.client(httpClient.build()).build();
        LoginClient client = retrofit.create(LoginClient.class);
        Call<LoginResponse> call = client.login(name, pwd);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.d("LoginActivity", "xx");
                LoginResponse body = response.body();
                if (body.getErrorcode() == 0) {
                    listener.onLoginSuccess(body.getData());
                } else {
                    listener.onLoginFail(body.getErrormsg());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d("LoginActivity", "xx");
            }
        });
    }


}
