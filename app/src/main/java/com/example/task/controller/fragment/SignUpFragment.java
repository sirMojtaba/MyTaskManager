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
import com.example.task.controller.activity.StarterActivity;
import com.example.task.model.User;
import com.example.task.repository.UserRepository;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;


public class SignUpFragment extends Fragment {
    private EditText mEditTextUserName;
    private TextInputEditText mEditTextPassword;
    private Button mButtonSignUp;
    private UserRepository mUserRepository;

    public SignUpFragment() {
        // Required empty public constructor
    }

    public static SignUpFragment newInstance() {
        SignUpFragment fragment = new SignUpFragment();
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
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        findViews(view);
        setClickListeners();

        return view;
    }

    private void findViews(View view) {
        mEditTextUserName = view.findViewById(R.id.edit_text_user_name_signup);
        mEditTextPassword = view.findViewById(R.id.edit_text_password_signup);
        mButtonSignUp = view.findViewById(R.id.button_signup);
    }

    private void setClickListeners() {

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
                        Intent intent = new Intent(getActivity(), StarterActivity.class);
                        startActivity(intent);
                        Toast.makeText(getActivity(), "The user created successfully, now you can Log in now.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private boolean isUserExist(User user) {
        for (int i = 0; i < mUserRepository.getUserList().size(); i++) {
            if (mUserRepository.getUserList().get(i).getUserName().equals(user.getUserName()))
                return true;
        }
        return false;
    }
}