package com.acercow.oneday.note.timeline;

import com.acercow.oneday.BasePresenter;
import com.acercow.oneday.BaseView;
import com.acercow.oneday.data.Note;

import java.util.List;

/**
 * Created by zhaosen on 2018/2/9.
 */

public interface TimelineContract {

    interface View extends BaseView<Presenter> {
        void showNotes(List<Note> notes);
        void showEmptyView();
        void showNoteDetail(Note note);
        void showNoteDeleted();
        void showNoteSaved();

    }

    interface Presenter extends BasePresenter {
        void addNote();
        void editNote(Note note);
        void deleteNotes(Note... notes);
        void listNotes();
        void result(int requestCode, int resultCode);
    }
}
