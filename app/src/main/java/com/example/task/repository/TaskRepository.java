package com.example.task.repository;

import android.content.Context;

import androidx.room.Room;

import com.example.task.database.AppDatabase;
import com.example.task.model.Task;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    private static TaskRepository sTaskRepository;
    private static Context mContext;

    public static TaskRepository getInstance(Context context) {
        mContext = context.getApplicationContext();
        if (sTaskRepository == null)
            sTaskRepository = new TaskRepository();
        return sTaskRepository;
    }

    AppDatabase db =
            Room.databaseBuilder(mContext, AppDatabase.class, "database-name")
                    .allowMainThreadQueries()
                    .build();


    private TaskRepository() {
    }

    public List<Task> getTaskList() {
        return db.appDao().getTaskList();
    }

    //create
    public void addTask(Task task) {
        db.appDao().insertTask(task);
    }

    //delete one task
    public void removeTask(Task task) {
        db.appDao().deleteTask(task);
    }

    public void updateTask(Task task) {
        db.appDao().updateTask(task);
    }

    public File getPhotoFile(Context context, Task task){
        return new File(context.getFilesDir(), task.getPhotoFileName());
    }
}
