package com.example.task.database;

import android.content.Context;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.task.model.Task;
import com.example.task.model.User;

import java.io.File;
import java.util.List;

@Dao
public interface AppDao {

    @Query("Select * from user")
    List<User> getUserList();

    @Insert(entity = User.class)
    void insertUser(User user);

    @Update(entity = User.class)
    void updateUser(User user);

    @Delete(entity = User.class)
    void deleteUser(User user);

    @Query("Select * from task")
    List<Task> getTaskList();

    @Insert(entity = Task.class)
    void insertTask(Task task);

    @Update(entity = Task.class)
    void updateTask(Task task);
//    File getPhotoFile(Context context, Task task);

    @Delete(entity = Task.class)
    void deleteTask(Task task);

}
