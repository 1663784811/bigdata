package com.cyyaw.spider.run;


import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.ObjectUtils;

import java.io.IOException;

/**
 * 标准爬虫
 */
@Slf4j
public class StandardSpider implements RunSpider{

    private UrlFilters urlFilter = new UrlFilters();


    public String getHttpData(String url) {
        if(!ObjectUtils.isEmpty(url) && url.indexOf("http")==0 && urlFilter.filter(url)){
            try {
                log.info("==============  地址数据："+ url);
                Document document = Jsoup.connect(url).get();
                return document.html();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}
