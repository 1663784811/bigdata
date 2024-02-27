package com.cyyaw.algorithm.map.app;

import java.util.List;

public class MapTest {


    public static void main(String[] args) {


        AppGraph appGraph = new AppGraph();
        // 添加节点
        for (int i = 0; i < 10; i++) {
            AppNode appNode = new AppNode();
            appNode.setNodeId(i + "");
            appGraph.addNode(appNode);
        }
        // 添加边
        appGraph.addEdge("0", "6", 6);
        appGraph.addEdge("1", "2", 1);
        appGraph.addEdge("1", "3", 2);
        appGraph.addEdge("1", "8", 3);
        appGraph.addEdge("2", "5", 3);
        appGraph.addEdge("3", "6", 3);
        appGraph.addEdge("5", "7", 2);
        appGraph.addEdge("5", "8", 3);
        appGraph.addEdge("8", "9", 1);

        // 查找某节点到某节点的最短路径
        List<String> list = appGraph.shortestRoute("1", "8");

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

    }

}
