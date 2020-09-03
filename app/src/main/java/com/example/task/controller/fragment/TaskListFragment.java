package com.example.task.controller.fragment;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.task.R;
import com.example.task.model.Task;
import com.example.task.repository.TaskRepository;

import java.util.List;


public class TaskListFragment extends Fragment {
    private RecyclerView mRecyclerView;


    TaskRepository mTaskRepository;


    public TaskListFragment() {
        // Required empty public constructor
    }

    /*public static TaskFragment newInstance() {
        TaskFragment fragment = new TaskFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String mName = getActivity().getIntent().getStringExtra(StarterFragment.EXTRA_USER_NAME);
        int mNumberOfTasks = getActivity().getIntent().getIntExtra(StarterFragment.EXTRA_NUMBER_OF_TASKS, 0);

        mTaskRepository = TaskRepository.getInstance(mName, mNumberOfTasks);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);

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
            mTextViewName.setText(task.getName());
            mTextViewState.setText(String.valueOf(task.getState()));

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

            else{
                holder.mLayoutRow.setBackgroundColor(Color.parseColor("#27FF0057"));
            }
        }

        @Override
        public int getItemCount() {
            return mTasks.size();
        }
    }
}