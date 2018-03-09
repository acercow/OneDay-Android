package com.acercow.oneday.splash;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

import com.acercow.androidlib.utils.ExecutorUtils;
import com.acercow.oneday.dialog.bean.DiaryBean;
import com.acercow.oneday.dialog.bean.TaskBean;
import com.acercow.oneday.dialog.bean.UserInfoBean;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by JansenZhao on 2017/11/15.
 */

public class SplashPresenter implements SplashContract.Presenter {
    public static final int MSG_SPLASH_FINISH = 0x01;

    private SplashContract.View mSplashView;
    private ExecutorUtils mExecutor;
    private int mCountdown;
    private Timer mTimer;

    SplashPresenter(@NonNull SplashContract.View view) {
        this.mSplashView = view;
        view.setPresenter(this);
    }


    @Override
    public void startCountdown(int seconds) {
        mCountdown = seconds;
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (mCountdown == -1) {
                    mTimer.cancel();
                    mSplashView.toNextActivity();
                } else {
                    mExecutor.mainUI().execute(new Runnable() {
                        @Override
                        public void run() {
                            mSplashView.setCountdown(mCountdown--);
                        }
                    });
                }
            }
        }, 0, 1000);
    }

    @Override
    public UserInfoBean loadUserInfo() {
        return null;
    }

    @Override
    public TaskBean loadTask(UserInfoBean userInfoBean) {
        return null;
    }

    @Override
    public DiaryBean loadDiary(UserInfoBean userInfoBean) {
        return null;
    }

    @Override
    public boolean checkPermission(Context context) {
        int locationPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION);
        int cameraPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA);
        boolean requestPermission = locationPermission != PackageManager.PERMISSION_GRANTED || cameraPermission != PackageManager.PERMISSION_GRANTED;
        return requestPermission;
    }


    @Override
    public void subscribe() {
        mExecutor = ExecutorUtils.getInstance();
    }

    @Override
    public void unSubscribe() {
        mTimer.cancel();
    }

    public static class SplashHandler extends Handler {
        private final WeakReference<Activity> mActivity;

        private SplashHandler(Activity activity) {
            this.mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            Activity activity = mActivity.get();
            switch (msg.what) {
                case MSG_SPLASH_FINISH:
                    if (activity != null) {
                        activity.finish();
                    }
                    break;
            }
        }
    }
}
