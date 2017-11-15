package com.acercow.oneday.splash;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.acercow.oneday.R;

import java.lang.ref.WeakReference;

public class SplashActivity extends AppCompatActivity implements SplashContract.View {

    public static final int MSG_SPLASH_FINISH = 0x01;
    private Handler mHandler;
    private SplashContract.Presenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new SplashPresenter(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mHandler = new SplashHandler(this);
        mHandler.sendEmptyMessageDelayed(MSG_SPLASH_FINISH, 2000);
    }

    @Override
    public void setPresenter(SplashContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void startCountdown(int millionTimes) {

    }

    @Override
    public void showAdImage(Drawable drawable) {

    }

    @Override
    public void startAnim() {

    }


    // To avoid memory leak on long post-delay
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
