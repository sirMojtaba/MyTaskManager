package com.example.task.controller.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.task.R;
import com.example.task.adapter.TaskViewPagerAdapter;
import com.example.task.controller.fragment.LoginFragment;
import com.example.task.controller.fragment.NewTaskDialogFragment;
import com.example.task.controller.fragment.TaskDetailDialogFragment;
import com.example.task.controller.fragment.TaskRecyclerViewFragment;
import com.example.task.controller.fragment.WarningDialogFragment;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class PagerActivity extends AppCompatActivity implements NewTaskDialogFragment.OnNewTaskListener,
        TaskDetailDialogFragment.TaskDetailInterface, WarningDialogFragment.WarningInterface {
    public static final String NEW_TASK_DIALOG_FRAGMENT = "task dialog fragment";
    public static final String TASK_DETAIL_DIALOG_FRAGMENT = "task detail dialog fragment";
    public static final String TAG_RECYCLER_VIEW = "recyclerView";
    private TabLayout mTabLayout;
    private ViewPager2 mViewPager;
    private FloatingActionButton mFloatingActionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);

        findViews();

        setViewPagerAdapter();

        new TabLayoutMediator(mTabLayout, mViewPager,
                (mTabLayout, position) -> mTabLayout.setText(setTabText(position))).attach();

        mTabLayout.getTabAt(0).setIcon(R.drawable.ic_todo);
        mTabLayout.getTabAt(1).setIcon(R.drawable.ic_doing);
        mTabLayout.getTabAt(2).setIcon(R.drawable.ic_done);

        setClickListeners();



    }

    private void setViewPagerAdapter() {
        TaskViewPagerAdapter taskViewPagerAdapter = new TaskViewPagerAdapter(this);
        mViewPager.setAdapter(taskViewPagerAdapter);
    }

    private void setClickListeners() {
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewTaskDialogFragment newTaskDialogFragment = NewTaskDialogFragment.newInstance();
                newTaskDialogFragment.show(getSupportFragmentManager(), NEW_TASK_DIALOG_FRAGMENT);
            }
        });
    }

    private void findViews() {
        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.view_pager);
        mFloatingActionButton = findViewById(R.id.floating_action_button);
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

    @Override
    public void updateRecyclerView() {
        for (int i = 0; i < getSupportFragmentManager().getFragments().size(); i++) {
            if (getSupportFragmentManager().getFragments().get(i) instanceof TaskRecyclerViewFragment)
                ((TaskRecyclerViewFragment) getSupportFragmentManager().getFragments().get(i)).updateUI();
        }
    }

    @Override
    public void onTaskClicked() {
        for (int i = 0; i < getSupportFragmentManager().getFragments().size(); i++) {
            if (getSupportFragmentManager().getFragments().get(i) instanceof TaskRecyclerViewFragment)
                ((TaskRecyclerViewFragment) getSupportFragmentManager().getFragments().get(i)).updateUI();
        }
    }

    @Override
    public void onDeleteAll() {
        for (int i = 0; i < getSupportFragmentManager().getFragments().size(); i++) {
            if (getSupportFragmentManager().getFragments().get(i) instanceof TaskRecyclerViewFragment)
                ((TaskRecyclerViewFragment) getSupportFragmentManager().getFragments().get(i)).updateUI();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.recycler_view_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.menu_item_delete_all):
                WarningDialogFragment warningDialogFragment = WarningDialogFragment.newInstance();
                warningDialogFragment.show(getSupportFragmentManager(), TAG_RECYCLER_VIEW);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}


