package com.cyyaw.browser.listen;


import com.cyyaw.browser.controller.SpiderPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

@EnableAsync
@Component
@Slf4j
public class ListenConfig {


    @Autowired
    private ApplicationContext applicationContext;


    @PostConstruct
    public void init(){
        String url = "https://www.douyin.com/discover";
        spiderFn(url);
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
        log.info(" -----------------{}", finish);

    }


}
