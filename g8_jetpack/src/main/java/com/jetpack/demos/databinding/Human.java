package com.jetpack.demos.databinding;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.jetpack.demos.BR;

/**
 * @author wangzhichao
 * @since 2019/12/28
 */
public class Human extends BaseObservable {
    private String firstName;
    private String lastName;
    @Bindable
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        notifyPropertyChanged(BR.firstName);
    }
    @Bindable
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        notifyPropertyChanged(BR.lastName);
    }
}
