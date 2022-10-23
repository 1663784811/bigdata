package com.cyyaw.service.admin;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@RequestMapping("/admin")
@RestController
public class IndexController {


    @GetMapping("/index")
    public String index() throws InterruptedException {

        TimeUnit.SECONDS.sleep(10);

        return "index"+ new Date().getTime();
    }


}
