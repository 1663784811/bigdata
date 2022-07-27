package com.cyyaw.browser;

import com.cyyaw.browser.controller.Hao123Controller;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class BrowserApplication {


    public static void main(String[] args) {


        Hao123Controller hao123Controller = new Hao123Controller();

        hao123Controller.login();




//        driver.close();

    }

}
