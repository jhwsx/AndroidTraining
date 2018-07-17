package com.wzc.g2_permission_lib;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author wzc
 * @date 2018/7/17
 */
public class DBOpenHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "wzc.db";
    private static final int DB_VERSION = 1;
    public static final String USER_TABLE = "user";
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
            + USER_TABLE + "(_id INTEGER PRIMARY KEY," + "name TEXT)";
    public DBOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
