package com.acercow.oneday.utils;

import android.content.Context;

import com.acercow.androidlib.utils.FileUtils;
import com.jakewharton.disklrucache.DiskLruCache;

import java.io.File;
import java.io.IOException;


/**
 * Created by zhaosen on 2018/1/25.
 */

public class LruCacheUtils {
    private static final String CACHE_DIR_NAME = "app_cache";
    private static DiskLruCache mDiskLruCache = null;

    public static DiskLruCache getInstance(Context context) {
        if (mDiskLruCache == null) {
            try {
                synchronized (LruCacheUtils.class) {
                    if (mDiskLruCache == null) {
                        File cacheDir = FileUtils.getDiskCacheDir(context, CACHE_DIR_NAME);
                        if (!cacheDir.exists()) {
                            cacheDir.mkdir();
                        }
                        mDiskLruCache = DiskLruCache.open(cacheDir, FileUtils.getAppVersion(context), 1, 10 * 1024 * 1024);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return mDiskLruCache;
    }


}
