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
import java.util.HashMap;
import java.util.List;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

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
                                // =========== 收集数据
                                Log.d(TAG, "onClick: " + chq);
                                if (chq.toString().indexOf("视频") == 0) {
                                    SystemClock.sleep(2000);
                                    clickNode(findNodeInfoById("com.xingin.xhs:id/matrixAvatarView", 0));
                                    // =========== 收集数据
                                    userViewPage();
                                    back();
                                } else if (chq.toString().indexOf("笔记") == 0) {
                                    SystemClock.sleep(1000);
                                    clickNode(findNodeInfoById("com.xingin.xhs:id/avatarLayout", 0));
                                    // =========== 收集数据
                                    userViewPage();
                                    back();
                                } else if (chq.toString().indexOf("直播") == 0) {
                                    SystemClock.sleep(3000);
                                }
                                back();


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


    private void userViewPage() {
        StringBuffer sb = new StringBuffer();
        JSONObject json = new JSONObject();
        json.set("host", "小红书app");
        json.set("url", "");
        json.set("source", 2);
        // 大聪明小强
        AccessibilityNodeInfo gfl = findNodeInfoById("com.xingin.xhs:id/gfl", 0);
        CharSequence nickName = null;
        if (null != gfl) {
            nickName = gfl.getText();
            sb.append(nickName);
            json.set("nickName", nickName);
        }
        // 小红书号
        AccessibilityNodeInfo gfn = findNodeInfoById("com.xingin.xhs:id/gfn", 0);
        CharSequence no = null;
        if (null != gfn) {
            no = gfn.getText();
            sb.append(no);
            json.set("account", no);
        }
        // 公司
        AccessibilityNodeInfo vb = findNodeInfoById("com.xingin.xhs:id/vb", 0);
        CharSequence vb_t = null;
        if (null != vb) {
            vb_t = vb.getText();
            sb.append(vb_t);
            json.set("company", vb_t);
        }
        //IP属地：湖南
        AccessibilityNodeInfo gfj = findNodeInfoById("com.xingin.xhs:id/gfj", 0);
        CharSequence ip = null;
        if (null != gfj) {
            ip = gfj.getText();
            sb.append(ip);
            json.set("address", ip);
        }
        // 生活技巧分享，创意制作制作解压视频
        AccessibilityNodeInfo j0l = findNodeInfoById("com.xingin.xhs:id/j0l", 0);
        CharSequence dec = null;
        if (null != j0l) {
            dec = j0l.getText();
            sb.append(dec);
        }
        // 关注
        AccessibilityNodeInfo u = findNodeInfoById("com.xingin.xhs:id/u_", 0);
        CharSequence uu = null;
        if (null != u) {
            uu = u.getText();
            sb.append("关注数:" + uu);
            json.set("follow", 2);
        }
        // 粉丝
        AccessibilityNodeInfo c13 = findNodeInfoById("com.xingin.xhs:id/c13", 0);
        CharSequence c13_x = null;
        if (null != c13) {
            c13_x = u.getText();
            sb.append("粉丝数:" + c13_x);
            json.set("fans", Integer.valueOf(c13_x + ""));
        }
        // 5.2 万获赞与收藏
        AccessibilityNodeInfo e4g = findNodeInfoById("com.xingin.xhs:id/e4g", 0);
        CharSequence e4g_x = null;
        if (null != c13) {
            e4g_x = e4g.getText();
            sb.append("获赞与收藏:" + e4g_x);
        }
        // 男，26岁
        // 标签
        String tag = null;
        List<AccessibilityNodeInfo> nodeInfoById = findNodeInfoById("com.xingin.xhs:id/gfk");
        if (null != nodeInfoById) {
            for (int i = 0; i < nodeInfoById.size(); i++) {
                tag += "," + nodeInfoById.get(i).getContentDescription();
            }
        }
        sb.append(tag);
        // 性别
        if (sb.indexOf("男") != -1) {
            json.set("gender", 1);
        } else if (sb.indexOf("女") != -1) {
            json.set("gender", 2);
        } else {
            json.set("gender", 0);
        }
        // 内容
        json.set("content", sb.toString());
        //
        HttpRequest.post("http://192.168.0.103:8080/admin/sss/spider/nickname/saveSpiderNickName").body(JSONUtil.toJsonStr(json)).execute().body();
    }


}
