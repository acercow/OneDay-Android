package com.acercow.oneday.splash;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;

import com.acercow.oneday.bean.DiaryBean;
import com.acercow.oneday.bean.TaskBean;
import com.acercow.oneday.bean.UserInfoBean;
import com.acercow.oneday.utils.ExecutorUtils;

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
                if (mCountdown < 0) {
                    mTimer.cancel();
                    mSplashView.jumpToNext();
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
    public void start() {
        mExecutor = ExecutorUtils.getInstance();
    }

    @Override
    public void destroy() {
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
