package com.cyyaw.demoapplication.service.map.page;

import android.view.accessibility.AccessibilityNodeInfo;

/**
 * 判断当前页面
 */
public interface IsPage {


    boolean isThisPage(AccessibilityNodeInfo nodeInfo);


}
