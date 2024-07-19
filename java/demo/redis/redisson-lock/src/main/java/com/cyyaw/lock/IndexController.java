package com.cyyaw.lock;

import org.redisson.api.RFuture;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@RestController
public class IndexController {

    @Autowired
    private RedisTemplate<String, String> masterRedisTemplate;

    @Autowired
    private RedissonClient redissonClient;


    @GetMapping("")
    public String index() {
        masterRedisTemplate.opsForValue().set("key", "木木木木ssssssssssssssssssssssssssssssssssssssssssssss");
        Object o = masterRedisTemplate.opsForValue().get("key");
        System.out.println(o);
        RLock lock = null;
        try {
            lock = redissonClient.getLock("aaa");
            lock.lock(5, TimeUnit.SECONDS);
            System.out.println("加锁成功");
            Thread.sleep(1000 * 5);
        } catch (Exception e) {

        } finally {
            if (null != lock && lock.isLocked()) {
                lock.unlock();
                System.out.println("解锁成功");
            }
        }
        return "ok";
    }


}
