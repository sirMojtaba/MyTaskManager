package com.example.task.repository;

import com.example.task.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    private static TaskRepository sTaskRepository;

    public static TaskRepository getInstance(){
        if (sTaskRepository == null)
            sTaskRepository = new TaskRepository();
        return sTaskRepository;
    }

    private TaskRepository() {
        mTasks = new ArrayList<>();
        /*for (int i = 0; i < ; i++) {

        }*/
    }
    private List<Task> mTasks;

    public List<Task> getTasks() {
        return mTasks;
    }

    public void setTasks(List<Task> tasks) {
        mTasks = tasks;
    }
}
