package com.acercow.oneday.permissiongrant;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Toast;

import com.acercow.androidlib.activity.BaseActivity;
import com.acercow.oneday.R;
import com.acercow.oneday.utils.ActivityUtils;
import com.acercow.oneday.wizard.WizardActivity;

import java.util.HashMap;
import java.util.Map;

public class PermissionGrantActivity extends BaseActivity implements PermissionGrantContract.View {

    private PermissionGrantContract.Presenter mPresenter;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.setFullScreen(true);
        super.onCreate(savedInstanceState);
        new PermissionGrantPresenter(this);
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.unSubscribe();
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_permission_grant;
    }

    @Override
    public void initView(View view) {
        findViewById(R.id.permit_confirm_button).setOnClickListener(this);
    }

    @Override
    public void doBusiness(Context mContext, Bundle savedInstanceState) {

    }

    @Override
    public void onSingleClick(View v) {
        switch (v.getId()) {
            case R.id.permit_confirm_button:
                mPresenter.requestPermission(this, REQUEST_ID_MULTIPLE_PERMISSIONS);
                break;
        }
    }

    @Override
    public void setPresenter(PermissionGrantContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showContentAnim() {

    }

    @Override
    public void toMainActivity() {
        ActivityUtils.startActivity(this, WizardActivity.class);
        finish();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_ID_MULTIPLE_PERMISSIONS: {
                Map<String, Integer> perms = new HashMap<>();
                perms.put(Manifest.permission.ACCESS_FINE_LOCATION, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.CAMERA, PackageManager.PERMISSION_GRANTED);
                if (grantResults.length > 0) {
                    for (int i = 0; i < permissions.length; i++)
                        perms.put(permissions[i], grantResults[i]);
                    if (perms.get(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                            && perms.get(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        toMainActivity();
                    } else {
                        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION) || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                            showDialogOK("Location and Camera Services Permission required for this app",
                                    (dialog, which) -> {
                                        switch (which) {
                                            case DialogInterface.BUTTON_POSITIVE:
                                                mPresenter.requestPermission(PermissionGrantActivity.this, REQUEST_ID_MULTIPLE_PERMISSIONS);
                                                break;
                                            case DialogInterface.BUTTON_NEGATIVE:
                                                // proceed with logic by disabling the related features or quit the app.
                                                break;
                                        }
                                    });
                        }
                        //permission is denied (and never ask again is  checked)
                        //shouldShowRequestPermissionRationale will return false
                        else {
                            Toast.makeText(this, "Go to settings and enable permissions", Toast.LENGTH_LONG)
                                    .show();
                            //                            //proceed with logic by disabling the related features or quit the app.
                        }
                    }
                }
            }
        }

    }

    private void showDialogOK(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", okListener)
                .create()
                .show();
    }
}
