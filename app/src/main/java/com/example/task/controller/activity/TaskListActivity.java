package com.example.task.controller.activity;

import androidx.fragment.app.Fragment;

import com.example.task.controller.fragment.TaskListFragment;

public class TaskListActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return new TaskListFragment();
    }

}