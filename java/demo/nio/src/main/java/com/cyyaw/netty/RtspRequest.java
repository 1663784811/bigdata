package com.cyyaw.netty;

import java.util.*;

/**
 * RTSP Request model class
 */
public class RtspRequest {
    private final String method;
    private final String uri;
    private final String version;
    private final Map<String, String> headers;

    public RtspRequest(String method, String uri, String version) {
        this.method = method;
        this.uri = uri;
        this.version = version;
        this.headers = new HashMap<>();
    }

    public void addHeader(String name, String value) {
        headers.put(name, value);
    }

    public String getMethod() {
        return method;
    }

    public String getUri() {
        return uri;
    }

    public String getVersion() {
        return version;
    }

    public String getHeader(String name) {
        return headers.get(name);
    }

    public Set<String> getHeaderNames() {
        return headers.keySet();
    }
}