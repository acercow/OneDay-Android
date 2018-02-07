package com.acercow.oneday.data.repository;

import android.support.annotation.NonNull;
import android.util.LruCache;

import com.acercow.oneday.data.Note;
import com.acercow.oneday.data.NotesDataSource;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by zhaosen on 2018/2/5.
 */

// 服务器待开发 等我
public class NotesRepository implements NotesDataSource {

    private static final int LRUCACHE_SIZE = 30;

    private static NotesRepository INSTANCE = null;
    private NotesDataSource mNotesLocalDataSource = null;
    private NotesDataSource mNotesRemoteDataSource = null;
    private LruCache<String, Note> mCachedNotes = null;

    private NotesRepository(NotesDataSource notesLocalDataSource, NotesDataSource notesRemoteDataSource) {
        mNotesLocalDataSource = notesLocalDataSource;
        mNotesRemoteDataSource = notesRemoteDataSource;
    }

    public static NotesRepository getInstance(NotesDataSource notesLocalDataSource, NotesDataSource notesRemoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new NotesRepository(notesLocalDataSource, notesRemoteDataSource);
        }
        return INSTANCE;
    }


    @Override
    public Flowable<List<Note>> getNotes() {
        if (mCachedNotes == null) {
            mCachedNotes = new LruCache<>(LRUCACHE_SIZE);
        }
        return mNotesLocalDataSource.getNotes();
    }

    @Override
    public Flowable<Note> getNote(@NonNull String id) {
        Note note = null;
        if (mCachedNotes != null) {
            note = getNoteWithId(id);
        }
        if (note == null) {
            Flowable<Note> local = getNoteWithIdFromLocalAndCache(id);
            Flowable<Note> remote = getNoteWithIdFromRemoteAndCache(id);
            return Flowable.concat(local, remote)
                    .firstElement()
                    .toFlowable();
        } else {
            return Flowable.just(note);
        }
    }

    @Override
    public void saveNote(@NonNull Note note) {
        mNotesLocalDataSource.saveNote(note);
        mNotesLocalDataSource.saveNote(note);
        if (mCachedNotes == null) {
            mCachedNotes = new LruCache<>(LRUCACHE_SIZE);
        }
        mCachedNotes.put(note.getId(), note);
    }

    @Override
    public void updateNote(@NonNull Note note) {
        mNotesLocalDataSource.updateNote(note);
        mNotesRemoteDataSource.updateNote(note);
        if (mCachedNotes == null) {
            mCachedNotes = new LruCache<>(LRUCACHE_SIZE);
        }
        mCachedNotes.put(note.getId(), note);
    }

    @Override
    public void deleteNotes(@NonNull Note... notes) {
        mNotesLocalDataSource.deleteNotes(notes);
        mNotesRemoteDataSource.deleteNotes(notes);
        if (mCachedNotes == null) {
            mCachedNotes = new LruCache<>(LRUCACHE_SIZE);
        }
        for (Note note : notes) {
            mCachedNotes.remove(note.getId());
        }
    }

    @Override
    public void deleteAll() {
        mNotesLocalDataSource.deleteAll();
        mNotesRemoteDataSource.deleteAll();
        if (mCachedNotes == null) {
            mCachedNotes = new LruCache<>(LRUCACHE_SIZE);
        }
        mCachedNotes.evictAll();
    }

    private Note getNoteWithId(String id) {
        if (mCachedNotes != null) {
            return mCachedNotes.get(id);
        } else {
            return null;
        }
    }

    private Flowable<Note> getNoteWithIdFromLocalAndCache(String id) {
        return mNotesLocalDataSource.getNote(id)
                .doOnNext(v -> mCachedNotes.put(v.getId(), v));
    }

    private Flowable<Note> getNoteWithIdFromRemoteAndCache(String id) {
        return mNotesRemoteDataSource.getNote(id)
                .doOnNext(v -> {
                    mCachedNotes.put(v.getId(), v);
                    mNotesLocalDataSource.saveNote(v);
                });
    }
}
