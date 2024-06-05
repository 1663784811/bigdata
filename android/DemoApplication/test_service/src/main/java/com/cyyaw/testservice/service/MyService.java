package com.cyyaw.testservice.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {

    private static final String TAG = "MyService";


    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new LocalBinder();
    }

    public void performTask() {


        Toast.makeText(this, "sssssseeeeeeeeee", Toast.LENGTH_LONG).show();


    }


    public class LocalBinder extends Binder {
        public MyService getService() {
            return MyService.this;
        }
    }

}