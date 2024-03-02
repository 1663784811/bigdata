package com.cyyaw.demoapplication.service.map.page;

import android.view.accessibility.AccessibilityNodeInfo;

import com.cyyaw.demoapplication.service.map.page.IsPage;

import java.util.List;

public class BossIndexPage implements IsPage {
    @Override
    public boolean isThisPage(AccessibilityNodeInfo nodeInfo) {
        CharSequence packageName = nodeInfo.getPackageName();
        if ("com.hpbr.bosszhipin".equals(packageName + "")) {
            List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByViewId("com.hpbr.bosszhipin:id/appBarLayout");
            if (null != list && list.size() > 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
