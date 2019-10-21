package com.nius.union_find.UnionFind;

// 基于Rank的优化 + 路径减半
// 让链路上每隔一个节点就让其指向祖父节点
// 思路：1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7
// find(1) ==> 1 -> 3 -> 5 -> 7
// 只需实现：1 -> 3 -> 5 -> 7
// 剩余节点：2 -> 3、4 -> 5、6 -> 7
// 最终效果和路劲分裂效果基本相同
// 但是该实现方式的效果更优，操作的时间复杂度更多，应为最优解
public class UnionFind_QuickUnion_Rank_PathHalving extends UnionFind_QuickUnion_Rank {

    public UnionFind_QuickUnion_Rank_PathHalving(int capacity) {
        super(capacity);
    }

    @Override
    public int find(int v) {
        rangeCheck(v);
        while (v != parents[v]) {
            // 让链路上每隔一个节点就让其指向祖父节点
            parents[v] = parents[parents[v]];
            // 这里让v走两步（每隔一个节点）
            // 但经过上述逻辑后，parent[v]刚好是下一个需要执行相同逻辑的节点
            // 于是直接让v=parents[v]，parents[v]再接着执行相同的逻辑
            v = parents[v];
        }
        return parents[v];
    }
}
