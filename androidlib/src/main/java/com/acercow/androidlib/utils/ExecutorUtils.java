package com.acercow.androidlib.utils;

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

    private static ExecutorUtils instance;

    public static ExecutorUtils getInstance() {
        if (instance == null) {
            synchronized (ExecutorUtils.class) {
                if (instance == null) {
                    instance = new ExecutorUtils();
                }
            }
        }
        return instance;
    }

    private ExecutorUtils(Executor fileIO, Executor networkIO, Executor mainUI) {
        this.fileIO = fileIO;
        this.networkIO = networkIO;
        this.mainUI = mainUI;
    }

    private ExecutorUtils() {
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
