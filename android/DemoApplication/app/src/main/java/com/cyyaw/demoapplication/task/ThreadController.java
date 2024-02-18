package com.cyyaw.demoapplication.task;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.graphics.Rect;
import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;

import com.cyyaw.demoapplication.service.FloatWindowLogService;

/**
 * 任务控制线程
 * 启动、停止、暂停
 */
public class ThreadController {

    public static final String TAG = "ThreadController";


    private volatile AccessibilityService accessibilityService;


//    private View aa= new View();

    /**
     * 启动
     */
    public void start(AccessibilityService accessibilityService) {
        this.accessibilityService = accessibilityService;
////        ThreadTask threadTask = new ThreadTask();
////        threadTask.run();
//
//        //  ==============================================
//        AccessibilityNodeInfo rootInActiveWindow = accessibilityService.getRootInActiveWindow();
//        CharSequence packageName = rootInActiveWindow.getPackageName();
//        List<AccessibilityNodeInfo.AccessibilityAction> actionList = rootInActiveWindow.getActionList();
//        Log.d(TAG, "onClick: " + packageName);
//        // 开启任务
//        ServerMessage.sendMsg(FloatWindowLogService.class, String.valueOf(packageName));
//        traverseLayout(rootInActiveWindow);


    }


    /**
     * 遍历布局文件
     */
    private void traverseLayout(AccessibilityNodeInfo nodeInfo) {
        if (nodeInfo == null) {
            return;
        }
        // 获取节点的文本内容
        CharSequence text = nodeInfo.getText();
        // 获取节点的类名
        CharSequence className = nodeInfo.getClassName();
        Rect boundsInScreen = new Rect();
        nodeInfo.getBoundsInScreen(boundsInScreen);
        int left = boundsInScreen.left;
        int top = boundsInScreen.top;

        System.out.println("节点在屏幕上的左上角坐标：(" + className + "--" + text + "---" + left + ", " + top + ")");


        if ("微信".equals(text)) {
            // 点击打开微信
            AccessibilityNodeInfo parent = nodeInfo.getParent();
            parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
//            showWindowInfo("点击：" + text);
//            SquareView square = AppUtil.createSquare(wManager, context, parent);
        }
        // 递归遍历子节点
        for (int i = 0; i < nodeInfo.getChildCount(); i++) {
            traverseLayout(nodeInfo.getChild(i));
        }
        // ========================================================
    }

    /**
     * 获取窗口信息
     */
    public void getWinInfo(AccessibilityService accessibilityService) {
        AccessibilityNodeInfo rootInActiveWindow = accessibilityService.getRootInActiveWindow();
        CharSequence packageName = rootInActiveWindow.getPackageName();
        Log.d("mmmmmmmm", "seeeeeeeeeee:" + packageName);
        // 获取包名
        new Thread(() -> {
            accessibilityService.sendBroadcast(new Intent(FloatWindowLogService.class.getName()).putExtra("data", packageName));
        }).start();

    }

    /**
     * 更新窗口
     */
    public void updateWindow(AccessibilityService sss) {
        new Thread(() -> {
            getWinInfo(accessibilityService);
        }).start();


    }
}
