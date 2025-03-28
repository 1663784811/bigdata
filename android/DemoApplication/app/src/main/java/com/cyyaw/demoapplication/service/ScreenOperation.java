package com.cyyaw.demoapplication.service;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.content.Intent;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.SystemClock;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public abstract class ScreenOperation extends AccessibilityService {


    public volatile boolean dug = true;


    @Override
    public void onInterrupt() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    // =============================================


    /**
     * 点击Home
     */
    public void keyHome() {
        performGlobalAction(AccessibilityService.GLOBAL_ACTION_HOME);
        SystemClock.sleep(500);
    }

    /**
     * 返回按键
     */
    public void back() {
        performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK);
        SystemClock.sleep(500);
    }


    /**
     * 点击
     */
    public void clickAtXY(int x, int y) {
        sendBroadcast(new Intent(FloatWindowLogService.class.getName()).putExtra("data", String.format("构建点击: %s,%s", x, y)));
        Path path = new Path();
        path.moveTo(x, y);
        path.lineTo(x + 2, y + 2);
        dispatchGesture(new GestureDescription.Builder().addStroke(new GestureDescription.StrokeDescription(path, 0, 100)).build(), null, null);
        SystemClock.sleep(500);
    }

    /**
     * 划动
     */
    public void performSwipeLeft(int x1, int y1, int x2, int y2, int duration) {
        sendBroadcast(new Intent(FloatWindowLogService.class.getName()).putExtra("data", String.format("滑动手势: %s,%s - %s,%s", x1, y1, x2, y2)));
        Path path = new Path();
        path.moveTo(x1, y1);
        path.lineTo(x2, y2);
        GestureDescription.Builder gestureBuilder = new GestureDescription.Builder();
        gestureBuilder.addStroke(new GestureDescription.StrokeDescription(path, 0, duration));
        // 发送手势事件
        dispatchGesture(gestureBuilder.build(), null, null);
        SystemClock.sleep(duration + 300);
    }


    // =============================================

    /**
     * 打开APP
     *
     * @return
     */
    public boolean openApp(String packageName, String appName) {
        AccessibilityNodeInfo windowRoot = getRootInActiveWindow();
        CharSequence pk = windowRoot.getPackageName();
        sendBroadcast(new Intent(FloatWindowLogService.class.getName()).putExtra("data", "打开:" + appName));
        if (!packageName.equals(pk)) {
            // 不在当前的package
            keyHome();
            SystemClock.sleep(100);
            AccessibilityNodeInfo nodeInfo = findNodeInfoByName(appName);
            if (nodeInfo != null) {
                AccessibilityNodeInfo parent = nodeInfo.getParent();
                clickNode(parent);
                SystemClock.sleep(500);
                return true;
            }
        } else {
            return true;
        }
        sendBroadcast(new Intent(FloatWindowLogService.class.getName()).putExtra("data", "没有找到:" + appName));
        return false;
    }

    /**
     * 点击节点
     */
    public boolean clickNodeById(String id) {
        List<AccessibilityNodeInfo> nodeInfoList = findNodeInfoById(id);
        if (null != nodeInfoList && nodeInfoList.size() > 0) {
            AccessibilityNodeInfo nodeInfo = nodeInfoList.get(0);
//            nodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            clickNode(nodeInfo);
            SystemClock.sleep(100);
            return true;
        }
        return false;
    }

    public void clickNode(AccessibilityNodeInfo nodeInfo) {
        markRect(nodeInfo, null);
        Rect nodeRect = getNodeRect(nodeInfo);
        // 计算中心点
        int x = (nodeRect.right - nodeRect.left) / 2 + nodeRect.left;
        int y = (nodeRect.bottom - nodeRect.top) / 2 + nodeRect.top;
        clickAtXY(x, y);
    }


    // ==================================================================================
    // ==================================================================================
    public AccessibilityNodeInfo findNodeInfoById(String id, int index) {
        return findNodeInfoById(getRootInActiveWindow(), id, index);
    }

    public AccessibilityNodeInfo findNodeInfoById(AccessibilityNodeInfo nodeInfo, String id, int index) {
        List<AccessibilityNodeInfo> nodeInfoList = null;
        try {
            nodeInfoList = nodeInfo.findAccessibilityNodeInfosByViewId(id);
        } catch (Exception ignored) {

        }
        if (null != nodeInfoList && nodeInfoList.size() > index) {
            return nodeInfoList.get(index);
        }
        return null;
    }

    /**
     * 查找节点
     */
    public List<AccessibilityNodeInfo> findNodeInfoById(String id) {
        List<AccessibilityNodeInfo> nodeInfoList = null;
        try {
            nodeInfoList = getRootInActiveWindow().findAccessibilityNodeInfosByViewId(id);
        } catch (Exception ignored) {
        }
        return nodeInfoList;
    }


    public AccessibilityNodeInfo findNodeInfoByName(String name) {
        return traverseLayout(getRootInActiveWindow(), name);
    }

    /**
     * 遍历布局文件
     */
    public AccessibilityNodeInfo traverseLayout(AccessibilityNodeInfo nodeInfo, String byName) {
        if (nodeInfo != null) {
            // 获取节点的文本内容
            CharSequence text = nodeInfo.getText();
            if (StrUtil.isNotBlank(byName) && byName.equals(text)) {
                return nodeInfo;
            }
            // 递归遍历子节点
            for (int i = 0; i < nodeInfo.getChildCount(); i++) {
                AccessibilityNodeInfo rest = traverseLayout(nodeInfo.getChild(i), byName);
                if (null != rest) {
                    return rest;
                }
            }
        }
        return null;
    }

    public Rect getNodeRect(AccessibilityNodeInfo nodeInfo) {
        Rect bounds = new Rect();
        nodeInfo.getBoundsInScreen(bounds);
        return bounds;
    }

    /**
     * 方形标记
     */
    public void markRect(AccessibilityNodeInfo nodeInfo) {
        Rect nodeRect = getNodeRect(nodeInfo);
        JSONObject json = new JSONObject();
        json.set("x1", nodeRect.left);
        json.set("y1", nodeRect.top);
        json.set("x2", nodeRect.right);
        json.set("y2", nodeRect.bottom);
        sendBroadcast(new Intent(FloatWindowLogService.class.getName()).putExtra("data", "坐标：(left:" + nodeRect.left + "--top" + nodeRect.top + "---right" + nodeRect.right + ", bottom" + nodeRect.bottom + ")"));
        sendBroadcast(new Intent(FloatMarkWindowService.class.getName()).putExtra("data", JSONUtil.toJsonStr(json)));

    }

    public void markRect(AccessibilityNodeInfo nodeInfo, Long time) {
        markRect(nodeInfo);
        SystemClock.sleep(time == null ? 1000L : time);
        removeMarkRect();
        SystemClock.sleep(time == null ? 1000L : time);
    }

    public void removeMarkRect() {
        JSONObject json = new JSONObject();
        json.set("x1", 0);
        json.set("y1", 0);
        json.set("x2", 1);
        json.set("y2", 1);
        sendBroadcast(new Intent(FloatMarkWindowService.class.getName()).putExtra("data", JSONUtil.toJsonStr(json)));
    }


    /**
     * 划动列表
     */
    public int strokeList(final int index, final String mark,final String boxId,final String childId) {
        boolean isBreak = true;
        int numx = 0;
        AccessibilityNodeInfo ch = null;
        do {
            performSwipeLeft(480, 1000, 480, 850, 200);
            SystemClock.sleep(1500);
            ch = findNodeInfoById(boxId, 0);
            if (ch != null) {
                int cc = ch.getChildCount();
                if (cc > index) {
                    String cs = null;
                    if (StrUtil.isNotBlank(childId)) {
                        cs = findNodeInfoById(ch.getChild(index), childId, 0).getText().toString();
                    } else {
                        cs = ch.getChild(index).getContentDescription().toString();
                    }
                    isBreak = mark.equals(cs);
                } else {
                    isBreak = false;
                }
            } else {
                numx++;
                back();
                if (numx > 5) {
                    isBreak = false;
                }
            }
        } while (isBreak);
        // 找Index 位置
        ch = findNodeInfoById(boxId, 0);
        if (ch != null) {
            for (int i = 0; i < ch.getChildCount(); i++) {
                CharSequence cc = null;
                if (StrUtil.isNotBlank(childId)) {
                    cc = findNodeInfoById(ch.getChild(i), childId, 0).getText().toString();
                } else {
                    cc = ch.getChild(i).getContentDescription();
                }
                if (null != cc && cc.equals(mark)) {
                    return i + 1;
                }
            }
        }
        return 0;
    }


}



