package com.example.t4_test_apps;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by wzc on 2017/12/26.
 */
@RunWith(Parameterized.class)
public class JUnitParameterizedTestUsingConstructor {
    private int m1;
    private int m2;

    public JUnitParameterizedTestUsingConstructor(int m1, int m2) {
        this.m1 = m1;
        this.m2 = m2;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{{1, 2}, {5, 3}, {121, 4}};
        return Arrays.asList(data);
    }

    @Test
    public void testMultiplyException() {
        MyClass myClass = new MyClass();
        Assert.assertEquals("Result", m1 * m2, myClass.multiply(m1, m2));
    }
}
