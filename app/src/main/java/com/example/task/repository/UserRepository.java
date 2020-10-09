package com.example.task.repository;

import com.example.task.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static UserRepository sUserRepository;

    public static UserRepository getInstance() {
        if (sUserRepository == null)
            sUserRepository = new UserRepository();
        return sUserRepository;
    }

    private UserRepository() {
    }

    List<User> mUsers = new ArrayList<>();

    public List<User> getUsers() {
        return mUsers;
    }

    public void setUsers(List<User> users) {
        mUsers = users;
    }

    public void addUser(User user){
        mUsers.add(user);
    }
}
