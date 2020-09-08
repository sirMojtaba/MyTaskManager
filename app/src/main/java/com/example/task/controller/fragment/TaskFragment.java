package com.example.task.controller.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.task.R;
import com.example.task.adapter.TaskListAdapter;
import com.example.task.enums.TaskState;
import com.example.task.model.Task;
import com.example.task.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;

public class TaskFragment extends Fragment {
    public static final String TASK_STATE_ARG = "task state arg";
    private RecyclerView mRecyclerView;
    private TaskRepository mTaskRepository;
    private TaskState mTaskState;
    private TaskListAdapter mTaskListAdapter;


    public TaskFragment() {
        // Required empty public constructor
    }

    public static TaskFragment newInstance(TaskState taskState) {
        TaskFragment fragment = new TaskFragment();
        Bundle args = new Bundle();
        args.putSerializable(TASK_STATE_ARG, taskState);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTaskRepository = TaskRepository.getInstance();
        mTaskState = (TaskState) getArguments().getSerializable(TASK_STATE_ARG);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task, container, false);
        mRecyclerView = view.findViewById(R.id.recycler_view_pager);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mTaskListAdapter = new TaskListAdapter(setList());
        mRecyclerView.setAdapter(mTaskListAdapter);
        return view;
    }

    private List<Task> setList() {
        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < mTaskRepository.getTasks().size(); i++) {
            if(mTaskRepository.getTasks().get(i).getTaskState() == mTaskState)
                tasks.add(mTaskRepository.getTasks().get(i));

        }
        return tasks;
    }
    public void updateUI(){

    }

}