package com.example.t5_performing_network_operations;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements DownloadCallback {

    private boolean mDownloading;
    private TextView mTextView;
    private DownloadFragment mDownloadFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.tv_result);
        mDownloadFragment = DownloadFragment.getInstance(getSupportFragmentManager(), "https://www.baidu.com");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_download, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_fetch_data:
                if (!mDownloading && mDownloadFragment != null) {
                    mDownloadFragment.startDownload();
                    mDownloading = true;
                }
                return true;
            case R.id.action_clear_data:
                mTextView.setText("");
                mDownloadFragment.cancelDownload();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDownloadFinished(String result) {
        Log.d("MainActivity", "onDownloadFinished");
        if (result == null) {
            mTextView.setText("No network");
            return;
        }
        mTextView.setText(result);
    }

    @Override
    public void onDownloadProgressUpdated(int responseCode, int percent) {
        switch (responseCode) {
            case ResponseCode.CODE_ERROR:
                Log.d("MainActivity", "onDownloadProgressUpdated CODE_ERROR");
                break;
            case ResponseCode.CODE_CONNECT_SUCCESS:
                Log.d("MainActivity", "onDownloadProgressUpdated CODE_CONNECT_SUCCESS");
                break;
            case ResponseCode.CODE_GET_INPUT_STREAM_SUCCESS:
                Log.d("MainActivity", "onDownloadProgressUpdated CODE_GET_INPUT_STREAM_SUCCESS");
                break;
            case ResponseCode.CODE_PROCESS_STREAM_IN_PROGRESS:
                Log.d("MainActivity", "onDownloadProgressUpdated CODE_PROCESS_STREAM_IN_PROGRESS progress = " + percent + "%");
                mTextView.setText("" + percent + "%");
                break;
            case ResponseCode.CODE_PROCESS_STREAM_SUCCESS:
                Log.d("MainActivity", "onDownloadProgressUpdated CODE_PROCESS_STREAM_SUCCESS");
                break;
        }
    }

    @Override
    public NetworkInfo getActiveNetworkInfo() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo();
    }

    @Override
    public void finishDownloading() {
        Log.d("MainActivity", "finishDownloading()");
        mDownloading = false;
        if (mDownloadFragment != null) {
            mDownloadFragment.cancelDownload();
        }
    }
}
