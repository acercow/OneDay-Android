package com.acercow.oneday.data;

import android.content.Context;

import com.acercow.oneday.data.repository.NotesRepository;
import com.acercow.oneday.data.repository.local.NotesLocalDataSource;
import com.acercow.oneday.data.repository.remote.NotesRemoteDataSource;

/**
 * Created by zhaosen on 2018/2/9.
 */

public class Injection {
    public static NotesRepository getNotesDataRepository(Context context) {
        return NotesRepository.getInstance(NotesLocalDataSource.getInstance(context), NotesRemoteDataSource.getInstance());
    }
}
