package com.cyyaw.algorithm.map.app;


import lombok.Data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 图的点
 */
@Data
public class AppNode {

    private String nodeId;

    private Map<String, Integer> edge = new ConcurrentHashMap<>();


    /**
     * 添加边
     */
    public void addEdge(String to, int weight) {
        if (null != edge.get(to)) {
            throw new RuntimeException("所添加图的边已经存在");
        } else {
            edge.put(to, weight);
        }
    }


}
