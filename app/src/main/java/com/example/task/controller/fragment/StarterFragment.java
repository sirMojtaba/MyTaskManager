package com.example.task.controller.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.task.R;
import com.example.task.controller.activity.PagerActivity;
import com.example.task.enums.TaskState;
import com.example.task.model.Task;
import com.example.task.repository.TaskRepository;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class StarterFragment extends Fragment {
    private EditText mEditTextUserName;
    private EditText mEditTextNumberOfTasks;
    private Button mButtonBuild;
    private TaskRepository mTaskRepository;


    public StarterFragment() {
        // Required empty public constructor
    }

    public static StarterFragment newInstance() {
        StarterFragment fragment = new StarterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTaskRepository = TaskRepository.getInstance();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_starter, container, false);
        findViews(view);
        setClickListeners();
        return view;
    }

    private void findViews(View view) {
        mEditTextUserName = view.findViewById(R.id.edit_text_user_name);
        mEditTextNumberOfTasks = view.findViewById(R.id.edit_text_number_of_tasks);
        mButtonBuild = view.findViewById(R.id.button_build);
    }

    private void setClickListeners() {
        mButtonBuild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEditTextUserName.getText().length() == 0 || mEditTextNumberOfTasks.getText().length() == 0)
                    Snackbar.make(getActivity().findViewById(R.id.fragment_container),
                            "Fill both of the blanks first!", Snackbar.LENGTH_LONG).show();
                else {
                    mTaskRepository.setTasks(buildTaskList());
                    startActivity();
                }
            }
        });
    }

    private void startActivity() {
        Intent intent = new Intent(getActivity(), PagerActivity.class);
        startActivity(intent);
    }

    private List<Task> buildTaskList() {
        List<Task> taskList = new ArrayList<>();
        for (int i = 0; i < Integer.parseInt(mEditTextNumberOfTasks.getText().toString()); i++) {
            Task task = new Task(mEditTextUserName.getText().toString(), getState());
            taskList.add(task);
        }
        return taskList;
    }

    public static TaskState getState() {
        int pick = new Random().nextInt(TaskState.values().length);
        return TaskState.values()[pick];
    }
}