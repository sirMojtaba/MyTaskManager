package com.example.task.controller.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.example.task.R;

import java.util.Calendar;
import java.util.Date;


public class DatePickerDialogFragment extends DialogFragment {
    public static final String EXTRA_USER_SELECTED_DATE = "com.example.task.userSelectedDate";
    public static final String BUTTON_DATE = "buttonDate";
    private DatePicker mDatePicker;


    public DatePickerDialogFragment() {
        // Required empty public constructor
    }

    public static DatePickerDialogFragment newInstance() {
        DatePickerDialogFragment fragment = new DatePickerDialogFragment();
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
        View view = inflater.inflate(R.layout.fragment_date_picker_dialog, null);

        findViews(view);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Date userDatePicked = getUserSelectedDateFromDatePicker();
                        Fragment fragment = getTargetFragment();
                        Intent intent = new Intent();
                        intent.putExtra(EXTRA_USER_SELECTED_DATE, userDatePicked);
                        fragment.onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
                    }
                })
                .setNegativeButton(android.R.string.cancel, null);
        AlertDialog dialog = builder.create();
        return dialog;


    }

    private Date getUserSelectedDateFromDatePicker() {
        int year = mDatePicker.getYear();
        int month = mDatePicker.getMonth();
        int day = mDatePicker.getDayOfMonth();
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime();
    }

    private void findViews(View view) {
        mDatePicker = view.findViewById(R.id.date_picker);
    }
}