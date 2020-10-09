package com.example.task.model;

public class User {
    private String mUserName;
    private int mPassword;

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

    public User(String userName, int password) {
        mUserName = userName;
        mPassword = password;
    }

    public User(String userName) {
        mUserName = userName;
    }
}
