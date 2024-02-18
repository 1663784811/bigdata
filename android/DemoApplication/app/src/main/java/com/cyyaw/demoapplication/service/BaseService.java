package com.cyyaw.demoapplication.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

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

    public void onCreate() {
        String receiverClass = receiverClass();
        if (null != receiverClass) {
            registerReceiver(new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    if (receiverClass.equals(intent.getAction())) {
                        String data = intent.getStringExtra("data");
                        // 处理接收到的数据
                        receiveMsg(data);
                    }
                }
            }, new IntentFilter(receiverClass));
        }
    }

    public String receiverClass() {
        return null;
    }

    /**
     * 接收到的message
     */
    public void receiveMsg(String msg) {
    }


}
