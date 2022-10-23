package com.cyyaw.sentinel;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {


    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @RequestMapping(value = {"","index"})
    public String index(String key){
        return  redisTemplate.opsForValue().get(key);
    }

    @RequestMapping(value = {"set"})
    public String set(String key, String val){
        redisTemplate.opsForValue().set(key,val);
        return "ok";
    }

}
