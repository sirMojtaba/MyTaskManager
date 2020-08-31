package com.example.task.controller.activity;

import androidx.fragment.app.Fragment;

import com.example.task.controller.fragment.TaskFragment;

public class TaskActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return new TaskFragment();
    }

}