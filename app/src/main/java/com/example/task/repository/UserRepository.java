package com.example.task.repository;

import android.content.Context;

import androidx.room.Room;

import com.example.task.database.AppDatabase;
import com.example.task.model.User;

import java.util.List;

public class UserRepository {
    private static UserRepository sUserRepository;
    private static Context mContext;
    private User mCurrentUser;

    public static UserRepository getInstance(Context context) {
        mContext = context.getApplicationContext();
        if (sUserRepository == null)
            sUserRepository = new UserRepository();
        return sUserRepository;
    }

    AppDatabase db =
            Room.databaseBuilder(mContext, AppDatabase.class, "database-name")
                    .allowMainThreadQueries()
                    .build();


    private UserRepository() {
        if (getUserList().size() == 0) {
            User admin = new User("admin", 1234);
            db.appDao().insertUser(admin);
        }
    }


    public List<User> getUserList() {
        return db.appDao().getUserList();
    }

    public void addUser(User user) {
        db.appDao().insertUser(user);
    }

    public User getCurrentUser() {
        return mCurrentUser;
    }

    public void setCurrentUser(User currentUser) {
        mCurrentUser = currentUser;
    }

}
