package com.cyyaw.browser.controller.data10jqka.spider;


import cn.hutool.core.net.url.UrlBuilder;
import com.cyyaw.browser.controller.SpiderPageAbstract;
import com.cyyaw.browser.core.Browser;
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
        super.spiderFinish();

        Browser browser = super.getBrowser();
        browser.find(".changePage");


        // 判断是否有下一页，有则获取下一页数据

    }


}
