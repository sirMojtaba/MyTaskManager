package com.example.task.controller.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.task.R;


public class TaskImageDialogFragment extends DialogFragment {
    public static final String ARGS_TASK_IMAGE = "task_image";
    private ImageView mImageViewTaskImage;
    private Bitmap mBitmap;



    public TaskImageDialogFragment() {
        // Required empty public constructor
    }

    public static TaskImageDialogFragment newInstance(Bitmap bitmap) {
        TaskImageDialogFragment fragment = new TaskImageDialogFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARGS_TASK_IMAGE, bitmap);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBitmap = getArguments().getParcelable(ARGS_TASK_IMAGE);

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_task_image_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view)
                .setPositiveButton(android.R.string.ok, null);
        mImageViewTaskImage = view.findViewById(R.id.image_view_task_full_size_image);
        mImageViewTaskImage.setImageBitmap(mBitmap);

        return builder.create();
    }
}