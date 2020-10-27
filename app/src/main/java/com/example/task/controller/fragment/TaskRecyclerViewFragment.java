package com.example.task.controller.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.task.R;
import com.example.task.adapter.TaskRecyclerViewAdapter;
import com.example.task.enums.TaskState;
import com.example.task.model.Task;
import com.example.task.repository.TaskRepository;
import com.example.task.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class TaskRecyclerViewFragment extends Fragment {
    public static final String ARGS_TASK_STATE = "task state arg";
    public static final String TAG = "tag";

    private RecyclerView mRecyclerView;
    private TaskRepository mTaskRepository;
    private TaskState mTaskState;
    private ImageView mImageViewNoTask;
    private TaskRecyclerViewAdapter mTaskRecyclerViewAdapter;
    private UserRepository mUserRepository;


    public TaskRecyclerViewFragment() {
        // Required empty public constructor
    }

    public static TaskRecyclerViewFragment newInstance(TaskState taskState) {
        TaskRecyclerViewFragment fragment = new TaskRecyclerViewFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARGS_TASK_STATE, taskState);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTaskRepository = TaskRepository.getInstance(getActivity());
        mUserRepository = UserRepository.getInstance(getActivity());
        assert getArguments() != null;
        mTaskState = (TaskState) getArguments().getSerializable(ARGS_TASK_STATE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task_recycler_view, container, false);
        findViews(view);
        initRecyclerView();
        showNoTaskImage();
        return view;

    }

    private void findViews(View view) {
        mImageViewNoTask = view.findViewById(R.id.image_view_no_task);
        mRecyclerView = view.findViewById(R.id.recycler_view);
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mTaskRecyclerViewAdapter = new TaskRecyclerViewAdapter(setList(), getActivity());
        mRecyclerView.setAdapter(mTaskRecyclerViewAdapter);
    }

    private void showNoTaskImage() {
        if (mTaskRecyclerViewAdapter.getItemCount() != 0) {
            mRecyclerView.setVisibility(View.VISIBLE);
            mImageViewNoTask.setVisibility(View.GONE);
        } else {
            mImageViewNoTask.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        }
    }

    private List<Task> setList() {
        List<Task> taskList = new ArrayList<>();

        for (int i = 0; i < mTaskRepository.getTasks().size(); i++) {
            if (mUserRepository.getCurrentUser().getUserName().equals("admin") &&
                    mTaskRepository.getTasks().get(i).getTaskState() == mTaskState) {
                taskList.add(mTaskRepository.getTasks().get(i));
                Log.d(TAG, "setList: admin");
            }
            else {
                Task task = mTaskRepository.getTasks().get(i);
                Log.d(TAG, "setList: no admin");
                Log.d(TAG, "setList: " + task.getTitle() +
                        " " + task.getUserId() + "  State : " + task.getTaskState());
                Log.d(TAG, "setList: " + mUserRepository.getCurrentUser().getUserId());
                if (mTaskRepository.getTasks().get(i).getTaskState().equals(mTaskState) &&
                        mTaskRepository.getTasks().get(i).getUserId() .equals(mUserRepository.getCurrentUser().getUserId())) {
                    taskList.add(mTaskRepository.getTasks().get(i));
                    Log.d(TAG, "setList: 2");
                }
            }

        }
        return taskList;
    }

    public void updateUI() {
        if (mTaskRecyclerViewAdapter == null) {
            mTaskRecyclerViewAdapter = new TaskRecyclerViewAdapter(setList(), getActivity());
            mRecyclerView.setAdapter(mTaskRecyclerViewAdapter);

            
        } else {
            mTaskRecyclerViewAdapter.setTaskList(setList());
            mTaskRecyclerViewAdapter.notifyDataSetChanged();
            Log.d(TAG, "updateUI: " + setList().size());

        }
        showNoTaskImage();
    }
}