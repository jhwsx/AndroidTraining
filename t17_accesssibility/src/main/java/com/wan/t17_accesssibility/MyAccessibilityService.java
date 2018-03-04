package com.wan.t17_accesssibility;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

/**
 * @author wzc
 * @date 2018/3/4
 */
public class MyAccessibilityService extends AccessibilityService {
    private static final String TAG = MyAccessibilityService.class.getSimpleName();
    //  有关AccessibilityEvent事件的回调函数.系统通过sendAccessibiliyEvent()不断的发送AccessibilityEvent到此处
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.d(TAG, "onAccessibilityEvent: " + event.toString());
    }

    @Override
    public void onInterrupt() {

    }
}
