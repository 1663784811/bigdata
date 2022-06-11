package cn.cyyaw.netty.shorts.client;


import lombok.Data;

@Data
public class NettyRequest {

    private String requestId;

    private String className;

    private String methodName;

    private String parameterTypes;

    private String parameterValues;
}
