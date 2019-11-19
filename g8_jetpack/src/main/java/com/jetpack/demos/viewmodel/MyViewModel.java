package com.jetpack.demos.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.os.SystemClock;
import android.util.Log;

import com.jetpack.demos.livedata.AppExecutors;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wzc
 * @date 2019/2/16
 */
public class MyViewModel extends ViewModel {
    private MutableLiveData<List<User>> users;

    public MutableLiveData<List<User>> getUsers() {
        Log.d(ViewModelActivity.TAG, "getUsers: ");
        if (users == null) {
            users = new MutableLiveData<>();
            loadUsers();
        }
        Log.d(ViewModelActivity.TAG, "getUsers: users=" + users);
        return users;
    }

    private void loadUsers() {
        Log.d(ViewModelActivity.TAG, "loadUsers: ");
        final AppExecutors appExecutors = new AppExecutors();
        appExecutors.networkIO().execute(new Runnable() {
            @Override
            public void run() {
                final List<User> list = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    SystemClock.sleep(500);
                    User user = new User("User" + i);
                    list.add(user);
                }
                appExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        users.setValue(list);
                    }
                });
            }
        });
    }
}
