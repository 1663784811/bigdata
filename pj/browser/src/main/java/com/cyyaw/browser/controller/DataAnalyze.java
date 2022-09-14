package com.cyyaw.browser.controller;


import org.jsoup.nodes.Document;

/**
 * 数据分析
 */
public interface DataAnalyze {


    /**
     * url 规则
     * @param url
     * @return
     */
    boolean urlRule(String url);

    /**
     * 分析数据
     */
    void analyze(Document document);

    /**
     * after处理
     */
    void afterHandle(Document document);

}
