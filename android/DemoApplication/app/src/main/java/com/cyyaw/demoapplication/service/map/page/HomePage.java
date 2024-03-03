package com.cyyaw.demoapplication.service.map.page;


import android.view.accessibility.AccessibilityNodeInfo;

import com.cyyaw.demoapplication.service.ScreenOperation;

/**
 * 判断是否是Home 页面
 */
public class HomePage implements IsPage {


    @Override
    public boolean isThisPage(ScreenOperation nodeInfo) {
        AccessibilityNodeInfo rootInActiveWindow = nodeInfo.getRootInActiveWindow();
        if (null != rootInActiveWindow) {
            CharSequence packageName = rootInActiveWindow.getPackageName();
            if ("com.miui.home".equals(packageName + "")) {
                return true;
            }
        }
        return false;
    }
}
