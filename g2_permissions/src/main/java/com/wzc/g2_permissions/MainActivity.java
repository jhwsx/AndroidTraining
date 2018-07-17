package com.wzc.g2_permissions;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.wzc.g2_permission_lib.ICompute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startActivity(View view) {
        ComponentName componentName = new ComponentName("com.wzc.g2_permission_lib",
                "com.wzc.g2_permission_lib.PermissionActivity");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        startActivity(intent);

    }

    public void startService(View view) {
        ComponentName componentName = new ComponentName("com.wzc.g2_permission_lib",
                "com.wzc.g2_permission_lib.PermissionService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        startService(intent);
    }

    public void sendBroadcast(View view) {
        Intent intent = new Intent("com.wzc.g2_permission_lib.PermissionReceiver_ACTION");
        sendBroadcast(intent,"com.wzc.g2_permission.SendBroadcast_ACCESS");
    }


    public void queryProvider(View view) {
        Uri userUri = Uri.parse("content://com.wzc.g2_permission_lib.permissionprovider/user");
        Cursor cursor = getContentResolver().query(userUri, null, null, null, null);
        List<String> list = new ArrayList<>(3);
        if (cursor != null) {
            try {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    list.add(cursor.getString(cursor.getColumnIndex("name")));
                    cursor.moveToNext();
                }
            } finally {
                cursor.close();
            }
        }
        String[] arr = new String[list.size()];
        arr = list.toArray(arr);
        Toast.makeText(MainActivity.this, Arrays.toString(arr), Toast.LENGTH_SHORT).show();
    }

    public void bindService(View view) {
        if (!mIsBound) {
            ComponentName componentName = new ComponentName("com.wzc.g2_permission_lib",
                    "com.wzc.g2_permission_lib.PermissionBindService");
            Intent intent = new Intent();
            intent.setComponent(componentName);
            bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
        }
        if (mICompute != null) {
            try {
                final int add = mICompute.add(1, 1);

                Toast.makeText(MainActivity.this, "1 + 1 = " + add, Toast.LENGTH_SHORT).show();

            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private ICompute mICompute;
    private boolean mIsBound;
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIsBound = true;
            mICompute = ICompute.Stub.asInterface(service);

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mIsBound = false;
        }
    };
}
