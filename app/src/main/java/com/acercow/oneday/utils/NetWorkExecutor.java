package com.acercow.oneday.utils;

import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by JansenZhao on 2017/11/15.
 *
 * <p>Proxy to execute network thread</p>
 */

public class NetWorkExecutor implements Executor {
    private final Executor mNetWorkExecutor;

    public NetWorkExecutor(int threadCount) {
        this.mNetWorkExecutor = Executors.newFixedThreadPool(threadCount);
    }

    @Override
    public void execute(@NonNull Runnable command) {
        mNetWorkExecutor.execute(command);
    }
}
