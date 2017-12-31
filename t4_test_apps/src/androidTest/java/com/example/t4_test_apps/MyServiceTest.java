package com.example.t4_test_apps;

import android.content.Intent;
import android.os.IBinder;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ServiceTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeoutException;

import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

/**
 * Created by wzc on 2017/12/29.
 */

@RunWith(AndroidJUnit4.class)
@MediumTest
public class MyServiceTest {

    @Rule
    public final ServiceTestRule mServiceTestRule = new ServiceTestRule();

    @Test
    public void testWithStartedService() {
        try {
            mServiceTestRule.startService(new Intent(InstrumentationRegistry.getTargetContext(),MyService.class));
        } catch (TimeoutException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testWithBoundService() {
        try {
            IBinder binder = mServiceTestRule.bindService(new Intent(InstrumentationRegistry.getTargetContext(), MyService.class));
            MyService service = ((MyService.MyBinder) binder).getService();
            assertTrue("True wasn't returned", service.doSomethingToReturnTrue());
        } catch (TimeoutException e) {
            e.printStackTrace();
            fail();
        }
    }
}
