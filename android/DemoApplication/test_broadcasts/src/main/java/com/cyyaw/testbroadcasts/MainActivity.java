package com.cyyaw.testbroadcasts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    public static final String receiverKey = "com.cyyaw.testbroadcasts.MainActivity";


    // 动态注册广播接收器
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // 处理接收到的广播
            if (receiverKey.equals(intent.getAction())) {
                Toast.makeText(MainActivity.this, "sssss", Toast.LENGTH_SHORT).show();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // 注册广播接收器
        IntentFilter filter = new IntentFilter();
        filter.addAction(receiverKey);
        registerReceiver(receiver, filter);


        // 发送标准广播
        Intent intent = new Intent(receiverKey);
        sendBroadcast(intent);





    }


    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(receiver);
    }
}


