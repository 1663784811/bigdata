package com.cyyaw.browser.core;

import com.cyyaw.browser.entity.PageElement;

public interface Browser {


    /**
     *  打开浏览器
     */
    void open(String url);

    /**
     * 获取Url
     */
    String getUrl();

    /**
     * 获取源码
     * @return
     */
    String getPageSource();


    /**
     *
     */
    PageElement find(String element);


    /**
     * 右键点击某个元素
     */
    void clickRightElement(String element);

    /**
     * 左键点击
     * @param s
     */
    void clickElement(String s);

    /**
     * 获取域名
     */
    String getHost();

}
