package com.wan.t9_mvp_demo.mvp;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wan.t9_mvp_demo.R;
import com.wan.t9_mvp_demo.mvp.presenter.IPresenter;
import com.wan.t9_mvp_demo.mvp.presenter.LoginConstract;
import com.wan.t9_mvp_demo.mvp.presenter.LoginPresenter;
import com.wan.t9_mvp_demo.response.Data;

public class LoginActivity extends BaseActivity implements LoginConstract.ILoginView {

    private LoginPresenter mLoginPresenter = new LoginPresenter();
    private SharedPreferences mSharedPreferences;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected IPresenter[] getPresenters() {
        return new IPresenter[]{mLoginPresenter};
    }

    @Override
    protected void onInitPresenters() {
        mLoginPresenter.init(this);
    }

    private EditText mEtName;
    private EditText mEtPwd;
    private Button mBtnLogin;
    private TextView mTextView;
    private ProgressDialog mProgressDialog;
    @Override
    public void initView() {
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

        mProgressDialog = new ProgressDialog(LoginActivity.this);
        mProgressDialog.setMessage("登录中...");
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mEtName.getText().toString();
                String pwd = mEtPwd.getText().toString();
                mLoginPresenter.login(name, pwd);
            }
        });
    }

    @Override
    public void onShowSuccessLoginView(Data data) {
        mTextView.setText("登录成功");
        mSharedPreferences.edit().putString("username", data.getUsername()).apply();
        mSharedPreferences.edit().putString("password", data.getPassword()).apply();
    }

    @Override
    public void onShowFailLoginView(String message) {
        mTextView.setText(message);
    }

    @Override
    public void onShowLoginingView() {
        mProgressDialog.show();
    }

    @Override
    public void onDismissLoginingView() {
        mProgressDialog.dismiss();
    }
}
