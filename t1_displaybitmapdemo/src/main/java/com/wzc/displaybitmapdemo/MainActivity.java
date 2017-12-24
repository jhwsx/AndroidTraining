package com.wzc.displaybitmapdemo;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.widget.RadioGroup;

public class MainActivity extends ListActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup mRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        mRadioGroup.setOnCheckedChangeListener(this);

        setListAdapter(new ImageAdapter(this));
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        int mode = -1;
        switch (checkedId) {
            case R.id.radiobutton_random:
                mode = ImageDownloader.MODE_RANDOM;
                break;
            case R.id.radiobutton_correct:
                mode = ImageDownloader.MODE_CORRECT;
                break;
            default:
                break;
        }

        ((ImageAdapter) getListAdapter()).getImageDownloader().setMode(mode);
        ((ImageAdapter) getListAdapter()).notifyDataSetChanged();
    }
}
