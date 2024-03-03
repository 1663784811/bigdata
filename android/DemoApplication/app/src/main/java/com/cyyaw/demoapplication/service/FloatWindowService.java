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
import com.cyyaw.demoapplication.permission.PageCode;
import com.cyyaw.demoapplication.service.map.AppGraph;
import com.cyyaw.demoapplication.service.map.AppNode;
import com.cyyaw.demoapplication.service.map.Task;
import com.cyyaw.demoapplication.service.map.page.BossIndexPage;
import com.cyyaw.demoapplication.service.map.page.BossWorkDetailsPage;
import com.cyyaw.demoapplication.service.map.page.HomePage;
import com.cyyaw.demoapplication.service.map.page.RedBookIndexPage;
import com.cyyaw.demoapplication.service.map.page.RedBookLivePage;
import com.cyyaw.demoapplication.service.map.page.RedBookNotePage;
import com.cyyaw.demoapplication.service.map.page.RedBookUserPage;
import com.cyyaw.demoapplication.service.map.page.RedBookVideoPage;
import com.cyyaw.demoapplication.service.window.FloatWindow;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
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

    private static volatile AppGraph appGraph = new AppGraph();

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


            // 首页
            appGraph.addNode(new AppNode(PageCode.Home.getPageCode(), new HomePage()));
            // 小红书
            appGraph.addNode(new AppNode(PageCode.RedBookIndex.getPageCode(), new RedBookIndexPage()));
            appGraph.addNode(new AppNode(PageCode.RedBookVideo.getPageCode(), new RedBookVideoPage()));
            appGraph.addNode(new AppNode(PageCode.RedBookUser.getPageCode(), new RedBookUserPage()));
            appGraph.addNode(new AppNode(PageCode.RedBookNote.getPageCode(), new RedBookNotePage()));
            appGraph.addNode(new AppNode(PageCode.RedBookLive.getPageCode(), new RedBookLivePage()));


            // BOSS直聘
            appGraph.addNode(new AppNode(PageCode.BossIndex.getPageCode(), new BossIndexPage()));
            appGraph.addNode(new AppNode(PageCode.BossWorkDetails.getPageCode(), new BossWorkDetailsPage()));
            //


            // ======================   页面关联
            appGraph.addEdge(PageCode.Home.getPageCode(), PageCode.BossIndex.getPageCode(), 1, (AccessibilityNodeInfo nodeInfo, String json) -> {
                openApp("com.hpbr.bosszhipin", "BOSS直聘");
            });

            appGraph.addEdge(PageCode.Home.getPageCode(), PageCode.RedBookIndex.getPageCode(), 1, (AccessibilityNodeInfo nodeInfo, String json) -> {
                openApp("com.xingin.xhs", "小红书");
            });

            // ================
            appGraph.addEdge(PageCode.RedBookVideo.getPageCode(), PageCode.RedBookUser.getPageCode(), 1, (AccessibilityNodeInfo nodeInfo, String json) -> {
                AccessibilityNodeInfo inf = findNodeInfoById("com.xingin.xhs:id/matrixAvatarView", 0);
                clickNode(inf);
            });
            appGraph.addEdge(PageCode.RedBookNote.getPageCode(), PageCode.RedBookUser.getPageCode(), 1, (AccessibilityNodeInfo nodeInfo, String json) -> {
                AccessibilityNodeInfo inf = findNodeInfoById("com.xingin.xhs:id/avatarLayout", 0);
                clickNode(inf);
            });
            appGraph.addEdge(PageCode.RedBookLive.getPageCode(), PageCode.RedBookUser.getPageCode(), 1, (AccessibilityNodeInfo nodeInfo, String json) -> {
                AccessibilityNodeInfo inf = findNodeInfoById("com.xingin.xhs:id/xg", 0);
                clickNode(inf);
                SystemClock.sleep(100);
                AccessibilityNodeInfo exz = findNodeInfoById("com.xingin.xhs:id/exz", 0);
                clickNode(exz);
            });
            // ========================
            appGraph.addEdge(PageCode.BossWorkDetails.getPageCode(), PageCode.BossIndex.getPageCode(), 1, (AccessibilityNodeInfo nodeInfo, String json) -> {
                back();
            });
            // ========================


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
        int id = v.getId();
        if (R.id.btn_start_task == id) {
            // startTask();
            // ======================================  初始化
            if (!start) {
                // 当前任务: 打开小红书
                new Thread(() -> {
                    execTask(PageCode.RedBookIndex.getPageCode(), (AccessibilityNodeInfo nodeInfo) -> {
                        start = true;
                        // 列表标题
                        Integer index = 0;
                        String title = "";
                        // 找列表
                        while (start) {
                            // 找第index个点击
                            String boxId = "com.xingin.xhs:id/eq2";
                            AccessibilityNodeInfo boxContent = findNodeInfoById(boxId, 0);
                            if (null != boxContent) {
                                int childCount = boxContent.getChildCount();
                                if (childCount > index) {
                                    AccessibilityNodeInfo child = boxContent.getChild(index);
                                    title = child.getContentDescription().toString();
                                    if (title.indexOf("视频") == 0 || title.indexOf("笔记") == 0) {
                                        clickNode(child);
                                        SystemClock.sleep(2000);
                                        // 已到视频页
                                        execTask(PageCode.RedBookUser.getPageCode(), (AccessibilityNodeInfo nodeInfoUser) -> {
                                            userViewPage();
                                        });
                                        back();
                                        back();
                                    } else if (title.indexOf("直播") == 0) {
                                        SystemClock.sleep(2000);
                                    }
                                    // =================
                                    if (index > 2) {
                                        index = strokeList(index, title, boxId, null);
                                    } else {
                                        ++index;
                                    }
                                } else {
                                    index = 0;
                                }
                            } else {
                                execTask(PageCode.RedBookIndex.getPageCode(), (AccessibilityNodeInfo fo) -> {
                                });
                            }
                        }
                        sendBroadcast(new Intent(FloatWindowLogService.class.getName()).putExtra("data", String.format("已经停止")));
                        start = false;
                    });
                }).start();
            } else {
                start = false;
                sendBroadcast(new Intent(FloatWindowLogService.class.getName()).putExtra("data", String.format("正在停止...")));
            }
        } else if (R.id.btnWinInfo == id) {
            if (!start) {
                new Thread(() -> {
                    start = true;
                    execTask(PageCode.BossIndex.getPageCode(), (AccessibilityNodeInfo nodeInfo) -> {
                        // 列表标题
                        Integer index = 0;
                        String title = "";

                        while (start) {
                            // 找第index个点击
                            String boxId = "com.hpbr.bosszhipin:id/rv_list";
                            AccessibilityNodeInfo boxContent = findNodeInfoById(boxId, 0);
                            if (null != boxContent) {
                                int childCount = boxContent.getChildCount();
                                if (childCount > index) {
                                    AccessibilityNodeInfo child = boxContent.getChild(index);
                                    AccessibilityNodeInfo info = findNodeInfoById(child, "com.hpbr.bosszhipin:id/tv_position_name", 0);
                                    if (null != info) {
                                        title = info.getText().toString();
                                        clickNode(child);
                                        SystemClock.sleep(2000);
                                        execTask(PageCode.BossWorkDetails.getPageCode(), (AccessibilityNodeInfo inf) -> {
                                            workDetailsViewPage();
                                        });
                                        back();
                                    }
                                    // =================
                                    if (index > 2) {
                                        index = strokeList(index, title, boxId, "com.hpbr.bosszhipin:id/tv_position_name");
                                    } else {
                                        ++index;
                                    }
                                } else {
                                    index = 0;
                                }
                            } else {
                                execTask(PageCode.BossIndex.getPageCode(), (AccessibilityNodeInfo fo) -> {
                                });
                            }
                        }
                    });

                    sendBroadcast(new Intent(FloatWindowLogService.class.getName()).putExtra("data", String.format("已经停止")));
                    start = false;
                }).start();
            } else {
                start = false;
                sendBroadcast(new Intent(FloatWindowLogService.class.getName()).putExtra("data", String.format("正在停止...")));
            }
        }
    }

    /**
     * 执行任务
     */
    public boolean execTask(String targetPage, Task task) {
        // 最大10跳
        int nodeNum = 0;
        int noRout = 0;
        while (true) {
            // 当前任务 、 当前页面
            String page = nowPage(targetPage);
            if (targetPage.equals(page)) {
                // 执行任务
                task.exec(getRootInActiveWindow());
                // 执行成功
                return true;
            } else {
                if (null != page) {
                    // 查找图最短路线
                    List<String> listPage = appGraph.shortestRoute(page, targetPage);
                    if (listPage.size() > 1) {
                        AppNode nodeByKey = appGraph.getNodeByKey(page);
                        AppNode.AppNodeRout appNodeRout = nodeByKey.getEdgeByKey(listPage.get(1));
                        appNodeRout.getOpenPage().exec(getRootInActiveWindow(), "");
                    } else {
                        if (noRout < 3) {
                            back();
                        } else {
                            keyHome();
                        }
                        SystemClock.sleep(3000);
                        ++noRout;
                    }
                } else {
                    if (nodeNum < 3) {
                        back();
                    } else {
                        keyHome();
                    }
                    SystemClock.sleep(3000);
                }
                ++nodeNum;
                if (nodeNum > 10 || noRout > 5) {
                    break;
                }
            }
        }
        return false;
    }

    /**
     * 查找当前页面
     */
    public String nowPage(String targetPage) {
        Map<String, AppNode> node = appGraph.getNode();
        AppNode appNode = node.get(targetPage);
        if (appNode.isThisPage(this)) {
            return appNode.getNodeId();
        }
        for (String key : node.keySet()) {
            AppNode appNodeTemp = node.get(key);
            if (appNodeTemp.isThisPage(this)) {
                return appNodeTemp.getNodeId();
            }
        }
        return null;
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

    public void workDetailsViewPage() {
        JSONObject json = new JSONObject();
        JSONObject js = new JSONObject();
        AccessibilityNodeInfo name = findNodeInfoById("com.hpbr.bosszhipin:id/tv_job_name", 0);
        if (null != name) {
            js.set("name", name.getText());
        }
        AccessibilityNodeInfo activity = findNodeInfoById("com.hpbr.bosszhipin:id/boss_label_tv", 0);
        if (null != activity) {
            js.set("activity", activity.getText());
        }

        AccessibilityNodeInfo sala = findNodeInfoById("com.hpbr.bosszhipin:id/tv_job_salary", 0);
        if (null != sala) {
            String text = sala.getText().toString();
            text = text.replaceAll("K", "").replaceAll(" ", "");
            int i = text.indexOf("·");
            if (i != -1) {
                text = text.substring(0, i);
            }
            String[] split = text.split("-");
            if (split.length == 2) {
                js.set("minPrice", new BigDecimal(split[0]));
                js.set("maxPrice", new BigDecimal(split[1]));
            }
        }
        AccessibilityNodeInfo above = findNodeInfoById("com.hpbr.bosszhipin:id/fl_content_above", 0);
        StringBuffer sbv = new StringBuffer("");
        if (null != above) {
            int childCount = above.getChildCount();
            for (int i = 0; i < childCount; i++) {
                if (sbv.length() > 0) {
                    sbv.append("," + above.getChild(i).getText());
                } else {
                    sbv.append(above.getChild(i).getText());
                }
            }
        }
        js.set("tag", sbv);
        AccessibilityNodeInfo escription = findNodeInfoById("com.hpbr.bosszhipin:id/tv_description", 0);
        if (null != escription) {
            js.set("demand", escription.getText());
        }
        AccessibilityNodeInfo hr = findNodeInfoById("com.hpbr.bosszhipin:id/tv_boss_name", 0);
        if (null != hr) {
            js.set("hr", hr.getText());
        }
        AccessibilityNodeInfo bossTitle = findNodeInfoById("com.hpbr.bosszhipin:id/tv_boss_title", 0);
        if (null != bossTitle) {
            js.set("hr", bossTitle.getText() + "-" + js.getStr("hr"));
        }
        AccessibilityNodeInfo com_name = findNodeInfoById("com.hpbr.bosszhipin:id/tv_com_name", 0);
        if (com_name == null) {
            performSwipeLeft(480, 1000, 480, 600, 200);
            SystemClock.sleep(1000);
            com_name = findNodeInfoById("com.hpbr.bosszhipin:id/tv_com_name", 0);
            if (com_name == null) {
                performSwipeLeft(480, 1000, 480, 600, 200);
                SystemClock.sleep(1000);
                com_name = findNodeInfoById("com.hpbr.bosszhipin:id/tv_com_name", 0);
                if (com_name == null) {
                    performSwipeLeft(480, 1000, 480, 600, 200);
                    SystemClock.sleep(1000);
                    com_name = findNodeInfoById("com.hpbr.bosszhipin:id/tv_com_name", 0);
                }
            }
        }
        if (null != com_name) {
            json.set("name", com_name.getText());
        }
        AccessibilityNodeInfo location = findNodeInfoById("com.hpbr.bosszhipin:id/tv_location", 0);
        if (location == null) {
            performSwipeLeft(480, 1000, 480, 600, 200);
            SystemClock.sleep(1000);
            location = findNodeInfoById("com.hpbr.bosszhipin:id/tv_location", 0);
            if (location == null) {
                performSwipeLeft(480, 1000, 480, 600, 200);
                SystemClock.sleep(1000);
                location = findNodeInfoById("com.hpbr.bosszhipin:id/tv_location", 0);
                if (location == null) {
                    performSwipeLeft(480, 1000, 480, 600, 200);
                    SystemClock.sleep(1000);
                    location = findNodeInfoById("com.hpbr.bosszhipin:id/tv_location", 0);
                }
            }
        }
        if (null != location) {
            js.set("address", location.getText());
        }

        AccessibilityNodeInfo tv_com_info = findNodeInfoById("com.hpbr.bosszhipin:id/tv_com_info", 0);
        if (null != tv_com_info) {
            json.set("industry", tv_com_info.getText());
        }
        js.set("resource", "BOOS直聘");
        JSONArray arr = new JSONArray();
        arr.add(js);
        json.set("recruitList", arr);
        if (StrUtil.isNotBlank(js.getStr("name"))) {
            HttpRequest.post("http://192.168.0.103:8080/admin/sss/company/recruit/saveCpRecruit").body(JSONUtil.toJsonStr(json)).execute().body();
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
