package com.nius.union_find.UnionFind;

// Find: O(log2n)  union: O(log2n)
// 可优化至 Find: O(α(n)) α(n)<5   union: O(α(n)) α(n)<5
public class UnionFind_QuickUnion extends UnionFind {

    public UnionFind_QuickUnion(int capacity) {
        super(capacity);
    }

    // 不断向上找父节点
    // O(n)
    @Override
    public int find(int v) {
        rangeCheck(v);
        while (v != parents[v]) {
            v = parents[v];
        }
        return parents[v];
    }

    // 将v1集合挂到v2集合上
    // 将v1的父节点p1挂到v2的父节点p2上
    // O(n)
    @Override
    public void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1 == p2) return;
        parents[p1] = p2;
    }
}
