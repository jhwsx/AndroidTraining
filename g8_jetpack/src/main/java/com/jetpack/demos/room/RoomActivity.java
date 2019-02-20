package com.jetpack.demos.room;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jetpack.demos.R;

import java.util.List;

public class RoomActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = RoomActivity.class.getSimpleName();
    private UserDao mUserDao;
    private CompanyDao mCompanyDao;

    public static void start(Context context) {
        Intent starter = new Intent(context, RoomActivity.class);
        context.startActivity(starter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "roomDemo-database")
                .addMigrations(AppDatabase.MIGRATION_1_2).build();
        mUserDao = db.userDao();
        mCompanyDao = db.companyDao();

        initViews();
    }

    private void initViews() {
        Button btnInsertOne = (Button) findViewById(R.id.button_insert_one);
        Button btnInsertSome = (Button) findViewById(R.id.button_insert_some);
        Button btnUpdateOne = (Button) findViewById(R.id.button_update_one);
        Button btnDeleteOne = (Button) findViewById(R.id.button_delete_one);
        Button btnFindOne = (Button) findViewById(R.id.button_find_one);
        Button btnFindAll = (Button) findViewById(R.id.button_find_all);
        Button btnDeleteAll = (Button) findViewById(R.id.button_delete_all);
        Button button_get_company = (Button) findViewById(R.id.button_get_company);
        Button button_insert_company = (Button) findViewById(R.id.button_insert_company);
        btnInsertOne.setOnClickListener(this);
        btnInsertSome.setOnClickListener(this);
        btnUpdateOne.setOnClickListener(this);
        btnDeleteOne.setOnClickListener(this);
        btnFindOne.setOnClickListener(this);
        btnFindAll.setOnClickListener(this);
        btnDeleteAll.setOnClickListener(this);
        button_get_company.setOnClickListener(this);
        button_insert_company.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.button_insert_one) {
            insertOne();
        } else if (id == R.id.button_insert_some) {
            insertSome();
        } else if (id == R.id.button_update_one) {
            updateOne();
        } else if (id == R.id.button_delete_one) {
            deleteOne();
        } else if (id == R.id.button_find_one) {
            findOne();
        } else if (id == R.id.button_find_all) {
            findAll();
        } else if (id == R.id.button_delete_all) {
            deleteAll();
        }  else if (id == R.id.button_get_company) {
            getCompany();
        }  else if (id == R.id.button_insert_company) {
            insertCompany();
        }
    }



    private void deleteAll() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<User> users = mUserDao.getAll();

                int i = mUserDao.deleteAll(users);
                Log.d(TAG, "deleteAll : delete="+i);
            }
        }).start();
    }

    private void findAll() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<User> users = mUserDao.getAll();
                for (User user : users) {
                    Log.d(TAG, "findAll user = " + user);
                }
            }
        }).start();
    }

    private void findOne() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                User user = mUserDao.findByUid(1);
                Log.d(TAG, "findOne user = " + user);
            }
        }).start();
    }

    private void deleteOne() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                User user = mUserDao.findByUid(1);
                int delete = mUserDao.delete(user);
                Log.d(TAG, "deleteOne delete=" + delete);
            }
        }).start();
    }

    private void updateOne() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                User user = new User(1);
                user.setFirstName("updatedFirstName");
                int update = mUserDao.update(user);
                Log.d(TAG, "updateOne: update = " + update);
            }
        }).start();
    }

    private void insertSome() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Long> longs = mUserDao.insertAll(new User("t" + (System.currentTimeMillis() / 1000), "allen"), new User("t" + (System.currentTimeMillis() / 1000), "allen"));
                if (longs != null && !longs.isEmpty()) {
                    for (Long aLong : longs) {
                        Log.d(TAG, "insert success: id = " + aLong);
                    }
                } else {
                    Log.d(TAG, "insert fail");
                }
            }
        }).start();
    }

    private void insertOne() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Long aLong = mUserDao.insert(new User("t" + (System.currentTimeMillis() / 1000), "allen"));
                if (aLong > 0) {
                    Log.d(TAG, "insert success: id = " + aLong);
                } else {
                    Log.d(TAG, "insert fail");
                }
            }
        }).start();

    }

    private void insertCompany() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Company company = new Company();
                company.setId(1);
                company.setName("adups");
                Long aLong = mCompanyDao.insert(company);
                if (aLong > 0) {
                    Log.d(TAG, "insert success: id = " + aLong);
                } else {
                    Log.d(TAG, "insert fail");
                }
            }
        }).start();
    }

    private void getCompany() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Company> users = mCompanyDao.getAll();
                for (Company user : users) {
                    Log.d(TAG, "findAll user = " + user);
                }
            }
        }).start();
    }
}
