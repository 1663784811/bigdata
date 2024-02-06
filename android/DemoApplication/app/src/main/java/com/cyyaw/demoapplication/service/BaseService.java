package com.cyyaw.demoapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public abstract class BaseService extends Service {

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
     * 接收到的message
     */
    public void receiveMsg(String msg){}



}
