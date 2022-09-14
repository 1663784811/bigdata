package com.cyyaw.browser.controller.data10jqka.spider;


import cn.hutool.core.net.url.UrlBuilder;
import com.cyyaw.browser.controller.SpiderPageAbstract;
import com.cyyaw.browser.core.Browser;
import com.cyyaw.browser.entity.PageElement;
import org.openqa.selenium.WebElement;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Data10jqkaSpider extends SpiderPageAbstract {


    public Data10jqkaSpider(ApplicationContext context) {
        super(context);
    }

    @Override
    public Boolean policyDecision(String url) {
        // http://data.10jqka.com.cn/funds/ggzjl/
        UrlBuilder builder = UrlBuilder.ofHttp(url);
        String host = builder.getHost();
        if (host.equals("data.10jqka.com.cn")) {
            return true;
        } else {
            return false;
        }
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
                    break;
                }
            }
        }while (next!=null);


        // 判断是否有下一页，有则获取下一页数据

    }


}
