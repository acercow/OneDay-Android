package com.acercow.oneday.data;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

/**
 * Created by zhaosen on 2018/2/1.
 */

public interface NotesDataSource {
    Flowable<List<Note>> getNotes();
    Flowable<Note> getNote(@NonNull String id);

    Completable saveNote(@NonNull Note note);
    Completable updateNote(@NonNull Note note);
    Completable deleteNotes(@NonNull Note... notes);
    Completable deleteAll();
}
