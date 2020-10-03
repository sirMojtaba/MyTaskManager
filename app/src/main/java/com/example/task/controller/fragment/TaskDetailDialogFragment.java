package com.example.task.controller.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.task.R;


public class TaskDetailDialogFragment extends DialogFragment {
    public static final String DATE_PICKER = "date picker";
    private EditText mEditTextTitle;
    private EditText mEditTextDescription;
    private Button mButtonDate;
    private Button mButtonTime;


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
        findViews(view);
        setClickListeners();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("task")
                .setNeutralButton("save", null)
                .setNegativeButton(android.R.string.cancel, null)
                .setView(view);

        AlertDialog dialog = builder.create();
        return dialog;
    }

    private void findViews(View view) {
        mEditTextTitle = view.findViewById(R.id.edit_text_new_task_title);
        mEditTextDescription = view.findViewById(R.id.edit_text_new_task_description);
        mButtonDate = view.findViewById(R.id.button_new_task_date);
        mButtonTime = view.findViewById(R.id.button_new_task_time);
    }

    private void setClickListeners (){
        mButtonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialogFragment datePickerDialogFragment = DatePickerDialogFragment.newInstance();
                datePickerDialogFragment.show(getFragmentManager(), DATE_PICKER);
            }
        });
    }
}