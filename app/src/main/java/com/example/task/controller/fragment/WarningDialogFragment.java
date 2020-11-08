package com.example.task.controller.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.task.repository.TaskRepository;
import com.example.task.repository.UserRepository;


public class WarningDialogFragment extends DialogFragment {
    TaskRepository mTaskRepository;
    UserRepository mUserRepository;
    WarningInterface mWarningInterface;


    public WarningDialogFragment() {
        // Required empty public constructor
    }

    public static WarningDialogFragment newInstance() {
        WarningDialogFragment fragment = new WarningDialogFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTaskRepository = TaskRepository.getInstance(getActivity());
        mUserRepository = UserRepository.getInstance(getActivity());

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder
                .setMessage("Are you sure to delete all tasks?")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i = 0; i < mTaskRepository.getTaskList().size(); i++) {
                            if (mUserRepository.getCurrentUser().getUserName().equals("admin"))
                                mTaskRepository.removeTask(mTaskRepository.getTaskList().get(i--));
                            else {
                                if (mTaskRepository.getTaskList().get(i).getUserId() == mUserRepository.getCurrentUser().getUserId())
                                    mTaskRepository.removeTask(mTaskRepository.getTaskList().get(i--));
                            }
                        }
                        mWarningInterface.onDeleteAll();
                    }
                })
                .setNegativeButton(android.R.string.cancel, null);
        AlertDialog dialog = builder.create();
        return dialog;
    }

    public interface WarningInterface {
        void onDeleteAll();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof WarningInterface)
            mWarningInterface = (WarningInterface) context;
    }
}