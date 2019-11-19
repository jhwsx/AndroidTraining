package com.wan.t11_swipe_to_refresh;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;
import androidx.core.view.ViewCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.wan.t10_swipe_to_refresh.R;

import java.util.List;


/**
 * @author wzc
 * @date 2018/2/10
 */
public class SwipeRefreshListFragment extends ListFragment {
    private static final String TAG = SwipeRefreshListFragment.class.getSimpleName();

    public static SwipeRefreshListFragment newInstance() {

        Bundle args = new Bundle();

        SwipeRefreshListFragment fragment = new SwipeRefreshListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private ListFragmentSwipeRefreshLayout mListFragmentSwipeRefreshLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View listFragmentView = super.onCreateView(inflater, container, savedInstanceState);

        mListFragmentSwipeRefreshLayout = new ListFragmentSwipeRefreshLayout(getContext());
        mListFragmentSwipeRefreshLayout.addView(listFragmentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mListFragmentSwipeRefreshLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return mListFragmentSwipeRefreshLayout;
    }

    private static final int LIST_ITEM_COUNT = 20;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, Cheeses.randomList(LIST_ITEM_COUNT));
        setListAdapter(adapter);
        mListFragmentSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d(TAG, "onRefresh: call from swipe to refresh");
                initiateRefresh();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_basic, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                if (!mListFragmentSwipeRefreshLayout.isRefreshing()) {
                    mListFragmentSwipeRefreshLayout.setRefreshing(true);
                }
                Log.d(TAG, "onRefresh: call from toobar");
                initiateRefresh();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initiateRefresh() {
        new DummyBackgroundTask().execute();
    }

    private class DummyBackgroundTask extends AsyncTask<Void, Void, List<String>> {
        private static final int TASK_DURATION = 3000;

        @Override
        protected List<String> doInBackground(Void... params) {
            try {
                Thread.sleep(TASK_DURATION);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Cheeses.randomList(LIST_ITEM_COUNT);
        }

        @Override
        protected void onPostExecute(List<String> strings) {
            super.onPostExecute(strings);
            onRefreshCompleted(strings);
        }
    }

    private void onRefreshCompleted(List<String> strings) {
        ArrayAdapter<String> stringArrayAdapter = (ArrayAdapter<String>) getListAdapter();
        stringArrayAdapter.clear();
        for (String string : strings) {
            stringArrayAdapter.add(string);
        }
        mListFragmentSwipeRefreshLayout.setRefreshing(false);
    }

    /**
     * 这里创建SwipeRefreshLayout的子类,原因是SwipeRefreshLayout支持一个子View的情况,一般应该是ListView,GridView或者RecyclerView,
     * 而这里SwipeRefreshLayout的子类是一个ViewGroup,我们想要下拉刷新时,仍然生效,就需要重写canChildScrollup()方法
     */
    private class ListFragmentSwipeRefreshLayout extends SwipeRefreshLayout {

        public ListFragmentSwipeRefreshLayout(Context context) {
            super(context);
        }

        @Override
        public boolean canChildScrollUp() {
            ListView listView = getListView();
            if (listView.getVisibility() == View.VISIBLE) {
                return ViewCompat.canScrollVertically(listView, -1);
            }
            return false;
        }
    }
}
