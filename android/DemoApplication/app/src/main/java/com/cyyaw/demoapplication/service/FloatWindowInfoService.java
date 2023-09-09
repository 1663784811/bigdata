package com.cyyaw.demoapplication.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cyyaw.demoapplication.R;
import com.cyyaw.demoapplication.data.LogInfoAdapter;
import com.cyyaw.demoapplication.service.window.FloatWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * 打印信息
 */
public class FloatWindowInfoService extends Service {

    public static String logKey = "msg";

    private WindowManager wManager;

    private FloatWindow floatWindow;

    private WindowManager.LayoutParams layoutParams;

    private List<String> itemList = new ArrayList<>();

    private LogInfoAdapter adapter;

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Context context = getApplicationContext();
        if (null == wManager) {
            wManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            floatWindow = FloatWindow.crateDefaultWindow(context, wManager, R.layout.float_window_info);
            layoutParams = floatWindow.getFloatWindowParams();

            recyclerView = floatWindow.findViewById(R.id.recyclerInfoList);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new LogInfoAdapter(itemList);
            recyclerView.setAdapter(adapter);
            wManager.addView(floatWindow, layoutParams);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 在服务端 Service 中接收来自客户端 Service 的数据
        if (intent != null && intent.hasExtra(FloatWindowInfoService.logKey)) {
            String msg = intent.getStringExtra(FloatWindowInfoService.logKey);
            // 在这里处理接收到的数据
            // 例如，将数据记录到日志
            // 或执行其他操作
            // 这里只是示例，你可以根据需求处理数据
            Log.i("显示日志", msg);
            itemList.add(msg);
            int newItemPosition = itemList.size() - 1;
            adapter.notifyItemInserted(newItemPosition);
            // ====
            View scrollView = floatWindow.findViewById(R.id.scrollViewListData);
            scrollView.scrollTo(0, recyclerView.getBottom());
        }
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 输出信息到窗口
     */
    public void outInfo(String msg) {
        floatWindow.findViewById(R.class.getModifiers());
    }
}