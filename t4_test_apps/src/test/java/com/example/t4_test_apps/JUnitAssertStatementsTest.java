package com.example.t4_test_apps;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by wzc on 2017/12/26.
 */

public class JUnitAssertStatementsTest {

    @Test(timeout = 0)
    public void multiplicationOfZeroIntegersShouldReturnZero() {
        MyClass tester = new MyClass(); // MyClass is tested

        // assert statements
        Assert.assertEquals("10 x 0 must be 0", 0, tester.multiply(10, 0));
        Assert.assertEquals("0 x 10 must be 0", 0, tester.multiply(0, 10));
        Assert.assertEquals("0 x 0 must be 0", 0, tester.multiply(0, 0));
    }
    @Test
    public void isStringAShouldReturnFalse() {
        MyClass myClass = new MyClass();

        Assert.assertFalse("null is not equal to A", myClass.isStringA(null));
    }

    @Test
    public void isStringAShouldReturnTrue() {
        MyClass myClass = new MyClass();

        Assert.assertTrue("A is equal to A", myClass.isStringA("A"));
    }
    @BeforeClass
    @Ignore("I don't need it")
    public static void testmmm() {

    }
}
