package com.cyyaw.browser.listen;


import org.springframework.context.ApplicationEvent;

/**
 * 爬取完成事件
 */
public class SpiderFinish extends ApplicationEvent {


    public SpiderFinish(Object source) {
        super(source);
    }
}
