package com.jetpack.demos.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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
