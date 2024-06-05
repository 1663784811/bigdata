package com.cyyaw.testbroadcasts;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

public class MyService extends Service {

    private BroadcastReceiver br = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            if (MainActivity.broadcastKey.equals(intent.getAction())) {
                String message = intent.getStringExtra("data");
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                System.out.println(MyService.this);
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        // 注册广播接收器
        IntentFilter filter = new IntentFilter();
        filter.addAction(MainActivity.broadcastKey);
        ContextCompat.registerReceiver(this, br, filter,  ContextCompat.RECEIVER_EXPORTED);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(br);
    }
}