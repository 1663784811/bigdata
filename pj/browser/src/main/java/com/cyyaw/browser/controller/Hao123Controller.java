package com.cyyaw.browser.controller;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Set;

public class Hao123Controller {


    /**
     * 登录操作
     */
    public void login() {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();

        driver.get("http://www.hao123.com/");

        String title = driver.getTitle();
        System.out.println(title);
        String currentUrl = driver.getCurrentUrl();

        driver.navigate().refresh();


        //Click the link to activate the alert
//        driver.findElement(By.linkText("See an example alert")).click();

//        //Wait for the alert to be displayed and store it in a variable
//        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
//
//        //Store the alert text in a variable
//        String text = alert.getText();
//
//        //Press the OK button
//        alert.accept();

        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().window().maximize();
        String windowHandle = driver.getWindowHandle();



        Set<Cookie> cookies = driver.manage().getCookies();
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getName());
        }


        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));

//        WebElement searchBox = driver.findElement(By.name("q"));
//        WebElement searchButton = driver.findElement(By.name("btnK"));
//
//
//        System.out.println(searchBox);
//        System.out.println(searchButton);

    }


}
