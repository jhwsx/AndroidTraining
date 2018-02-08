package com.wan.t9_mvp_demo.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wan.t9_mvp_demo.mvp.presenter.IPresenter;

import java.util.HashSet;
import java.util.Set;

/**
 * @author wzc
 * @date 2018/2/3
 */
public abstract class BaseActivity extends AppCompatActivity {
    private Set<IPresenter> mAllPresenters = new HashSet<IPresenter>(1);

    /**
     * 获取layout的id
     * @return
     */
    protected abstract int getLayoutResId();

    /**
     * 获取子类的IPresenter,一个activity有可能有多个IPresenter
     * @return
     */
    protected abstract IPresenter[] getPresenters();

    /**
     * 初始化presenters
     */
    protected abstract void onInitPresenters();

    /**
     * 从intent中解析数据
     */
    protected void parseArgumentFromIntent(Intent argIntent) {

    }

    private void addPresenters() {
        IPresenter[] presenters = getPresenters();
        if (presenters != null) {
            for (int i = 0; i < presenters.length; i++) {
                mAllPresenters.add(presenters[i]);
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        if (getIntent() != null) {
            parseArgumentFromIntent(getIntent());
        }
        addPresenters();

        onInitPresenters();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 依次调用IPresenter的onResume方法
        for (IPresenter presenter : mAllPresenters) {
            if (presenter != null) {
                presenter.onResume();
            }
        }
    }
}
