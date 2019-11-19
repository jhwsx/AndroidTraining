package com.wan.t12_add_search_functionality;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;

public class MainActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return MainFragment.newInstance();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        // 这里的SearchView的作用是什么呢?
        // 接收用户的query,并且开启一个searchable activity with the Intent.ACTION_SEARCH intent
        // 把searchable configuration和SearchView相关联
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        ComponentName componentName = new ComponentName(this, SearchResultActivity.class);
        // SearchManager对象调用getSearchableInfo()方法就把searchable.xml中的内容转成了一个SearchInfo对象
        SearchableInfo searchableInfo = searchManager.getSearchableInfo(componentName);
        // 把SearchableInfo对象和SearchView对象关联,这样用户提交一个查询时,SearchView就会开启一个拥有Intent.ACTION_SEARCH的Activity
        searchView.setSearchableInfo(searchableInfo);
        return true;
    }
}
