package com.cyyaw.test;

import org.openqa.selenium.chrome.ChromeDriver;

/**
 * 测试----- 通过窗口打开谷歌浏览器
 * @author WangSong
 *
 */
public class TestOpenBrowser {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver","C:\\Program Files\\Google\\Chrome\\Application\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();



    }



}
