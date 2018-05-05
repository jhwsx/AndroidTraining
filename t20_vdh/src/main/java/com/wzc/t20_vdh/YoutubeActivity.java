package com.wzc.t20_vdh;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.wzc.t19_vdh.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wzc
 * @date 2018/3/24
 */
public class YoutubeActivity extends Activity {

    private ListView mListView;
    private TextView mHeader;
    private YoutubeLayout mYoutubeLayout;
    private List<String> mData = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);
        initData();
        mListView = (ListView) findViewById(R.id.listview);
        mHeader = (TextView) findViewById(R.id.header);
        mYoutubeLayout = (YoutubeLayout) findViewById(R.id.dragLayout);

        mListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mData));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mHeader.setText(mListView.getAdapter().getItem(position).toString());
                mYoutubeLayout.setVisibility(View.VISIBLE);
                mYoutubeLayout.maximize();
            }
        });
    }

    private void initData() {
        for (int i = 0; i < 50; i++) {
            mData.add("item" + i);
        }
    }
}
