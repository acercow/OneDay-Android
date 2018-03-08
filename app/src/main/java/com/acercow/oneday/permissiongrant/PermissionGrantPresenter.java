package com.acercow.oneday.permissiongrant;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by jansen on 2018/2/8.
 */

public class PermissionGrantPresenter implements PermissionGrantContract.Presenter {

    private CompositeDisposable mCompositeDisposable;
    private PermissionGrantContract.View mPermissionGrantView;
    public static final int MULTIPLE_PERMISSIONS = 10;

    String[] permissions= new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.ACCESS_FINE_LOCATION};

    public PermissionGrantPresenter(PermissionGrantContract.View permissionGrantView) {
        this.mCompositeDisposable = new CompositeDisposable();
        this.mPermissionGrantView = permissionGrantView;
        permissionGrantView.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {
        mCompositeDisposable.clear();
    }

    @Override
    public void requestPermission(Context context, int requestCode) {
        checkAndRequestPermissions(context);
    }

    private  boolean checkAndRequestPermissions(Context context) {
        int locationPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION);
        int cameraPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA);
        int readPhoneStatePermission = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (locationPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (readPhoneStatePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions((Activity)context, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), PermissionGrantActivity.REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }


}
