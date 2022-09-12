package com.cyyaw.browser.controller;

import com.cyyaw.browser.core.Browser;
import com.cyyaw.browser.core.ChromeBrowser;
import com.cyyaw.browser.listen.SpiderData;
import com.cyyaw.browser.listen.SpiderFinish;
import lombok.Data;
import org.springframework.context.ApplicationContext;

@Data
public abstract class SpiderPageAbstract implements SpiderPage{

    private Browser browser = new ChromeBrowser();

    private ApplicationContext context;

    public SpiderPageAbstract() {}

    public SpiderPageAbstract(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void spider(String url) {
        browser.open(url);
    }

    /**
     *抓取完成
     */
    public void spiderFinish(){
        // 发布事件
        String pageSource = browser.getPageSource();
        String url = browser.getUrl();
        if(null != context){
            SpiderData spiderData = new SpiderData();
            spiderData.setUrl(url);
            spiderData.setSpiderData(pageSource);
            context.publishEvent(new SpiderFinish(spiderData));
        }
    }

}
