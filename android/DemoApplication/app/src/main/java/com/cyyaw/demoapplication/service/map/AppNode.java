package com.cyyaw.demoapplication.service.map;


import android.view.accessibility.AccessibilityNodeInfo;

import com.cyyaw.demoapplication.service.map.page.IsPage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 图的点
 */
public class AppNode {

    private String nodeId;

    private Map<String, AppNodeRout> edge = new ConcurrentHashMap<>();

    private IsPage isPage;

    public AppNode(String nodeId, IsPage isPage) {
        this.nodeId = nodeId;
        this.isPage = isPage;
    }

    /**
     * 添加边
     */
    public void addEdge(String to, int weight, Task task) {
        if (null != edge.get(to)) {
            throw new RuntimeException("所添加图的边已经存在");
        } else {
            edge.put(to, new AppNodeRout(weight, task));
        }
    }


    public AppNodeRout getEdgeByKey(String key) {
        return edge.get(key);
    }

    // =================================

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public Map<String, AppNodeRout> getEdge() {
        return edge;
    }

    public void setEdge(Map<String, AppNodeRout> edge) {
        this.edge = edge;
    }

    public boolean isThisPage(AccessibilityNodeInfo nodeInfo) {
        if (null != isPage) {
            return isPage.isThisPage(nodeInfo);
        }
        return false;
    }


    public static class AppNodeRout {
        private Integer width;

        private Task task;

        public AppNodeRout(int weight, Task task) {
            this.width = weight;
            this.task = task;
        }

        public Integer getWidth() {
            return width;
        }

        public void setWidth(Integer width) {
            this.width = width;
        }

        public Task getTask() {
            return task;
        }

        public void setTask(Task task) {
            this.task = task;
        }
    }
}
