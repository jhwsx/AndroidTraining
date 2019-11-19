package com.wan.t15_showing_popup_messages;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wzc
 * @date 2018/3/9
 */
public class MyAdapter extends RecyclerView.Adapter<MyHolder> {
    private List<String> mStringList = new ArrayList<>();

    public MyAdapter(List<String> stringList) {
        mStringList = stringList;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, null);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.setText(mStringList.get(position));
    }

    @Override
    public int getItemCount() {
        return mStringList.size();
    }
}
