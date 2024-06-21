package com.cyyaw.testwebrtc.aaaa;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.cyyaw.testwebrtc.aaaa.webrtc.session.CallSession;
import com.cyyaw.testwebrtc.aaaa.webrtc.SkyEngineKit;

/**
 * 耳机处理
 */
public class HeadsetPlugReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.hasExtra("state")) {
            CallSession session = SkyEngineKit.Instance().getCurrentSession();
            if (session == null) {
                return;
            }
            if (intent.getIntExtra("state", 0) == 0) { //拔出耳机
                session.toggleHeadset(false);
            } else if (intent.getIntExtra("state", 0) == 1) { //插入耳机
                session.toggleHeadset(true);
            }
        }
    }
}