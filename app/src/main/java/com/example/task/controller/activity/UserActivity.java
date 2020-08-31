package com.example.task.controller.activity;

import androidx.fragment.app.Fragment;

import com.example.task.controller.fragment.UserFragment;

public class UserActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return new UserFragment();
    }
}