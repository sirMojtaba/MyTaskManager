package com.example.task.repository;

import com.example.task.model.User;

import org.xml.sax.helpers.XMLReaderAdapter;

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
        User admin = new User("admin", 1234);
        mUsers.add(admin);
    }

    private User mCurrentUser;
    List<User> mUsers = new ArrayList<>();


    public List<User> getUsers() {
        return mUsers;
    }

    public void setUsers(List<User> users) {
        mUsers = users;
    }

    public User getCurrentUser() {
        return mCurrentUser;
    }

    public void setCurrentUser(User currentUser) {
        mCurrentUser = currentUser;
    }

    public void addUser(User user){
        mUsers.add(user);
    }
}
