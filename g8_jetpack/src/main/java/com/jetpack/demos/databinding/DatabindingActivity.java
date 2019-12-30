package com.jetpack.demos.databinding;

import android.content.Context;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.jetpack.demos.R;

/**
 * @author wzc
 * @date 2019/2/21
 */
public class DatabindingActivity extends AppCompatActivity {
    public static void start(Context context) {
        Intent starter = new Intent(context, DatabindingActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDatabindingBinding databinding = DataBindingUtil.setContentView(this, R.layout.activity_databinding);
        databinding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutsBindingExpressionsActivity.start(DatabindingActivity.this);
            }
        });
        databinding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObservableDataObjectsActivity.start(DatabindingActivity.this);
            }
        });
        databinding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GenerateBindingActivity.start(DatabindingActivity.this);
            }
        });
    }
}
