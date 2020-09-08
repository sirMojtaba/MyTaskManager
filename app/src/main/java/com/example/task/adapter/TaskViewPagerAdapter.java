package com.example.task.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.task.controller.fragment.DoingFragment;
import com.example.task.controller.fragment.DoneFragment;
import com.example.task.controller.fragment.TodoFragment;

public class TaskViewPagerAdapter extends FragmentStateAdapter {


    public TaskViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return setTabFragment(position);
    }

    @Override
    public int getItemCount() {
        return 3;
    }
    private Fragment setTabFragment(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = TodoFragment.newInstance();
                break;
            case 1:
                fragment = DoingFragment.newInstance();
                break;
            case 2:
                fragment = DoneFragment.newInstance();
                break;
        }
        return fragment;
    }
}
