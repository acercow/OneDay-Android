package com.acercow.oneday.note.edit;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.acercow.oneday.BaseFragment;
import com.acercow.oneday.R;
import com.acercow.oneday.data.Note;
import com.acercow.oneday.dialog.NoteInfoDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditNoteFragment extends BaseFragment implements EditNoteContract.View {

    public static final String ARG_NOTE_ID = "arg_note_id";
    private EditNoteContract.Presenter mPresenter;
    private EditText etNoteTitle;
    private EditText etNoteContent;
    private TextView tvNoteHeaderDate;
    private TextView tvNoteHeaderWeather;
    private TextView tvNoteHeaderEmotion;
    private ShareActionProvider mShareActionProvider;

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
        setHasOptionsMenu(true);
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
        tvNoteHeaderDate = view.findViewById(R.id.note_header_date);
        tvNoteHeaderWeather = view.findViewById(R.id.note_header_weather);
        tvNoteHeaderEmotion = view.findViewById(R.id.note_header_emotion);
    }

    @Override
    public void doBusiness(Context mContext, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        String noteId = "";
        if (bundle != null) {
            noteId = bundle.getString(ARG_NOTE_ID);
        }

        mPresenter.populateNote(noteId);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.action_note_save:

                break;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_edit_note, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().finish();
                break;

            case R.id.action_note_share:
                mPresenter.share();
                break;

            case R.id.action_note_info:
                mPresenter.info();
                break;

            case R.id.action_note_save:
                mPresenter.save(etNoteTitle.getText().toString(), etNoteContent.getText().toString(), -1, -1, -1);
                getActivity().setResult(Activity.RESULT_OK);
                getActivity().finish();
                break;
        }
        return super.onOptionsItemSelected(item);
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
    public void setPresenter(EditNoteContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showNoteInEditor(Note note) {
        if (note != null) {
            etNoteTitle.setText(note.getNoteTitle());
            etNoteContent.setText(note.getNoteContent());
            tvNoteHeaderDate.setText(note.getNoteDate().substring(0, 10));
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
    public void showShareDialog(Intent shareIntent) {
        shareIntent.putExtra(Intent.EXTRA_TITLE, etNoteTitle.getText().toString());
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, etNoteTitle.getText().toString());
        shareIntent.putExtra(Intent.EXTRA_TEXT, etNoteContent.getText().toString());
        startActivity(Intent.createChooser(shareIntent, getResources().getString(R.string.app_name)));
    }

    @Override
    public void showEmptyError() {

    }

    @Override
    public void showInfoDialog() {

            // DialogFragment.show() will take care of adding the fragment
            // in a transaction.  We also want to remove any currently showing
            // dialog, so make our own transaction and take care of that here.
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            Fragment prev = getFragmentManager().findFragmentByTag("dialog");
            if (prev != null) {
                ft.remove(prev);
            }
            ft.addToBackStack(null);

            // Create and show the dialog.
            NoteInfoDialog newFragment = new NoteInfoDialog();
            newFragment.show(ft, "dialog");
    }

    @Override
    public void showExportMenu() {

    }


}
