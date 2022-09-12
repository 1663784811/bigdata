package com.cyyaw.browser.listen;


import lombok.Data;
import org.springframework.context.ApplicationEvent;

/**
 * 爬取完成事件
 */
@Data
public class SpiderFinish extends ApplicationEvent {

    private String spiderData;

    public SpiderFinish(String source) {
        super(source);
        this.spiderData = source;
    }
}
