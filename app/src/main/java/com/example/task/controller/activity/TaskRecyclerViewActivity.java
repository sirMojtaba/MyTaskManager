package com.example.task.controller.activity;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.example.task.controller.fragment.TaskRecyclerViewFragment;

public class TaskRecyclerViewActivity extends SingleFragmentActivity {
    public static Intent newIntent(Context context){
        Intent intent = new Intent(context, TaskRecyclerViewActivity.class);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        return TaskRecyclerViewFragment.newInstance();
    }

}