package com.acercow.oneday.data.repository.remote;

import android.support.annotation.NonNull;

import com.acercow.oneday.data.Note;
import com.acercow.oneday.data.NotesDataSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;

/**
 * Created by zhaosen on 2018/2/5.
 */

public class NotesRemoteDataSource implements NotesDataSource {
    private static NotesRemoteDataSource INSTANCE;
    private static final int SERVICE_LATENCY_IN_MILLIS = 1000;
    private Map<String, Note> FAKE_REMOTE_DATA = null;


    private NotesRemoteDataSource() {
        FAKE_REMOTE_DATA = new HashMap<>();
        FAKE_REMOTE_DATA.put("00000000", new Note("00000000", 1, 1, 1, 0, 1, "tester", "test title", "test content"));
    }

    public static NotesRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NotesRemoteDataSource();
        }
        return INSTANCE;
    }


    @Override
    public Flowable<List<Note>> getNotes() {
        return Flowable.fromIterable(FAKE_REMOTE_DATA.values())
                .delay(SERVICE_LATENCY_IN_MILLIS, TimeUnit.MILLISECONDS)
                .toList()
                .toFlowable();
    }

    @Override
    public Flowable<Note> getNote(@NonNull String id) {
        Note note = FAKE_REMOTE_DATA.get(id);
        if (note != null) {
            return Flowable.just(FAKE_REMOTE_DATA.get(id))
                    .delay(SERVICE_LATENCY_IN_MILLIS, TimeUnit.MILLISECONDS);
        } else {
            return Flowable.empty();
        }
    }

    @Override
    public void saveNote(@NonNull Note note) {
        FAKE_REMOTE_DATA.put(note.getId(), note);
    }

    @Override
    public void updateNote(@NonNull Note note) {
        FAKE_REMOTE_DATA.put(note.getId(), note);
    }

    @Override
    public void deleteNotes(@NonNull Note... notes) {
        for (Note note : notes) {
            if (FAKE_REMOTE_DATA.containsKey(note.getId())) {
                FAKE_REMOTE_DATA.remove(note.getId());
            }
        }
    }

    @Override
    public void deleteAll() {
        FAKE_REMOTE_DATA.clear();
    }
}
