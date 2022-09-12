package com.cyyaw.browser.controller;

import com.cyyaw.browser.core.Browser;
import com.cyyaw.browser.core.ChromeBrowser;

public abstract class SpiderPageAbstract implements SpiderPage{


    Browser browser = new ChromeBrowser();


    @Override
    public void spider(String url) {

        browser.open(url);



    }

}
