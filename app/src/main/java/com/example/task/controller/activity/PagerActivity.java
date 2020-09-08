package com.example.task.controller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.task.R;
import com.example.task.adapter.TaskViewPagerAdapter;
import com.example.task.controller.fragment.TaskFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class PagerActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager2 mViewPager;
    private TaskViewPagerAdapter mTaskViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);

        findViews();

        mTaskViewPagerAdapter = new TaskViewPagerAdapter(this);
        mViewPager.setAdapter(mTaskViewPagerAdapter);

        new TabLayoutMediator(mTabLayout, mViewPager,
                (mTabLayout, position) -> mTabLayout.setText(setTabText(position))).attach();
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

    private void updateRecyclerView() {
        for (int i = 0; i < getSupportFragmentManager().getFragments().size(); i++) {
            if (getSupportFragmentManager().getFragments().get(i) instanceof TaskFragment)
                ((TaskFragment) getSupportFragmentManager().getFragments().get(i)).updateUI();

        }
    }

}


