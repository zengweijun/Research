package com.nius.graph;

public interface Graph<V, E> {
    int edgeCount();  // 边数
    int vertexCount(); // 顶点数

    // 添加一个顶点
    void addVertex(V v);

    // 添加一条边
    void addEdge(V from, V to);
    void addEdge(V from, V to, E weight);

    // 删除一个顶点
    void removeVertex(V v);
    // 删除一条边
    void removeEdge(V from, V to);

    /// 遍历
    // 广度优先搜索（宽度优先、横向优先）【层序遍历】
    void bfs(V begin);
}
