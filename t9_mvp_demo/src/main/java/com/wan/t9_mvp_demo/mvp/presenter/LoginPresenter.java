package com.wan.t9_mvp_demo.mvp.presenter;

import android.text.TextUtils;

import com.wan.t9_mvp_demo.mvp.model.LoginManager;
import com.wan.t9_mvp_demo.response.Data;

/**
 * 登录的presenter
 *
 * @author wzc
 * @date 2018/2/3
 */
public class LoginPresenter implements LoginConstract.ILoginPresenter {

    @Override
    public void onStop() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStart() {

    }

    private LoginConstract.ILoginView mILoginView;
    private LoginManager mLoginManager = LoginManager.getInstance();

    @Override
    public void init(LoginConstract.ILoginView view) {
        mILoginView = view;
        mILoginView.initView();
    }

    @Override
    public void login(final String name, final String password) {
        // 验证name,password的合法性
        if (isValidName(name) && isValidPwd(password)) {
            mILoginView.onShowLoginingView();
            mLoginManager.login(name, password, new LoginManager.OnLoginListener() {
                @Override
                public void onLoginSuccess(Data data) {
                    mILoginView.onShowSuccessLoginView(data);
                    mILoginView.onDismissLoginingView();
                }
                @Override
                public void onLoginFail(String message) {
                    mILoginView.onShowFailLoginView(message);
                    mILoginView.onDismissLoginingView();
                }
            });


        }
    }

    private boolean isValidName(String name) {
        if (TextUtils.isEmpty(name)) {
            return false;
        }

        return true;
    }

    private boolean isValidPwd(String pwd) {
        if (TextUtils.isEmpty(pwd)) {
            return false;
        }

        return true;
    }
}
