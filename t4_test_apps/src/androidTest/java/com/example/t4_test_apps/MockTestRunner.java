package com.example.t4_test_apps;

import android.app.Application;
import android.content.Context;
import androidx.test.runner.AndroidJUnitRunner;

/**
 * Created by wzc on 2017/12/27.
 */

public class MockTestRunner extends AndroidJUnitRunner {

    @Override
    public Application newApplication(ClassLoader cl, String className, Context context)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return super.newApplication(cl, className, context);
    }
}
