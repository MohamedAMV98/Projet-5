package com.cleanup.todoc.base;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Task;

@Database(entities = {Task.class}, version = 1, exportSchema = false)
public abstract class TodocDatabase extends RoomDatabase {

    // SINGLETON
    private static volatile TodocDatabase INSTANCE;

    // DAO
    public abstract TaskDao mTaskDao();

    // INSTANCE
    public static synchronized TodocDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    TodocDatabase.class, "MyDatabase.db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}
