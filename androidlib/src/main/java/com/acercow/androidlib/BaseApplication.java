package com.acercow.androidlib;

import android.app.Application;

/**
 * Created by Administrator on 2018/1/18.
 */

public class BaseApplication extends Application {
    public static boolean isDebug = true;

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
