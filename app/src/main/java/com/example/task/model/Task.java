package com.example.task.model;

import com.example.task.enums.TaskState;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {
    private String mTitle;
    private String mDescription;
    private TaskState mTaskState;
    private Date mDate = new Date();



    public TaskState getTaskState() {
        return mTaskState;
    }

    public void setTaskState(TaskState taskState) {
        mTaskState = taskState;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Task(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }


    public Task(String title, String description, TaskState taskState, Date date) {
        mTitle = title;
        mDescription = description;
        mTaskState = taskState;
        mDate = date;
    }

    public Task(String title, TaskState taskState) {
        mTitle = title;
        mTaskState = taskState;
    }

    public Task() {
    }
}
