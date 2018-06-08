package com.wzc.t23_background_jobs;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

/**
 * @author wzc
 * @date 2018/6/8
 */
public class CursorLoaderActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{
private static final String TAG = CursorLoaderActivity.class.getSimpleName();
    private ListView mListView;
    private static final int LOADER_ID = 1;
    private CustomContactAdapter mContactAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursor_loader);
        mListView = (ListView) findViewById(R.id.list);
        // initialize the query
        getSupportLoaderManager().initLoader(LOADER_ID, null, this);
    }

    // 当系统初始化好Loader后调用
    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        Log.d(TAG, "onCreateLoader: ");
        switch (id) {
            case LOADER_ID: {
                Uri contentUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                // 返回 CursorLoader 实例并且开始查询.
                return new CursorLoader(this, contentUri, null, null, null, null);
            }
            default:
                return null;
        }

    }
    // 获取查询结果
    @Override
    public void onLoadFinished(Loader loader, Cursor cursor) {
        Log.d(TAG, "onLoadFinished: ");
        cursor.moveToFirst();
        mContactAdapter = new CustomContactAdapter(this, cursor);
        mListView.setAdapter(mContactAdapter);
    }

    @Override
    public void onLoaderReset(Loader loader) {
        Log.d(TAG, "onLoaderReset: ");
        // 清除 Adapter 对 Cursor 的引用, 避免内存泄露
        mContactAdapter.changeCursor(null);
    }
}
