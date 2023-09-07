package com.cyyaw.demoapplication.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.view.WindowManager;

import androidx.annotation.Nullable;

public class FloatWindowService extends Service {

    private WindowManager wManager;

    private FloatWindow floatWindow;

    private WindowManager.LayoutParams layoutParams;




    @Override
    public void onCreate() {
        Log.i("ssssssssssssssssssssssssssssss", "seeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
        Context context = getApplicationContext();
        wManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        floatWindow = FloatWindow.crateDefaultWindow(context, wManager);
//        floatWindow.setBackground(getDrawable(R.drawable.float_bg));
        layoutParams = floatWindow.getFloatWindowParams();
        wManager.addView(floatWindow, layoutParams);
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








}
