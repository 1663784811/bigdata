package com.cyyaw.demoapplication.service.map.page;


import android.view.accessibility.AccessibilityNodeInfo;

import com.cyyaw.demoapplication.service.ScreenOperation;

import java.util.List;

/**
 * 小红书视频
 */
public class RedBookLivePage implements IsPage{


    @Override
    public boolean isThisPage(ScreenOperation nodeInfo) {
        List<AccessibilityNodeInfo> list = nodeInfo.findNodeInfoById("com.xingin.xhs:id/pi");
        if (null != list && list.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
