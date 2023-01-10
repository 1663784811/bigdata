package com.cyyaw.netty.shorts.test;

public interface NettyClientService {


    String sendSyncMsg(String text, String dataId, String serviceId);

    void ackSyncMsg(String msg);
}
