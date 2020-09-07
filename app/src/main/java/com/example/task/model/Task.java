package com.example.task.model;

import com.example.task.enums.TaskState;

import java.util.Random;

public class Task {
    private String mName;
    private TaskState mTaskState;


    public TaskState getTaskState() {
        return mTaskState;
    }

    public void setTaskState(TaskState taskState) {
        mTaskState = taskState;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Task(String name) {
        mName = name;
    }

    public Task(String name, TaskState taskState) {
        mName = name;
        mTaskState = taskState;
    }

    public Task() {
    }
}
