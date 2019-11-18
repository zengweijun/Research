package com.nius.union_find.UnionFind;

// 基于size的优化
public class UnionFind_QuickUnion_Size extends UnionFind_QuickUnion {
    private int[] sizes; // 每个集合（树）的总元素数量
    public UnionFind_QuickUnion_Size(int capacity) {
        super(capacity);
        sizes = new int[capacity];
        for (int i = 0; i < sizes.length; i++) {
            sizes[i] = 1; // 一开始创建，所有集合都是单个元素，集合元素数量size为1
        }
    }

    // 当处于最坏情况的时候，parent链可能退化为链表
    // 此处做基于size的优化
    // 将元素数量少的集合挂到元素数量多的集合上，防止退化为链表的

    @Override
    public void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1 == p2) return;

        // 先对比两个集合的size（元素数量）
        // 注意：这里是元素数量，而不是链的长度，不是链表高度
        if (sizes[p1] < sizes[p2]) {
            parents[p1] = p2;
            sizes[p2] += sizes[p1];
        } else {
            parents[p2] = p1;
            sizes[p1] += sizes[p2];
        }
    }
}
