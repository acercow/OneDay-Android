package com.acercow.oneday.note.edit;

import com.acercow.oneday.BasePresenter;
import com.acercow.oneday.BaseView;
import com.acercow.oneday.data.Note;

/**
 * Created by zhaosen on 2018/2/28.
 */

public interface EditNoteContract {

    interface Presenter extends BasePresenter {
        void search();
        void createNote();
        void share();
        void preview();
        void info();
        void populateNote(String noteId);
        boolean isDataMissing();
        void save(String title, String content, int weather, int color, int emotion);
        void discard();
        void undo();
        void redo();
        void export();
    }

    interface View extends BaseView<Presenter> {
        void showNoteInEditor(Note note);
        void toEditNoteActivity();
        void toPreviewNoteActivity();
        void toHomeActivity();
        void showSaveStatus();
        void showDiscardConfirmAlert();
        void highlightKeyword(String key);
        void nextKeyword();
        void previousKeyword();
        void showShareDialog();
        void showEmptyError();
        void showInfoDialog();
        void showExportMenu();
    }
}
