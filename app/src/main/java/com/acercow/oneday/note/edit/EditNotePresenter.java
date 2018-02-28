package com.acercow.oneday.note.edit;

import android.text.TextUtils;

import com.acercow.oneday.data.NotesDataSource;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by zhaosen on 2018/2/28.
 */

public class EditNotePresenter implements EditNoteContract.Presenter {
    private String mNoteId;
    private EditNoteContract.View mEditNoteView;
    private NotesDataSource mDataSource;
    private CompositeDisposable mCompositeDisposable;

    public EditNotePresenter(String noteId, EditNoteContract.View view, NotesDataSource dataSource) {
        this.mNoteId = noteId;
        this.mEditNoteView = view;
        this.mDataSource = dataSource;
        this.mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void subscribe() {
        if (isEditNote()) {
            populateNote(mNoteId);
        }
    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void search() {

    }

    @Override
    public void createNote() {

    }

    @Override
    public void share(String noteId) {

    }

    @Override
    public void preview() {

    }

    @Override
    public void populateNote(String noteId) {
        mCompositeDisposable.add(
                mDataSource.getNote(noteId)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(item -> {
                            if (item != null) {
                                mEditNoteView.showNoteInEditor(item);
                            } else {
                                mEditNoteView.showErr();
                            }
                        }));
    }


    @Override
        public void save(String title, String content, int weather, int color, int emotion) {

    }

    @Override
    public void discard() {

    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }

    @Override
    public void export() {

    }

    private boolean isEditNote() {
        return !TextUtils.isEmpty(mNoteId);
    }
}
