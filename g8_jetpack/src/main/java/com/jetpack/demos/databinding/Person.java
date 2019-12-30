package com.jetpack.demos.databinding;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

/**
 * @author wangzhichao
 * @since 2019/12/28
 */
public class Person {
    public final ObservableField<String> firstName = new ObservableField<>();
    public final ObservableField<String> lastName = new ObservableField<>();
    public final ObservableInt age = new ObservableInt();
}
