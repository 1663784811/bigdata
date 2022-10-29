package com.cyyaw.browser.controller.douyin.spider;

import com.cyyaw.browser.controller.data.SpiderPageAbstract;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class DouyinUserSpiderPage extends SpiderPageAbstract {


    public DouyinUserSpiderPage(ApplicationContext context) {
        super(context);
    }

    @Override
    public Boolean policyDecision(String url) {
        return true;
    }

    @Override
    public void spider(String url) {
        super.spider(url);
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        super.spiderFinish();
    }
}
