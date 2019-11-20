package com.nius.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class Graph<V, E> {
    protected WeightManager<E> weightManager;

    public Graph() {}
    public Graph(WeightManager<E> weightManager) {
        this.weightManager = weightManager;
    }

    public abstract int edgeCount();  // 边数
    public abstract int vertexCount(); // 顶点数

    // 添加一个顶点
    public abstract void addVertex(V v);

    // 添加一条边
    public abstract void addEdge(V from, V to);
    public abstract void addEdge(V from, V to, E weight);

    // 删除一个顶点
    public abstract void removeVertex(V v);
    // 删除一条边
    public abstract void removeEdge(V from, V to);

    /// 遍历
    // Breadth First Search
    // 广度优先搜索（宽度优先、横向优先）【层序遍历】
    public abstract void bfs(V begin, VertexVisitor<V> visitor);

    // Depth First Search
    // 深度优先搜索（类似二叉树前序遍历，一条路走到底再返回）
    // 二叉树：沿左子树走到底再遍历右子树
    public abstract void dfs(V begin, VertexVisitor<V> visitor);

    // 拓扑排序(AOV网：Activity On Vertex Network)
    // topo排序只能针对有向无环图
    // 有向无环图(directed acyclic graph)，简称DAG图
    // 类似的还有AOE网
    public abstract List<V> topologicalSort();

    // 无向图（最小生成树：minimax spanning tree）
    // 最小生成树（支撑树）：最小联通子图，即n个点有n-1条边，且这n-1条边的总权值在所有情况中最小
    // 注意：对于任意一个顶点出去的边的权值唯一的时候，最小生成树唯一，否则最小生成树可能不唯一
    public abstract Set<EdgeInfo<V, E>> mst();

    // 最短路劲（有负权环的图没有最短路劲）
    public abstract Map<V, PathInfo<V, E>> shortestPath(V begin);

    public static class PathInfo<V, E> {
        // 这个是Graph的内部类，protected属性时Graph的子类可以访问
        protected E weight;
        protected List<EdgeInfo<V, E>> edgeInfos = new LinkedList<>();
        public PathInfo() {}
        public PathInfo(E weight) {
            this.weight = weight;
        }
        public E getWeight() {
            return weight;
        }
        public void setWeight(E weight) {
            this.weight = weight;
        }
        public List<EdgeInfo<V, E>> getEdgeInfos() {
            return edgeInfos;
        }
        public void setEdgeInfos(List<EdgeInfo<V, E>> edgeInfos) {
            this.edgeInfos = edgeInfos;
        }
        @Override
        public String toString() {
            return "PathInfo [weight=" + weight + ", edgeInfos=" + edgeInfos + "]";
        }
    }

    public interface WeightManager<E> {
        int compare(E w1, E w2);
        E add(E w1, E w2);
        E zero();
    }
    public interface VertexVisitor<V> {
        boolean visit(V v);
    }
    public static class EdgeInfo<V, E> {
        private V from;
        private V to;
        private E weight;
        public EdgeInfo(V from, V to, E weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        public V getFrom() {
            return from;
        }
        public void setFrom(V from) {
            this.from = from;
        }
        public V getTo() {
            return to;
        }
        public void setTo(V to) {
            this.to = to;
        }
        public E getWeight() {
            return weight;
        }
        public void setWeight(E weight) {
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "EdgeInfo{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    '}';
        }
    }
}
