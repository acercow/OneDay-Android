package com.acercow.oneday.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/**
 * Created by JansenZhao on 2017/11/15.
 */

public class ActivityUtils {


    public static void startActivity(Context fromActivity, Class<?> toActivity) {
       startActivity(fromActivity, toActivity, null);
    }

    public static void startActivity(Context fromActivity, Class<?> toActivity, Bundle bundle) {
        Intent intent = new Intent(fromActivity, toActivity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        fromActivity.startActivity(intent);
    }

    public static void addFragment(FragmentActivity activity, Fragment fragment, int containerId) {
        FragmentManager fm = activity.getSupportFragmentManager();
        fm.beginTransaction().add(containerId, fragment).commit();
    }
}
