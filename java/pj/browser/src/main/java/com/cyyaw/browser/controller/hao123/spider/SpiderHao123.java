package com.cyyaw.browser.controller.hao123.spider;

import com.cyyaw.browser.controller.data.SpiderPageAbstract;
import org.springframework.stereotype.Component;

@Component
public class SpiderHao123  extends SpiderPageAbstract {


    @Override
    public Boolean policyDecision(String url) {

        return false;
    }

    @Override
    public void spider(String url) {

    }
}


