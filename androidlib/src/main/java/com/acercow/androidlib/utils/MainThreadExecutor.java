package com.acercow.androidlib.utils;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;


/**
 * Created by JansenZhao on 2017/11/15.
 */

class MainThreadExecutor implements Executor {
    private final Handler mHandler;
    public MainThreadExecutor() {
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void execute(@NonNull Runnable command) {
        mHandler.post(command);
    }
}
