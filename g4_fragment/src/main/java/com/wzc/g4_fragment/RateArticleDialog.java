package com.wzc.g4_fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AlertDialog;

/**
 * @author wzc
 * @date 2018/7/25
 */
public class RateArticleDialog extends DialogFragment {
    String[] rateArr = new String[]{"Bad", "Good", "Very Good"};
    public static final String EXTRA_REPSONSE_RATE = "extra_repsonse_rate";
    public static final int REQUEST_FEEDBACK_RATE_CODE = 3;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Holo);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle("Rate")
                .setItems(rateArr, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setResult(which);
                    }
                });
        return builder.create();
    }

    private void setResult(int which) {
        if (getTargetFragment() == null) {
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_REPSONSE_RATE, rateArr[which]);
        getTargetFragment().onActivityResult(REQUEST_FEEDBACK_RATE_CODE, Activity.RESULT_OK, intent);

    }
}
