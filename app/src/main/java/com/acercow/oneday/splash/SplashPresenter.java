package com.acercow.oneday.splash;

import android.support.annotation.NonNull;

import com.acercow.oneday.bean.DiaryBean;
import com.acercow.oneday.bean.TaskBean;
import com.acercow.oneday.bean.UserInfoBean;
import com.acercow.oneday.utils.ActivityUtils;

/**
 * Created by JansenZhao on 2017/11/15.
 */

public class SplashPresenter implements SplashContract.Presenter {

    private SplashContract.View mSplashView;

    SplashPresenter(@NonNull SplashContract.View view) {
        this.mSplashView = view;
        view.setPresenter(this);
    }

    @Override
    public void jumpToNext() {
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
}
