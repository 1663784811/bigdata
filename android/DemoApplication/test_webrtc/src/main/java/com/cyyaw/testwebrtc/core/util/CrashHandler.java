package com.cyyaw.testwebrtc.core.util;

import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.cyyaw.testwebrtc.App;
import com.cyyaw.testwebrtc.LauncherActivity;
import com.cyyaw.testwebrtc.aaaa.webrtc.session.CallSession;
import com.cyyaw.testwebrtc.aaaa.webrtc.SkyEngineKit;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static final String TAG = "MyUncaughtExceptionHand";

    @Override
    public void uncaughtException(@NonNull Thread thread, @NonNull Throwable ex) {
        SkyEngineKit gEngineKit = SkyEngineKit.Instance();
        CallSession session = gEngineKit.getCurrentSession();

        Log.d(TAG, "uncaughtException session = " + session);
        if (session != null) {
            gEngineKit.endCall();
        } else {
            gEngineKit.sendDisconnected(App.getInstance().getRoomId(), App.getInstance().getOtherUserId(), true);
        }
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        Throwable cause = ex;
        while (null != cause) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        final String stacktraceAsString = result.toString();
        Log.e(TAG, "uncaughtException: " + stacktraceAsString);
        printWriter.close();
        restartApp();
    }

    private void restartApp() {
        Intent i = new Intent(App.getInstance(), LauncherActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        App.getInstance().startActivity(i);
    }


}