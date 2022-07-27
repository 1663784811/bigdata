package com.cyyaw.browser.listen;


import com.cyyaw.browser.controller.spider.SpiderPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@EnableAsync
@Component
public class ListenConfig {

    private static ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(10);


    @Autowired
    private ApplicationContext applicationContext;


    public void aaa() {


        String url = "";


        newFixedThreadPool.submit(new Runnable() {
            @Override
            public void run() {
                spiderFn(url);
            }
        });
    }

    /**
     * 爬取数据
     */
    private void spiderFn(String url) {
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


    }


}
