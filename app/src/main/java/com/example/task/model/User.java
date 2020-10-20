package com.example.task.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.UUID;

@Entity(tableName = "user")
public class User implements Serializable {

    private String mUserName;
    private int mPassword;
    private UUID mUserId;
    @PrimaryKey
    private int id;

    public UUID getUserId() {
        return mUserId;
    }

    public void setUserId(UUID userId) {
        mUserId = userId;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public int getPassword() {
        return mPassword;
    }

    public void setPassword(int password) {
        mPassword = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User(String userName, int password) {
        mUserName = userName;
        mPassword = password;
        mUserId = UUID.randomUUID();
    }
}
