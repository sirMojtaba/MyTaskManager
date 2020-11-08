package com.example.task.controller.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.task.R;
import com.example.task.model.User;
import com.example.task.repository.TaskRepository;
import com.example.task.repository.UserRepository;


public class HistoryDialogFragment extends DialogFragment {

    private TextView mTextViewHistory;
    //    private AppDatabase db;
    private UserRepository mUserRepository;
    private TaskRepository mTaskRepository;

    public HistoryDialogFragment() {
        // Required empty public constructor
    }

    public static HistoryDialogFragment newInstance() {
        HistoryDialogFragment fragment = new HistoryDialogFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*db = Room.databaseBuilder(getContext(), AppDatabase.class, "database")
                .allowMainThreadQueries()
                .build();*/
        mUserRepository = UserRepository.getInstance(getActivity());
        mTaskRepository = TaskRepository.getInstance(getActivity());
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_history_dialog, null);
        findViews(view);
        mTextViewHistory.setText(getUsersHistory());

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder
                .setView(view)
                .setPositiveButton(android.R.string.ok, null);
        AlertDialog dialog = builder.create();


        return dialog;
    }

    private void findViews(View view) {
        mTextViewHistory = view.findViewById(R.id.text_view_history);
    }


    private String getUsersHistory() {
        String list = "";
        for (int i = 1; i < mUserRepository.getUserList().size(); i++) {
            list = list + "User " + i + ": " + mUserRepository.getUserList().get(i).getUserName()
                    + "\n" + "Number of tasks: " + numberOfUserTasks(mUserRepository.getUserList().get(i))
                    + "\n" + "\n";
        }
        return list;
    }

    private int numberOfUserTasks(User user) {
        int count = 0;
        for (int i = 0; i < mTaskRepository.getTaskList().size(); i++) {
            if (mTaskRepository.getTaskList().get(i).getUserId().equals(user.getUserId()))
                count++;
        }
        return count;
    }
}