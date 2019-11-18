package com.nius.union_find.UnionFind;

// 使用基于rank、size的 + 路劲压缩、分裂、减半 优化
// 最终优化可以确保每次操作的时间复杂度 O(α(n)) α(n)<5
// 建议：Rank + PathHalving 或 + PathSpliting

/** 并查集
 * [0, 1, 2, 3, 4, 5, 6] 编号(索引)
 * [0, 1, 2, 3, 4, 5, 6] 元素
 * [a, b, c, d, e, f, g] 元素 (可以理解为0-6的映射)
 */
public abstract class UnionFind {
    // 编号做索引，对象放在数组中（当然这里可以使用哈希表或其它数据结构）
    // 假设这里的对象为[0, capacity)
    protected int[] parents;

    public UnionFind(int capacity) {
        if (capacity < 1) { throw new IllegalArgumentException("capacity must be >= 1"); }

        parents = new int[capacity];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
    }

    /** 查找v所属的集合（根节点） */
    public abstract int find(int v);

    /** 合并v1、v2所在的集合 */
    public abstract void union(int v1, int v2);

    /** 检查v1、v2是否属于同一个集合 */
    public boolean isSame(int v1, int v2) {
        return find(v1) == find(v2);
    }

    protected void rangeCheck(int v) {
//        if (v < 0 || v >= parents.length) {
//            throw new IllegalAccessException("v is out of bounds");
//        }
    }
}
