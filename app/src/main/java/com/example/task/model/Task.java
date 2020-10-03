package com.example.task.model;

import com.example.task.enums.TaskState;

import java.sql.Time;
import java.util.Date;

public class Task {
    private String mTitle;
    private String mDescription;
    private TaskState mTaskState;
    private Date mDate;
    private Time mTime;



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

    public Time getTime() {
        return mTime;
    }

    public void setTime(Time time) {
        mTime = time;
    }

    public Task(String title, String description, TaskState taskState, Date date, Time time) {
        mTitle = title;
        mDescription = description;
        mTaskState = taskState;
        mDate = date;
        mTime = time;
    }

    public Task(String title, TaskState taskState) {
        mTitle = title;
        mTaskState = taskState;
    }

    public Task() {
    }
}
