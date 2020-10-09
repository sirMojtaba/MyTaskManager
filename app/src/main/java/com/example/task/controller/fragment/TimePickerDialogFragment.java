package com.example.task.controller.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;

import com.example.task.R;

public class TimePickerDialogFragment extends DialogFragment {
    public static final String EXTRA_HOUR = "hour";
    public static final String EXTRA_MINUTE = "minute";
    private TimePicker mTimePicker;


    public TimePickerDialogFragment() {
        // Required empty public constructor
    }

    public static TimePickerDialogFragment newInstance() {
        TimePickerDialogFragment fragment = new TimePickerDialogFragment();
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
        View view = inflater.inflate(R.layout.fragment_time_picker_dialog, null);

        findViews(view);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Fragment fragment = getTargetFragment();
                        int hour = mTimePicker.getHour();
                        int minute = mTimePicker.getMinute();
                        Intent intent = new Intent();
                        intent.putExtra(EXTRA_HOUR, hour);
                        intent.putExtra(EXTRA_MINUTE, minute);
                        fragment.onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
                    }
                })
                .setNegativeButton(android.R.string.cancel, null);
        AlertDialog dialog = builder.create();
        return dialog;

    }

    private void findViews(View view) {
        mTimePicker = view.findViewById(R.id.time_picker);
    }
}