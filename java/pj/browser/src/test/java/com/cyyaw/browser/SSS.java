package com.cyyaw.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class SSS {


    public static void main(String[] args) throws MalformedURLException {

        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress", "localhost:9222");

        WebDriver driver = new RemoteWebDriver(new URL("http://localhost:9222"), options);

        driver.get("http://www.example.com");
        String title = driver.getTitle();

        System.out.println("sssssssssssssssssssss"+title);

    }

}
