package com.acercow.oneday.data.repository.local;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.NonNull;

import com.acercow.oneday.data.AppDataBase;
import com.acercow.oneday.data.Note;
import com.acercow.oneday.data.NotesDataSource;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by zhaosen on 2018/2/3.
 */
public class NotesLocalDataSource implements NotesDataSource {

    private static NotesLocalDataSource INSTANCE = null;
    private AppDataBase mDatabase = null;
    private NotesLocalDataSource(Context context) {
        mDatabase = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "one-day")
        .build();
    }

    public static NotesLocalDataSource getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new NotesLocalDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public Flowable<List<Note>> getNotes() {
        return mDatabase.noteDao().queryAll();
    }

    @Override
    public Flowable<Note> getNote(@NonNull String id) {
        return mDatabase.noteDao().queryItemById(id);
    }

    @Override
    public void saveNote(@NonNull Note note) {
        mDatabase.noteDao().insertAll(note);
    }

    @Override
    public void updateNote(@NonNull Note note) {
        mDatabase.noteDao().updateNotes(note);
    }


    @Override
    public void deleteNotes(@NonNull Note... notes) {
        mDatabase.noteDao().deleteNotes(notes);
    }

    @Override
    public void deleteAll() {
        mDatabase.noteDao().deleteAll();
    }
}
