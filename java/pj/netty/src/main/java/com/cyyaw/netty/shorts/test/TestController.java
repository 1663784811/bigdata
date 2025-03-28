package com.cyyaw.netty.shorts.test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private NettyClientService clientService;

    @RequestMapping("/sendSyncMsg")
    public String sendSyncMsg(String dataId, String text) {
        String serviceId = "mmmm";
        String result = clientService.sendSyncMsg(text, dataId, serviceId);
        return "result:" + result;
    }

}

