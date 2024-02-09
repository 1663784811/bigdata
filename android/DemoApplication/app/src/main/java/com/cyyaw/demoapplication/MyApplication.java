package com.cyyaw.demoapplication;

import android.app.Application;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // 注册Activity生命周期回调
        // registerActivityLifecycleCallbacks(new MyActivityLifecycleCallbacks());
    }
}
