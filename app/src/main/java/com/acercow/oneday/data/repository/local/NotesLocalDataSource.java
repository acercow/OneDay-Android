package com.acercow.oneday.data.repository.local;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.NonNull;

import com.acercow.oneday.data.AppDataBase;
import com.acercow.oneday.data.Note;
import com.acercow.oneday.data.NotesDataSource;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zhaosen on 2018/2/3.
 */
public class NotesLocalDataSource implements NotesDataSource {

    private static NotesLocalDataSource INSTANCE = null;
    private AppDataBase mDatabase = null;

    private NotesLocalDataSource(Context context) {
        Completable.fromAction(() ->
                mDatabase = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "one-day").build())
                .subscribeOn(Schedulers.io())
                .subscribe();
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
        Completable.fromAction(() -> mDatabase.noteDao().insertNote(note))
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    @Override
    public void updateNote(@NonNull Note note) {
        Completable.fromAction(() -> mDatabase.noteDao().updateNote(note))
                .subscribeOn(Schedulers.io())
                .subscribe();
    }


    @Override
    public void deleteNotes(@NonNull Note... notes) {
        Completable.fromAction(() -> mDatabase.noteDao().deleteNotes(notes))
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    @Override
    public void deleteAll() {
        Completable.fromAction(() -> mDatabase.noteDao().deleteAll())
                .subscribeOn(Schedulers.io())
                .subscribe();
    }
}
