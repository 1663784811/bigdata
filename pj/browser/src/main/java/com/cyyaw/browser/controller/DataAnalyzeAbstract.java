package com.cyyaw.browser.controller;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public abstract class DataAnalyzeAbstract implements DataAnalyze  {




    public void afterHandle(Document document){

        // 处理A标签
        Elements aTags = document.getElementsByTag("a");

        for (Element e: aTags) {
            String attr = e.attr("href");
            String text = e.text();


        }




        // 保存到本地文件

    }

}
