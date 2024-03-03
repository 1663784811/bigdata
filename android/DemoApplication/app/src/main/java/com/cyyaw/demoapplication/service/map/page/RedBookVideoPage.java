package com.cyyaw.demoapplication.service.map.page;


import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

/**
 * 小红书视频
 */
public class RedBookVideoPage implements IsPage{


    @Override
    public boolean isThisPage(AccessibilityNodeInfo nodeInfo) {
        List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByViewId("com.xingin.xhs:id/j81");
        if (null != list && list.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
