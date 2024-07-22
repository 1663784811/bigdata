package com.cyyaw.test.order;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {


    @Value("${application.config.file-path:}")
    private String filePath;

    @GetMapping("")
    public String index() {
        return "index: " + filePath;
    }


}
