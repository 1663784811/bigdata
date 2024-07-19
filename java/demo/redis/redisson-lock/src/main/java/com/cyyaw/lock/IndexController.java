package com.cyyaw.lock;


//import org.redisson.api.RLock;
//import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class IndexController {

    @Autowired
    private RedisTemplate<String, String> masterRedisTemplate;

//    @Autowired
//    private RedissonClient redissonClient;


    @GetMapping("")
    public String index() {
        masterRedisTemplate.opsForValue().set("key", "木木木木ssssssssssssssssssssssssssssssssssssssssssssss");
        Object o = masterRedisTemplate.opsForValue().get("key");
        System.out.println(o);

//        RLock lock = redissonClient.getLock("");
//        try {
//            boolean isLocked = lock.tryLock(1, TimeUnit.MILLISECONDS);
//            if (isLocked) {
//                // TODO
//            }
//        } catch (Exception e) {
//            lock.unlock();
//        }


        return "ok";
    }


}
