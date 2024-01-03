package com.cyyaw.demoapplication.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import com.cyyaw.demoapplication.R;
import com.cyyaw.demoapplication.service.window.FloatWindow;

/**
 * 任务窗口
 */
public class FloatWindowTaskService extends Service {


    private WindowManager wManager;

    private FloatWindow floatWindow;

    private WindowManager.LayoutParams layoutParams;


    @Override
    public void onCreate() {
        Context context = getApplicationContext();
        createWindow(context);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    /**
     * 创建窗口
     */
    private void createWindow(Context context) {
        WindowManager wManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        FloatWindow floatWindow = FloatWindow.crateDefaultWindow(context, wManager, R.layout.float_window_task);
        WindowManager.LayoutParams layoutParams = floatWindow.getFloatWindowParams();
        wManager.addView(floatWindow, layoutParams);
    }




}