package com.example.task.controller.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.task.R;

public class TaskDetailDialogFragment extends DialogFragment {


    public TaskDetailDialogFragment() {
        // Required empty public constructor
    }

    public static TaskDetailDialogFragment newInstance() {
        TaskDetailDialogFragment fragment = new TaskDetailDialogFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_task_detail_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setNeutralButton("Delete", null)
                .setNeutralButton("Edit", null)
                .setNeutralButton("Save", null)
                .setView(view);
        AlertDialog dialog = builder.create();
        return dialog;
    }
}