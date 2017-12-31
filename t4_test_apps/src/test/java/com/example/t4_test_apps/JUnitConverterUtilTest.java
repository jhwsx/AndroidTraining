package com.example.t4_test_apps;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by wzc on 2017/12/27.
 */

public class JUnitConverterUtilTest {

    @Test
    public void testConvertFahrenheitToCelsius() {
        float actual = ConverterUtil.convertFahrenheitToCelsius(212);
        float expected = 100;

        assertEquals("Convert from fahrenheit to celsius success", expected, actual, 0.001);
    }
    @Test
    public void testConvertCelsiusToFahrenheit() {
        float actual = ConverterUtil.convertCelsiusToFahrenheit(100);
        float expected = 212;

        assertEquals("Convert from celsius to fahrenheit success", expected, actual, 0.001);

    }
}
