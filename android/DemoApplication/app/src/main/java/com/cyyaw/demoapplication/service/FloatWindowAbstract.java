package com.cyyaw.demoapplication.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public abstract class FloatWindowAbstract extends Service {

    public static final String winLog = "com.cyyaw.demoapplication.service.FloatWindowInfoService";


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    public void sendLog(String log) {
        Intent intent = new Intent();
        intent.setAction(winLog);
        intent.putExtra("aaa", log);
        sendBroadcast(intent);
    }

}
