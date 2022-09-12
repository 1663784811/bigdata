package com.cyyaw.browser.controller;


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
    void analyze(String data);


}
