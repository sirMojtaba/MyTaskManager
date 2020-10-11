package com.example.task.model;

import java.util.UUID;

public class User {
    private String mUserName;
    private int mPassword;
    private UUID mUserId;


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

    public User(String userName) {
        mUserName = userName;
        mUserId = UUID.randomUUID();
    }

    public User(String userName, int password) {
        mUserName = userName;
        mPassword = password;
        mUserId = UUID.randomUUID();
    }
}
