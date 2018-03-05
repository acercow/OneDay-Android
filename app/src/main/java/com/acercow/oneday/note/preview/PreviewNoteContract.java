package com.acercow.oneday.note.preview;

import android.webkit.WebView;

import com.acercow.oneday.BasePresenter;
import com.acercow.oneday.BaseView;
import com.acercow.oneday.data.Note;

/**
 * Preview Note Contract
 * Created by zhaosen on 2018/3/2.
 */

public class PreviewNoteContract {

    interface Presenter extends BasePresenter {
        void populateNote(String noteId);
        void refreshNote();
        void editNote();
        void noteProperty();
        void share(int shareType);
        void deleteNote();
        boolean isDataMissing();
        void exit();
        void initMarkdownWebView(WebView webView);
        void result(int requestCode, int resultCode);
    }


    interface View extends BaseView<Presenter> {
        void showNoteInWebView(Note note);
        void showNoteTitle(Note note);
        void toEditNoteActivity(String noteId);
        void showDetailDialog(Note note);
        void showShareDialog();
        void showIdErrorDialog();
        void backToHomeActivity();
    }

}
