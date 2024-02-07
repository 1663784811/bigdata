package com.cyyaw.demoapplication.service;

import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityNodeInfo;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cyyaw.demoapplication.R;
import com.cyyaw.demoapplication.task.view.TaskBean;
import com.cyyaw.demoapplication.task.view.TaskListAdapter;
import com.cyyaw.demoapplication.service.window.FloatWindow;

import java.util.ArrayList;
import java.util.List;


/**
 * 任务窗口
 */
public class FloatWindowTaskService extends BaseService implements View.OnClickListener {

    private WindowManager wManager;

    private FloatWindow floatWindow;

    private WindowManager.LayoutParams layoutParams;

    private RecyclerView recyclerView;
    private List<TaskBean> itemList = new ArrayList<>();

    private TaskListAdapter adapter;

    @Override
    public void onCreate() {
        Context context = getApplicationContext();
        createWindow(context);
        ServerMessage.register(this);
    }

    /**
     * 创建窗口
     */
    private void createWindow(Context context) {
        WindowManager wManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        FloatWindow floatWindow = FloatWindow.crateDefaultWindow(context, wManager, R.layout.float_window_task);
        //====
        recyclerView = floatWindow.findViewById(R.id.recyclerInfoList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemList.add(new TaskBean("任务1", ""));
        itemList.add(new TaskBean("任务1", ""));
        itemList.add(new TaskBean("任务1", ""));
        itemList.add(new TaskBean("任务1", ""));
        itemList.add(new TaskBean("任务1", ""));


        adapter = new TaskListAdapter(itemList);
        recyclerView.setAdapter(adapter);
        //=================
        WindowManager.LayoutParams layoutParams = floatWindow.getFloatWindowParams();
        wManager.addView(floatWindow, layoutParams);
    }

    @Override
    public void onClick(View v) {
        ServerMessage.sendMsg(FloatWindowLogService.class, "----------");
        Log.d("AccessibilityService", "Left: " );

    }
}
