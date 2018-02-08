package com.wan.t9_mvp_demo.mvp.presenter;

import com.wan.t9_mvp_demo.response.Data;

/**
 * 登录的条约接口,分别定义了登录view的一些方法,和登录presenter的一些方法
 * @author wzc
 * @date 2018/2/3
 */
public interface LoginConstract {
    // 需要view层来实现的登录view接口,IView是所有view的基类
    interface ILoginView extends IView {
        void onShowSuccessLoginView(Data data);

        void onShowFailLoginView(String message);

        void onShowLoginingView();

        void onDismissLoginingView();
    }
    // 定义了登录presenter的一些方法,IPresenter是所有presenter的基类
    interface ILoginPresenter extends IPresenter<ILoginView> {
        void login(String name, String password);
    }

}
