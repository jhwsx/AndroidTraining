package com.jetpack.demos.databinding;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.jetpack.demos.R;

/**
 * @author wangzhichao
 * @since 2019/12/28
 */
public class GenerateBindingFragment extends Fragment {
    public static GenerateBindingFragment newInstance() {
        
        Bundle args = new Bundle();
        
        GenerateBindingFragment fragment = new GenerateBindingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        com.jetpack.demos.databinding.Fragment binding = DataBindingUtil.inflate(inflater, R.layout.fragment_generate_binding, container, false);
        return binding.getRoot();
    }
}
