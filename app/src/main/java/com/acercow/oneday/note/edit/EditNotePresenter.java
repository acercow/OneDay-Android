package com.acercow.oneday.note.edit;

import android.text.TextUtils;

import com.acercow.oneday.bean.DocumentType;
import com.acercow.oneday.bean.SyncStatus;
import com.acercow.oneday.data.Note;
import com.acercow.oneday.data.NotesDataSource;
import com.acercow.oneday.utils.DateUtils;

import java.util.UUID;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by zhaosen on 2018/2/28.
 */

public class EditNotePresenter implements EditNoteContract.Presenter {
    private String mNoteId;
    private Note mNote;
    private boolean mIsDataMissing;
    private EditNoteContract.View mEditNoteView;
    private NotesDataSource mDataSource;
    private CompositeDisposable mCompositeDisposable;

    public EditNotePresenter(String noteId, EditNoteContract.View view, NotesDataSource dataSource, boolean shouldLoadDataFromRepo) {
        this.mNoteId = noteId;
        this.mEditNoteView = view;
        this.mDataSource = dataSource;
        this.mIsDataMissing = shouldLoadDataFromRepo;
        this.mCompositeDisposable = new CompositeDisposable();
        mEditNoteView.setPresenter(this);
    }

    @Override
    public void subscribe() {
        if (mIsDataMissing) {
            populateNote(mNoteId);
        }
    }

    @Override
    public void unSubscribe() {
        mCompositeDisposable.clear();
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
                                mIsDataMissing = false;
                                mNote = item;
                                mEditNoteView.showNoteInEditor(item);
                            } else {
                                mEditNoteView.showEmptyError();
                            }
                        }, throwable -> mEditNoteView.showEmptyError()));
    }

    @Override
    public boolean isDataMissing() {
        return mIsDataMissing;
    }


    @Override
    public void save(String title, String content, int weather, int color, int emotion) {
        if (TextUtils.isEmpty(title)) {
            title = "未标题";
        }
        String date = DateUtils.getFormatDate();
        if (mNote == null) { // 首次创建
            mNote = new Note();
            mNoteId = UUID.randomUUID().toString();
            mNote.setNoteGUID(mNoteId);
            mNote.setNoteReadCount(0);
            mNote.setCreatedDate(date);
            mNote.setNoteDate(date);
            mNote.setSyncStatus(SyncStatus.LOCAL_ADDED);
            mNote.setNoteType(DocumentType.NOTE);
        } else {
            mNote.setSyncStatus(SyncStatus.LOCAL_MODIFIED);
        }
        mNote.setNoteTitle(title.trim());
        mNote.setNoteContent(content.trim());
        mNote.setNoteWeather(weather == -1 ? 0 : weather);
        mNote.setNoteColor(color == -1 ? 0 : color);
        mNote.setNoteEmotion(emotion == -1 ? 0 : emotion);

        // TODO 日记日期
        mNote.setModifiedDate(date);
        mNote.setNoteAbstract(content.length() > 20 ? content.substring(0, 20) : content);

        mDataSource.saveNote(mNote);
    }

    @Override
    public void discard() {
        mEditNoteView.toPreviewNoteActivity();
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
