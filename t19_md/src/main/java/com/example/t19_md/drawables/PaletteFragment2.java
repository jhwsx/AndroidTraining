package com.example.t19_md.drawables;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.t19_md.R;

/**
 * @author wzc
 * @date 2018/4/3
 */
public class PaletteFragment2 extends Fragment {
    private static final String ARG_POSITION = "arg_position";
    private static final int[] sDrawables = {
            R.drawable.one,
            R.drawable.two,
            R.drawable.three,
            R.drawable.four,
            R.drawable.five,
    };
    private int mPos;

    public static PaletteFragment2 newInstance(int position) {

        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        PaletteFragment2 fragment = new PaletteFragment2();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPos = getArguments().getInt(ARG_POSITION);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return    inflater.inflate(R.layout.fragment_palette2, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((ImageView) view).setImageResource(sDrawables[mPos]);
    }

    public static int getCurrentDrawableRes(int pos) {
        return sDrawables[pos];
    }
}
