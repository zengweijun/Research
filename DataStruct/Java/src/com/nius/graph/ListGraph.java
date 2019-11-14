package com.nius.graph;

import com.sun.org.apache.xpath.internal.operations.Equals;

import java.util.*;

/// 接近邻接表实现方式
public class ListGraph<V, E> implements Graph<V, E> {
    private Map<V, Vertex<V, E>> vertices = new HashMap<>();
    private Set<Edge<V, E>> edges = new HashSet<>();

    public void print() {
        vertices.forEach((V v, Vertex<V, E> vt)->{
            StringBuilder sb = new StringBuilder();
            sb.append(vt);
            sb.append(": 出[");
            vt.outEdges.forEach((Edge<V, E> e)-> {
                sb.append(e.to + ",");
            });
            if (vt.outEdges.size() > 0) {
                sb.replace(sb.length()-1, sb.length(), "");
            }
            sb.append("]、");
            sb.append("进[");
            vt.inEdges.forEach((Edge<V, E> e)-> {
                sb.append(e.from + ",");
            });
            if (vt.inEdges.size() > 0) {
                sb.replace(sb.length()-1, sb.length(), "");
            }
            sb.append("]");
            System.out.println(sb.toString());
        });
        edges.forEach((Edge<V, E> e)->{
            System.out.println(e);
        });
    }

    @Override
    public int edgeCount() {
        return edges.size();
    }

    @Override
    public int vertexCount() {
        return vertices.size();
    }

    @Override
    public void addVertex(V v) {
        // 此处为判断null，即允许为空
        if (vertices.containsKey(v)) return;
        vertices.put(v, new Vertex<>(v));
    }

    @Override
    public void addEdge(V from, V to) {
        addEdge(from, to, null);
    }

    @Override
    public void addEdge(V from, V to, E weight) {
        // 先判断顶点fron、to是否存在
        Vertex fromVertex = vertices.get(from);
        if (fromVertex == null) {
            fromVertex = new Vertex(from);
            vertices.put(from, fromVertex);
        }

        Vertex toVertex = vertices.get(to);
        if (toVertex == null) {
            toVertex = new Vertex(to);
            vertices.put(to, toVertex);
        }

        // 先看以前是否存在边，如果存在边，更新权值，否则添加一条边
        // 这里可以选择fromVertex.outEdges 或者 toVertex.inEdges
        Edge e = new Edge(fromVertex, toVertex);
        e.weight = weight;
        // 统一先删除以前的边，然后将新的边加进去
        // if (fromVertex.outEdges.contains(e)) { }
        if (fromVertex.outEdges.remove(e)) {
            // 删除成功则说明存在这条边，注意，这里e的比较是比较hash值，不是内存地址
            // 此时，终点的inEdges也许要维护一下
            toVertex.inEdges.remove(e);
            edges.remove(e);
        }

        // 添加边
        fromVertex.outEdges.add(e);
        toVertex.inEdges.add(e);
        edges.add(e);
    }

    @Override
    public void removeVertex(Object o) {

    }

    @Override
    public void removeEdge(Object from, Object to) {

    }



    private static class Vertex<V, E> {
        // 存储的值
        V value;

        // 这里的边通常不用考虑顺序，因此用set存储效率更高
        // 以改点为终点的边(进来的边)
        Set<Edge<V, E>> inEdges = new HashSet<>();
        // 以该点为起点的边(出去的边)
        Set<Edge<V, E>> outEdges = new HashSet<>();

        public Vertex(V value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex<?, ?> vertex = (Vertex<?, ?>) o;
            // Value 相等视为相等
            return Objects.equals(value, vertex.value);
        }

        @Override
        public int hashCode() {
            // return value == null ? 0: value.hashCode();
            return Objects.hash(value);
        }

        @Override
        public String toString() {
            return value == null ? "null" : value.toString();
        }
    }
    private static class Edge<V, E> {
        Vertex<V, E> from; // 起点
        Vertex<V, E> to;   // 终点
        E weight;          // 权值

        public Edge(Vertex<V, E> from, Vertex<V, E> to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge<?, ?> edge = (Edge<?, ?>) o;
            // from、to相等视为相等
            return Objects.equals(from, edge.from) && Objects.equals(to, edge.to);
        }

        @Override
        public int hashCode() {
            // 如果equals返回true，则hash值必须相等才有意义
            // 上边方法使用from和to属性判断，因此这里也取from和to做计算

            // return from.hashCode() * 31 + to.hashCode();
            return Objects.hash(from, to); // 系统方法，更优
        }

        @Override
        public String toString() {
            return "Edge{" +
                    from +
                    "->" +
                    to +
                    ", weight=" + weight +
                    '}';
        }
    }
}
