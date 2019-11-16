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

    // Breadth First Search
    /// 遍历
    // 广度优先搜索（宽度优先、横向优先）【层序遍历】
    void bfs(V begin, VertexVisitor<V> visitor);

    // Depth First Search
    // 深度优先搜索（类似二叉树前序遍历，一条路走到底再返回）
    // 二叉树：沿左子树走到底再遍历右子树
    void dfs(V begin, VertexVisitor<V> visitor);

    interface VertexVisitor<V> {
        boolean visit(V v);
    }
}
