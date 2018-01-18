package com.acercow.oneday.utils;

import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by jansen on 2018/1/18.
 */

public class ScheduledExecutor implements Executor {
    private ScheduledExecutorService mScheduledExecutor;
    private long initialDelay;
    private long period;
    private TimeUnit unit;

    public ScheduledExecutor(long initialDelay, long period, TimeUnit unit) {
        mScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        this.initialDelay = initialDelay;
        this.unit = unit;
        this.period = period;
    }

    @Override
    public void execute(@NonNull Runnable command) {
        mScheduledExecutor.scheduleAtFixedRate(command, initialDelay, period, unit);
    }
}
