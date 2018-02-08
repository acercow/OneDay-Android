package com.acercow.oneday.permissiongrant;

import android.content.Context;

import com.acercow.oneday.BasePresenter;
import com.acercow.oneday.BaseView;

/**
 * Created by zhaosen on 2018/2/8.
 */

public class PermissionGrantContract {

    interface View extends BaseView<Presenter> {
        void showContentAnim();
        void toMainActivity();
    }

    interface Presenter extends BasePresenter {
        void requestPermission(Context context, int requestCode);
    }
}
