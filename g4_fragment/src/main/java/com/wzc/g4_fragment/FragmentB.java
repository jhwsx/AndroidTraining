package com.wzc.g4_fragment;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentB extends Fragment {
    public static FragmentB newInstance() {

        Bundle args = new Bundle();
        FragmentB fragment = new FragmentB();
        fragment.setArguments(args);
        return fragment;
    }

    private PopBackStackCallback callback;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callback = (PopBackStackCallback) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_b, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onFragmentBButtonClicked();
            }
        });
    }
}
