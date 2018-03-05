package com.acercow.oneday.note.preview;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.acercow.oneday.BaseFragment;
import com.acercow.oneday.R;
import com.acercow.oneday.data.Injection;
import com.acercow.oneday.data.Note;
import com.acercow.oneday.note.edit.EditNoteActivity;
import com.acercow.oneday.utils.ActivityUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class PreviewNoteFragment extends BaseFragment implements PreviewNoteContract.View {

    public static final String ARG_NOTE_ID = "arg_note_id";
    public static final String SHOULD_LOAD_DATA_FROM_REPO_KEY = "SHOULD_LOAD_DATA_FROM_REPO_KEY";
    public static final int REQUEST_EDIT_NOTE = 1;


    private PreviewNoteContract.Presenter mPresenter;
    private WebView wvNote;
    private TextView tvNote;

    public PreviewNoteFragment() {
    }


    public static PreviewNoteFragment newInstance(String noteId) {
        PreviewNoteFragment fragment = new PreviewNoteFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_NOTE_ID, noteId);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_preview_note;
    }

    @Override
    public void initView(View view) {
        wvNote = view.findViewById(R.id.preview_note_content);
        tvNote = view.findViewById(R.id.preview_note_title);
    }

    @Override
    public void doBusiness(Context mContext, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle == null) {
            mPresenter.exit();
            return;
        }
        Boolean shouldLoadDataFromRepo = true;
        if (savedInstanceState != null) {
            shouldLoadDataFromRepo = savedInstanceState.getBoolean(SHOULD_LOAD_DATA_FROM_REPO_KEY);
        }
        String mNoteId = bundle.getString(ARG_NOTE_ID);
        new PreviewNotePresenter(mNoteId, this, Injection.getNotesDataRepository(mContext), shouldLoadDataFromRepo);
        mPresenter.initMarkdownWebView(wvNote);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FloatingActionButton floatingActionButton = getActivity().findViewById(R.id.fab_edit_note);
        floatingActionButton.setOnClickListener(this);
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
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.fab_edit_note:
                mPresenter.editNote();
                break;
        }
    }

    @Override
    public void setPresenter(PreviewNoteContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SHOULD_LOAD_DATA_FROM_REPO_KEY, mPresenter.isDataMissing());
    }


    @Override
    public void showNoteInWebView(Note note) {

        String body = note.getNoteContent().replace("\n", "\\n");

        String result = "<!doctype html>\n" +
                "<html>\n" +
                "<head>\n" +
                "  <meta charset=\"utf-8\"/>\n" +
                "  <title>Marked in the browser</title>\n" +
                "  <script src=\"file:///android_asset/marked.js\"></script>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <div id=\"content\"></div>\n" +
                "  <script>\n" +
                "   function show(body)\n" +
                "  {\n" +
                "    document.getElementById('content').innerHTML =\n" +
                "      marked(body);\n" +
                "  }\n" +
                "  </script>\n" +
                "</body>\n" +
                "</html>";
        wvNote.loadDataWithBaseURL(null, result, "text/html", "utf-8", null);
        wvNote.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                wvNote.loadUrl("javascript:show(\'" + body + "\')");
            }
        });
    }

    @Override
    public void showNoteTitle(Note note) {
        tvNote.setText(note.getNoteTitle());
    }

    @Override
    public void toEditNoteActivity(String noteId) {
        Bundle bundle = new Bundle();
        bundle.putString(EditNoteActivity.EXTRA_NOTE_ID, noteId);
        ActivityUtils.startActivityForResult(this, EditNoteActivity.class, bundle, REQUEST_EDIT_NOTE);
    }

    @Override
    public void showDetailDialog(Note note) {

    }

    @Override
    public void showShareDialog() {

    }

    @Override
    public void showIdErrorDialog() {
        Toast.makeText(getContext(), "ID is empty", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void backToHomeActivity() {
        getActivity().finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.result(requestCode, resultCode);
    }
}
