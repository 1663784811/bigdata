package com.example.hlapplication;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.example.hlapplication.service.AccessibilityServiceMonitor;
import com.example.hlapplication.util.AlarmTaskUtil;
import com.example.hlapplication.util.Config;
import com.example.hlapplication.util.ShareUtil;


public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initValue();
    }

    /**
     * 初始化值
     */
    private void initValue() {
        startAlarmTask(this);
    }

    public static void startAlarmTask(Context mContext) {
        ShareUtil mShareUtil = new ShareUtil(mContext);
        int hour = mShareUtil.getInt(Config.KEY_HOUR, 07);
        int minute = mShareUtil.getInt(Config.KEY_MINUTE,0);

        Intent intent = new Intent(mContext, AccessibilityServiceMonitor.class);
        intent.setAction(AccessibilityServiceMonitor.ACTION_ALAM_TIMER);
        AlarmTaskUtil.starRepeatAlarmTaskByService(mContext, hour, minute, 0, intent);
    }
}
