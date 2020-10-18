package com.example.task.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DBDao {

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

    @Delete(entity = Task.class)
    void deleteTask(Task task);

}
