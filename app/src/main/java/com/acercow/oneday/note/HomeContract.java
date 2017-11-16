package com.acercow.oneday.note;

import android.net.Uri;

import com.acercow.oneday.BasePresenter;
import com.acercow.oneday.BaseView;

/**
 * Created by jansen on 2017/11/16.
 */

public interface HomeContract {

    interface View extends BaseView<Presenter> {
        void showSearch();
        void showProfile();
        void showCalendar();

        void inputTextMode();
        void choosePhotoMode();
        void recorderMode();
        void scheduleMode();

    }

    interface Presenter extends BasePresenter {
        void search();
        void profile();
        void calendar();
        void sendTextNote(String str);
        void sendPhoto(Uri uri);
        void sendSound(Uri uri);

    }

}
