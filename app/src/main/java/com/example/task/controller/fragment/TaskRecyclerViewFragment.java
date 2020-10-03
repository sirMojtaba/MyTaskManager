package com.example.task.controller.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.task.R;
import com.example.task.model.Task;
import com.example.task.repository.TaskRepository;

import java.util.List;


public class TaskRecyclerViewFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private TaskRepository mTaskRepository;

    public TaskRecyclerViewFragment() {
        // Required empty public constructor
    }

    public static TaskRecyclerViewFragment newInstance() {
        TaskRecyclerViewFragment fragment = new TaskRecyclerViewFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTaskRepository = TaskRepository.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task_recycler_view, container, false);

        findViews(view);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        TaskAdapter taskAdapter = new TaskAdapter(mTaskRepository.getTasks());
        mRecyclerView.setAdapter(taskAdapter);

        return view;
    }

    private void findViews(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_view);
    }

    private class TaskHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewName;
        private TextView mTextViewState;
        private LinearLayout mLayoutRow;


        public TaskHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewName = itemView.findViewById(R.id.text_view_row_name);
            mTextViewState = itemView.findViewById(R.id.text_view_row_state);
            mLayoutRow = itemView.findViewById(R.id.layout_row);
        }

        public void bindTask(Task task) {
            mTextViewName.setText(task.getTitle());
            mTextViewState.setText(String.valueOf(task.getTaskState()));
        }
    }

    private class TaskAdapter extends RecyclerView.Adapter<TaskHolder> {
        private List<Task> mTasks;

        public TaskAdapter(List<Task> tasks) {
            mTasks = tasks;
        }

        @NonNull
        @Override
        public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.list_row, parent, false);

            TaskHolder taskHolder = new TaskHolder(view);
            return taskHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull TaskHolder holder, int position) {

            Task task = mTasks.get(position);
            holder.bindTask(task);
            if (position % 2 == 0)
                holder.mLayoutRow.setBackgroundColor(Color.parseColor("#00FFFFFF"));

            else {
                holder.mLayoutRow.setBackgroundColor(Color.parseColor("#27FF0057"));
            }
        }

        @Override
        public int getItemCount() {
            return mTasks.size();
        }
    }
}