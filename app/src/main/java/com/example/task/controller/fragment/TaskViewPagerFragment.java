package com.example.task.controller.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.task.R;
import com.example.task.adapter.TaskRecyclerViewAdapter;
import com.example.task.enums.TaskState;
import com.example.task.model.Task;
import com.example.task.repository.TaskRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.task.controller.activity.PagerActivity.TASK_DETAIL_DIALOG_FRAGMENT;

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
        assert getArguments() != null;
        mTaskState = (TaskState) getArguments().getSerializable(ARGS_TASK_STATE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task_recycler_view, container, false);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mTaskRecyclerViewAdapter = new TaskRecyclerViewAdapter(setList(), getActivity());
        /*if (mTaskRecyclerViewAdapter.getItemCount() == 0){
            ImageView imageView = view.findViewById(R.id.image_view_no_task_list);
            imageView.setVisibility(View.VISIBLE);
        }*/
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
            mTaskRecyclerViewAdapter = new TaskRecyclerViewAdapter(setList(), getActivity());
            mRecyclerView.setAdapter(mTaskRecyclerViewAdapter);
        } else {
            mTaskRecyclerViewAdapter.setTaskList(setList());
            mTaskRecyclerViewAdapter.notifyDataSetChanged();
        }
    }
}