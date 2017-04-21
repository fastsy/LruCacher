package com.hyts.lrucacher;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

/**
 * Created by sunyan on 17/4/21.
 */

public class LruCacher {

    private static LruCacher lruCacher;

    //分配的最大缓存内存
    private static int maxMemory = (int) (Runtime.getRuntime().maxMemory()/1024/8);

    private static LruCache<String,Bitmap> cacheMemory;

    private LruCacher(){}

    public static synchronized LruCacher getInstance(){

        lruCacher = new LruCacher();
        cacheMemory = new LruCache<String,Bitmap>(maxMemory){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount()/1024;
            }
        };

        return lruCacher;
    }

    public Bitmap getBitmapFromMemory(String url){
        return cacheMemory.get(url);
    }

    public void putBitmapToMemory(String url,Bitmap bitmap){
        if (getBitmapFromMemory(url) == null){
            cacheMemory.put(url,bitmap);
        }
    }


}
