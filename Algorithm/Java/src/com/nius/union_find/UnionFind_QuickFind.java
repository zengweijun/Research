package com.nius.union_find;

public class UnionFind_QuickFind extends UnionFind {
    // Find O(1)  union O(n)
    // 不过一般不建议使用，因为这个union效率太低

    public UnionFind_QuickFind(int capacity) {
        super(capacity);
    }

    /// 这里parents[v]就是v的父节点
    /// O(1)
    public int find(int v) {
        rangeCheck(v);
        return parents[v];
    }

    /// 这里固定将左边的v1所在集合合并到v2所在集合
    /// 同时为了保持find为O(1)，这里必须使用保证树的深度最大为2
    /// O(n)
    public void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1 == p2) return;

        for (int i = 0; i < parents.length; i++) {
            if (parents[i] == p1) {
                parents[i] = p2;
            }
        }
    }
}
