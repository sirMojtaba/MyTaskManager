package com.example.task.controller.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task.R;
import com.example.task.adapter.TaskRecyclerViewAdapter;
import com.example.task.controller.activity.PagerActivity;
import com.example.task.enums.TaskState;
import com.example.task.model.Task;
import com.example.task.repository.TaskRepository;
import com.google.android.material.button.MaterialButton;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class NewTaskDialogFragment extends DialogFragment {
    public static final String DATE_PICKER = "date picker";
    public static final String TIME_PICKER = "time picker";
    private EditText mEditTextTitle;
    private EditText mEditTextDescription;
    private Button mButtonDate;
    private Button mButtonTime;
    private RadioGroup mRadioGroup;
    private TaskState mTaskState;
    private TaskRepository mTaskRepository;
    private OnNewTaskListener mOnNewTaskListener;


    public NewTaskDialogFragment() {
        // Required empty public constructor
    }

    public static NewTaskDialogFragment newInstance() {
        NewTaskDialogFragment fragment = new NewTaskDialogFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTaskRepository = TaskRepository.getInstance();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_new_task_dialog, null);

        findViews(view);
        setClickListeners();
        mButtonDate.setText(new Date().toString());
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("New task")
                .setNegativeButton(android.R.string.cancel, null)
                .setNeutralButton("save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onRadioButtonClicked();
                        Task task = buildNewTask();
                        mTaskRepository.addTask(task);
                        mOnNewTaskListener.updateRecyclerView();
                    }
                })
                .setView(view);
        AlertDialog dialog = builder.create();
        return dialog;
    }

    private Task buildNewTask() {
        String newTaskTitle = mEditTextTitle.getText().toString();
        String newTaskDescription = mEditTextDescription.getText().toString();
        return new Task(newTaskTitle, newTaskDescription, mTaskState, new Date());
    }

    private void onRadioButtonClicked() {
        int selectedId = mRadioGroup.getCheckedRadioButtonId();
        switch (selectedId) {
            case R.id.radio_button_todo:
                mTaskState = TaskState.TODO;
                break;
            case R.id.radio_button_doing:
                mTaskState = TaskState.DOING;
                break;
            case R.id.radio_button_done:
                mTaskState = TaskState.DONE;
                break;
        }
    }

    private void findViews(View view) {
        mEditTextTitle = view.findViewById(R.id.edit_text_new_task_title);
        mEditTextDescription = view.findViewById(R.id.edit_text_new_task_description);
        mButtonDate = view.findViewById(R.id.button_new_task_date);
        mButtonTime = view.findViewById(R.id.button_new_task_time);
        mRadioGroup = view.findViewById(R.id.radio_group_new_task);
    }

    private void setClickListeners() {
        mButtonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialogFragment datePickerDialogFragment = DatePickerDialogFragment.newInstance();
                datePickerDialogFragment.show(getFragmentManager(), DATE_PICKER);
            }
        });

        mButtonTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialogFragment timePickerDialogFragment = TimePickerDialogFragment.newInstance();
                timePickerDialogFragment.show(getFragmentManager(), TIME_PICKER);
            }
        });


    }

    public interface OnNewTaskListener {
        void updateRecyclerView();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnNewTaskListener)
            mOnNewTaskListener = (OnNewTaskListener) context;
    }
}