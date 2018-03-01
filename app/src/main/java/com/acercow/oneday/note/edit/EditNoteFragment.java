package com.acercow.oneday.note.edit;


import android.content.Context;
import android.os.Bundle;
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


    public static final String ARG_NOTE = "arg_note";
    private EditNoteContract.Presenter mPresenter;
    private EditText etNoteTitle;
    private EditText etNoteContent;

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
        etNoteTitle = view.findViewById(R.id.note_edit_title);
        etNoteContent = view.findViewById(R.id.note_edit_content);
    }

    @Override
    public void doBusiness(Context mContext) {
        Bundle bundle = getArguments();
        String noteId = "";
        if (bundle != null) {
            noteId = bundle.getString(ARG_NOTE);
        }
        mPresenter = new EditNotePresenter(noteId, this, Injection.getNotesDataRepository(mContext));
    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.save(etNoteTitle.getText().toString(), etNoteContent.getText().toString(), -1, -1, -1);
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
