package com.example.task.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.task.controller.fragment.TaskRecyclerViewFragment;
import com.example.task.enums.TaskState;

public class TaskViewPagerAdapter extends FragmentStateAdapter {


    public TaskViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);

    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0)
            return TaskRecyclerViewFragment.newInstance(TaskState.TODO);
        else if (position == 1)
            return TaskRecyclerViewFragment.newInstance(TaskState.DOING);
        else
            return TaskRecyclerViewFragment.newInstance(TaskState.DONE);
    }

    @Override
    public int getItemCount() {
        return 3;
    }

}
