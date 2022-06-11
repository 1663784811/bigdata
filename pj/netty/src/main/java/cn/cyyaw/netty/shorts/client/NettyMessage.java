package cn.cyyaw.netty.shorts.client;


import lombok.Data;

@Data
public class NettyMessage {

    private String type;

    private NettyRequest body;

}
