package com.cyyaw.browser.controller.douyin.data;

import com.cyyaw.browser.controller.data.DataAnalyzeAbstract;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;


/**
 * 抖音用户数据分析
 */
@Component
public class DouyinUserDataAnalyze extends DataAnalyzeAbstract {


    @Override
    public boolean urlRule(String url) {
        return true;
    }

    @Override
    public void analyze(Document document) {


        Elements elements = document.select(".PSMUj2A_ .LmoNsGLD");
        for (Element element : elements) {
            String href = element.select(".B3AsdZT9").first().attr("href");
            String nickName = element.select(".O23tuHy1").first().text();
            String content = element.select(".B8CDxCkp").first().text();
            String url = "";



        }


    }


}
