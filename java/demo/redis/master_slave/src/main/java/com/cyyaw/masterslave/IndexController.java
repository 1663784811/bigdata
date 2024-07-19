package com.cyyaw.masterslave;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {


    @Autowired
    private RedisTemplate<String, Object> masterRedisTemplate;

    @GetMapping("")
    @ResponseBody
    public String index() {
        masterRedisTemplate.opsForValue().set("key", "木木木木ssssssssssssssssssssssssssssssssssssssssssssss");
        Object o = masterRedisTemplate.opsForValue().get("key");
        System.out.println(o);
        return "ok";
    }


}
