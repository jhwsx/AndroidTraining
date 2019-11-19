package com.example.t8_acitivity_lifecycle;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @author wzc
 * @date 2018/1/29
 */
public class RetainedFragment extends Fragment {

    private String mData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 保留此片段,在运行时配置变更期间将有状态的对象保留在片段中
        setRetainInstance(true);
    }

    public String getData() {
        return mData;
    }

    public void setData(String data) {
        mData = data;
    }
}
