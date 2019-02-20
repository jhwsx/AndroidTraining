package com.jetpack.demos.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * @author wzc
 * @date 2019/2/16
 */
public class SharedViewModel extends ViewModel {
    private final MutableLiveData<Integer> selected = new MutableLiveData<>();

    public void setSelected(Integer value) {
        selected.setValue(value);
    }

    public MutableLiveData<Integer> getSelected() {
        return selected;
    }
}
