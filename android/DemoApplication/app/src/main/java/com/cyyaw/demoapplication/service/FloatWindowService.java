package com.cyyaw.demoapplication.service;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import com.cyyaw.demoapplication.R;
import com.cyyaw.demoapplication.service.window.FloatWindow;
import com.cyyaw.demoapplication.task.AppInfo;

/**
 * 辅助 触发
 */
public class FloatWindowService extends ScreenOperation implements View.OnClickListener {

    public static final String TAG = "FloatWindowService";


    private volatile static WindowManager wManager;

    private FloatWindow floatWindow;

    private WindowManager.LayoutParams layoutParams;

    private Context context;


    private volatile int index = 0;

    @Override
    public void onCreate() {
        if (wManager == null) {
            context = getApplicationContext();
            wManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            floatWindow = FloatWindow.crateDefaultWindow(context, wManager);
            layoutParams = floatWindow.getFloatWindowParams();
            floatWindow.findViewById(R.id.btn_start_task).setOnClickListener(this);
            floatWindow.findViewById(R.id.btnWinInfo).setOnClickListener(this);
            wManager.addView(floatWindow, layoutParams);
            // ==============
        }
    }


    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        int eventType = event.getEventType();
        if (eventType == 222) {
            Log.d("mmmmmmmm", "seeeeeeeeeeeeeeeeeeeeeeeddddddeeeeeee:" + eventType);
            CharSequence packageName = event.getPackageName();
        }

    }


    @Override
    public void onClick(View v) {
        try {
            int id = v.getId();
            if (R.id.btn_start_task == id) {
                // 获取窗口信息
                new Thread(() -> {


                    // =========== 第一步: 打开小红书
                    AppInfo appInfo = new AppInfo();
                    appInfo.setPackageName("com.xingin.xhs");
                    appInfo.setAppName("小红书");
                    openApp(appInfo);
                    // =========== 第二步: 点击首页
                    clickNodeById("com.xingin.xhs:id/dhc");
                    // ===========
                    SystemClock.sleep(2000);
                    // ===========
                    // 获取外部列表框
                    AccessibilityNodeInfo boxContent = findNodeInfoById("com.xingin.xhs:id/eq2", 0);

                    int childCount = boxContent.getChildCount();

                    if (childCount > index) {
                        AccessibilityNodeInfo child = boxContent.getChild(index);
                        clickNode(child);
                        // ===========
                        SystemClock.sleep(2000);
                        // ===========
                        back();
//                    markRect(child,1000L);
                    }


                    // ===========
                    clickAtXY(480, 520);
                    performSwipeLeft(480, 500, 480, 1000, 100);
                    SystemClock.sleep(100);

                }).start();


            } else if (R.id.btnWinInfo == id) {


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
