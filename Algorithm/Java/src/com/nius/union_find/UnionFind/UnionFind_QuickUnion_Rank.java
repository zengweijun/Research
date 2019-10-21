package com.nius.union_find.UnionFind;

// 基于Rank的优化
public class UnionFind_QuickUnion_Rank extends UnionFind_QuickUnion {
    private int[] ranks; // rank 每个集合（树）的高度
    public UnionFind_QuickUnion_Rank(int capacity) {
        super(capacity);
        ranks = new int[capacity];
        for (int i = 0; i < ranks.length; i++) {
            ranks[i] = 1; // 一开始创建，高度1
        }
    }

    // 当处于最坏情况的时候，parent链可能退化为链表
    // 此处做基于rank的优化
    // 将矮的树挂到高的树上，这样树的高度增加的几率变小，为搜索带来较低时间复杂度
    @Override
    public void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1 == p2) return;

        // 先对比两个集合的高度
        if (ranks[p1] < ranks[p2]) {
            parents[p1] = p2;
        } else if (ranks[p1] > ranks[p2]) {
            parents[p2] = p1;
        } else {
            parents[p1] = p2;
            ranks[p2]++;
        }
    }
}
