package com.example.task.controller.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.task.R;
import com.example.task.enums.TaskState;
import com.example.task.model.Task;
import com.example.task.repository.TaskRepository;
import com.example.task.repository.UserRepository;
import com.example.task.utils.DateTime;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class NewTaskDialogFragment extends DialogFragment {
    public static final String DATE_PICKER = "date picker";
    public static final String TIME_PICKER = "time picker";
    public static final int REQUEST_CODE_NEW_TASK_DATE_PICKER = 0;
    public static final int REQUEST_CODE_NEW_TASK_TIME_PICKER = 1;
    public static final String BUNDLE_CALENDAR_DATE = "date";
    private static final String BUNDLE_CALENDAR_TIME = "time";
    private EditText mEditTextTitle;
    private EditText mEditTextDescription;
    private Button mButtonDate;
    private Button mButtonTime;
    private RadioGroup mRadioGroup;
    private TaskState mTaskState;
    private TaskRepository mTaskRepository;
    private OnNewTaskListener mOnNewTaskListener;
    private GregorianCalendar mGregorianCalendarTime;
    private GregorianCalendar mGregorianCalendarDate;
    private UserRepository mUserRepository;
    private Calendar mCalendar;

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
        mTaskRepository = TaskRepository.getInstance(getActivity());
        mUserRepository = UserRepository.getInstance(getActivity());
        mGregorianCalendarDate = new GregorianCalendar();
        mGregorianCalendarTime = new GregorianCalendar();
        if (savedInstanceState != null) {
            mGregorianCalendarDate = (GregorianCalendar) savedInstanceState.getSerializable(BUNDLE_CALENDAR_DATE);
            mGregorianCalendarTime = (GregorianCalendar) savedInstanceState.getSerializable(BUNDLE_CALENDAR_TIME);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_new_task_dialog, null);

        findViews(view);
        setClickListeners();
        setDateTimeButtonText();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("New task")
                .setNegativeButton(android.R.string.cancel, null)
                .setPositiveButton("save", new DialogInterface.OnClickListener() {
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

    private void setDateTimeButtonText() {
        if (mGregorianCalendarDate == null) {
            mButtonDate.setText(DateTime.getDate(new Date()));
            mButtonTime.setText(DateTime.getTime(new Date()));
        } else {
            mButtonDate.setText(DateTime.getDate(mGregorianCalendarDate.getTime()));
            mButtonTime.setText(DateTime.getTime(mGregorianCalendarTime.getTime()));
        }
    }


    private Task buildNewTask() {
        String newTaskTitle = mEditTextTitle.getText().toString();
        String newTaskDescription = mEditTextDescription.getText().toString();
        mCalendar = setCalendar();
        return new Task(newTaskTitle, newTaskDescription, mTaskState, mCalendar.getTime(), mUserRepository.getCurrentUser().getUserId());
    }

    private Calendar setCalendar() {
        int year = mGregorianCalendarDate.get(Calendar.YEAR);
        int month = mGregorianCalendarDate.get(Calendar.MONTH);
        int day = mGregorianCalendarDate.get(Calendar.DAY_OF_MONTH);
        int hour = mGregorianCalendarTime.get(Calendar.HOUR_OF_DAY);
        int minute = mGregorianCalendarTime.get(Calendar.MINUTE);
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, hour, minute);
        return calendar;
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
                datePickerDialogFragment.setTargetFragment(NewTaskDialogFragment.this, REQUEST_CODE_NEW_TASK_DATE_PICKER);
                datePickerDialogFragment.show(getFragmentManager(), DATE_PICKER);
            }
        });

        mButtonTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialogFragment timePickerDialogFragment = TimePickerDialogFragment.newInstance();
                timePickerDialogFragment.setTargetFragment(NewTaskDialogFragment.this, REQUEST_CODE_NEW_TASK_TIME_PICKER);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode != Activity.RESULT_OK || data == null)
            return;
        if (requestCode == REQUEST_CODE_NEW_TASK_DATE_PICKER) {
            Date date = (Date) data.getSerializableExtra(DatePickerDialogFragment.EXTRA_USER_SELECTED_DATE);
            mGregorianCalendarDate.setTime(date);
            mButtonDate.setText(DateTime.getDate(date));
        } else if (requestCode == REQUEST_CODE_NEW_TASK_TIME_PICKER) {
            int userSelectedHour = data.getIntExtra(TimePickerDialogFragment.EXTRA_HOUR, 0);
            int userSelectedMinute = data.getIntExtra(TimePickerDialogFragment.EXTRA_MINUTE, 0);
            String userSelectedTime = userSelectedHour + ":" + userSelectedMinute;
            mButtonTime.setText(userSelectedTime);
            mGregorianCalendarTime.set(Calendar.HOUR_OF_DAY, userSelectedHour);
            mGregorianCalendarTime.set(Calendar.MINUTE, userSelectedMinute);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(BUNDLE_CALENDAR_DATE, mGregorianCalendarDate);
        outState.putSerializable(BUNDLE_CALENDAR_TIME, mGregorianCalendarTime);
    }
}
