package com.acercow.oneday.note.preview;

import android.app.Activity;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.acercow.oneday.data.Note;
import com.acercow.oneday.data.NotesDataSource;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;

/**
 * Preview Note Presenter in Markdown
 * Created by zhaosen on 2018/3/2.
 */

public class PreviewNotePresenter implements PreviewNoteContract.Presenter {

    private PreviewNoteContract.View mPreviewNoteView;
    private NotesDataSource mDataSource;
    private String mNoteId;
    private Note mNote;
    private CompositeDisposable mCompositeDisposable;
    private boolean mIsDataMissing;

    public PreviewNotePresenter(String mNoteId, PreviewNoteContract.View view, NotesDataSource dataSource, boolean shouldLoadDataFromRepo) {
        this.mNoteId = mNoteId;
        this.mDataSource = dataSource;
        this.mPreviewNoteView = view;
        this.mCompositeDisposable = new CompositeDisposable();
        this.mIsDataMissing = shouldLoadDataFromRepo;
        mPreviewNoteView.setPresenter(this);
    }

    @Override
    public void subscribe() {
        if (isDataMissing()) {
            populateNote(mNoteId);
        }
    }

    @Override
    public void unSubscribe() {
        mCompositeDisposable.clear();
    }

    @Override
    public void populateNote(String noteId) {
        mCompositeDisposable.add(
                mDataSource.getNote(noteId)
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(new Function<Note, Note>() {
                            @Override
                            public Note apply(Note note) throws Exception {
                                note.setNoteReadCount(note.getNoteReadCount() + 1);
                                mDataSource.updateNote(note).subscribe();
                                return note;
                            }
                        })
                        .subscribe(item -> {
                            mIsDataMissing = false;
                            mNote = item;
                            mPreviewNoteView.showNoteInWebView(item);
                            mPreviewNoteView.showNoteTitle(item);
                        }, throwable -> {

                        }));

    }

    @Override
    public void refreshNote() {
        if (TextUtils.isEmpty(mNoteId)) {
            return;
        }
        mCompositeDisposable.add(
                mDataSource.getNote(mNoteId)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(item -> {
                            mIsDataMissing = false;
                            mNote = item;
                            mPreviewNoteView.showNoteInWebView(item);
                            mPreviewNoteView.showNoteTitle(item);
                        }, throwable -> {

                        }));
    }

    @Override
    public void editNote() {
        mPreviewNoteView.toEditNoteActivity(mNoteId);
    }

    @Override
    public void noteProperty() {

    }

    @Override
    public void share(int shareType) {

    }


    @Override
    public void deleteNote() {

    }

    @Override
    public boolean isDataMissing() {
        return mIsDataMissing;
    }

    @Override
    public void exit() {
        mPreviewNoteView.backToHomeActivity();
    }

    @Override
    public void initMarkdownWebView(WebView webView) {

        WebSettings webSettings = webView.getSettings();
//        String cacheDirPath = getFilesDir().getAbsolutePath() + APP_CACAHE_DIRNAME;
//        webSettings.setAppCachePath(cacheDirPath); //设置  Application Caches 缓存目录
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);

        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setDisplayZoomControls(false);

        webSettings.setTextZoom(200);

        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setAllowFileAccess(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setDefaultTextEncodingName("utf-8");

        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setAppCacheEnabled(true);

    }

    @Override
    public void result(int requestCode, int resultCode) {
        if (requestCode == PreviewNoteFragment.REQUEST_EDIT_NOTE) {
            if (resultCode == Activity.RESULT_OK) {
                refreshNote();
            }
        }
    }
}
