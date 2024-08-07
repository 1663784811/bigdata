package com.cyyaw.browser.controller.data;


import com.cyyaw.browser.listen.SpiderData;
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
    void analyze(Document document, SpiderData spiderData);

    /**
     * after处理
     */
    void afterHandle(String host, String url, Document doc);

}
