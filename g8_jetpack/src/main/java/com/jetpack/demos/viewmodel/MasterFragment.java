package com.jetpack.demos.viewmodel;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.jetpack.demos.R;
import com.jetpack.demos.databinding.FragmentMasterBinding;

/**
 * @author wzc
 * @date 2019/2/16
 */
public class MasterFragment extends Fragment {

    private FragmentMasterBinding mBinding;
        String[] data = {"one","two","three","four","five","six","seven","eight","nine","ten"};
    private ArrayAdapter<String> mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_master, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 传入 getActivity()，而不是 fragment；否则，获取到的不是一个 SharedViewModel 对象
        final SharedViewModel sharedViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        mAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, data);
        mBinding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sharedViewModel.setSelected(position);
            }
        });
        mBinding.listview.setAdapter(mAdapter);
    }
}
