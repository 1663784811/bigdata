package com.cyyaw.lock.distributed;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 *   (1) T1加锁成功: 会添加TimerTask; 每10s执行一次检测锁是否为当前线程占用, 若占用, 则将key超时时间重置为30s;
 *   (2) T2加锁失败后会不停的去重试;
 *      redissonLock.lock();
 *   (1) T1加锁成功后直接返回, 20s后锁自动过期; (没有watch dog)
 *   (2) T2加锁失败后会不停的去重试;
 *
 *   redissonLock.lock(20, TimeUnit.SECONDS);
 *   (1) T1尝试加锁成功, 返回true; 会添加TimerTask; 每10s执行一次检测锁是否为当前线程占用, 若占用, 则将key超时时间重置为30s;
 *   (2) T2没有获取到锁会不停的去重试, 在50s后停止重试, 返回false;
 *
 *   boolean isGetLock = redissonLock.tryLock(50, TimeUnit.SECONDS);
 *   (1) T1尝试加锁成功, 返回true, 锁在20s后自动过期; (没有watch dog)
 *   (2) T2尝试加锁失败, 在50s后停止重试, 返回false;
 *   boolean isGetLock = redissonLock.tryLock(50, 20, TimeUnit.SECONDS);
 *
 *   //2. 公平锁 保证 Redisson 客户端线程将以其请求的顺序获得锁
 *   RLock fairLock = redissonClient.getFairLock("fairLock");
 *
 *   //3. 读写锁 没错与JDK中ReentrantLock的读写锁效果一样
 *   RReadWriteLock readWriteLock = redissonClient.getReadWriteLock("readWriteLock");
 *   readWriteLock.readLock().lock();
 *   readWriteLock.writeLock().lock();
 */
@Service
public class RedisLockDemo {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private Redisson redisson;

    public String deduceStock() {



//        redisson.getLock();


        String lockKey = "lockKey";
        RLock redissonLock = redisson.getLock(lockKey);

        try {
            //加锁, 实现锁续命的功能(加锁成功，后台启动一个timer, 默认每10s检测一次是否持有锁，若持有锁则继续续命30s)
            redissonLock.lock();
            //------ 执行业务逻辑 ----start------
            int stock = Integer.valueOf(redisTemplate.opsForValue().get("stock"));
            if (stock > 0) {
                int newStock = stock - 1;
                //执行业务操作减库存
                redisTemplate.opsForValue().set("stock", newStock + "");
                System.out.println("扣减库存成功, 剩余库存:" + newStock);
            } else {
                System.out.println("库存已经为0，不能继续扣减");
            }
            //------ 执行业务逻辑 ----end------
        } finally {
            //解锁
            redissonLock.unlock();
        }
        return "success";
    }
}

