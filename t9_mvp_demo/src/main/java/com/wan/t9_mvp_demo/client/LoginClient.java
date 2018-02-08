package com.wan.t9_mvp_demo.client;

import com.wan.t9_mvp_demo.response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author wzc
 * @date 2018/2/3
 */
public interface LoginClient {
    @POST("/user/login")
    @FormUrlEncoded
    Call<LoginResponse> login(
            @Field("username") String username,
            @Field("password") String password
            );
}
