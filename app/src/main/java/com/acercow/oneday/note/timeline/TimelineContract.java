package com.acercow.oneday.note.timeline;

import com.acercow.oneday.BasePresenter;
import com.acercow.oneday.BaseView;
import com.acercow.oneday.data.Note;

import java.util.List;

/**
 * Created by zhaosen on 2018/2/9.
 */

public interface TimelineContract {

    interface Presenter extends BasePresenter {
        void addNote();
        void previewNote(String noteId);
        void deleteNotes(Note... notes);
        void listNotes();
        void result(int requestCode, int resultCode);
    }

    interface View extends BaseView<Presenter> {
        void showNotes(List<Note> notes);
        void showEmptyView();
        void toEditNoteActivity(String noteId);
        void toPreviewNoteActivity(String noteId);
        void showNoteDeleted();
        void showNoteSaved();

    }

}
