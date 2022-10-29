package com.cyyaw.browser.core;

import com.cyyaw.browser.entity.PageElement;
import org.openqa.selenium.WebElement;

public interface Browser {


    /**
     *  打开浏览器
     */
    void open(String url);

    /**
     * 获取Url
     */
    String getUrl();

    /**
     * 获取源码
     * @return
     */
    String getPageSource();


    /**
     * 获取某个元素
     */
    PageElement find(String element);


    // =====================================  事件  START
    /**
     * 右键点击某个元素
     */
    void clickRightElement(String element);

    /**
     * 左键点击
     * @param s
     */
    void clickElement(String s);

    void scroll(WebElement element);

    // ====================================  事件END

    /**
     * 获取域名
     */
    String getHost();

}
