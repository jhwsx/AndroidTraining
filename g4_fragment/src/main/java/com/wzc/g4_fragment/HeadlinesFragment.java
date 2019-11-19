package com.wzc.g4_fragment;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wzc
 * @date 2018/7/23
 */
public class HeadlinesFragment extends Fragment {
    private OnHeadlineItemClickListener mOnHeadlineItemClickListener;
    private ListView mListView;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mOnHeadlineItemClickListener = (OnHeadlineItemClickListener) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.headlines_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListView = (ListView) view.findViewById(R.id.listview);
        mListView.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, getData()));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mOnHeadlineItemClickListener != null) {
                    mOnHeadlineItemClickListener.onHeadlineItemClicked(position, getData().get(position));
                }
            }
        });
    }

    private List<String> getData() {
        List<String> result = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            result.add("Headline " + i);
        }

        return result;
    }



    public interface OnHeadlineItemClickListener {
        void onHeadlineItemClicked(int position, String headline);
    }
}
