package com.cyyaw.browser.controller.douyin.spider;

import com.cyyaw.browser.controller.data.SpiderPageAbstract;
import com.cyyaw.browser.core.Browser;
import com.cyyaw.browser.entity.PageElement;
import org.openqa.selenium.WebElement;
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
        int i=0;
        while (i<100000){
            System.out.println("次数："+ i);
            i++;
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Browser browser = super.getBrowser();
            PageElement pageElement = browser.find(".ARHQtNo4");
            int size = pageElement.size();
            if(size>0){
                WebElement indexElement = pageElement.getIndexElement(size - 1);
                browser.scroll(indexElement);
            }
        }
        super.spiderFinish();
    }
}
