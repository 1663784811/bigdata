package com.cyyaw.testwebrtc;

import android.app.Application;

import com.cyyaw.testwebrtc.core.util.CrashHandler;
import com.cyyaw.testwebrtc.core.voip.VoipEvent;
import com.cyyaw.testwebrtc.net.HttpRequestPresenter;
import com.cyyaw.testwebrtc.net.urlconn.UrlConnRequest;
import com.cyyaw.testwebrtc.rtc.SkyEngineKit;


/**
 * Created by dds on 2019/8/25.
 * android_shuai@163.com
 */
public class App extends Application {

    private static App app;
    private String username = "33";
    private String roomId = "11";
    private String otherUserId = "";

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        Thread.setDefaultUncaughtExceptionHandler(new CrashHandler());
        // init http request
        HttpRequestPresenter.init(new UrlConnRequest());
        // init SkyEngineKit
        SkyEngineKit.init(new VoipEvent());

    }

    public static App getInstance() {
        return app;
    }

    public String getUsername() {

//        return username;
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getOtherUserId() {
        return otherUserId;
    }

    public void setOtherUserId(String otherUserId) {
        this.otherUserId = otherUserId;
    }
}
