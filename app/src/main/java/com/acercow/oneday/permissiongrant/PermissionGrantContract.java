package com.acercow.oneday.permissiongrant;

import com.acercow.oneday.BasePresenter;
import com.acercow.oneday.BaseView;

/**
 * Created by zhaosen on 2018/2/8.
 */

public class PermissionGrantContract {

    interface View extends BaseView<Presenter> {
        void showContentAnim();
    }

    interface Presenter extends BasePresenter {
        void requestPermission();
        void toMainActivity();
    }
}
