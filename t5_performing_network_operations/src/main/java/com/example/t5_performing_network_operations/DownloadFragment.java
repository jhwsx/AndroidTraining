package com.example.t5_performing_network_operations;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by wzc on 2017/12/29.
 * <p>
 * 这是一个Headless Fragment,
 * Headless的含义是说这个Fragment没有引用任何UI元素.但是,它仅被用于封装逻辑和处理生命周期事件,更新UI的工作是由宿主Activity来完成的
 */

public class DownloadFragment extends Fragment {
    private static final String URL_KEY = "url";
    private static final String TAG = DownloadFragment.class.getSimpleName();
    private DownTask mDownTask;

    public static DownloadFragment getInstance(FragmentManager fragmentManager, String url) {

        DownloadFragment fragment = (DownloadFragment) fragmentManager.findFragmentByTag(TAG);
        if (fragment == null) {
            Bundle args = new Bundle();
            args.putString(URL_KEY, url);
            fragment = new DownloadFragment();
            fragment.setArguments(args);
            fragmentManager.beginTransaction().add(fragment, TAG).commit();
        }
        return fragment;
    }

    private String mUrl;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mUrl = getArguments().getString(URL_KEY);
    }

    private DownloadCallback mCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // 持有处理任务回调的Activity
        mCallback = (DownloadCallback) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        // 清理掉对Activity的引用,避免内存泄露
        mCallback = null;
    }

    @Override
    public void onDestroy() {
        // 当Fragment销毁时,清理任务
        cancelDownload();
        super.onDestroy();
    }

    public void startDownload() {
        cancelDownload();
        mDownTask = new DownTask();
        mDownTask.execute(mUrl);

    }

    public void cancelDownload() {
        if (mDownTask != null) {
            mDownTask.cancel(true);
        }
    }

    private class DownTask extends AsyncTask<String, Integer, DownTask.Result> {

        /**
         * 这个封装的结果很小,却很好
         * Wrapper class that serves as a union of a result value and an exception. When the download
         * task has completed, either the result value or exception can be a non-null value.
         * This allows you to pass exceptions to the UI thread that were thrown during doInBackground().
         */
        class Result {
            public String mValueResult;
            public Exception mException;

            public Result(String valueResult) {
                mValueResult = valueResult;
            }

            public Result(Exception exception) {
                mException = exception;
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (mCallback != null) {
                NetworkInfo activeNetworkInfo = mCallback.getActiveNetworkInfo();
                if (activeNetworkInfo == null
                        || !activeNetworkInfo.isConnected()
                        || (activeNetworkInfo.getType() != ConnectivityManager.TYPE_WIFI
                        && activeNetworkInfo.getType() != ConnectivityManager.TYPE_MOBILE)) {
                    mCallback.onDownloadFinished(null);
                    cancel(true);
                }
            }

        }

        @Override
        protected Result doInBackground(String... params) {
            Result result = null;
            if (!isCancelled() && params != null && params.length > 0) {
                String urlString = params[0];
                try {
                    URL url = new URL(urlString);
                    String resultString = downloadUrl(url);
                    if (resultString != null) {
                        result = new Result(resultString);
                    } else {
                        throw new IOException("No response received");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    return new Result(e);
                }
            }
            return result;
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int length = values.length;
            if (length == 1) {
                mCallback.onDownloadProgressUpdated(values[0], 0);
            } else if (length == 2) {
                mCallback.onDownloadProgressUpdated(values[0], values[1]);
            }
        }

        @Override
        protected void onPostExecute(Result result) {
            super.onPostExecute(result);
            if (result != null && mCallback != null) {
                if (result.mException != null) {
                    mCallback.onDownloadFinished(result.mException.getMessage());
                } else if (result.mValueResult != null) {
                    mCallback.onDownloadFinished(result.mValueResult);
                }
                mCallback.finishDownloading();
            }
        }


        private String downloadUrl(URL url) throws Exception {
            HttpsURLConnection connection = null;
            InputStream inputStream = null;
            String result = null;
            try {
                connection = (HttpsURLConnection) url.openConnection();
                connection.setReadTimeout(3000);
                connection.setConnectTimeout(3000);
                connection.setRequestMethod("GET");
                connection.setDoInput(true);
                connection.connect();
                publishProgress(DownloadCallback.ResponseCode.CODE_CONNECT_SUCCESS, 0);
                int responseCode = connection.getResponseCode();
                if (responseCode != HttpsURLConnection.HTTP_OK) {
                    throw new IOException("HTTP error code is " + responseCode);
                }
                String contentType = connection.getContentType();
                Map<String, List<String>> headerFields = connection.getHeaderFields();
                int contentLength = connection.getContentLength();
                String contentEncoding = connection.getContentEncoding();

                inputStream = connection.getInputStream();
                publishProgress(DownloadCallback.ResponseCode.CODE_GET_INPUT_STREAM_SUCCESS, 0);
                if (inputStream != null) {
                    result = readStream(inputStream, 500);
                    publishProgress(DownloadCallback.ResponseCode.CODE_PROCESS_STREAM_SUCCESS, 0);
                }
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }

                if (inputStream != null) {
                    inputStream.close();
                }
            }

            return result;
        }

        private String readStream(InputStream inputStream, int maxReadSize) throws Exception {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            char[] buffer = new char[maxReadSize];
            int readSize;
            StringBuffer stringBuffer = new StringBuffer();
            while ((readSize = inputStreamReader.read(buffer)) != -1 && maxReadSize > 0) {
                if (readSize > maxReadSize) {
                    readSize = maxReadSize;
                }
                publishProgress(DownloadCallback.ResponseCode.CODE_PROCESS_STREAM_IN_PROGRESS, (int) (readSize * 1f / maxReadSize * 100));
                stringBuffer.append(buffer, 0, readSize);
                maxReadSize -= readSize;
            }
            return stringBuffer.toString();
        }
    }


}
