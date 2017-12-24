package com.wzc.displaybitmapdemo;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static java.net.HttpURLConnection.HTTP_OK;

/**
 * Created by wzc on 2017/11/12.
 * <p>
 * 负责图片下载
 */

public class ImageDownloader {
    public static final int MODE_RANDOM = 0;
    public static final int MODE_CORRECT = 1;

    private int mMode = -1;

    // Get max available VM memory, exceeding this amount will throw an
    // OutOfMemory exception. Stored in kilobytes as LruCache takes an
    // int in its constructor.
    private int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
    // Use 1/8th of the available memory for this memory cache.
    private int cacheSize = maxMemory / 8;
    private LruCache<String, Bitmap> mMemoryCache;

    private DiskLruCache mDiskLruCache;
    private final Object mDiskCacheLock = new Object();
    private boolean mDiskCacheStarting = true;
    private static final int DISK_CACHE_SIZE = 10 * 1024 * 1024; // 10M
    private static final String DISK_CACHE_SUBDIR = "thumbnails";

    public ImageDownloader(Context context) {
        // 初始化内存缓存
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
//                return super.sizeOf(key, value);
                // The cache size will be measured in kilobytes rather than
                // number of items.
                return bitmap.getByteCount() / 1024;
            }
        };
        // 在后台线程初始化磁盘缓存
        File cacheDir = getDiskCacheDir(context, DISK_CACHE_SUBDIR);
        if (!(cacheDir.exists())) {
            cacheDir.mkdirs();
        }
        new InitDiskCacheTask(context).execute(cacheDir);
    }

    class InitDiskCacheTask extends AsyncTask<File, Void, Void> {
        private Context mContext;

        public InitDiskCacheTask(Context context) {
            mContext = context;
        }

        @Override
        protected Void doInBackground(File... params) {
            synchronized (mDiskCacheLock) {
                File cacheDir = params[0];
                try {
                    mDiskLruCache = DiskLruCache.open(cacheDir, getAppVersion(mContext), 1, DISK_CACHE_SIZE);
                    mDiskCacheStarting = false; // finished initialization.
                    mDiskCacheLock.notifyAll(); // Wake any waiting threads
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

    public void download(String url, ImageView imageView) {
        if (mMode == -1) { // 没有配置模式,不往下走
            return;
        }
        Bitmap bitmapFromCache = getBitmapFromMemCache(url);
        if (bitmapFromCache != null) {
            Log.d("ImageDownloader", "来自缓存 url = " + url);
            cancelPotentialDownload(url, imageView);
            imageView.setImageBitmap(bitmapFromCache);
        } else {
            Log.d("ImageDownloader", "直接下载 url = " + url);
            forceDownload(url, imageView);
        }

    }

    private void forceDownload(String url, ImageView imageView) {
        if (url == null) {
            imageView.setImageDrawable(null);
            return;
        }
        if (cancelPotentialDownload(url, imageView)) {
            switch (mMode) {
                // there's no guarantee that the download tasks will finish in the order in which they were started
                case MODE_RANDOM:
                    imageView.setMinimumHeight(160);
                    BitmapDownloaderTask bitmapDownloaderTask = new BitmapDownloaderTask(imageView);
                    bitmapDownloaderTask.execute(url);
                    break;
                case MODE_CORRECT:
                    imageView.setMinimumHeight(160);
                    BitmapDownloaderTask bitmapDownloaderTask1 = new BitmapDownloaderTask(imageView);
                    DownloadedDrawable downloadedDrawable = new DownloadedDrawable(bitmapDownloaderTask1);
                    imageView.setImageDrawable(downloadedDrawable);
                    bitmapDownloaderTask1.execute(url);
                    break;
            }
        }

    }

    private Bitmap downloadBitmap(String url) {
        HttpURLConnection connection = null;
        try {
            URL uri = new URL(url);
            connection = (HttpURLConnection) uri.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(30 * 1000);
            connection.setReadTimeout(30 * 1000);
            connection.connect();

            if (connection.getResponseCode() == HTTP_OK) {
                BufferedInputStream inputStream = null;
                try {
                    inputStream = new BufferedInputStream(connection.getInputStream());
                    return BitmapFactory.decodeStream(inputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                }
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;
    }

    public void setMode(int mode) {
        mMode = mode;
    }

    private class BitmapDownloaderTask extends AsyncTask<String, Void, Bitmap> {
        private String mUrl;
        private WeakReference<ImageView> mImageViewWeakReference;

        public BitmapDownloaderTask(ImageView imageView) {
            mImageViewWeakReference = new WeakReference<ImageView>(imageView);
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            mUrl = params[0];
            return downloadBitmap(mUrl);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (isCancelled()) {
                bitmap = null;
            }

            addBitmapToCache(mUrl, bitmap);

            if (mImageViewWeakReference != null && bitmap != null) {
                final ImageView imageView = mImageViewWeakReference.get();
                BitmapDownloaderTask bitmapDownloaderTask = getBitmapDownloaderTask(imageView);
                if (imageView != null) {
                    // Change bitmap only if this process is still associated with it
                    // Or if we don't use any bitmap to task association
                    if (this == bitmapDownloaderTask || mMode != MODE_CORRECT) {
                        imageView.setImageBitmap(bitmap);
                    }
                }
            }
        }
    }

    // To solve this issue, we should remember the order of the downloads, so that the last started
    // one is the one that will effectively be displayed. It is indeed sufficient for each ImageView to
    // remember its last download.

    /**
     * A fake Drawable that will be attached to the imageView while the download is in progress.
     * <p>
     * <p>Contains a reference to the actual download task, so that a download task can be stopped
     * if a new binding is required, and makes sure that only the last started download process can
     * bind its result, independently of the download finish order.</p>
     */
    //  DownloadedDrawable  is used so that a placeholder image can be displayed in the ImageView while the task completes
    static class DownloadedDrawable extends ColorDrawable {
        private WeakReference<BitmapDownloaderTask> mBitmapDownloaderTaskWeakReference;

        public DownloadedDrawable(BitmapDownloaderTask bitmapDownloaderTask) {
            mBitmapDownloaderTaskWeakReference = new WeakReference<>(bitmapDownloaderTask);
        }

        public BitmapDownloaderTask getBitmapDownloaderTask() {
            return mBitmapDownloaderTaskWeakReference.get();
        }
    }

    // 获取和图片管理的那个任务
    private BitmapDownloaderTask getBitmapDownloaderTask(ImageView imageView) {
        if (imageView != null) {
            Drawable drawable = imageView.getDrawable();
            if (drawable instanceof DownloadedDrawable) {
                return ((DownloadedDrawable) drawable).getBitmapDownloaderTask();
            }
        }
        return null;
    }

    //stop the possible download in progress on this imageView since a new one is about to start.
    private boolean cancelPotentialDownload(String url, ImageView imageView) {
        BitmapDownloaderTask bitmapDownloaderTask = getBitmapDownloaderTask(imageView);

        if (bitmapDownloaderTask != null) {
            String downloadUrl = bitmapDownloaderTask.mUrl;
            // 正在下载任务中的url和当前要下载的url不是同一个,就把旧的任务停止掉
            if (downloadUrl != null && !downloadUrl.equals(url)) {
                // Cancel previous task
                bitmapDownloaderTask.cancel(true);
            } else { // 是同一个,就不要动了
                // The same URL is already being downloaded.
                return false;
            }
        }
        // No task associated with the ImageView, or an existing task was cancelled
        return true;
    }


    public Bitmap getBitmapFromMemCache(String key) {
        Bitmap bitmap = null;
        if (mMemoryCache != null) {
            bitmap = mMemoryCache.get(key);
        }
        return bitmap;
    }

    private static final int DISK_CACHE_INDEX = 0;

    public void addBitmapToCache(String key, Bitmap bitmap) {
        // Add to memory cache
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
        // also add to disk cache
        synchronized (mDiskCacheLock) {

            if (mDiskLruCache != null) {
                OutputStream out = null;
                try {
                    DiskLruCache.Snapshot snapshot = mDiskLruCache.get(key);
                    if (snapshot == null) {
                        DiskLruCache.Editor editor = mDiskLruCache.edit(key);
                        if (editor != null) {
                            out = editor.newOutputStream(DISK_CACHE_INDEX);
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, out);
                            editor.commit();
                            out.close();
                        }
                    } else {
                        snapshot.getInputStream(DISK_CACHE_INDEX).close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (out != null) {
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }
    }

    public Bitmap getBitmapFromDiskCache(String key) {
        Bitmap bitmap = null;
        synchronized (mDiskCacheLock) {
            while (mDiskCacheStarting) {
                try {
                    mDiskCacheLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (mDiskLruCache != null) {
                InputStream inputStream = null;
                try {
                    DiskLruCache.Snapshot snapshot = mDiskLruCache.get(key);
                    if (snapshot != null) {
                        inputStream = snapshot.getInputStream(DISK_CACHE_INDEX);
                        if (inputStream != null) {
                            FileDescriptor fd = ((FileInputStream) inputStream).getFD();
                            bitmap = BitmapFactory.decodeFileDescriptor(fd);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return bitmap;
    }

    public static File getDiskCacheDir(Context context, String uniqueName) {
        // 如果外部存储挂载好的或者外部存储是内置的,那么就使用外部存储的缓存目录;否则,使用内部存储的缓存目录
        String cachePath = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable() ? context.getExternalCacheDir().getPath() :
                context.getCacheDir().getPath();
        return new File(cachePath + File.separator + uniqueName);
    }

    public int getAppVersion(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }
}
