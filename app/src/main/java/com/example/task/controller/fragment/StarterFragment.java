package com.example.task.controller.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.task.R;
import com.example.task.controller.activity.TaskListActivity;
import com.google.android.material.textfield.TextInputEditText;


public class StarterFragment extends Fragment {
    public static final String EXTRA_NUMBER_OF_TASKS = "number of tasks";
    public static final String EXTRA_USER_NAME = "user name";
    private EditText mEditTextUserName;
    private EditText mEditTextNumberOfTasks;
    private Button mButtonBuild;


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
                    Toast.makeText(getActivity(), "fill the blanks first!", Toast.LENGTH_SHORT).show();
                else {
                    Intent intent = new Intent(getActivity(), TaskListActivity.class);
                    intent.putExtra(EXTRA_USER_NAME, mEditTextUserName.getText().toString());
                    intent.putExtra(EXTRA_NUMBER_OF_TASKS, Integer.parseInt(mEditTextNumberOfTasks.getText().toString()));
                    startActivity(intent);
                }
            }
        });
    }
}