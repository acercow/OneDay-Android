package com.acercow.oneday.splash;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.acercow.androidlib.activity.BaseActivity;
import com.acercow.oneday.R;
import com.acercow.oneday.note.HomeActivity;

public class SplashActivity extends BaseActivity implements SplashContract.View {
    private SplashContract.Presenter mPresenter;
    private TextView tvTimerCountdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        new SplashPresenter(this);
        super.setFullScreen(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView(View view) {
        tvTimerCountdown = findViewById(R.id.tv_timer_countdown);
        tvTimerCountdown.setOnClickListener(this);
    }

    @Override
    public void doBusiness(Context mContext) {
    }

    @Override
    public void onSingleClick(View v) {
        switch (v.getId()) {
            case R.id.tv_timer_countdown:
                jumpToNext();
                break;
        }
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
        startActivity(HomeActivity.class);
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
}
