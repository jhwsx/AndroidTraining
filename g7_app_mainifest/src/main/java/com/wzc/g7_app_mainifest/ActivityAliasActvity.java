package com.wzc.g7_app_mainifest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * https://blog.csdn.net/qq_16628781/article/details/69054325
 * @author wzc
 * @date 2018/10/15
 */
public class ActivityAliasActvity extends AppCompatActivity implements View.OnClickListener {

    public static void start(Context context) {
        Intent starter = new Intent(context, ActivityAliasActvity.class);
        context.startActivity(starter);
    }
    private PackageManager mPackageManager;
    private ComponentName[] mComponentNames = new ComponentName[3];
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_alias);
        mPackageManager = getPackageManager();

        mComponentNames [0] = new ComponentName(this,"com.wzc.g7_app_mainifest.MainActivity");
        mComponentNames [1] = new ComponentName(this,"com.wzc.g7_app_mainifest.MainActivity1");
        mComponentNames [2] = new ComponentName(this,"com.wzc.g7_app_mainifest.MainActivity2");
        findViewById(R.id.btn_icon_1).setOnClickListener(this);
        findViewById(R.id.btn_icon_2).setOnClickListener(this);
        findViewById(R.id.btn_icon_3).setOnClickListener(this);
    }

    /**
     * 启动组件
     *
     * @param componentName 组件名
     */
    private void enableComponent(ComponentName componentName) {
        //此方法用以启用和禁用组件，会覆盖Androidmanifest文件下定义的属性
        mPackageManager.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }

    /**
     * 禁用组件
     *
     * @param componentName 组件名
     */
    private void disableComponent(ComponentName componentName) {
        mPackageManager.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_icon_1) {
            enableComponent(mComponentNames[0]);
            disableComponent(mComponentNames[1]);
            disableComponent(mComponentNames[2]);
        } else if (id == R.id.btn_icon_2) {
            disableComponent(mComponentNames[0]);
            enableComponent(mComponentNames[1]);
            disableComponent(mComponentNames[2]);
        } else if (id == R.id.btn_icon_3) {
            disableComponent(mComponentNames[1]);
            disableComponent(mComponentNames[0]);
            enableComponent(mComponentNames[2]);
        }
        Toast.makeText(this, "请查看桌面应用icon的变化", Toast.LENGTH_SHORT).show();
    }
}
