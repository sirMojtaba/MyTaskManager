package com.example.task.controller.activity;

import androidx.fragment.app.Fragment;

import com.example.task.controller.fragment.SignUpFragment;

public class SinUpActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return SignUpFragment.newInstance();
    }
}