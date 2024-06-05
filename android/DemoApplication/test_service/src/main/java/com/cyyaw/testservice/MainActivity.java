package com.cyyaw.testservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

import com.cyyaw.testservice.service.MyService;
import com.cyyaw.testservice.service.MyService2;

public class MainActivity extends AppCompatActivity {


    private MyService myService;


    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            MyService.LocalBinder binder = (MyService.LocalBinder) service;
            myService = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button test01Btn = findViewById(R.id.test01Btn);
        test01Btn.setOnClickListener((View v)->{
            myService.performTask();
        });

        Button startServiceBtn = findViewById(R.id.startServiceBtn);
        startServiceBtn.setOnClickListener((View v)->{
            Intent intent = new Intent(this, MyService2.class);
            startService(intent);
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        // Bind to MyService
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (myService != null) {
            unbindService(connection);
        }
    }

}
