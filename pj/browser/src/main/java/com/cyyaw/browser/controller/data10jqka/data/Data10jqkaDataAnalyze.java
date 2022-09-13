package com.cyyaw.browser.controller.data10jqka.data;


import cn.hutool.core.net.url.UrlBuilder;
import com.cyyaw.browser.controller.DataAnalyze;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class Data10jqkaDataAnalyze  implements DataAnalyze {


    @Override
    public boolean urlRule(String url) {
        UrlBuilder builder = UrlBuilder.ofHttp(url);
        String host = builder.getHost();
        if (host.equals("data.10jqka.com.cn")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void analyze(Document doc) {
        Elements rows = doc.select(".m-table tbody tr");

        for (Element element : rows) {
            Elements td = element.select("td");

            Element element1 = td.get(2);




        }
    }








}
