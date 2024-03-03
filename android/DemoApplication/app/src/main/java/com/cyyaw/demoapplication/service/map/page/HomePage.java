package com.cyyaw.demoapplication.service.map.page;


import android.view.accessibility.AccessibilityNodeInfo;

import com.cyyaw.demoapplication.service.ScreenOperation;

/**
 * 判断是否是Home 页面
 */
public class HomePage implements IsPage {


    @Override
    public boolean isThisPage(ScreenOperation nodeInfo) {
        CharSequence packageName = nodeInfo.getRootInActiveWindow().getPackageName();
        if ("com.miui.home".equals(packageName + "")) {
            return true;
        } else {
            return false;
        }
    }
}
