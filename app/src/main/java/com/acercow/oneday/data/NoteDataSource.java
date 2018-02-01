package com.acercow.oneday.data;

import android.support.annotation.NonNull;

import java.util.List;
import java.util.Optional;

import io.reactivex.Flowable;

/**
 * Created by zhaosen on 2018/2/1.
 */

public interface NoteDataSource {
    Flowable<List<NoteBean>> getNotes();
    Flowable<Optional<NoteBean>> getNote(@NonNull long id);

    void saveNote(@NonNull NoteBean noteBean);
    void deleteNote(@NonNull long noteId);
    void deleteAll();
}
