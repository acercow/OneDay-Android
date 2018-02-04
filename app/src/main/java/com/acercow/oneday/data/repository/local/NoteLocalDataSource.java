package com.acercow.oneday.data.repository.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import android.content.Context;
import android.support.annotation.NonNull;

import com.acercow.oneday.data.Note;
import com.acercow.oneday.data.NoteDataSource;
import com.squareup.sqlbrite3.SqlBrite;

import java.util.List;
import java.util.Optional;

import io.reactivex.Flowable;

/**
 * Created by zhaosen on 2018/2/3.
 */
@Dao
public class NoteLocalDataSource implements NoteDataSource {


    public NoteLocalDataSource(Context context) {
        NoteDbHelper dbHelper = new NoteDbHelper(context);
        SqlBrite sqlBrite = new SqlBrite.Builder().build();
//        db = sqlBrite.wrapDatabaseHelper(dbHelper, Schedulers.io());
    }

    @Override
    @Query("SELECT * FROM note")
    public Flowable<List<Note>> getNotes() {
        return null;
    }

    @Override
    @Query("SELECT * FROM note WHERE id=:id")
    public Flowable<Optional<Note>> getNote(@NonNull long id) {
        return null;
    }

    @Override
    @Query("")
    public void saveNote(@NonNull Note note) {

    }

    @Override
    public void deleteNote(@NonNull long noteId) {

    }

    @Override
    public void deleteAll() {

    }
}
