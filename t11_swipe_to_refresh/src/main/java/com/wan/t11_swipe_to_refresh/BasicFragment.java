package com.wan.t11_swipe_to_refresh;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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
public class BasicFragment extends Fragment {
    private static final String TAG = BasicFragment.class.getSimpleName();
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ListView mListView;
    private static final int LIST_ITEM_COUNT = 20;
    private ArrayAdapter<String> mArrayAdapter;

    public static BasicFragment newInstance() {

        Bundle args = new Bundle();

        BasicFragment fragment = new BasicFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_basic, container, false);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        mSwipeRefreshLayout.setColorSchemeResources(
                R.color.color_swipe_1, R.color.color_swipe_2
        );
        mListView = (ListView) view.findViewById(android.R.id.list);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mArrayAdapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                Cheeses.randomList(LIST_ITEM_COUNT));
        mListView.setAdapter(mArrayAdapter);

        mSwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    /**
                     * 当下拉刷新时回调
                     */
                    @Override
                    public void onRefresh() {
                        Log.d(TAG, "onRefresh: call from swipe to refresh");
                        initiateRefresh();
                    }
                });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_basic,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                if (!mSwipeRefreshLayout.isRefreshing()) {
                    mSwipeRefreshLayout.setRefreshing(true);
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
        mArrayAdapter.clear();
        for (String string : strings) {
            mArrayAdapter.add(string);
        }
        // 当完成刷新时,调用此方法,这样SwipeRefreshLayout就会移除进度圈
        mSwipeRefreshLayout.setRefreshing(false);
    }

}
