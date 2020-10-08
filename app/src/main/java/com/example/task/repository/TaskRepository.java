package com.example.task.repository;

import com.example.task.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    private static TaskRepository sTaskRepository;

    public static TaskRepository getInstance() {
        if (sTaskRepository == null)
            sTaskRepository = new TaskRepository();

        return sTaskRepository;
    }

    private List<Task> mTasks = new ArrayList<>();

    private TaskRepository() {
    }

    public List<Task> getTasks() {
        return mTasks;
    }

    public void setTasks(List<Task> tasks) {
        mTasks = tasks;
    }

    //create
    public void addTask(Task task){
        mTasks.add(task);
    }

    public void removeTask(Task task){
        mTasks.remove(task);
    }
}
