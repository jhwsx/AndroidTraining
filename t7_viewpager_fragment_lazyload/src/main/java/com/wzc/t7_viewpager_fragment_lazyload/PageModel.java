package com.wzc.t7_viewpager_fragment_lazyload;

import androidx.annotation.StringRes;

public class PageModel {
    /**
     * tab文字
     */
    @StringRes
    private int mTitleRes;
    /**
     * Fragment的全类名
     */
    private String mFragmentName;

    public PageModel(@StringRes int titleRes, String fragmentName) {
        this.mTitleRes = titleRes;
        this.mFragmentName = fragmentName;
    }

    public int getTitleRes() {
        return mTitleRes;
    }

    public String getFragmentName() {
        return mFragmentName;
    }
}