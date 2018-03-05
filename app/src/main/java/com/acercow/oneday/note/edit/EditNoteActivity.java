package com.acercow.oneday.note.edit;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.acercow.androidlib.activity.BaseActivity;
import com.acercow.oneday.R;
import com.acercow.oneday.data.Injection;
import com.acercow.oneday.utils.ActivityUtils;

public class EditNoteActivity extends BaseActivity {
    public static final String EXTRA_NOTE_ID = "extra_note_id";
    public static final String SHOULD_LOAD_DATA_FROM_REPO_KEY = "SHOULD_LOAD_DATA_FROM_REPO_KEY";

    private String mNoteId;
    private EditNoteContract.Presenter mPresenter;

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
        return R.layout.activity_edit_note;
    }

    @Override
    public void initView(View view) {
        Toolbar toolbar = findViewById(R.id.edit_note_toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_black_24dp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void doBusiness(Context mContext, Bundle savedInstanceState) {

        boolean shouldLoadDataFromRepo = true;
        if (savedInstanceState != null) {
            shouldLoadDataFromRepo = savedInstanceState.getBoolean(SHOULD_LOAD_DATA_FROM_REPO_KEY);
        }
        EditNoteFragment fragment = EditNoteFragment.newInstance(mNoteId);
        mPresenter = new EditNotePresenter(mNoteId, fragment, Injection.getNotesDataRepository(mContext), shouldLoadDataFromRepo);
        ActivityUtils.addFragment(this, fragment, R.id.edit_note_container);

    }

    @Override
    public void onSingleClick(View v) {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SHOULD_LOAD_DATA_FROM_REPO_KEY, mPresenter.isDataMissing());
    }


}
