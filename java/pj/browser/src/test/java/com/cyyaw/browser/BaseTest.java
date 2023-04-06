package com.cyyaw.browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {
    public WebDriver driver;

    @BeforeAll static void setDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");//禁用沙箱
        options.addArguments("--disable-dev-shm-usage");
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
//        options.addArguments("--headless"); //无头浏览器，这样不会打开浏览器窗口
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--session-override");
        driver = new ChromeDriver(options);
//        driver = new ChromeDriver();
    }

    @AfterEach
    public void quit() {
//        driver.quit();
    }
}
