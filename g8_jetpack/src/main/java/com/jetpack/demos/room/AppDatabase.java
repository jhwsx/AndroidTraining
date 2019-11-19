package com.jetpack.demos.room;

import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;

/**
 * @author wzc
 * @date 2019/1/7
 */
@Database(entities = {User.class, Company.class}, version = 2, exportSchema = true)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    public abstract CompanyDao companyDao();

    //数据库变动添加Migration
    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            //数据库的具体变动，我是在之前的user表中添加了新的column，名字是age。
            //类型是integer，不为空，默认值是0
            database.execSQL("CREATE TABLE company (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, name TEXT)");
        }
    };

}
