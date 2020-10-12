package com.example.task.controller.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.task.R;
import com.example.task.enums.TaskState;
import com.example.task.model.Task;
import com.example.task.repository.TaskRepository;
import com.example.task.utils.DateTime;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TaskDetailDialogFragment extends DialogFragment {
    public static final String TASK_SELECTED = "taskSelected";
    public static final String DATE_PICKER = "datePicker";
    public static final String TIME_PICKER = "timePicker";
    public static final int REQUEST_CODE_TASK_DETAIL_DATE_PICKER = 0;
    public static final int REQUEST_CODE_TASK_DETAIL_TIME_PICKER = 1;
    private EditText mEditTextTitle;
    private EditText mEditTextDescription;
    private Button mButtonDate;
    private Button mButtonTime;
    private RadioGroup mRadioGroup;
    private RadioButton mRadioButtonTodo;
    private RadioButton mRadioButtonDoing;
    private RadioButton mRadioButtonDone;
    private TaskState mTaskState;
    private Task mTask;
    private TaskRepository mTaskRepository;
    private TaskDetailInterface mTaskDetailInterface;
    private GregorianCalendar mGregorianCalendarDate;
    private GregorianCalendar mGregorianCalendarTime;

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
        mTaskRepository = TaskRepository.getInstance();
        mGregorianCalendarDate = new GregorianCalendar();
        mGregorianCalendarTime = new GregorianCalendar();

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_task_detail_dialog, null);
        findViews(view);
        setClickListeners();
        updateUi();
        setViewDisabled();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder
                .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mTaskRepository.removeTask(mTask);
                        mTaskDetailInterface.onTaskClicked();
                    }
                })
                .setNeutralButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        updateTask();
                        mTaskDetailInterface.onTaskClicked();
                    }
                })
                .setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_NEUTRAL).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setViewEnabled();
            }
        });
        return dialog;
    }

    private void setViewEnabled() {
        mEditTextTitle.setEnabled(true);
        mEditTextDescription.setEnabled(true);
        mButtonDate.setEnabled(true);
        mButtonTime.setEnabled(true);
        mRadioButtonTodo.setEnabled(true);
        mRadioButtonDoing.setEnabled(true);
        mRadioButtonDone.setEnabled(true);
    }

    private void setViewDisabled() {
        mEditTextTitle.setEnabled(false);
        mEditTextDescription.setEnabled(false);
        mButtonDate.setEnabled(false);
        mButtonTime.setEnabled(false);
        mRadioButtonTodo.setEnabled(false);
        mRadioButtonDoing.setEnabled(false);
        mRadioButtonDone.setEnabled(false);
    }

    private void updateTask() {
        mTask.setTitle(mEditTextTitle.getText().toString());
        mTask.setDescription(mEditTextDescription.getText().toString());
        Calendar calendar = setTaskDate();
        mTask.setDate(calendar.getTime());
        onRadioButtonClicked();
        mTask.setTaskState(mTaskState);
    }

    private Calendar setTaskDate() {
        int year = mGregorianCalendarDate.get(Calendar.YEAR);
        int month = mGregorianCalendarDate.get(Calendar.MONTH);
        int day = mGregorianCalendarDate.get(Calendar.DAY_OF_MONTH);
        int hour = mGregorianCalendarTime.get(Calendar.HOUR_OF_DAY);
        int minute = mGregorianCalendarTime.get(Calendar.MINUTE);
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, hour, minute);
        return calendar;
    }

    private void findViews(View view) {
        mEditTextTitle = view.findViewById(R.id.edit_text_task_detail_title);
        mEditTextDescription = view.findViewById(R.id.edit_text_task_detail_description);
        mButtonDate = view.findViewById(R.id.button_task_detail_date);
        mButtonTime = view.findViewById(R.id.button_task_detail_time);
        mRadioGroup = view.findViewById(R.id.radio_group_task_detail);
        mRadioButtonTodo = view.findViewById(R.id.radio_button_task_detail_todo);
        mRadioButtonDoing = view.findViewById(R.id.radio_button_task_detail_doing);
        mRadioButtonDone = view.findViewById(R.id.radio_button_task_detail_done);
    }

    private void setClickListeners() {
        mButtonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialogFragment datePickerDialogFragment = DatePickerDialogFragment.newInstance();
                datePickerDialogFragment.setTargetFragment(TaskDetailDialogFragment.this, REQUEST_CODE_TASK_DETAIL_DATE_PICKER);
                datePickerDialogFragment.show(getFragmentManager(), DATE_PICKER);
            }
        });

        mButtonTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialogFragment timePickerDialogFragment = TimePickerDialogFragment.newInstance();
                timePickerDialogFragment.setTargetFragment(TaskDetailDialogFragment.this, REQUEST_CODE_TASK_DETAIL_TIME_PICKER);
                timePickerDialogFragment.show(getFragmentManager(), TIME_PICKER);
            }
        });
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
            case R.id.radio_button_task_detail_todo:
                mTaskState = TaskState.TODO;
                break;
            case R.id.radio_button_task_detail_doing:
                mTaskState = TaskState.DOING;
                break;
            default:
                mTaskState = TaskState.DONE;
                break;
        }
    }

    public interface TaskDetailInterface {
        void onTaskClicked();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof TaskDetailInterface)
            mTaskDetailInterface = (TaskDetailInterface) context;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK || data == null)
            return;
        if (requestCode == REQUEST_CODE_TASK_DETAIL_DATE_PICKER) {
            Date date = (Date) data.getSerializableExtra(DatePickerDialogFragment.EXTRA_USER_SELECTED_DATE);
            mGregorianCalendarDate.setTime(date);
            mButtonDate.setText(DateTime.getDate(date));
        } else if (requestCode == REQUEST_CODE_TASK_DETAIL_TIME_PICKER) {
            int userSelectedHour = data.getIntExtra(TimePickerDialogFragment.EXTRA_HOUR, 0);
            int userSelectedMinute = data.getIntExtra(TimePickerDialogFragment.EXTRA_MINUTE, 0);
            String userSelectedTime = userSelectedHour + ":" + userSelectedMinute;
            mButtonTime.setText(userSelectedTime);
            mGregorianCalendarTime.set(Calendar.HOUR_OF_DAY, userSelectedHour);
            mGregorianCalendarTime.set(Calendar.MINUTE, userSelectedMinute);
        }
    }
}