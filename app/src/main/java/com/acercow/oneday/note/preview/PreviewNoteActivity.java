package com.acercow.oneday.note.preview;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.acercow.androidlib.activity.BaseActivity;
import com.acercow.oneday.R;
import com.acercow.oneday.utils.ActivityUtils;

public class PreviewNoteActivity extends BaseActivity {
    public static final String EXTRA_NOTE_ID = "extra_note_id";

    private String mNoteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initParms(Bundle parms) {
        if (parms != null) {
            mNoteId = parms.getString(EXTRA_NOTE_ID);
        }
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_preview_note;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void doBusiness(Context mContext, Bundle savedInstanceState) {
        PreviewNoteFragment fragment = PreviewNoteFragment.newInstance(mNoteId);
        ActivityUtils.addFragment(this, fragment, R.id.preview_note_container);
    }

    @Override
    public void onSingleClick(View v) {

    }

}
