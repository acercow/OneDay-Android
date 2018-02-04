package com.acercow.oneday.data;

import android.support.annotation.NonNull;

import java.util.List;
import java.util.Optional;

import io.reactivex.Flowable;

/**
 * Created by zhaosen on 2018/2/1.
 */

public interface NoteDataSource {
    Flowable<List<Note>> getNotes();
    Flowable<Optional<Note>> getNote(@NonNull long id);

    void saveNote(@NonNull Note note);
    void deleteNote(@NonNull long noteId);
    void deleteAll();
}
