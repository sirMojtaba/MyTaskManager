package com.example.task.repository;

import com.example.task.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    private static TaskRepository sTaskRepository;

    public static TaskRepository getInstance(String name, int number) {
        if (sTaskRepository == null)
            sTaskRepository = new TaskRepository(name, number);

        return sTaskRepository;
    }

    private List<Task> mTasks = new ArrayList<>();

    private TaskRepository(String name, int number) {
        for (int i = 0; i < number; i++) {
            Task task = new Task(name + " " + (i + 1));
            mTasks.add(task);
        }
    }


    public List<Task> getTasks() {
        return mTasks;
    }

    public void setTasks(List<Task> tasks) {
        mTasks = tasks;
    }
}
