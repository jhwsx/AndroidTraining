package com.wzc.g2_permission_lib;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * @author wzc
 * @date 2018/7/17
 */
public class PermissionProvider extends ContentProvider {
    private static final String TAG = PermissionProvider.class.getSimpleName();
    private Context mContext;
    private SQLiteDatabase mDb;
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final String AUTHORITY = "com.wzc.g2_permission_lib.permissionprovider";
    private static final int USER_URI_CODE = 0;

    static {
        sUriMatcher.addURI(AUTHORITY, "user", USER_URI_CODE);
    }

    @Override
    public boolean onCreate() {
        Log.d(TAG, "onCreate: current thread:" + Thread.currentThread().getName());
        mContext = getContext();
        initProviderData();
        return true;
    }

    private void initProviderData() {
        mDb = new DBOpenHelper(mContext).getWritableDatabase();
        mDb.execSQL("delete from " + DBOpenHelper.USER_TABLE);
        mDb.execSQL("insert into " + DBOpenHelper.USER_TABLE + " values(1, 'wzc1')");
        mDb.execSQL("insert into " + DBOpenHelper.USER_TABLE + " values(2, 'wzc2')");
        mDb.execSQL("insert into " + DBOpenHelper.USER_TABLE + " values(3, 'wzc3')");
        mDb.execSQL("insert into " + DBOpenHelper.USER_TABLE + " values(4, 'wzc4')");
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        String tableName = getTableName(uri);

        if (tableName == null) {
            throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        return mDb.query(tableName, projection, selection, selectionArgs, null, null, sortOrder);
    }

    private String getTableName(Uri uri) {
        String tableName = null;
        int match = sUriMatcher.match(uri);
        switch (match) {
            case USER_URI_CODE:
                tableName = DBOpenHelper.USER_TABLE;
                break;
            default:
        }
        return tableName;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
