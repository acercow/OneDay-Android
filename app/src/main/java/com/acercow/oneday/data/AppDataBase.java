package com.acercow.oneday.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by zhaosen on 2018/2/5.
 */

@Database(entities = {Note.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
   public abstract NoteDao noteDao();
}
