package com.acercow.oneday.splash;

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
        void startCountdown(int millionTimes);
        void showAdImage(Drawable drawable);
        void startAnim();

    }




    interface Presenter extends BasePresenter {
        void jumpToNext();

        UserInfoBean loadUserInfo();

        TaskBean loadTask(UserInfoBean userInfoBean);

        DiaryBean loadDiary(UserInfoBean userInfoBean);

    }
}
