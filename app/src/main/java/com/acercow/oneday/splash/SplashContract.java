package com.acercow.oneday.splash;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.acercow.oneday.BasePresenter;
import com.acercow.oneday.BaseView;
import com.acercow.oneday.bean.DiaryBean;
import com.acercow.oneday.bean.TaskBean;
import com.acercow.oneday.bean.UserInfoBean;

/**
 * Created by JansenZhao on 2017/11/15.
 */

public interface SplashContract {
    interface View extends BaseView<Presenter> {
        void setCountdown(int seconds);

        void showAdImage(Drawable drawable);

        void startAnim();

        void jumpToNext();

        Context getContext();
    }




    interface Presenter extends BasePresenter {

        void startCountdown(int seconds);

        UserInfoBean loadUserInfo();

        TaskBean loadTask(UserInfoBean userInfoBean);

        DiaryBean loadDiary(UserInfoBean userInfoBean);

    }
}
