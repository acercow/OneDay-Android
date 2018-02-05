package com.acercow.oneday.data.repository.remote;

import android.support.annotation.NonNull;

import com.acercow.oneday.data.Note;
import com.acercow.oneday.data.NotesDataSource;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by zhaosen on 2018/2/5.
 */

public class NotesRemoteDataSource implements NotesDataSource {
    @Override
    public Flowable<List<Note>> getNotes() {
        return null;
    }

    @Override
    public Flowable<Note> getNote(@NonNull String id) {
        return null;
    }

    @Override
    public void saveNote(@NonNull Note note) {

    }

    @Override
    public void updateNote(@NonNull Note note) {

    }

    @Override
    public void deleteNotes(@NonNull Note... notes) {

    }

    @Override
    public void deleteAll() {

    }
}
