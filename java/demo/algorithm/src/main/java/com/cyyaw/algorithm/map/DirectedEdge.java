package com.cyyaw.algorithm.map;


/**
 * 加权有向图边的表示
 */
public class DirectedEdge {
    // 起点
    private final int v;
    // 终点
    private final int w;
    // 当前边的权重
    private final double weight;

    // 通过顶点v和w，以及权重weight值构造一个边对象
    public DirectedEdge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    //获取边的权重值
    public double weight(){
        return weight;
    }

    // 获取有向边的起点
    public int from(){
        return v;
    }

    // 获取有向边的终点
    public int to(){
        return w;
    }
}
