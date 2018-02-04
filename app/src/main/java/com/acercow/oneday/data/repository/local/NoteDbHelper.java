package com.acercow.oneday.data.repository.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zhaosen on 2018/2/1.
 */

public class NoteDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "oneday.db";
    public static final String TEXT_TYPE = " TEXT";
    public static final String BOOLEAN_TYPE = " INTEGER";
    public static final String INTEGER_TYPE = " INTEGER";
    public static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + NotesPersistenceContract.NoteEntry.TABLE_NAME + " ("
            + NotesPersistenceContract.NoteEntry.COLUNM_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP
            + NotesPersistenceContract.NoteEntry.COlUNM_NAME_AUTHOR + TEXT_TYPE + COMMA_SEP
            + NotesPersistenceContract.NoteEntry.COlUNM_NAME_TITLE + TEXT_TYPE + COMMA_SEP
            + NotesPersistenceContract.NoteEntry.COlUNM_NAME_CONTENT + TEXT_TYPE + COMMA_SEP
            + NotesPersistenceContract.NoteEntry.COlUNM_NAME_COLOR + INTEGER_TYPE + COMMA_SEP
            + NotesPersistenceContract.NoteEntry.COlUNM_NAME_DATE + INTEGER_TYPE + COMMA_SEP
            + NotesPersistenceContract.NoteEntry.COlUNM_NAME_EMOTION + INTEGER_TYPE + COMMA_SEP
            + NotesPersistenceContract.NoteEntry.COlUNM_NAME_WEATHER + INTEGER_TYPE + COMMA_SEP
            + NotesPersistenceContract.NoteEntry.COlUNM_NAME_LOCATION + INTEGER_TYPE + ")";


    public NoteDbHelper(Context context) {
        super(context, "oneday.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
