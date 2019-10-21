package com.nius.union_find;

import com.nius.tools.Asserts;
import com.nius.tools.Times;

public class MainUnionFind {
    static final int count = 100000;

    public static void main(String[] args) {
        test(new UnionFind_QuickFind(count));
    }


    static void test(UnionFind uf) {

        //
        uf.union(0, 1);
        uf.union(0, 3);
        uf.union(0, 4);
        uf.union(2, 3);
        uf.union(2, 5);

        uf.union(6, 7);

        uf.union(8, 10);
        uf.union(9, 10);
        uf.union(9, 11);

        Asserts.test(!uf.isSame(2, 7));
        uf.union(4, 6);
        Asserts.test(uf.isSame(2, 7));

        Times.test(uf.getClass().getSimpleName(), () -> {
            for (int i = 0; i < count; i++) {
                uf.union((int)(Math.random() * count), (int)(Math.random() * count));
            }

            for (int i = 0; i < count; i++) {
                uf.isSame((int)(Math.random() * count), (int)(Math.random() * count));
            }
        });
    }
}
