package com.acercow.oneday.note.edit;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acercow.oneday.BaseFragment;
import com.acercow.oneday.R;
import com.acercow.oneday.data.Note;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditNoteFragment extends BaseFragment {


    public static final String ARG_NOTE = "arg_note";

    public EditNoteFragment() {
        // Required empty public constructor
    }

    public static EditNoteFragment newInstance(Note note) {
        EditNoteFragment fragment = new EditNoteFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_NOTE, note);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_edit_note;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void widgetClick(View v) {

    }

}
