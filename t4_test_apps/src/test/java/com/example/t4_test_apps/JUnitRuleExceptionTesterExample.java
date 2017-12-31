package com.example.t4_test_apps;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by wzc on 2017/12/26.
 */

public class JUnitRuleExceptionTesterExample {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void throwsIllegalArgumentExceptionIfIconIsNull() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Negative value not allowed");
        MyClass myClass = new MyClass();
        myClass.multiply(1, 2);
    }
}
