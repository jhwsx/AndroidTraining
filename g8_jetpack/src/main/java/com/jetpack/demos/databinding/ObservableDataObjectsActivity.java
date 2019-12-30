package com.jetpack.demos.databinding;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableArrayMap;

import com.jetpack.demos.R;

import java.util.concurrent.ThreadLocalRandom;

/**
 * https://developer.android.google.cn/topic/libraries/data-binding/observability
 *
 * @author wangzhichao
 * @since 2019/12/28
 */
public class ObservableDataObjectsActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, ObservableDataObjectsActivity.class);
        context.startActivity(starter);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityObservableDataObjectsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_observable_data_objects);
        // Observable fields
        final Person person = new Person();
        person.firstName.set("zhichao");
        person.lastName.set("wang");
        person.age.set(18);
        binding.setPerson(person);

        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = ThreadLocalRandom.current().nextInt(10);
                person.firstName.set("zhichao" + i);
                person.lastName.set("wang" + i);
                person.age.set(18 + i);
            }
        });
        // Observable collections
        ObservableArrayMap<String, Object> user = new ObservableArrayMap<>();
        user.put(KeyConstants.KEY_FIRST_NAME, "Adups");
        user.put(KeyConstants.KEY_LAST_NAME, ".Inc");
        user.put(KeyConstants.KEY_AGE, 18);
        binding.setUser(user);

        ObservableArrayList<Object> company = new ObservableArrayList<>();
        company.add("Google");
        company.add(".Inc");
        company.add(0);
        binding.setCompany(company);

        // Observable objects
        Human human = new Human();
        human.setFirstName("Peter");
        human.setLastName("Wang");
        binding.setHuman(human);
    }
}
