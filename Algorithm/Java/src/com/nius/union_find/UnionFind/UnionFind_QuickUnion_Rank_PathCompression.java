package com.nius.union_find.UnionFind;

// 基于Rank的优化 + 路径压缩
// 思路：在find的时候，使从该条路劲从该点往上全部指向根节点
public class UnionFind_QuickUnion_Rank_PathCompression extends UnionFind_QuickUnion_Rank {

    public UnionFind_QuickUnion_Rank_PathCompression(int capacity) {
        super(capacity);
    }

    // 在查找根节点的过程中，跳步往上的同时，将树的路劲缩短
    // 1 -> 2 -> 3 -> 4 -> 5
    // find(1) ==> parent[1] = parent[2] = parent[3] = parent[4] = parent[5] = 5
    // 要实现该逻辑，可使用递归

    @Override
    public int find(int v) {
        rangeCheck(v);
        if (parents[v] != v) {
            // find的功能是找到根节点
            // 此处先让v指向根节点，而将v的父节点作为参数传入
            // 下次递归v的父节点执行相同操作，于是该条链路上所有
            // 的节点都会依次执行，最终该链路从该节点往上所有节点都会指向根节点
            parents[v] = find(parents[v]);
        }
        return parents[v];
    }
}
