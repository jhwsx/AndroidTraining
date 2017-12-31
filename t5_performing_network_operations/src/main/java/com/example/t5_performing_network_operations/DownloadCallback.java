package com.example.t5_performing_network_operations;

import android.net.NetworkInfo;

/**
 * Created by wzc on 2017/12/29.
 */

public interface DownloadCallback {

    interface ResponseCode {
        int CODE_ERROR = -1;
        int CODE_CONNECT_SUCCESS = 0;
        int CODE_GET_INPUT_STREAM_SUCCESS = 1;
        int CODE_PROCESS_STREAM_IN_PROGRESS = 2;
        int CODE_PROCESS_STREAM_SUCCESS = 3;
    }

    /**
     * 下载结束时回调
     * @param result
     */
    void onDownloadFinished(String result);

    /**
     * 下载进度更新时回调
     * @param responseCode
     * @param percent
     */
    void onDownloadProgressUpdated(int responseCode, int percent);


    NetworkInfo getActiveNetworkInfo();

    void finishDownloading();
}
