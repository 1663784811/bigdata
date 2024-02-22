package com.cyyaw.demoapplication.service;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import com.cyyaw.demoapplication.R;
import com.cyyaw.demoapplication.activity.MainActivity;
import com.cyyaw.demoapplication.service.window.FloatWindow;
import com.cyyaw.demoapplication.task.AppInfo;

import java.math.BigDecimal;

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

    private volatile Thread thread;

    private static volatile boolean start = false;

    @Override
    public void onCreate() {
        if (wManager == null) {
            // ==============
            Intent in = new Intent(this, FloatWindowLogService.class);
            startService(in);
//            Intent ins = new Intent(this, FloatWindowTaskService.class);
//            startService(ins);
            // 创建操作跟踪红点
            Intent inx = new Intent(this, FloatMarkWindowService.class);
            startService(inx);

            context = getApplicationContext();
            wManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            floatWindow = FloatWindow.crateDefaultWindow(context, wManager);
            layoutParams = floatWindow.getFloatWindowParams();
            layoutParams.y = layoutParams.y - 300;
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
                if (!start) {
                    start = true;
                    thread = new Thread(() -> {
                        // =========== 第一步: 打开小红书
                        AppInfo appInfo = new AppInfo();
                        appInfo.setPackageName("com.xingin.xhs");
                        appInfo.setAppName("小红书");
                        openApp(appInfo);
                        // =========== 第二步: 点击首页
                        boolean ok = true;
                        do {
                            if (findNodeInfoById("com.xingin.xhs:id/dhc", 0) == null) {
                                back();
                                SystemClock.sleep(500);
                            } else {
                                ok = false;
                            }
                        } while (ok);
                        clickNodeById("com.xingin.xhs:id/dhc");
                        // ===========
                        SystemClock.sleep(2000);
                        // ===========
                        // 获取外部列表框


                        // ===============================================
                        // ===============================================
                        // ===============================================
                        // ===============================================
                        // ===============================================
                        String content = null;
                        ok = true;
                        do {
                            AccessibilityNodeInfo boxContent = findNodeInfoById("com.xingin.xhs:id/eq2", 0);
                            if (boxContent == null) {
                                SystemClock.sleep(2000);
                                boxContent = findNodeInfoById("com.xingin.xhs:id/eq2", 0);
                            }
                            int childCount = boxContent.getChildCount();
                            if (childCount > index) {
                                AccessibilityNodeInfo child = boxContent.getChild(index);
                                CharSequence chq = child.getContentDescription();
                                SystemClock.sleep(100);
                                clickNode(child);
                                // ===========
                                SystemClock.sleep(3000);
                                // 详情页面  com.xingin.xhs:id/hts
                                // =========== 收集数据
                                Log.d(TAG, "onClick: " + chq);
                                if (chq.toString().indexOf("视频") == 0) {


                                } else if (chq.toString().indexOf("笔记") == 0) {


                                } else if (chq.toString().indexOf("直播") == 0) {

                                }
                                // =========== 点击主页面
                                //clickNodeById("com.xingin.xhs:id/wo");
                                // =========== 收集数据

                                // =========== 返回列表页面 收集第二个

                                //SystemClock.sleep(100);
                                back();
                                //SystemClock.sleep(5000);
                                // ===========
                                //back();
//                    markRect(child,1000L);


                                // ================================================================     滑动     =======================================================
                                if (index > 2) {

                                    // 移动屏幕
                                    boolean isBreak = false;
                                    AccessibilityNodeInfo ch = null;
                                    do {
                                        performSwipeLeft(480, 1000, 480, 800, 200);
                                        SystemClock.sleep(1500);
                                        ch = findNodeInfoById("com.xingin.xhs:id/eq2", 0);
                                        CharSequence cs = ch.getChild(index).getContentDescription();
                                        isBreak = chq.equals(cs);
                                    } while (isBreak);
                                    //  判断当前index位置
                                    for (int i = 0; i < ch.getChildCount(); i++) {
                                        CharSequence cc = ch.getChild(i).getContentDescription();
                                        if (cc.equals(chq)) {
                                            index = i + 1;
                                            break;
                                        }
                                    }
                                } else {
                                    index++;
                                }
                                // =======================================================================================================================
                            }
                            if (thread.isInterrupted()) {
                                ok = false;
                            }
                        } while (ok);
                        // ===============================================
                        // ===============================================
                        // ===============================================
                        // ===============================================
                        // ===============================================
                        Log.d(TAG, "onClick: =======================   结束");
                    });
                    thread.start();
                } else {
                    thread.interrupt();
                }
            } else if (R.id.btnWinInfo == id) {


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
