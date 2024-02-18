package com.cyyaw.demoapplication.service;

import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityNodeInfo;

import com.cyyaw.demoapplication.R;
import com.cyyaw.demoapplication.service.window.FloatWindow;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;


/**
 * 标记
 */
public class FloatMarkWindowService extends BaseService implements View.OnClickListener {

    private WindowManager wManager;

    private FloatWindow floatWindow;

    private WindowManager.LayoutParams layoutParams;


    @Override
    public void onCreate() {
        super.onCreate();
        Context context = getApplicationContext();
        createWindow(context);
    }


    /**
     * 创建窗口
     */
    private void createWindow(Context context) {
        wManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        floatWindow = FloatWindow.crateDefaultWindow(context, wManager, R.layout.mark_circle);

        WindowManager.LayoutParams layoutParams = floatWindow.getFloatWindowParams();
        layoutParams.width = 0;
        layoutParams.height = 0;
        wManager.addView(floatWindow, layoutParams);


    }


    @Override
    public void onClick(View v) {


        Log.d("AccessibilityService", "Left: ");

    }

    public String receiverClass() {
        return FloatMarkWindowService.class.getName();
    }

    /**
     * 接收到的message
     */
    public void receiveMsg(String msg) {
        try {
            JSONObject json = new JSONObject(msg);
            updateWindow(json.getInt("x1"), json.getInt("y1"), json.getInt("x2"),  json.getInt("y2"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateWindow(int x1, int y1, int x2, int y2) {
        WindowManager.LayoutParams windowParams = floatWindow.getFloatWindowParams();
        windowParams.width = x2 - x1;
        windowParams.height = y2 - y1;
        windowParams.x = x1;
        windowParams.y = y1;
        wManager.updateViewLayout(floatWindow, windowParams);
    }

}
