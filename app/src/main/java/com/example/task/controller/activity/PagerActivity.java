package com.example.task.controller.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.task.R;
import com.example.task.adapter.TaskViewPagerAdapter;
import com.example.task.controller.fragment.HistoryDialogFragment;
import com.example.task.controller.fragment.NewTaskDialogFragment;
import com.example.task.controller.fragment.TaskDetailDialogFragment;
import com.example.task.controller.fragment.TaskRecyclerViewFragment;
import com.example.task.controller.fragment.WarningDialogFragment;
import com.example.task.repository.UserRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class PagerActivity extends AppCompatActivity implements NewTaskDialogFragment.OnNewTaskListener,
        TaskDetailDialogFragment.TaskDetailInterface, WarningDialogFragment.WarningInterface {
    public static final String NEW_TASK_DIALOG_FRAGMENT = "task dialog fragment";
    public static final String TASK_DETAIL_DIALOG_FRAGMENT = "task detail dialog fragment";
    public static final String TAG_RECYCLER_VIEW = "recyclerView";
    public static final String TAG_PAGER_ACTIVITY = "pager_activity";
    private TabLayout mTabLayout;
    private ViewPager2 mViewPager;
    private FloatingActionButton mFbAdd;
    private FloatingActionButton mFbHistory;
    private UserRepository mUserRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserRepository = UserRepository.getInstance(this);
        setContentView(R.layout.activity_pager);

        findViews();
        initView();

        setViewPagerAdapter();

        new TabLayoutMediator(mTabLayout, mViewPager,
                (mTabLayout, position) -> mTabLayout.setText(setTabText(position))).attach();

        setTabsIcons();

        setClickListeners();
    }

    private void setTabsIcons() {
        mTabLayout.getTabAt(0).setIcon(R.drawable.ic_todo);
        mTabLayout.getTabAt(1).setIcon(R.drawable.ic_doing);
        mTabLayout.getTabAt(2).setIcon(R.drawable.ic_done);
    }

    private void initView() {
        if (!mUserRepository.getCurrentUser().getUserName().equals("admin"))
            mFbHistory.setVisibility(View.INVISIBLE);
    }

    private void findViews() {
        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.view_pager);
        mFbAdd = findViewById(R.id.fb_add);
        mFbHistory = findViewById(R.id.fb_history);
    }

    private void setViewPagerAdapter() {
        TaskViewPagerAdapter taskViewPagerAdapter = new TaskViewPagerAdapter(this);
        mViewPager.setAdapter(taskViewPagerAdapter);
    }

    private void setClickListeners() {
        mFbAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewTaskDialogFragment newTaskDialogFragment = NewTaskDialogFragment.newInstance();
                newTaskDialogFragment.show(getSupportFragmentManager(), NEW_TASK_DIALOG_FRAGMENT);
            }
        });

        mFbHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HistoryDialogFragment historyDialogFragment = HistoryDialogFragment.newInstance();
                historyDialogFragment.show(getSupportFragmentManager(), TAG_PAGER_ACTIVITY);

            }
        });
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

    private void updateUi() {
        for (int i = 0; i < getSupportFragmentManager().getFragments().size(); i++) {
            if (getSupportFragmentManager().getFragments().get(i) instanceof TaskRecyclerViewFragment)
                ((TaskRecyclerViewFragment) getSupportFragmentManager().getFragments().get(i)).updateUI();
        }
    }

    @Override
    public void updateRecyclerView() {
        updateUi();
    }

    @Override
    public void onTaskClicked() {
        updateUi();
    }

    @Override
    public void onDeleteAll() {
        updateUi();
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


