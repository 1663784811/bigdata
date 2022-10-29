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
        WebElement next = null;
        do {
            super.spiderFinish();
            Browser browser = super.getBrowser();
            PageElement pageElement = browser.find(".changePage");
            next = null;
            for (int i = 0; i < pageElement.size(); i++) {
                WebElement indexElement = pageElement.getIndexElement(i);
                String text = indexElement.getText();
                if("下一页".equals(text)){
                    indexElement.click();
                    next = indexElement;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }while (next!=null);
    }
}
