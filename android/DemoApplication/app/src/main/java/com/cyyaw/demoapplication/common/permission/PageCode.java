package com.cyyaw.demoapplication.common.permission;


/**
 * 判断当前手机屏幕显示的是哪个页面
 */
public enum PageCode {

    Home("Home", "首页", ""),



    // =====================================================================================================
    RedBookIndex("小红书-首页", "com.xingin.xhs", ""),
    RedBookVideo("小红书-视频页", "com.xingin.xhs", ""),
    RedBookUser("小红书-用户页面", "com.xingin.xhs", ""),
    RedBookNote("小红书-笔记页面", "com.xingin.xhs", ""),
    RedBookLive("小红书-直播页面", "com.xingin.xhs", ""),



    // =====================================================================================================
    BossIndex("BOSS直聘-首页", "", ""),
    BossWorkDetails("BOSS直聘-工作详情", "", ""),

    ;

    private String appPackage;

    private String note;
    private String pageCode;


    PageCode(String pageCode, String appPackage, String note) {
        this.appPackage = appPackage;
        this.note = note;
        this.pageCode = pageCode;
    }

    public String getAppPackage() {
        return appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPageCode() {
        return pageCode;
    }

    public void setPageCode(String pageCode) {
        this.pageCode = pageCode;
    }
}
