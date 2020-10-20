package com.example.task.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.task.enums.TaskState;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity(tableName = "task")
public class Task implements Serializable {
    private String mTitle;
    private String mDescription;
    private TaskState mTaskState;
    @Ignore
    private Date mDate;
    private UUID mUserId;
    @PrimaryKey
    private int id;

    public UUID getUserId() {
        return mUserId;
    }

    public void setUserId(UUID userId) {
        mUserId = userId;
    }

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
        this.mDate = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Task(String title, String description, TaskState taskState, Date date, UUID userId) {
        mTitle = title;
        mDescription = description;
        mTaskState = taskState;
        mDate = date;
        mUserId = userId;
    }

    public Task() {
    }
}
