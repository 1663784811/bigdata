package com.cyyaw.demoapplication.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import com.cyyaw.demoapplication.util.PreferenceUtil;

public class FloatWindowService extends Service {

    private WindowManager wManager;

    private FloatWindow floatWindow;

    private WindowManager.LayoutParams layoutParams;


    @Override
    public void onCreate() {
        Log.i("ssssssssssssssssssssssssssssss", "seeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
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

        Log.i("================================", "sssssssssssssssssssssssssss");

        return null;
    }


    private void createWindow(Context context) {
        wManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        floatWindow = FloatWindow.crateDefaultWindow(context, wManager);
        layoutParams = floatWindow.getFloatWindowParams();
        wManager.addView(floatWindow, layoutParams);
    }


}
