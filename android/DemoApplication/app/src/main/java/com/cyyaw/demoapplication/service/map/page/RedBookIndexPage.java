package com.cyyaw.demoapplication.service.map.page;


import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

/**
 * 小红书首页
 */
public class RedBookIndexPage implements IsPage{


    @Override
    public boolean isThisPage(AccessibilityNodeInfo nodeInfo) {
        List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByViewId("com.xingin.xhs:id/hvs");
        if (null != list && list.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
