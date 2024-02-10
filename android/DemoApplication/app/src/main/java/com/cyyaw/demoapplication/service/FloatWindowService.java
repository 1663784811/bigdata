package com.cyyaw.demoapplication.service;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import com.cyyaw.demoapplication.R;
import com.cyyaw.demoapplication.service.window.FloatWindow;
import com.cyyaw.demoapplication.task.ThreadController;

import java.util.List;

/**
 * 辅助 触发
 */
public class FloatWindowService extends AccessibilityService implements View.OnClickListener {

    public static final String TAG = "FloatWindowService";


    private WindowManager wManager;

    private FloatWindow floatWindow;

    private WindowManager.LayoutParams layoutParams;

    private Context context;


    private ThreadController threadController = new ThreadController();


    @Override
    public void onCreate() {
        context = getApplicationContext();
        createWindow();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        int eventType = event.getEventType();
        if (eventType == 222) {
            Log.d("mmmmmmmm", "seeeeeeeeeeeeeeeeeeeeeeeddddddeeeeeee:" + eventType);
            CharSequence packageName = event.getPackageName();
//            AccessibilityNodeInfo root = getRootInActiveWindow();
//           root.performAction(AccessibilityNodeInfo.ACTION_CLICK);
        }

    }

    @Override
    public void onInterrupt() {

    }


    /**
     * 创建窗口
     */
    private void createWindow() {
        if (wManager == null) {
            wManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            floatWindow = FloatWindow.crateDefaultWindow(context, wManager);
            layoutParams = floatWindow.getFloatWindowParams();
            floatWindow.findViewById(R.id.btnWinInfo).setOnClickListener(this);
            wManager.addView(floatWindow, layoutParams);
        }
    }


    @Override
    public void onClick(View v) {
        try {
//            AccessibilityNodeInfo rootInActiveWindow = getRootInActiveWindow();
//            CharSequence packageName = rootInActiveWindow.getPackageName();
//            List<AccessibilityNodeInfo.AccessibilityAction> actionList = rootInActiveWindow.getActionList();
//
//            Log.d(TAG, "onClick: " + packageName);
//            // 开启任务
//            ServerMessage.sendMsg(FloatWindowLogService.class, String.valueOf(packageName));
            threadController.start(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
