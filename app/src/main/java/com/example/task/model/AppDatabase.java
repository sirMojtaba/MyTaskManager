package com.example.task.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {User.class, Task.class}, exportSchema = false, version = 1)
@TypeConverters({DBConverter.DateConverter.class, DBConverter.EnumConverter.class, DBConverter.UUIDConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    private static final String DB_NAME = "user_db";
    private static AppDatabase instance;

    public abstract DBDao userDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DB_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }


}
