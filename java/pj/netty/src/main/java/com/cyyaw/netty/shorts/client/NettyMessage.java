package com.cyyaw.netty.shorts.client;


import lombok.Data;

@Data
public class NettyMessage {

    private String type;

    private com.cyyaw.netty.shorts.client.NettyRequest body;

}
