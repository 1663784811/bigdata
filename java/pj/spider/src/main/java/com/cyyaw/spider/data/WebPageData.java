package com.cyyaw.spider.data;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;


public class WebPageData {

    /**
     * 页面数据
     */
    public static volatile List<Map<String,Object>> pageList = new CopyOnWriteArrayList();

    /**
     * 页面地址
     */
    public static volatile List<String> pageUrl = new CopyOnWriteArrayList();
}
