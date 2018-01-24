package com.example.t6_volley;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by wzc on 2018/1/25.
 */

public class MySingleton {
    private static MySingleton sInstance;
    private static Context sContext;
    private RequestQueue sRequestQueue;
    private ImageLoader sImageLoader;
    private MySingleton(Context context) {
        sContext = context;
        sRequestQueue = getRequestQueue();
        sImageLoader = new ImageLoader(sRequestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(20);
                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });
    }

    public RequestQueue getRequestQueue() {
        if (sRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            sRequestQueue = Volley.newRequestQueue(sContext.getApplicationContext());
        }
        return sRequestQueue;
    }

    public static MySingleton getInstance(Context context) {
        if (sInstance == null) {
            synchronized (MySingleton.class) {
                if (sInstance == null) {
                    sInstance = new MySingleton(context);
                }
            }
        }
        return sInstance;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader() {
        return sImageLoader;
    }
}
