package com.example.task.controller.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.task.R;
import com.example.task.controller.activity.PagerActivity;
import com.example.task.model.User;
import com.example.task.repository.UserRepository;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;


public class LoginFragment extends Fragment {
    private EditText mEditTextUserName;
    private TextInputEditText mEditTextPassword;
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
        mUserRepository = UserRepository.getInstance(getActivity());

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
                else if (mEditTextUserName.getText().length() > 20 || mEditTextPassword.getText().length() > 8)
                    Snackbar.make(getView(),
                            "The username or password characters are more than needed!", Snackbar.LENGTH_LONG).show();
                else {
                    String userName = mEditTextUserName.getText().toString();
                    int password = Integer.parseInt(mEditTextPassword.getText().toString());
                    User user = new User(userName, password);
                    if (isUserValid(user)) {
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
                else if (mEditTextUserName.getText().length() > 20 || mEditTextPassword.getText().length() > 8)
                    Snackbar.make(getView(),
                            "The username or password characters are more than needed!", Snackbar.LENGTH_LONG).show();
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

    private boolean isUserValid(User user) {
        for (int i = 0; i < mUserRepository.getUserList().size(); i++) {
            if (mUserRepository.getUserList().get(i).getUserName().equals(user.getUserName())
                    && mUserRepository.getUserList().get(i).getPassword() == user.getPassword()) {
                mUserRepository.setCurrentUser(mUserRepository.getUserList().get(i));
                return true;
            }
        }
        return false;
    }

    private boolean isUserExist(User user) {
        for (int i = 0; i < mUserRepository.getUserList().size(); i++) {
            if (mUserRepository.getUserList().get(i).getUserName().equals(user.getUserName()))
                return true;
        }
        return false;
    }
}