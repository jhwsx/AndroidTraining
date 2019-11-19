package com.wan.t9_mvp_demo;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wan.t9_mvp_demo.client.LoginClient;
import com.wan.t9_mvp_demo.response.LoginResponse;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 参考文章:
 * https://www.jianshu.com/p/37892b4193a7
 */
public class LoginActivity extends AppCompatActivity {

    private EditText mEtName;
    private EditText mEtPwd;
    private Button mBtnLogin;
    private TextView mTextView;
    private ProgressDialog mProgressDialog;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
        mEtName = (EditText) findViewById(R.id.editText_name);
        mEtPwd = (EditText) findViewById(R.id.editText_pwd);
        mBtnLogin = (Button) findViewById(R.id.button);
        mTextView = (TextView) findViewById(R.id.textView);

        String username = mSharedPreferences.getString("username", "");
        String password = mSharedPreferences.getString("password", "");
        if (!TextUtils.isEmpty(username)) {
            mEtName.setText(username);
        }
        if (!TextUtils.isEmpty(password)) {
            mEtPwd.setText(password);
        }
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("登录中...");
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mEtName.getText().toString();
                String pwd = mEtPwd.getText().toString();
                if (isValidName(name) && isValidPwd(pwd)) {
                    mProgressDialog.show();
                    login(name,pwd);

                }
            }
        });
    }
    int count = 0;
    private void login(String name, String pwd) {
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
                    mTextView.setText("登录成功");
                    mSharedPreferences.edit().putString("username", body.getData().getUsername()).apply();
                    mSharedPreferences.edit().putString("password", body.getData().getPassword()).apply();
                } else {
                    mTextView.setText("登录失败");
                }
                mProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d("LoginActivity", "xx");
            }
        });
    }

    private boolean isValidName(String name) {
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(LoginActivity.this, "名字不可以为空", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private boolean isValidPwd(String pwd) {
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(LoginActivity.this, "密码不可以为空", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
