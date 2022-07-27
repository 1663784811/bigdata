package com.cyyaw.browser.listen;


import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
public class ListenConfig {



    /**
     * 爬取完成
     */
    @Async
    @EventListener(SpiderFinish.class)
    public void finish(SpiderFinish finish) {
        // 数据分析


        aaa();

    }


    private void aaa(){



    }







}
