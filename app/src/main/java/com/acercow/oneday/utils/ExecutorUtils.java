package com.acercow.oneday.utils;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;

/**
 * Created by JansenZhao on 2017/11/15.
 *
 * <p>App thread pool, very early version</p>
 */

public class ExecutorUtils {
    private static final int FIXED_NET_THREADS = 3;
    private static ExecutorUtils instance;

    private final Executor fileIO;
    private final Executor networkIO;
    private final Executor mainUI;

    private ExecutorUtils(Executor fileIO, Executor networkIO, Executor mainUI) {
        this.fileIO = fileIO;
        this.networkIO = networkIO;
        this.mainUI = mainUI;
    }

    public static ExecutorUtils getInstance() {
        synchronized (ExecutorUtils.class) {
            if (instance == null) {
                instance =  new ExecutorUtils(new FileExecutor(),
                        new NetWorkExecutor(FIXED_NET_THREADS), new MainThreadExecutor());
            }
            return instance;
        }
    }

    public Executor fileIO() {
        return instance.fileIO;
    }

    public Executor networkIO() {
        return instance.networkIO;
    }

    public Executor mainUI() {
        return  instance.mainUI;
    }

}
