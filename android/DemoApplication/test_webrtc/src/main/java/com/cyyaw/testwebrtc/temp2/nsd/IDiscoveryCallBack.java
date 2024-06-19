package com.cyyaw.testwebrtc.temp2.nsd;

import android.net.nsd.NsdServiceInfo;

public interface IDiscoveryCallBack {
    void onServiceFound(NsdServiceInfo nsdServiceInfo);

    void onServiceLost(NsdServiceInfo nsdServiceInfo);
}