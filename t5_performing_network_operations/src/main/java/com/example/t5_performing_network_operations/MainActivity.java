package com.example.t5_performing_network_operations;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements DownloadCallback {

    private boolean mDownloading;
    private TextView mTextView;
    private DownloadFragment mDownloadFragment;
    public static boolean sRefreshDisplay = true;
    private NetworkReceiver receiver;
    // The user's current network preference setting.
    public static String sPref = null;
    // Whether there is a Wi-Fi connection.
    private static boolean wifiConnected = false;
    // Whether there is a mobile connection.
    private static boolean mobileConnected = false;
    public static final String WIFI = "Wi-Fi";
    public static final String ANY = "Any";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Registers BroadcastReceiver to track network connection changes.
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        receiver = new NetworkReceiver();
        this.registerReceiver(receiver, filter);
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
                startDownload();
                return true;
            case R.id.action_clear_data:
                mTextView.setText("");
                mDownloadFragment.cancelDownload();
                return true;
            case R.id.action_settings:
                SettingsActivity.start(MainActivity.this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void startDownload() {
        if (!mDownloading && mDownloadFragment != null) {
            mDownloadFragment.startDownload();
            mDownloading = true;
        }
    }

    // Refreshes the display if the network connection and the
    // pref settings allow it.

    @Override
    public void onStart () {
        super.onStart();

        // Gets the user's network preference settings
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        // Retrieves a string value for the preferences. The second parameter
        // is the default value to use if a preference value is not found.
        sPref = sharedPrefs.getString("key5", "Wi-Fi");

        updateConnectedFlags();

        if(sRefreshDisplay){
            loadPage();
        }
    }

    // Uses AsyncTask subclass to download the XML feed from stackoverflow.com.
    public void loadPage() {
        if (((sPref.equals(ANY)) && (wifiConnected || mobileConnected))
                || ((sPref.equals(WIFI)) && (wifiConnected))) {
            // AsyncTask subclass
            startDownload();
        } else {
            mTextView.setText("No network");
            mDownloading = false;
        }
    }

    // Checks the network connection and sets the wifiConnected and mobileConnected
    // variables accordingly.
    public void updateConnectedFlags() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();
        if (activeInfo != null && activeInfo.isConnected()) {
            wifiConnected = activeInfo.getType() == ConnectivityManager.TYPE_WIFI;
            mobileConnected = activeInfo.getType() == ConnectivityManager.TYPE_MOBILE;
        } else {
            wifiConnected = false;
            mobileConnected = false;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Unregisters BroadcastReceiver when app is destroyed.
        if (receiver != null) {
            this.unregisterReceiver(receiver);
        }
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

    private class NetworkReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager conn =  (ConnectivityManager)
                    context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = conn.getActiveNetworkInfo();

            // Checks the user prefs and the network connection. Based on the result, decides whether
            // to refresh the display or keep the current display.
            // If the userpref is Wi-Fi only, checks to see if the device has a Wi-Fi connection.
            if (WIFI.equals(sPref) && networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                // If device has its Wi-Fi connection, sets refreshDisplay
                // to true. This causes the display to be refreshed when the user
                // returns to the app.
                sRefreshDisplay = true;
                Toast.makeText(context, "wifi_connected", Toast.LENGTH_SHORT).show();

                // If the setting is ANY network and there is a network connection
                // (which by process of elimination would be mobile), sets refreshDisplay to true.
            } else if (ANY.equals(sPref) && networkInfo != null) {
                sRefreshDisplay = true;

                // Otherwise, the app can't download content--either because there is no network
                // connection (mobile or Wi-Fi), or because the pref setting is WIFI, and there
                // is no Wi-Fi connection.
                // Sets refreshDisplay to false.
            } else {
                sRefreshDisplay = false;
                Toast.makeText(context, "lost_connection", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
