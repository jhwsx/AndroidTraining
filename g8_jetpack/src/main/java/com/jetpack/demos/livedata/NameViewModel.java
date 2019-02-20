package com.jetpack.demos.livedata;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * @author wzc
 * @date 2019/2/14
 */
public class NameViewModel extends ViewModel {
    private MutableLiveData<String> currentName;

    public MutableLiveData<String> getCurrentName() {
        if (currentName == null) {
            currentName = new MutableLiveData<>();
        }
        return currentName;
    }
}
