package cn.cyyaw.netty.shorts.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.cache.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class NettyClientServiceImpl implements NettyClientService {


    @Autowired
    private NettyClient nettyClient;


    //缓存接口这里是LoadingCache，LoadingCache在缓存项不存在时可以自动加载缓存
    private static LoadingCache<String, SyncFuture> futureCache = CacheBuilder.newBuilder()
            //设置缓存容器的初始容量为10
            .initialCapacity(100)
            // maximumSize 设置缓存大小
            .maximumSize(10000)
            //设置并发级别为20，并发级别是指可以同时写缓存的线程数
            .concurrencyLevel(20)
            // expireAfterWrite设置写缓存后8秒钟过期
            .expireAfterWrite(8, TimeUnit.SECONDS)
            //设置缓存的移除通知
            .removalListener(new RemovalListener<Object, Object>() {
                @Override
                public void onRemoval(RemovalNotification<Object, Object> notification) {
                    log.debug("LoadingCache: {} was removed, cause is {}", notification.getKey(), notification.getCause());
                }
            })
            //build方法中可以指定CacheLoader，在缓存不存在时通过CacheLoader的实现自动加载缓存
            .build(new CacheLoader<String, SyncFuture>() {
                @Override
                public SyncFuture load(String key) throws Exception {
                    // 当获取key的缓存不存在时，不需要自动添加
                    return null;
                }
            });

    @Override
    public String sendSyncMsg(String text, String dataId, String serviceId) {

        SyncFuture<String> syncFuture = new SyncFuture<String>();
        // 放入缓存中
        futureCache.put(dataId, syncFuture);

        // 封装数据
        JSONObject object = new JSONObject();
        object.put("dataId", dataId);
        object.put("text", text);

        // 发送同步消息
        String result = nettyClient.sendSyncMsg(object.toJSONString(), syncFuture);

        return result;
    }

    @Override
    public void ackSyncMsg(String msg) {
        log.info("ACK确认信息: {}", msg);
        JSONObject object = JSON.parseObject(msg);
        String dataId = object.getString("dataId");

        // 从缓存中获取数据
        SyncFuture<String> syncFuture = futureCache.getIfPresent(dataId);

        // 如果不为null, 则通知返回
        if (syncFuture != null) {
            syncFuture.setResponse(msg);
            //主动释放
            futureCache.invalidate(dataId);
        }
    }

}
