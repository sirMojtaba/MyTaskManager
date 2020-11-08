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


public class HistoryDialogFragment extends DialogFragment {

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

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder
                .setView(R.layout.fragment_history_dialog)
                .setPositiveButton(android.R.string.ok, null);
        AlertDialog dialog = builder.create();
        return dialog;
    }
}