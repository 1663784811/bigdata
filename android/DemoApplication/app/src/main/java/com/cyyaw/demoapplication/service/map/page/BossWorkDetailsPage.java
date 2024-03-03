package com.cyyaw.demoapplication.service.map.page;

import android.view.accessibility.AccessibilityNodeInfo;

import com.cyyaw.demoapplication.service.ScreenOperation;

import java.util.List;

public class BossWorkDetailsPage implements IsPage {
    @Override
    public boolean isThisPage(ScreenOperation nodeInfo) {
        List<AccessibilityNodeInfo> list = nodeInfo.findNodeInfoById("com.hpbr.bosszhipin:id/rv_list");
        if (null != list && list.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
