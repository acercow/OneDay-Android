package com.acercow.oneday.note.edit;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.acercow.androidlib.activity.BaseActivity;
import com.acercow.oneday.R;
import com.acercow.oneday.data.Note;
import com.acercow.oneday.utils.ActivityUtils;

public class EditNoteActivity extends BaseActivity {
    public static final String EXTRA_NOTE = "EXTRA_NOTE";
    private Note mNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initParms(Bundle parms) {
        mNote = (Note) parms.getSerializable(EXTRA_NOTE);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_edit_note;
    }

    @Override
    public void initView(View view) {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void doBusiness(Context mContext) {
        EditNoteFragment fragment = EditNoteFragment.newInstance(mNote);
        ActivityUtils.addFragment(this, fragment, R.id.edit_note_container);

    }

    @Override
    public void onSingleClick(View v) {

    }

}
