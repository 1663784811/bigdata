package com.cyyaw.browser.listen;


import lombok.Data;
import org.springframework.context.ApplicationEvent;

/**
 * 爬取完成事件
 */
@Data
public class SpiderFinish extends ApplicationEvent {


    private SpiderData spiderData;

    public SpiderFinish(SpiderData source) {
        super(source);
        this.spiderData = source;
    }
}
