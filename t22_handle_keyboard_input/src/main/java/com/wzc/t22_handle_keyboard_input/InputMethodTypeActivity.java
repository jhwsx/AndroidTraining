package com.wzc.t22_handle_keyboard_input;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 当使用 ScrollView 时，打开 Activity 时，会自动弹出软键盘。
 * @author wzc
 * @date 2018/5/12
 */
public class InputMethodTypeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_im_type);

        EditText search = (EditText) findViewById(R.id.search);
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean isHandled = false;
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    Toast.makeText(InputMethodTypeActivity.this, "search ========>" +
                            v.getText().toString(), Toast.LENGTH_SHORT).show();

                    isHandled = true;
                }
                return isHandled;
            }
        });

        EditText send = (EditText) findViewById(R.id.send);
        send.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean isHandled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    Toast.makeText(InputMethodTypeActivity.this, "send ========>" +
                            v.getText().toString(), Toast.LENGTH_SHORT).show();

                    isHandled = true;
                }
                return isHandled;
            }
        });
    }
}
