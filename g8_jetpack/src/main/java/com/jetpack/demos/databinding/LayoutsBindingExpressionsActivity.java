package com.jetpack.demos.databinding;

import android.content.Context;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jetpack.demos.R;

/**
 * @author wzc
 * @date 2019/2/21
 */
public class LayoutsBindingExpressionsActivity extends AppCompatActivity {
    public static void start(Context context) {
        Intent starter = new Intent(context, LayoutsBindingExpressionsActivity.class);
        context.startActivity(starter);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLayoutsBindingExpressionsBinding binding
                = DataBindingUtil.setContentView(this, R.layout.activity_layouts_binding_expressions);
        binding.includeTextview.tv.setText("I am a good man.");
        User user = new User();
        user.firstName = "willway";
        user.lastName = "wang";
        user.age = 18;
        binding.setUser(user);
        binding.setHandlers(new MyHandlers());
        binding.setPresenter(new Presenter());

    }
}
