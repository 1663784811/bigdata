package com.cyyaw.browser.core;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class ChromeBrowser implements Browser {

    private ChromeDriver driver;


    public ChromeBrowser() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");//禁用沙箱
        chromeOptions.addArguments("--disable-dev-shm-usage");
        //chromeOptions.addArguments("--headless"); //无头浏览器，这样不会打开浏览器窗口
        driver = new ChromeDriver(chromeOptions);
    }


    @Override
    public void open(String url) {
        driver.get(url);
    }

    @Override
    public String getPageSource() {
        return driver.getPageSource();
    }

    @Override
    public void clickRightElement(String element) {
        String type = element.substring(0, 1);
        String name = element.substring(1);
        if (".".equals(type)) {
            WebElement clickable = driver.findElement(By.className(name));
            new Actions(driver).contextClick(clickable).perform();
        } else if ("#".equals(type)) {
            WebElement clickable = driver.findElement(By.id(name));
            new Actions(driver).contextClick(clickable).perform();
        }
    }

    @Override
    public void clickElement(String element) {
        String type = element.substring(0, 1);
        String name = element.substring(1);
        WebElement clickable = null;
        if (".".equals(type)) {
            clickable = driver.findElement(By.className(name));
        } else if ("#".equals(type)) {
            clickable = driver.findElement(By.id(name));
        }else{
            clickable = driver.findElement(By.tagName(element));
        }
        new Actions(driver).click(clickable).perform();
    }
}
