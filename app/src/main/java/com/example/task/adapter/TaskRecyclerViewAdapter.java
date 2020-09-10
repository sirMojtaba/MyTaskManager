package com.example.task.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task.R;
import com.example.task.model.Task;

import java.util.List;

public class TaskRecyclerViewAdapter extends RecyclerView.Adapter<TaskRecyclerViewAdapter.TaskViewHolder> {

    private List<Task> mTaskList;

    public void setTaskList(List<Task> taskList) {
        mTaskList = taskList;
    }

    public TaskRecyclerViewAdapter(List<Task> taskList) {
        mTaskList = taskList;
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
            holder.mLayoutRow.setBackgroundColor(Color.parseColor("#27FF0057"));
        }
    }

    @Override
    public int getItemCount() {
        return mTaskList.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewName;
        private TextView mTextViewState;
        private LinearLayout mLayoutRow;


        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewName = itemView.findViewById(R.id.text_view_row_name);
            mTextViewState = itemView.findViewById(R.id.text_view_row_state);
            mLayoutRow = itemView.findViewById(R.id.layout_row);
        }

        public void bindTask(Task task) {
            mTextViewName.setText(task.getName());
            mTextViewState.setText(String.valueOf(task.getTaskState()));
        }
    }

}
