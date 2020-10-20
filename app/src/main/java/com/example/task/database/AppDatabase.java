package com.example.task.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.task.model.Task;
import com.example.task.model.User;

@TypeConverters({Converters.class})
@Database(entities = {User.class, Task.class}, exportSchema = false, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DB_NAME = "app_db";
    private static AppDatabase instance;

    public abstract AppDao appDao();

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DB_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
