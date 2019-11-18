package com.nius.union_find.UnionFind;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/// 泛型实现
// 这里实现了对泛型的支持，但是效率上不如之前单纯针对int型的
// 由于底层使用了HashMap等比价复杂的数据结构以及增加维护node节点
// 会带来一定的计算负担

// 当外部必须使用泛型的时候可以使用该GenericUnionFind，但若外部
// 模型中有唯一int标识，比如id等时，可以考虑使用非泛型的并查集，以
// 获得更优异的性能，用非泛型的并查集处理好数据后在和对应的模型建立联系

public class GenericUnionFind<V> {
    private Map<V, Node<V>> nodes = new HashMap<>();

    // 让V构造为一个集合
    public void makeSet(V v) {
        if (!nodes.containsKey(v)) {
            nodes.put(v, new Node<>(v));
        }
    }

    public V find(V v) {
        Node<V> node = findNode(v);
        return node == null ? null : node.value;
    }

    // 当处于最坏情况的时候，parent链可能退化为链表
    // 此处做基于rank的优化
    // 将矮的树挂到高的树上，这样树的高度增加的几率变小，为搜索带来较低时间复杂度
    public void union(V v1, V v2) {
        Node<V> p1 = findNode(v1);
        Node<V> p2 = findNode(v2);
        if (p1 == null || p2 == null) return;
        if (Objects.equals(p1.value, p2.value));

        // 先对比两个集合的高度
        if (p1.rank < p2.rank) {
            p1.parent = p2;
        } else if (p1.rank > p2.rank) {
            p2.parent = p1;
        } else {
            p1.parent = p2;
            p2.rank++;
        }
    }

    public boolean isSame(V v1, V v2) {
        return Objects.equals(find(v1), find(v2));
    }

    private Node findNode(V v) {
        Node<V> node = nodes.get(v);
        if (node == null) return null;
        while (!Objects.equals(node.value, node.parent.value)) {
            // 让链路上每隔一个节点就让其指向祖父节点
            node.parent = node.parent.parent;
            // 这里让v走两步（每隔一个节点）
            // 但经过上述逻辑后，parent[v]刚好是下一个需要执行相同逻辑的节点
            // 于是直接让v=parents[v]，parents[v]再接着执行相同的逻辑
            node = node.parent;
        }
        return node.parent;
    }

    private static class Node<V> {
        V value;
        Node<V> parent = this; // 初始指向自己
        int rank = 1; // 初始rank为1，树高度为1
        public Node(V value) {
            this.value = value;
        }
    }
}
