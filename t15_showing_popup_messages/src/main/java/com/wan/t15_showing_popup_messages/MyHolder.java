package com.wan.t15_showing_popup_messages;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author wzc
 * @date 2018/3/9
 */
public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private TextView mTextView;
    public MyHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        mTextView = (TextView) itemView.findViewById(android.R.id.text1);
    }

    public void setText(String s) {
        mTextView.setText(s);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(mTextView.getContext(), "click : " + mTextView.getText(), Toast.LENGTH_SHORT).show();
    }
}
