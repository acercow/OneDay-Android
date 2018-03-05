package com.acercow.oneday.note.edit;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.acercow.oneday.BaseFragment;
import com.acercow.oneday.R;
import com.acercow.oneday.data.Injection;
import com.acercow.oneday.data.Note;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditNoteFragment extends BaseFragment implements EditNoteContract.View {

    public static final String SHOULD_LOAD_DATA_FROM_REPO_KEY = "SHOULD_LOAD_DATA_FROM_REPO_KEY";
    public static final String ARG_NOTE_ID = "arg_note_id";
    private EditNoteContract.Presenter mPresenter;
    private EditText etNoteTitle;
    private EditText etNoteContent;

    public EditNoteFragment() {
        // Required empty public constructor
    }

    public static EditNoteFragment newInstance(String noteId) {
        EditNoteFragment fragment = new EditNoteFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_NOTE_ID, noteId);
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
        etNoteTitle = view.findViewById(R.id.note_edit_title);
        etNoteContent = view.findViewById(R.id.note_edit_content);
    }

    @Override
    public void doBusiness(Context mContext, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        String noteId = "";
        if (bundle != null) {
            noteId = bundle.getString(ARG_NOTE_ID);
        }
        boolean shouldLoadDataFromRepo = true;
        if (savedInstanceState != null) {
            shouldLoadDataFromRepo = savedInstanceState.getBoolean(SHOULD_LOAD_DATA_FROM_REPO_KEY);
        }
        new EditNotePresenter(noteId, this, Injection.getNotesDataRepository(mContext), shouldLoadDataFromRepo);
        mPresenter.populateNote(noteId);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.fab_save_note:
                mPresenter.save(etNoteTitle.getText().toString(), etNoteContent.getText().toString(), -1, -1, -1);
                getActivity().setResult(Activity.RESULT_OK);
                getActivity().finish();
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unSubscribe();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FloatingActionButton floatingActionButton = getActivity().findViewById(R.id.fab_save_note);
        floatingActionButton.setOnClickListener(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SHOULD_LOAD_DATA_FROM_REPO_KEY, mPresenter.isDataMissing());
    }

    @Override
    public void setPresenter(EditNoteContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showNoteInEditor(Note note) {
        if (note != null) {
            etNoteTitle.setText(note.getNoteTitle());
            etNoteContent.setText(note.getNoteContent());
        }
    }

    @Override
    public void toEditNoteActivity() {

    }

    @Override
    public void toPreviewNoteActivity() {

    }

    @Override
    public void toHomeActivity() {
        getActivity().finish();
    }

    @Override
    public void showSaveStatus() {
        Toast.makeText(getContext(), "Save Success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDiscardConfirmAlert() {

    }

    @Override
    public void highlightKeyword(String key) {

    }

    @Override
    public void nextKeyword() {

    }

    @Override
    public void previousKeyword() {

    }

    @Override
    public void showShareDialog() {

    }

    @Override
    public void showEmptyError() {

    }

    @Override
    public void showExportMenu() {

    }
}
