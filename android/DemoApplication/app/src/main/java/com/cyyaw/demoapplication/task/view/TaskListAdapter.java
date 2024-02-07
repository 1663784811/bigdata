package com.cyyaw.demoapplication.task.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cyyaw.demoapplication.R;

import java.util.List;


/**
 * 任务适配器
 */
public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ViewHolder> {

    private List<TaskBean> items;

    public TaskListAdapter(List<TaskBean> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.float_window_task_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TaskBean item = items.get(position);
        holder.taskIndex.setText(""+position);
        holder.taskName.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView taskIndex;
        public TextView taskName;

        public ViewHolder(View itemView) {
            super(itemView);
            taskIndex = itemView.findViewById(R.id.task_index);
            taskName = itemView.findViewById(R.id.task_name);

        }
    }

}
