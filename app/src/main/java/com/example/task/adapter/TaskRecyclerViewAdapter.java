package com.example.task.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task.R;
import com.example.task.controller.fragment.TaskDetailDialogFragment;
import com.example.task.model.Task;
import com.example.task.utils.DateTime;

import java.util.List;

import static com.example.task.controller.activity.PagerActivity.TASK_DETAIL_DIALOG_FRAGMENT;

public class TaskRecyclerViewAdapter extends RecyclerView.Adapter<TaskRecyclerViewAdapter.TaskViewHolder> {

    private List<Task> mTaskList;
    private Activity mActivity;

    public void setTaskList(List<Task> taskList) {
        mTaskList = taskList;
    }

    public TaskRecyclerViewAdapter(List<Task> taskList, Activity activity) {
        mTaskList = taskList;
        mActivity = activity;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = mTaskList.get(position);

        holder.bindTask(task);
        if (position % 2 == 0)
            holder.mLayoutRow.setBackgroundColor(Color.parseColor("#00FFFFFF"));

        else {
//            holder.mLayoutRow.setBackgroundColor(Color.parseColor("#27FF0057"));
            holder.mLayoutRow.setBackgroundResource(R.drawable.odd_row_background);
        }
    }

    @Override
    public int getItemCount() {
        return mTaskList.size();
    }


    public class TaskViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewTitle;
        private TextView mTextViewDescription;
        private TextView mTextViewDateTime;
        private RelativeLayout mLayoutRow;
        private TextView mTextViewIcon;
        private ImageButton mImageButtonShare;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewTitle = itemView.findViewById(R.id.text_view_row_name);
            mTextViewDescription = itemView.findViewById(R.id.text_view_row_description);
            mTextViewDateTime = itemView.findViewById(R.id.text_view_row_date_time);
            mLayoutRow = itemView.findViewById(R.id.layout_row);
            mTextViewIcon = itemView.findViewById(R.id.text_view_row_icon);
            mImageButtonShare = itemView.findViewById(R.id.image_button_share);

            mLayoutRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TaskDetailDialogFragment taskDetailDialogFragment =
                            TaskDetailDialogFragment.newInstance(mTaskList.get(getAdapterPosition()));
                    taskDetailDialogFragment.show(((AppCompatActivity) mActivity).getSupportFragmentManager(), TASK_DETAIL_DIALOG_FRAGMENT);
                }
            });

            mImageButtonShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, shareText());
                    sendIntent.setType("text/plain");
                    Intent shareIntent = Intent.createChooser(sendIntent, null);
                    mActivity.startActivity(shareIntent);
                }
            });


        }

        private String shareText() {
            String shareText =
                    "Title: " + mTaskList.get(getAdapterPosition()).getTitle() + "\n" +
                    "Description: " + mTaskList.get(getAdapterPosition()).getDescription() + "\n" +
                    "Date: " + DateTime.getDate(mTaskList.get(getAdapterPosition()).getDate()) + "\n" +
                    "Time: " + DateTime.getTime(mTaskList.get(getAdapterPosition()).getDate()) + "\n" +
                    "State: " + mTaskList.get(getAdapterPosition()).getTaskState();
            return shareText;
        }

        public void bindTask(Task task) {
            mTextViewTitle.setText(task.getTitle());
            mTextViewDescription.setText(task.getDescription());
            mTextViewDateTime.setText(task.getDate().toString());
            if (task.getTitle().length() != 0)
                mTextViewIcon.setText(task.getTitle().substring(0, 1));
        }
    }
}
