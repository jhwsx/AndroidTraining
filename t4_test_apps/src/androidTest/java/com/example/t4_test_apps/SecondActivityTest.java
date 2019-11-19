package com.example.t4_test_apps;

import android.content.Intent;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.filters.MediumTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import android.view.View;
import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Created by wzc on 2017/12/29.
 */

@MediumTest
@RunWith(AndroidJUnit4.class)
public class SecondActivityTest {

    @Rule
    public ActivityTestRule<SecondActivity> rule = new ActivityTestRule<SecondActivity>(SecondActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            // 获取intent
            InstrumentationRegistry.getTargetContext();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.putExtra("EXTRA_STRING", "hello");
            return intent;
        }
    };

    @Test
    public void ensureIntentDataIsDisplayed() throws Exception {
        SecondActivity activity = rule.getActivity();

        View viewById = activity.findViewById(R.id.textView);
        assertThat(viewById, notNullValue());
        assertThat(viewById, instanceOf(TextView.class));

        TextView textView = (TextView) viewById;
        assertThat(textView.getText().toString(), is("hello"));
    }
}