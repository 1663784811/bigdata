package com.cyyaw.browser.controller;

import com.cyyaw.browser.core.Browser;
import com.cyyaw.browser.core.ChromeBrowser;

public class Hao123Controller {


    /**
     * 登录操作
     */
    public void login() {
        // 打开浏览器
        Browser browser = new ChromeBrowser();
        // 打开网址
        browser.open("https://zh.stripchat.com/MissLan001");
        browser.clickElement(".btn-visitors-agreement-accept");
        //  获取


//        driver.manage().window().maximize();
//        driver.get("http://www.hao123.com/");
//
//        String pageSource = driver.getPageSource();
//        System.out.println(pageSource);
//
//        String title = driver.getTitle();
//        System.out.println(title);
//        String currentUrl = driver.getCurrentUrl();
//

//        ChromiumNetworkConditions networkConditions = driver.getNetworkConditions();

//        Duration latency = networkConditions.getLatency();
//        latency.getUnits();
//        DevTools devTools = driver.getDevTools();
//        Domains domains = devTools.getDomains();
//        Network<?, ?> network = domains.network();
//        Log log = domains.log();
//
//
//        System.out.println(network);
//
//        devTools.close();
//
//
//        WebElement vegetable = driver.findElement(By.tagName("html"));
//
//
//
//        driver.navigate().refresh();


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


//
//        String windowHandle = driver.getWindowHandle();
//
//
//
//        Set<Cookie> cookies = driver.manage().getCookies();
//        for (Cookie cookie : cookies) {
//            System.out.println(cookie.getName());
//        }
//
//
//        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
//        WebElement searchBox = driver.findElement(By.name("q"));
//        WebElement searchButton = driver.findElement(By.name("btnK"));
//
//
//        System.out.println(searchBox);
//        System.out.println(searchButton);
//        DevTools devTools = driver.getDevTools();
//        devTools.addListener(Network.requestWillBeSent(), info -> {
//            RequestId requestId = responseReceived.getRequestId();
//            Command<String> requestPostData = Network.getRequestPostData(requestId);
//
//
//            String method = requestPostData.getMethod();
//
//
//            Map<String, Object> params = requestPostData.getParams();
//            Command<String> stringCommand = requestPostData.doesNotSendResponse();
//
//
//
//            Command<Network.GetResponseBodyResponse> responseBody = Network.getResponseBody(requestId);
//
//
//            Network.GetResponseBodyResponse response = devTools.send(responseBody);
//            String body = response.getBody();
//
//            Request request = info.getRequest();
//            String url = request.getUrl();
//
//            Optional<Response> redirectResponse = info.getRedirectResponse();
//            RequestId requestId = info.getRequestId();
//
//
//            Command<Network.GetResponseBodyResponse> responseBody = Network.getResponseBody(requestId);
//
//
//
//
////            Network.GetResponseBodyResponse response = devTools.send(responseBody);
////            String body = response.getBody();
//
//            System.out.println("dddddddddddddddddddddd:"+redirectResponse);
//        });
//        devTools.addListener(Network.responseReceived(), info -> {
//
//            Response response = info.getResponse();
//
//
//            String url = response.getUrl();
//            Headers headers = response.getHeaders();
//
//
//            System.out.println("sssssssssss"+url);
//
//
//        });
//        devTools.createSession();
//        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
//        driver.get("http://www.hao123.com/");
//        String source = driver.getPageSource();











    }


}
