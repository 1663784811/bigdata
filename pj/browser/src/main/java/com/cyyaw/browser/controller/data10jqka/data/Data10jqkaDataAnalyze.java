package com.cyyaw.browser.controller.data10jqka.data;


import com.cyyaw.browser.controller.DataAnalyze;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component
public class Data10jqkaDataAnalyze  implements DataAnalyze {


    @Override
    public boolean urlRule(String url) {
        return false;
    }

    @Override
    public void analyze(Document document) {

    }
}
