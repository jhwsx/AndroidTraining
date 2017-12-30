package com.example.t4_test_apps;

import android.content.SharedPreferences;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

/**
 * Created by wzc on 2017/12/26.
 */

@RunWith(MockitoJUnitRunner.class)
public class MockitoTest {
    private static final String TEST_NAME = "Test name";

    private String mName;

    private SharedPreferencesHelper mMockSharedPreferencesHelper;

    private SharedPreferencesHelper mMockBrokenSharedPreferencesHelper;

    @Mock
    SharedPreferences mMockSharedPreferences;

    @Mock
    SharedPreferences mMockBrokenSharedPreferences;

    @Mock
    SharedPreferences.Editor mMockEditor;

    @Mock
    SharedPreferences.Editor mMockBrokenEditor;

    @Before
    public void initMocks() {
        // Create a mocked SharedPreferences.
        mMockSharedPreferencesHelper = createMockSharedPreference();

        // Create a mocked SharedPreferences that fails at saving data.
        mMockBrokenSharedPreferencesHelper = createBrokenMockSharedPreference();
    }

    @Test
    public void sharedPreferencesHelper_saveAndReadName() {
        // save name
        boolean success = mMockSharedPreferencesHelper.saveName(mName);

        assertThat("Checking that name.save... return true",success,is(true));

        // read name
        String name = mMockSharedPreferencesHelper.getName();

        // 保证写入和读取的是一致的
        assertThat("Checking that name has been persisted and read correctly",
                mName,
                is(equalTo(name)));

    }

    @Test
    public void sharedPreferencesHelper_saveNameFailed_Returnfalse() {
        boolean success = mMockBrokenSharedPreferencesHelper.saveName(mName);

        assertThat("Makes sure writing to a broken SharedPreferencesHelper returns false", success,
                is(false));
    }

    private SharedPreferencesHelper createMockSharedPreference() {
        // Mocking reading the SharedPreferences as if mMockSharedPreferences was previously written
        // correctly.模拟从sp中读取name值,就好像之前已经正确写入了一样
        when(mMockSharedPreferences.getString(eq(SharedPreferencesHelper.KEY_NAME), anyString()))
                .thenReturn(mName);

        // Mocking a successful commit.模拟一次成功的提交
        when(mMockEditor.commit()).thenReturn(true);

        // Return the MockEditor when requesting it.
        when(mMockSharedPreferences.edit()).thenReturn(mMockEditor);
        return new SharedPreferencesHelper(mMockSharedPreferences);
    }

    private SharedPreferencesHelper createBrokenMockSharedPreference() {

        // Mocking a commit that fails.模拟一次失败的提交
        when(mMockBrokenEditor.commit()).thenReturn(false);

        // Return the broken MockEditor when requesting it.当请求SharedPreferences.Editor对象时,返回失败的SharedPreferences.Editor对象
        when(mMockBrokenSharedPreferences.edit()).thenReturn(mMockBrokenEditor);
        return new SharedPreferencesHelper(mMockBrokenSharedPreferences);
    }
}
