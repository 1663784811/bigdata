package com.cyyaw.demoapplication.service.map;


import android.view.accessibility.AccessibilityNodeInfo;

import com.cyyaw.demoapplication.service.ScreenOperation;
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
    public void addEdge(String to, int weight, OpenPage openPage) {
        if (null != edge.get(to)) {
            throw new RuntimeException("所添加图的边已经存在");
        } else {
            edge.put(to, new AppNodeRout(weight, openPage));
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

    public boolean isThisPage(ScreenOperation nodeInfo) {
        if (null != isPage) {
            return isPage.isThisPage(nodeInfo);
        }
        return false;
    }


    public static class AppNodeRout {
        private Integer width;

        private OpenPage openPage;

        public AppNodeRout(int weight, OpenPage openPage) {
            this.width = weight;
            this.openPage = openPage;
        }

        public Integer getWidth() {
            return width;
        }

        public void setWidth(Integer width) {
            this.width = width;
        }

        public OpenPage getOpenPage() {
            return openPage;
        }

        public void setOpenPage(OpenPage openPage) {
            this.openPage = openPage;
        }
    }
}
