package com.cyyaw.demoapplication.service.map.page;


import android.view.accessibility.AccessibilityNodeInfo;

/**
 * 判断是否是Home 页面
 */
public class HomePage implements IsPage {


    @Override
    public boolean isThisPage(AccessibilityNodeInfo nodeInfo) {
        CharSequence packageName = nodeInfo.getPackageName();
        if ("com.miui.home".equals(packageName + "")) {
            return true;
        } else {
            return false;
        }
    }
}
