package com.acercow.oneday.permissiongrant;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.acercow.androidlib.activity.BaseActivity;
import com.acercow.oneday.R;
import com.acercow.oneday.data.repository.local.NotesLocalDataSource;

public class PermissionGrantActivity extends BaseActivity implements PermissionGrantContract.View {

    private PermissionGrantContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.setFullScreen(true);
        NotesLocalDataSource.getInstance(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_permission_grant;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void onSingleClick(View v) {

    }

    @Override
    public void setPresenter(PermissionGrantContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showContentAnim() {

    }
}
