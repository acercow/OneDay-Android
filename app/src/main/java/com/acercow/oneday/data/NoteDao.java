package com.acercow.oneday.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by zhaosen on 2018/2/5.
 */

@Dao
public interface NoteDao {

    @Query("SELECT * FROM notes")
    Flowable<List<Note>> queryAll();

    @Query("SELECT * FROM notes WHERE note_guid = :id")
    Flowable<Note> queryItemById(String id);

    @Query("SELECT * FROM notes WHERE note_weather = :weather")
    Flowable<List<Note>> queryItemByWeather(int weather);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Note item);

    @Delete
    void deleteNotes(Note... items);

    @Query("DELETE FROM notes")
    void deleteAll();

    @Update
    void updateNotes(Note item);
}
