package com.cyyaw.demoapplication.permission;


/**
 * 判断当前手机屏幕显示的是哪个页面
 */
public enum PageCode {

    Home("", "首页", ""),
    RedBookIndex("com.xingin.xhs", "小红书首页", "com.xingin.xhs:id/dhc"),
    RedBookUser("", "小红书用户页面", "");

    private String appPackage;

    private String note;
    private String pageCode;


    PageCode(String appPackage, String note, String pageCode) {
        this.appPackage = appPackage;
        this.note = note;
        this.pageCode = pageCode;
    }
}
