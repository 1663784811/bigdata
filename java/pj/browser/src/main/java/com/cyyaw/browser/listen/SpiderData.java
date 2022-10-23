package com.cyyaw.browser.listen;

import lombok.Data;

@Data
public class SpiderData {

    /**
     * html 标签
     */
    private String html;

    /**
     * 地址
     */
    private String url;

    /**
     * 域名
     */
    private String host;
}
