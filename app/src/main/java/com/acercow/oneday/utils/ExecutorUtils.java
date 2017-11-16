package com.acercow.oneday.utils;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;

/**
 * Created by JansenZhao on 2017/11/15.
 * <p>
 * <p>App thread pool, very early version</p>
 */

public class ExecutorUtils {
    private static final int FIXED_NET_THREADS = 3;

    private Executor fileIO;
    private Executor networkIO;
    private Executor mainUI;

    ExecutorUtils(Executor fileIO, Executor networkIO, Executor mainUI) {
        this.fileIO = fileIO;
        this.networkIO = networkIO;
        this.mainUI = mainUI;
    }

    public ExecutorUtils() {
        this(new FileExecutor(), new NetWorkExecutor(FIXED_NET_THREADS), new MainThreadExecutor());
    }

    public Executor fileIO() {
        return fileIO;
    }

    public Executor networkIO() {
        return networkIO;
    }

    public Executor mainUI() {
        return mainUI;
    }

}
