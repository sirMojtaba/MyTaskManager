package com.example.task.controller.activity;

import androidx.fragment.app.Fragment;

import com.example.task.controller.fragment.StarterFragment;

public class StarterActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return new StarterFragment();
    }
}