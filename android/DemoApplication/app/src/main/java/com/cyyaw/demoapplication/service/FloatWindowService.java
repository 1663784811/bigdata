package com.cyyaw.demoapplication.service;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import com.cyyaw.demoapplication.R;
import com.cyyaw.demoapplication.service.window.FloatWindow;
import com.cyyaw.demoapplication.task.AppInfo;

import java.math.BigDecimal;
import java.util.List;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
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

    private volatile int collect = 0;

    private static volatile boolean start = false;

    @Override
    public void onInterrupt() {
        wManager = null;
    }

    @Override
    public void onDestroy() {
        wManager = null;
        super.onDestroy();
    }

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
                startTask();
            } else if (R.id.btnWinInfo == id) {
                startBoos();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void startTask() {
        // 获取窗口信息
        if (!start) {
            start = true;
            Thread thread = new Thread(() -> {
                collect = 0;
                while (start || (!start && collect == 0)) {
                    start = true;
                    restart();
                }
            });
            thread.start();
        } else {
            sendBroadcast(new Intent(FloatWindowLogService.class.getName()).putExtra("data", String.format("正在停止....")));
            start = false;
        }
    }

    public void startBoos() {
        // 获取窗口信息
        if (!start) {
            start = true;
            Thread thread = new Thread(() -> {
                collect = 0;
//                while (start || (!start && collect == 0)) {
//                    start = true;
//                    restartBoos();
//                }
                restartBoos();
            });
            thread.start();
        } else {
            sendBroadcast(new Intent(FloatWindowLogService.class.getName()).putExtra("data", String.format("正在停止....")));
            start = false;
        }
    }


    private void restartBoos() {
        // =========== 第一步: 打开小红书
        AppInfo appInfo = new AppInfo();
        appInfo.setPackageName("com.hpbr.bosszhipin");
        appInfo.setAppName("BOSS直聘");
        openApp(appInfo);
        // =========== 第二步: 点击职位
        boolean ok = true;
        do {
            if (findNodeInfoById("com.hpbr.bosszhipin:id/cl_tab_1", 0) == null) {
                back();
                SystemClock.sleep(500);
            } else {
                ok = false;
            }
        } while (ok);
        clickNodeById("com.hpbr.bosszhipin:id/cl_tab_1");
        // ===========
        SystemClock.sleep(2000);
        // ===========
        // 获取外部列表框


        // ===============================================
        // ===============================================
        // ===============================================
        // ===============================================
        // ===============================================
        int nnn = 0;
        while (start) {
            AccessibilityNodeInfo boxContent = findNodeInfoById("com.hpbr.bosszhipin:id/rv_list", 0);
            if (boxContent == null) {
                SystemClock.sleep(3000);
                boxContent = findNodeInfoById("com.hpbr.bosszhipin:id/rv_list", 0);
            }
            if (null != boxContent) {
                nnn = 0;
                int childCount = boxContent.getChildCount();
                if (childCount > index) {
                    // ================================================================     收集数据     =======================================================
                    SystemClock.sleep(500);
                    AccessibilityNodeInfo child = findNodeInfoById(boxContent.getChild(index), "com.hpbr.bosszhipin:id/tv_position_name", 0);
                    CharSequence chq = null;
                    if(child != null){
                        chq = child.getText();
                        clickNode(child);
                        // ===========
                        // =========== 收集数据

                        back();

                    }



                    // ================================================================     滑动     =======================================================
                    if (index > 2) {
                        // 移动屏幕
                        boolean isBreak = false;
                        AccessibilityNodeInfo ch = null;
                        int numx = 0;
                        do {
                            performSwipeLeft(480, 1000, 480, 850, 200);
                            SystemClock.sleep(1500);
                            ch = findNodeInfoById("com.hpbr.bosszhipin:id/rv_list", 0);
                            if (ch != null) {
                                int cc = ch.getChildCount();
                                if (cc > index) {
                                    CharSequence cs = findNodeInfoById(ch.getChild(index), "com.hpbr.bosszhipin:id/tv_position_name", 0).getText();
                                    isBreak = chq.equals(cs);
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
                        //  判断当前index位置
                        int num = 0;
                        if (ch != null) {
                             for (int i = 0; i < ch.getChildCount(); i++) {
                                CharSequence cc = ch.getChild(i).getContentDescription();
                                if (null != cc && cc.equals(chq)) {
                                    num = i + 1;
                                    break;
                                }
                            }
                        }
                        index = num;
                    } else {
                        index++;
                    }
                    // =======================================================================================================================
                }
            } else {
                SystemClock.sleep(2000);
                back();
                if (nnn > 20) {
                    back();
                    nnn++;
                }
            }
            collect++;
            if (collect > 30) {
                start = false;
                collect = 0;
            }
        }
        // ===============================================
        // ===============================================
        // ===============================================
        // ===============================================
        // ===============================================
        Log.d(TAG, "onClick: =======================   结束");
        sendBroadcast(new Intent(FloatWindowLogService.class.getName()).putExtra("data", String.format("已经停止")));
    }


    private void restart() {
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
        int nnn = 0;
        while (start) {
            AccessibilityNodeInfo boxContent = findNodeInfoById("com.xingin.xhs:id/eq2", 0);
            if (boxContent == null) {
                SystemClock.sleep(3000);
                boxContent = findNodeInfoById("com.xingin.xhs:id/eq2", 0);
            }
            if (null != boxContent) {
                nnn = 0;
                int childCount = boxContent.getChildCount();
                if (childCount > index) {
                    SystemClock.sleep(500);
                    AccessibilityNodeInfo child = boxContent.getChild(index);
                    CharSequence chq = child.getContentDescription();
                    clickNode(child);
                    // ===========
                    // =========== 收集数据
                    Log.d(TAG, "onClick: " + Thread.currentThread().getName() + "----------" + chq);
                    if (chq.toString().indexOf("视频") == 0) {
                        SystemClock.sleep(2000);
                        AccessibilityNodeInfo nodeInfo = findNodeInfoById("com.xingin.xhs:id/matrixAvatarView", 0);
                        if (null != nodeInfo) {
                            clickNode(nodeInfo);
                            // =========== 收集数据
                            userViewPage();
                            back();
                        }

                    } else if (chq.toString().indexOf("笔记") == 0) {
                        SystemClock.sleep(2000);
                        AccessibilityNodeInfo nodeInfo = findNodeInfoById("com.xingin.xhs:id/avatarLayout", 0);
                        if (null != nodeInfo) {
                            clickNode(nodeInfo);
                            // =========== 收集数据
                            userViewPage();
                            back();
                        }
                    } else if (chq.toString().indexOf("直播") == 0) {
                        SystemClock.sleep(2000);
                    }
                    back();


                    // ================================================================     滑动     =======================================================
                    if (index > 2) {
                        // 移动屏幕
                        boolean isBreak = false;
                        AccessibilityNodeInfo ch = null;
                        int numx = 0;
                        do {
                            performSwipeLeft(480, 1000, 480, 850, 200);
                            SystemClock.sleep(1500);
                            ch = findNodeInfoById("com.xingin.xhs:id/eq2", 0);
                            if (ch != null) {
                                int cc = ch.getChildCount();
                                if (cc > index) {
                                    CharSequence cs = ch.getChild(index).getContentDescription();
                                    isBreak = chq.equals(cs);
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
                        //  判断当前index位置
                        int num = 0;
                        if (ch != null) {
                            for (int i = 0; i < ch.getChildCount(); i++) {
                                CharSequence cc = ch.getChild(i).getContentDescription();
                                if (null != cc && cc.equals(chq)) {
                                    num = i + 1;
                                    break;
                                }
                            }
                        }
                        index = num;
                    } else {
                        index++;
                    }
                    // =======================================================================================================================
                }
            } else {
                SystemClock.sleep(2000);
                back();
                if (nnn > 20) {
                    back();
                    nnn++;
                }
            }
            collect++;
            if (collect > 30) {
                start = false;
                collect = 0;
            }
        }
        // ===============================================
        // ===============================================
        // ===============================================
        // ===============================================
        // ===============================================
        Log.d(TAG, "onClick: =======================   结束");
        sendBroadcast(new Intent(FloatWindowLogService.class.getName()).putExtra("data", String.format("已经停止")));
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
            sb.append(nickName + " --- ");
            json.set("nickName", nickName);
        }
        // 头像
        AccessibilityNodeInfo y0 = findNodeInfoById("com.xingin.xhs:id/y0", 0);
        CharSequence y0_t = null;
        if (null != y0) {
            y0_t = y0.getContentDescription();
            json.set("face", y0_t);
        }
        // 小红书号
        AccessibilityNodeInfo gfn = findNodeInfoById("com.xingin.xhs:id/gfn", 0);
        CharSequence no = null;
        if (null != gfn) {
            no = gfn.getText();
            sb.append(no + " --- ");
            json.set("account", no);
        }
        // 公司
        AccessibilityNodeInfo vb = findNodeInfoById("com.xingin.xhs:id/vb", 0);
        CharSequence vb_t = null;
        if (null != vb) {
            vb_t = vb.getText();
            sb.append(vb_t + " --- ");
            json.set("company", vb_t);
        }
        //IP属地：湖南
        AccessibilityNodeInfo gfj = findNodeInfoById("com.xingin.xhs:id/gfj", 0);
        CharSequence ip = null;
        if (null != gfj) {
            ip = gfj.getText();
            sb.append(ip + " --- ");
            json.set("address", ip);
        }
        // 生活技巧分享，创意制作制作解压视频
        AccessibilityNodeInfo j0l = findNodeInfoById("com.xingin.xhs:id/j0l", 0);
        CharSequence dec = null;
        if (null != j0l) {
            dec = j0l.getText();
            sb.append(dec + " --- ");
        }
        // 关注
        AccessibilityNodeInfo u = findNodeInfoById("com.xingin.xhs:id/u_", 0);
        CharSequence uu = null;
        if (null != u) {
            uu = u.getText();
            sb.append("关注数:" + uu + " --- ");
            json.set("follow", stringToNumber(uu));
        }
        // 粉丝
        AccessibilityNodeInfo c13 = findNodeInfoById("com.xingin.xhs:id/c13", 0);
        CharSequence c13_x = null;
        if (null != c13) {
            c13_x = c13.getText();
            sb.append("粉丝数:" + c13_x + " --- ");
            json.set("fans", stringToNumber(c13_x));
        }
        // 5.2 万获赞与收藏
        AccessibilityNodeInfo e4g = findNodeInfoById("com.xingin.xhs:id/e4g", 0);
        CharSequence e4g_x = null;
        if (null != e4g) {
            e4g_x = e4g.getText();
            sb.append("获赞与收藏:" + e4g_x + " --- ");
            json.set("likeNum", stringToNumber(e4g_x));
        }
        // 男，26岁
        // 标签
        String tag = "";
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
        if (StrUtil.isNotBlank(nickName)) {
            HttpRequest.post("http://192.168.0.103:8080/admin/sss/spider/nickname/saveSpiderNickName").body(JSONUtil.toJsonStr(json)).execute().body();
        }
    }


    public int stringToNumber(CharSequence str) {
        if (null != str) {
            if (!"-".equals(str)) {
                if (str.toString().contains("万")) {
                    BigDecimal bd = new BigDecimal(str.toString().replaceAll("万", "").replace(" ", ""));
                    BigDecimal mu = bd.multiply(new BigDecimal("10000"));
                    return mu.intValue();
                } else {
                    return Integer.valueOf(str.toString());
                }
            }
        }
        return 0;
    }


}
