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
public class JUnitParameterizedTest {

    @Parameterized.Parameter(0)
    public int m1;
    @Parameterized.Parameter(1)
    public int m2;
    @Parameterized.Parameter(2)
    public int result;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{{1, 2, 2}, {5, 3, 15}, {121, 4, 484}};
        return Arrays.asList(data);
    }
    @Test
    public void testMultiplyException() {
        MyClass myClass = new MyClass();
        Assert.assertEquals("Result", result, myClass.multiply(m1, m2));
    }
}
