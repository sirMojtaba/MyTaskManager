package com.example.task.controller.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.task.R;
import com.example.task.adapter.TaskRecyclerViewAdapter;
import com.example.task.enums.TaskState;
import com.example.task.model.Task;
import com.example.task.repository.TaskRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class TaskViewPagerFragment extends Fragment {
    public static final String ARGS_TASK_STATE = "task state arg";
    private RecyclerView mRecyclerView;
    private TaskRepository mTaskRepository;
    private TaskState mTaskState;

    private TaskRecyclerViewAdapter mTaskRecyclerViewAdapter;


    public TaskViewPagerFragment() {
        // Required empty public constructor
    }

    public static TaskViewPagerFragment newInstance(TaskState taskState) {
        TaskViewPagerFragment fragment = new TaskViewPagerFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARGS_TASK_STATE, taskState);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTaskRepository = TaskRepository.getInstance();
        mTaskState = (TaskState) getArguments().getSerializable(ARGS_TASK_STATE);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task_recycler_view, container, false);
        mRecyclerView = view.findViewById(R.id.recycler_view);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mTaskRecyclerViewAdapter = new TaskRecyclerViewAdapter(setList());
        mRecyclerView.setAdapter(mTaskRecyclerViewAdapter);
        return view;
    }

    private List<Task> setList() {
        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < mTaskRepository.getTasks().size(); i++) {
            if (mTaskRepository.getTasks().get(i).getTaskState() == mTaskState)
                tasks.add(mTaskRepository.getTasks().get(i));
        }
        return tasks;
    }


    public void updateUI() {
        if (mTaskRecyclerViewAdapter == null) {
            mTaskRecyclerViewAdapter = new TaskRecyclerViewAdapter(setList());
            mRecyclerView.setAdapter(mTaskRecyclerViewAdapter);
        } else {
            mTaskRecyclerViewAdapter.setTaskList(setList());
            mTaskRecyclerViewAdapter.notifyDataSetChanged();
        }
    }

}