package com.acercow.oneday.splash;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.acercow.oneday.R;

import java.lang.ref.WeakReference;

public class SplashActivity extends AppCompatActivity {

    public static final int MSG_SPLASH_FINISH = 0x01;
    private Handler mHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }


    @Override
    protected void onStart() {
        super.onStart();
        mHandler = new SplashHandler(this);
        mHandler.sendEmptyMessageDelayed(MSG_SPLASH_FINISH, 2000);
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
