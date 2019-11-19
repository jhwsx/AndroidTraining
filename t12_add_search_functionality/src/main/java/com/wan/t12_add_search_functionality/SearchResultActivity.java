package com.wan.t12_add_search_functionality;

import android.app.SearchManager;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 根据用户提交的query,来完成查询任务和显示搜索结果
 * @author wzc
 * @date 2018/2/10
 */
public class SearchResultActivity extends AppCompatActivity {
    private static final String TAG = SearchResultActivity.class.getSimpleName();
    DatabaseTable db = new DatabaseTable(this);
    private ListView mListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        mListView = (ListView) findViewById(R.id.list);

        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Log.d(TAG, "handleIntent: query = " + query);
            Cursor cursor = db.getWordMatches(query, null);
            if (cursor == null) {
                return;
            }
            List<Result> list= new ArrayList<>();
            try {
                cursor.moveToFirst();
                while (cursor.moveToNext()) {
                    String word = cursor.getString(0);
                    String definition = cursor.getString(1);
                    list.add(new Result(word, definition));
                }
            } finally {
                cursor.close();
            }
            ArrayAdapter<Result> adapter = new ArrayAdapter<>(SearchResultActivity.this, android.R.layout.simple_list_item_1, list);
            mListView.setAdapter(adapter);
        }
    }
}
