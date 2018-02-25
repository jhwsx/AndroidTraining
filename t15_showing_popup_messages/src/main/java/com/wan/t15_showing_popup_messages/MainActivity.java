package com.wan.t15_showing_popup_messages;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    boolean flag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button = (Button) findViewById(R.id.btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinatorlayout), "Snackbar", Snackbar.LENGTH_SHORT);
                snackbar.setAction("改变button的颜色", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (flag) {
                            button.setBackgroundResource(R.color.colorAccent);
                            flag = false;
                        } else {
                            button.setBackgroundResource(R.color.colorPrimaryDark);
                            flag = true;
                        }

                    }
                });
                snackbar.show();
            }
        });
    }
}
