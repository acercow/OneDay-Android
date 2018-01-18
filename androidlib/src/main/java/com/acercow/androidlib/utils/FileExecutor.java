package com.acercow.androidlib.utils;

import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by JansenZhao on 2017/11/15.
 */

 class FileExecutor implements Executor {

    private final Executor mFileExecutor;

    public FileExecutor() {
        mFileExecutor = Executors.newSingleThreadExecutor();
    }

    @Override
    public void execute(@NonNull Runnable command) {
        mFileExecutor.execute(command);
    }
}
