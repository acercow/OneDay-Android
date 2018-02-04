package com.acercow.oneday.data.repository.local;

import android.provider.BaseColumns;

/**
 * Created by Administrator on 2018/2/1.
 */

public final class NotesPersistenceContract  {

    private NotesPersistenceContract() {
    }

    public static abstract class NoteEntry implements BaseColumns {
        public static final String TABLE_NAME = "notes";
        public static final String COLUNM_NAME_ENTRY_ID = "entryid";
        public static final String COlUNM_NAME_WEATHER = "emotion";
        public static final String COlUNM_NAME_EMOTION = "emotion";
        public static final String COlUNM_NAME_COLOR = "color";
        public static final String COlUNM_NAME_DATE = "date";
        public static final String COlUNM_NAME_LOCATION = "location";
        public static final String COlUNM_NAME_AUTHOR = "author";
        public static final String COlUNM_NAME_TITLE = "title";
        public static final String COlUNM_NAME_CONTENT = "content";
    }


}
