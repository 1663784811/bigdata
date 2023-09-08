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
 * 打印信息
 */
public class FloatWindowInfoService extends Service {

    private WindowManager wManager;

    private FloatWindow floatWindow;

    private WindowManager.LayoutParams layoutParams;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Context context = getApplicationContext();
        wManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        floatWindow = FloatWindow.crateDefaultWindow(context, wManager, R.layout.float_window_info);
        layoutParams = floatWindow.getFloatWindowParams();

        wManager.addView(floatWindow, layoutParams);


    }
}