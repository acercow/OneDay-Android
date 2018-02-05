package com.acercow.oneday.data;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by zhaosen on 2018/2/1.
 */

public interface NotesDataSource {
    Flowable<List<Note>> getNotes();
    Flowable<Note> getNote(@NonNull String id);

    void saveNote(@NonNull Note note);
    void updateNote(@NonNull Note note);
    void deleteNotes(@NonNull Note... notes);
    void deleteAll();
}
