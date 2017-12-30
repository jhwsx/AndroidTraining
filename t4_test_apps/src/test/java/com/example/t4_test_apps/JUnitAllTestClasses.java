package com.example.t4_test_apps;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by wzc on 2017/12/26.
 * JUnit Reference:
 * http://www.vogella.com/tutorials/JUnit/article.html#junit_namingconventions
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        JUnitAssertStatementsTest.class,
        JUnitAssertStatementsTest1.class })
public class JUnitAllTestClasses {
}
