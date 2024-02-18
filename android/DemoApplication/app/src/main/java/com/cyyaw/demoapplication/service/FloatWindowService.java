package com.cyyaw.demoapplication.service;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;

import com.cyyaw.demoapplication.R;
import com.cyyaw.demoapplication.service.window.FloatWindow;
import com.cyyaw.demoapplication.task.AppInfo;
import com.cyyaw.demoapplication.task.ThreadController;

import cn.hutool.json.JSONUtil;
import cn.hutool.json.JSONObject;

/**
 * 辅助 触发
 */
public class FloatWindowService extends ScreenOperation implements View.OnClickListener {

    public static final String TAG = "FloatWindowService";


    private WindowManager wManager;

    private FloatWindow floatWindow;

    private WindowManager.LayoutParams layoutParams;

    private Context context;


    private volatile ThreadController threadController = null;


    @Override
    public void onCreate() {
        context = getApplicationContext();
        createWindow();
        threadController = new ThreadController(this);
    }


    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        int eventType = event.getEventType();
        if (eventType == 222) {
            Log.d("mmmmmmmm", "seeeeeeeeeeeeeeeeeeeeeeeddddddeeeeeee:" + eventType);
            CharSequence packageName = event.getPackageName();
        }

    }

    /**
     * 创建窗口
     */
    private void createWindow() {
        if (wManager == null) {
            wManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            floatWindow = FloatWindow.crateDefaultWindow(context, wManager);
            layoutParams = floatWindow.getFloatWindowParams();
            floatWindow.findViewById(R.id.btnWinInfo).setOnClickListener(this);
            wManager.addView(floatWindow, layoutParams);
        }
    }


    @Override
    public void onClick(View v) {
        try {
            int id = v.getId();
            if (R.id.btnWinInfo == id) {
                // 获取窗口信息
                new Thread(() -> {
                    AppInfo appInfo = new AppInfo();
                    appInfo.setPackageName("com.xingin.xhs");
                    appInfo.setAppName("小红书");
                    openApp(appInfo);
                    threadController.getWinInfo();
                    // 刷新
                    SystemClock.sleep(500);
                    clickAtXY(480, 520);

                    performSwipeLeft(480, 500, 480, 1000, 100);

                    SystemClock.sleep(100);
                    JSONObject json = new JSONObject();
                    json.set("x1", 500);
                    json.set("y1", 500);
                    json.set("x2", 510);
                    json.set("y2", 510);
                    sendBroadcast(new Intent(FloatMarkWindowService.class.getName()).putExtra("data", JSONUtil.toJsonStr(json)));

                }).start();

            } else if (id == 1111) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
