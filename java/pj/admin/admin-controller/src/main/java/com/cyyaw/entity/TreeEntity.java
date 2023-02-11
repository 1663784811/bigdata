package com.cyyaw.entity;

import cn.hutool.core.util.StrUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 树对象
 */
@Data
public class TreeEntity<T> {

    /**
     * 森林
     */
    private List<Node<T>> root = new ArrayList<>();

    /**
     * 添加数据
     */
    public void add(TreeEntity.Node<T> node) {
        if (root.size() > 0) {
            String pid = node.getPid();
            if (StrUtil.isBlank(pid)) {
                root.add(node);
            } else {
                boolean have = false;
                for (int i = 0; i < root.size(); i++) {
                    Node<T> nodeItem = root.get(i);
                    String nodePid = nodeItem.getPid();
                    List<Node> children = nodeItem.getChildren();
                    // ========

                }
                if (!have) {
                    root.add(node);
                }
            }
        } else {
            root.add(node);
        }
    }


    @Data
    public static class Node<S> {

        /**
         * 节点ID
         */
        private String tid;

        /**
         * 父级ID
         */
        private String pid;

        /**
         * 名称
         */
        private String name;

        /**
         * 子节点
         */
        private List<Node> children = new ArrayList<>();

        /**
         * 数据
         */
        private S data;

    }
}
