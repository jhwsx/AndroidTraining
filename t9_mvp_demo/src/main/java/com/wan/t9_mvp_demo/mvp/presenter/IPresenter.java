package com.wan.t9_mvp_demo.mvp.presenter;

/**
 * IPresenter提供了一些基础方法,其实这些方法是对应Activity或者Fragment的生命周期方法
 * @author wzc
 * @date 2018/2/3
 */
public interface IPresenter<V extends IView> {

    void onStop();

    void onResume();

    void onDestroy();

    void onPause();

    void onStart();

    void init(V view);
}
