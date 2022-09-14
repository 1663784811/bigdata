package com.cyyaw.browser.controller.douyin.data;

import com.cyyaw.browser.controller.DataAnalyze;
import com.cyyaw.browser.controller.DataAnalyzeAbstract;
import org.jsoup.nodes.Document;
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


        //System.out.println(document);



    }


}
