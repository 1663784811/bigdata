package com.cyyaw.algorithm.map.app;


import lombok.Data;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * app 图的实现
 */
public class AppGraph {

    /**
     * 节点
     */
    private Map<String, AppNode> node = new ConcurrentHashMap();

    /**
     * 节点最短路径表
     */
    private Map<String, List<String>> route = new ConcurrentHashMap<>();


    /**
     * 添加节点
     */
    public void addNode(AppNode appNode) {
        String key = appNode.getNodeId();
        if (null != node.get(key)) {
            throw new RuntimeException("所添加的图的节点已经存在");
        } else {
            node.put(key, appNode);
        }
    }

    /**
     * 添加边
     */
    public void addEdge(String from, String to, int weight) {
        AppNode fromNode = node.get(from);
        AppNode toNode = node.get(to);
        if (null != fromNode && null != toNode) {
            fromNode.addEdge(to, weight);
            // 重构
            route = new ConcurrentHashMap<>();
        } else {
            throw new RuntimeException("某条边节点不存在");
        }

    }


    public List<String> shortestRoute(String from, String to) {
        List<String> routeTableList = route.get(from);
        if (null != routeTableList) {
            return routeTableList;
        }
        // 第一步:
        Map<String, RouteTable> tableMap = new ConcurrentHashMap<>();
        for (String key : node.keySet()) {
            RouteTable routeTable = new RouteTable();
            if (from.equals(key)) {
                routeTable.setPath(from);
                routeTable.setDist(0);
            }
            routeTable.setVertex(key);
            tableMap.put(key, routeTable);
        }
        // 第二步:
        List<KeyValue> tableList = new CopyOnWriteArrayList<>();
        KeyValue kv = new KeyValue();
        kv.setKey(from);
        kv.setValue(0);
        tableList.add(kv);
        Map<String, RouteTable> mapTable = getRouteTableList(tableList, tableMap);
        // 第三步:
        List<String> list = routeTableFn(mapTable, to);
        route.put(from, list);
        return list;
    }

    public List<String> routeTableFn(Map<String, RouteTable> tableMap, String to) {
        List<String> rest = new ArrayList<>();
        RouteTable routeTable = tableMap.get(to);
        String vertex = routeTable.getVertex();
        int dist = routeTable.getDist();
        String path = routeTable.getPath();
        if (dist > 0 && null != path && !path.equals(to)) {
            rest.addAll(routeTableFn(tableMap, path));
        }
        rest.add(vertex);
        return rest;
    }

    /**
     * 获取 最短距离表
     */
    public Map<String, RouteTable> getRouteTableList(List<KeyValue> list, Map<String, RouteTable> tableMap) {
        if (list.size() > 0) {
            // 取出第一个
            KeyValue kv = list.remove(0);
            int distance = kv.getValue();
            String nodeId = kv.getKey();
            //
            AppNode appNode = node.get(nodeId);
            Map<String, Integer> edge = appNode.getEdge();
            // 遍历边框
            for (String key : edge.keySet()) {
                // 权重
                Integer weight = edge.get(key);
                RouteTable routeTable = tableMap.get(key);
                int dist = routeTable.getDist();
                // 判断是否是最短
                int addWeight = weight + distance;
                if (dist < 0 || dist > addWeight) {
                    // 更新最短路径
                    routeTable.setPath(nodeId);
                    routeTable.setDist(addWeight);
                }
                // =================== 放置队列中,并排序
                KeyValue newKv = new KeyValue();
                newKv.setKey(key);
                newKv.setValue(addWeight);
                list.add(newKv);
                for (int i = 0; i < list.size(); i++) {
                    for (int j = i + 1; j < list.size(); j++) {
                        KeyValue iobj = list.get(i);
                        KeyValue jobj = list.get(j);
                        if (iobj.getValue() > jobj.getValue()) {
                            list.set(i, jobj);
                            list.set(j, iobj);
                        }
                    }
                }
                // ===================
                getRouteTableList(list, tableMap);
            }


        }
        return tableMap;
    }

    @Data
    static class RouteTable {
        private String vertex;
        private int dist = -1;
        private String path;
    }

    @Data
    static class KeyValue {
        private String key;
        private int value;
    }

}
