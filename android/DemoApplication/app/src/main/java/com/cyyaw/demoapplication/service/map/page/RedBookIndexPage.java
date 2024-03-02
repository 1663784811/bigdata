package com.cyyaw.demoapplication.service.map.page;


import android.view.accessibility.AccessibilityNodeInfo;

/**
 * 小红书首页
 */
public class RedBookIndexPage implements IsPage{


    @Override
    public boolean isThisPage(AccessibilityNodeInfo nodeInfo) {
        return false;
    }
}
