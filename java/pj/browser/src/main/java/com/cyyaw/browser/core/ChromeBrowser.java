package com.cyyaw.browser.core;


import cn.hutool.core.net.url.UrlBuilder;
import cn.hutool.core.util.CharsetUtil;
import com.cyyaw.browser.entity.PageElement;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class ChromeBrowser implements Browser {

    private ChromeDriver driver;


    public ChromeBrowser() {
        // start chrome  --flag-switches-begin --flag-switches-end --remote-debugging-port=9887
        // https://www.cnblogs.com/testway/p/16676195.html
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");//禁用沙箱
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
        //chromeOptions.addArguments("--headless"); //无头浏览器，这样不会打开浏览器窗口
        //
        driver = new ChromeDriver(chromeOptions);
    }


    @Override
    public void open(String url) {
        driver.get(url);
    }

    @Override
    public String getUrl() {
        return driver.getCurrentUrl();
    }

    @Override
    public String getPageSource() {
        return driver.getPageSource();
    }

    @Override
    public PageElement find(String element) {
        String type = element.substring(0, 1);
        String name = element.substring(1);
        By by = null;
        if (".".equals(type)) {
            by = By.className(name);
        } else if ("#".equals(type)) {
            by = By.id(name);
        }
        List<WebElement> elements = driver.findElements(by);
        PageElement p = new PageElement();
        p.setElementList(elements);
        return p;
    }

    @Override
    public void clickRightElement(String element) {
        WebElement clickable = getWebElement(element);
        new Actions(driver).contextClick(clickable).perform();
    }

    @Override
    public void clickElement(String element) {
        WebElement clickable = getWebElement(element);
        new Actions(driver).click(clickable).perform();
    }

    @Override
    public void scroll(WebElement element) {
        new Actions(driver).moveToElement(element, 0, 0).perform();
    }

    private WebElement getWebElement(String element) {
        String type = element.substring(0, 1);
        String name = element.substring(1);
        WebElement webElement = null;
        if (".".equals(type)) {
            webElement = driver.findElement(By.className(name));
        } else if ("#".equals(type)) {
            webElement = driver.findElement(By.id(name));
        } else {
            webElement = driver.findElement(By.tagName(element));
        }
        return webElement;
    }


    @Override
    public String getHost() {
        String url = getUrl();
        UrlBuilder builder = UrlBuilder.ofHttp(url, CharsetUtil.CHARSET_UTF_8);
        return builder.getHost();
    }
}
