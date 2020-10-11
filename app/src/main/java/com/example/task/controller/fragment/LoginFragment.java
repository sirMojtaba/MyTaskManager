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
import com.example.task.model.User;
import com.example.task.repository.TaskRepository;
import com.example.task.repository.UserRepository;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class LoginFragment extends Fragment {
    private EditText mEditTextUserName;
    private EditText mEditTextPassword;
    private Button mButtonLogin;
    private Button mButtonSignUp;
    private UserRepository mUserRepository;


    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserRepository = UserRepository.getInstance();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_page, container, false);
        findViews(view);
        setClickListeners();
        return view;
    }

    private void findViews(View view) {
        mEditTextUserName = view.findViewById(R.id.edit_text_user_name);
        mEditTextPassword = view.findViewById(R.id.edit_text_password);
        mButtonLogin = view.findViewById(R.id.button_login);
        mButtonSignUp = view.findViewById(R.id.button_signup);
    }

    private void setClickListeners() {
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEditTextUserName.getText().length() == 0 || mEditTextPassword.getText().length() == 0)
                    Snackbar.make(getView(),
                            "Fill both of the blanks first!", Snackbar.LENGTH_LONG).show();
                else {
                    String userName = mEditTextUserName.getText().toString();
                    int password = Integer.parseInt(mEditTextPassword.getText().toString());
                    User user = new User(userName, password);
                    if (validationUser(user)) {
                        startPagerActivity();
                    } else
                        Snackbar.make(getView(), "No user found with this name! Sign up first.", Snackbar.LENGTH_LONG).show();
                }
            }
        });

        mButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEditTextUserName.getText().length() == 0 || mEditTextPassword.getText().length() == 0)
                    Snackbar.make(getView(),
                            "Fill both of the blanks first!", Snackbar.LENGTH_LONG).show();
                else {
                    String userName = mEditTextUserName.getText().toString();
                    int password = Integer.parseInt(mEditTextPassword.getText().toString());
                    User user = new User(userName, password);
                    if (isUserExist(user))
                        Snackbar.make(getView(), "An user found with this name.", Snackbar.LENGTH_LONG).show();
                    else {
                        mUserRepository.addUser(user);
                        Snackbar.make(getView(), "The user created successfully, now you can Log in.", Snackbar.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private void startPagerActivity() {
        Intent intent = new Intent(getActivity(), PagerActivity.class);
        startActivity(intent);
    }

    private List<Task> buildTaskList() {
        List<Task> taskList = new ArrayList<>();
        for (int i = 0; i < Integer.parseInt(mEditTextPassword.getText().toString()); i++) {
            Task task = new Task(mEditTextUserName.getText().toString(), getState());
            taskList.add(task);
        }
        return taskList;
    }

    public static TaskState getState() {
        int pick = new Random().nextInt(TaskState.values().length);
        return TaskState.values()[pick];
    }

    private boolean validationUser(User user) {
        for (int i = 0; i < mUserRepository.getUsers().size(); i++) {
            if (mUserRepository.getUsers().get(i).getUserName().equals(user.getUserName())
                    && mUserRepository.getUsers().get(i).getPassword() == user.getPassword()){
                mUserRepository.setCurrentUser(mUserRepository.getUsers().get(i));
                return true;
            }
        }
        return false;
    }
    private boolean isUserExist(User user) {
        for (int i = 0; i < mUserRepository.getUsers().size(); i++) {
            if (mUserRepository.getUsers().get(i).getUserName().equals(user.getUserName()))
                return true;
        }
        return false;
    }
}