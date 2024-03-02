package com.cyyaw.demoapplication.service.map.page;


import android.view.accessibility.AccessibilityNodeInfo;

/**
 *  判断是否是Home 页面
 */
public class HomePage implements IsPage{


    @Override
    public boolean isThisPage(AccessibilityNodeInfo nodeInfo) {
        return false;
    }
}
