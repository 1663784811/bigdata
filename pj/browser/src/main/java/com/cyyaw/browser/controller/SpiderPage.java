package com.cyyaw.browser.controller;


/**
 * 爬取页面
 */
public interface SpiderPage {


    /**
     * 分析url来抓取
     */
    Boolean policyDecision(String url);


    /**
     * 爬取并分析页面
     */
    void spider(String url);


}
