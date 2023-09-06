package com.example.hlapplication.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.WindowManager;

import androidx.annotation.Nullable;

public class FloatWindowService  extends Service {

    /**
     * // 窗口管理者
     */
    private WindowManager wManager;
    /**
     * // 窗口的属性
     */
    private WindowManager.LayoutParams mParams;

//    private FloatButtonLayout windowView;

    private SurfaceHolder holder;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



    @Override
    public void onCreate() {

        super.onCreate();
        wManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        mParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.TYPE_SYSTEM_ERROR, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSPARENT);
        mParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;// 系统提示window
        mParams.format = PixelFormat.TRANSLUCENT;// 支持透明
        // mParams.format = PixelFormat.RGBA_8888;
        mParams.flags |= WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;// 焦点
        mParams.width = WindowManager.LayoutParams.WRAP_CONTENT;// 窗口的宽和高
        mParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mParams.gravity = Gravity.LEFT | Gravity.TOP;
//        mParams.y = SharedPreferencesUtils.getSharedPreferencesUtils().getParamsY(getApplicationContext());
//        mParams.x = SharedPreferencesUtils.getSharedPreferencesUtils().getParamsX(getApplicationContext());
//        mParams.windowAnimations = android.R.style.Animation_Toast;
//        // mParams.alpha = 0.8f;//窗口的透明度
//
//        LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
//        windowView = (FloatButtonLayout) layoutInflater.inflate(R.layout.float_button_layout, null);
//
//        wManager.addView(windowView, mParams);// 添加窗口

    }



}
