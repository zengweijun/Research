package com.nius.union_find.UnionFind;

// 基于Rank的优化 + 路径分裂
// 让所有节点都指向其祖父节点
// 思路：1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7
// find(1) ==> 1 -> 3 -> 5 -> 7 和 2 -> 4 -> 6 -> 7 操作
// 这种实现方式相对于路径压缩，实现成本相对较低，路劲压缩该路劲上的
// 所有节点都要实现指向根节点的操作，实现成本较高，路劲分裂方式虽然
// 树的高度没降到那么低，但是现成本较低，折中后相对更加优秀
public class UnionFind_QuickUnion_Rank_PathSpliting extends UnionFind_QuickUnion_Rank {

    public UnionFind_QuickUnion_Rank_PathSpliting(int capacity) {
        super(capacity);
    }

    @Override
    public int find(int v) {
        rangeCheck(v);
        while (v != parents[v]) {
            // 让所有节点都指向其祖父节点
            int p = parents[v];
            parents[v] = parents[parents[v]];
            // 注意，上述操作将parent[v]覆盖
            // 此处用p记录parent[v]，让后让v = p
            // 这样下一次循环parent[v]会做同样的操作
            // 最终会实现降低树的高度
            v = p;
        }
        return parents[v];
    }
}
