package com.cyyaw.testservice.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService2 extends Service {

    private static final String TAG = "MyService2";

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "dddddddddddddddddd", Toast.LENGTH_LONG).show();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(this, "sssssssssssssssssssssssss", Toast.LENGTH_LONG).show();



        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}