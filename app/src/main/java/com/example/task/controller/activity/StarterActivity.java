package com.example.task.controller.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.example.task.controller.fragment.LoginFragment;

public class StarterActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return LoginFragment.newInstance();
    }
}