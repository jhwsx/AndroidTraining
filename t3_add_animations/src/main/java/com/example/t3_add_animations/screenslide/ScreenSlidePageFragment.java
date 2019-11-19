package com.example.t3_add_animations.screenslide;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.t3_add_animations.R;

/**
 * Created by wzc on 2017/12/24.
 */

public class ScreenSlidePageFragment extends Fragment {
    private static final String ARG_PAGE = "arg_page";

    public static ScreenSlidePageFragment newInstance(int page) {

        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private int mPage;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_screen_slide_page, container, false);
        TextView textView = (TextView) view.findViewById(R.id.text);
        String string = getResources().getString(R.string.text_page_content,String.valueOf(mPage));
        String replace = string.replace("\n", "<br/>");
        textView.setText(Html.fromHtml(replace));
        return view;
    }
}
