package com.cyyaw.browser.controller.douyin.data;

import com.cyyaw.browser.controller.DataAnalyze;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;


/**
 * 抖音用户数据分析
 */
@Component
public class DouyinUserDataAnalyze implements DataAnalyze {


    @Override
    public boolean urlRule(String url) {
        return true;
    }

    @Override
    public void analyze(Document document) {


        System.out.println(document);



    }


}
