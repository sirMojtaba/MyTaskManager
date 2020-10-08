package com.example.task.controller.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.task.R;
import com.example.task.adapter.TaskRecyclerViewAdapter;
import com.example.task.enums.TaskState;
import com.example.task.model.Task;
import com.example.task.repository.TaskRepository;
import com.example.task.utils.DateTime;

import java.io.Serializable;

public class TaskDetailDialogFragment extends DialogFragment {
    public static final String TASK_SELECTED = "taskSelected";
    private static final String TAG = "tag";
    private EditText mEditTextTitle;
    private EditText mEditTextDescription;
    private Button mButtonDate;
    private Button mButtonTime;
    private RadioGroup mRadioGroup;
    private TaskState mTaskState;
    private Task mTask;
    private TaskRepository mTaskRepository;


    public TaskDetailDialogFragment() {
        // Required empty public constructor
    }

    public static TaskDetailDialogFragment newInstance(Task task) {
        TaskDetailDialogFragment fragment = new TaskDetailDialogFragment();
        Bundle args = new Bundle();
        args.putSerializable(TASK_SELECTED, task);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTask = (Task) getArguments().getSerializable(TASK_SELECTED);
        Log.d(TAG, "onCreate: " + mTask.getTitle() + ".................................................................................");
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_task_detail_dialog, null);
        findViews(view);

        updateUi();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        })
                .setNeutralButton("Edit", null)
                .setNeutralButton("Save", null)
                .setView(view);
        AlertDialog dialog = builder.create();
        return dialog;
    }

    private void findViews(View view) {
        mEditTextTitle = view.findViewById(R.id.edit_text_task_detail_title);
        mEditTextDescription = view.findViewById(R.id.edit_text_task_detail_description);
        mButtonDate = view.findViewById(R.id.button_task_detail_date);
        mButtonTime = view.findViewById(R.id.button_task_detail_time);
        mRadioGroup = view.findViewById(R.id.radio_group_task_detail);
    }

    private void updateUi() {
        mEditTextTitle.setText(mTask.getTitle());
        mEditTextDescription.setText(mTask.getDescription());
        mTaskState = mTask.getTaskState();
        mButtonDate.setText(DateTime.getDate(mTask.getDate()));
        mButtonTime.setText(DateTime.getTime(mTask.getDate()));
        showRadioButtonClicked();

    }

    private void showRadioButtonClicked() {
        switch (mTaskState) {
            case TODO:
                mRadioGroup.check(R.id.radio_button_task_detail_todo);
                break;
            case DOING:
                mRadioGroup.check(R.id.radio_button_task_detail_doing);
                break;
            default:
                mRadioGroup.check(R.id.radio_button_task_detail_done);
                break;
        }

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
            default:
                mTaskState = TaskState.DONE;
                break;
        }
    }
}