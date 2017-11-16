package com.acercow.oneday.splash;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.acercow.oneday.BaseActivity;
import com.acercow.oneday.R;
import com.acercow.oneday.note.HomeActivity;
import com.acercow.oneday.utils.ActivityUtils;

import java.lang.ref.WeakReference;

public class SplashActivity extends BaseActivity implements SplashContract.View, View.OnClickListener {
    private SplashContract.Presenter mPresenter;
    private TextView tvTimerCountdown;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new SplashPresenter(this);
        tvTimerCountdown = findViewById(R.id.tv_timer_countdown);
        tvTimerCountdown.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.start();
        mPresenter.startCountdown(6);
    }

    @Override
    public void setPresenter(SplashContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void setCountdown(int seconds) {
        tvTimerCountdown.setText(seconds + "s");
    }

    @Override
    public void showAdImage(Drawable drawable) {

    }

    @Override
    public void startAnim() {

    }

    @Override
    public void jumpToNext() {
        ActivityUtils.startActivity(this, HomeActivity.class);
        finish();
    }

    @Override
    public Context getContext() {
        return this;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.destroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_timer_countdown:
                jumpToNext();
                break;
        }
     }
}
