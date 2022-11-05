package com.cyyaw.browser.listen;


import com.cyyaw.browser.controller.data.DataAnalyze;
import com.cyyaw.browser.controller.data.SpiderPage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.annotation.PostConstruct;
import java.util.Map;

@Slf4j
@Configuration
@EnableAsync
public class ListenConfig {


    @Autowired
    private ApplicationContext applicationContext;


    @PostConstruct
    public void init(){
        String url = "http://data.10jqka.com.cn/funds/ggzjl/";
        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                Thread.sleep(5000L);
                spiderFn(url);
            }
        }).start();

    }

    /**
     * 爬取数据
     */
    public  void spiderFn(String url) {
        Map<String, SpiderPage> spiderPageMap = applicationContext.getBeansOfType(SpiderPage.class);
        for (String key : spiderPageMap.keySet()) {
            SpiderPage spiderPage = spiderPageMap.get(key);
            Boolean ok = spiderPage.policyDecision(url);
            if (ok) {
                spiderPage.spider(url);
            }
        }
    }


    /**
     * 爬取完成
     */
    @Async
    @EventListener(SpiderFinish.class)
    public void finish(SpiderFinish finish) {
        // 数据分析
        SpiderData spiderData = finish.getSpiderData();

        Map<String, DataAnalyze> spiderPageMap = applicationContext.getBeansOfType(DataAnalyze.class);
        for (String key : spiderPageMap.keySet()) {
            DataAnalyze dataAnalyze = spiderPageMap.get(key);
            String url = spiderData.getUrl();
            String host = spiderData.getHost();
            Boolean ok = dataAnalyze.urlRule(url);
            if (ok) {
                String html = spiderData.getHtml();
                Document doc = Jsoup.parse(html);
                dataAnalyze.analyze(doc, spiderData);
                dataAnalyze.afterHandle(host,url,doc);
            }
        }

    }


}
