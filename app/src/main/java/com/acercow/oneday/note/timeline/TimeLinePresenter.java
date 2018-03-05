package com.acercow.oneday.note.timeline;

import android.content.Context;

import com.acercow.oneday.data.Injection;
import com.acercow.oneday.data.Note;
import com.acercow.oneday.data.NotesDataSource;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by zhaosen on 2018/2/27.
 */

public class TimeLinePresenter implements TimelineContract.Presenter {
    TimelineContract.View view;
    NotesDataSource notesDataSource;
    List<Note> mNotes;

    public TimeLinePresenter(TimelineContract.View view, Context context) {
        this.view = view;
        notesDataSource = Injection.getNotesDataRepository(context);
    }

    @Override
    public void subscribe() {
      listNotes();
    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void addNote() {
        view.toEditNoteActivity(null);
    }

    @Override
    public void previewNote(String noteId) {
        view.toPreviewNoteActivity(noteId);
    }

    @Override
    public void deleteNotes(Note... notes) {
        notesDataSource.deleteNotes(notes);
    }

    @Override
    public void listNotes() {
        notesDataSource.getNotes()
                .observeOn(AndroidSchedulers.mainThread())
//                .doOnNext(items -> Collections.sort(items, (o1, o2) -> o1.getNoteDate().compareTo(o2.getNoteDate())))
                .subscribe(items -> {
                    if (items == null || items.size() == 0) {
                        view.showEmptyView();
                    } else {
                        mNotes = items;
                        view.showNotes(mNotes);
                    }
                });
    }

    @Override
    public void result(int requestCode, int resultCode) {
    }
}
