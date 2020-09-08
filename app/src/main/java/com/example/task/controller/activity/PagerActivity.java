package com.example.task.controller.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.task.R;
import com.example.task.controller.fragment.DoingFragment;
import com.example.task.controller.fragment.DoneFragment;
import com.example.task.controller.fragment.TodoFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class PagerActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager2 mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);
        findViews();
        FragmentStateAdapter adapter = new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return setTabFragment(position);
            }

            @Override
            public int getItemCount() {
                return 3;
            }
        };
        mViewPager.setAdapter(adapter);

        new TabLayoutMediator(mTabLayout, mViewPager,
                (mTabLayout, position) -> mTabLayout.setText(setTabText(position))).attach();
    }

    private Fragment setTabFragment(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new TodoFragment();
                break;
            case 1:
                fragment = new DoingFragment();
                break;
            case 2:
                fragment = new DoneFragment();
                break;
        }
        return fragment;
    }

    private void findViews() {
        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.view_pager);
    }

    private String setTabText(int position) {
        String title = null;
        switch (position) {
            case 0:
                title = "TODO";
                break;
            case 1:
                title = "Doing";
                break;
            case 2:
                title = "Done";
                break;
        }
        return title;
    }

}


