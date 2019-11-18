package com.nius.union_find;

import com.nius.sort.tools.Asserts;
import com.nius.sort.tools.Times;
import com.nius.union_find.UnionFind.*;

public class MainUnionFindTest {
    static final int count = 1000000;

    public static void main(String[] args) {

        // 以下测试不会太严谨，由于存在数据随机情况，不过能看出大体情况
//        test(new UnionFind_QuickFind(count));
//        test(new UnionFind_QuickUnion(count));
//        test(new UnionFind_QuickUnion_Size(count));
//        test(new UnionFind_QuickUnion_Rank(count));
        test(new UnionFind_QuickUnion_Rank_PathCompression(count));
        test(new UnionFind_QuickUnion_Rank_PathSpliting(count));
        test(new UnionFind_QuickUnion_Rank_PathHalving(count));
        test(new GenericUnionFind<Integer>());

        // 使用路劲压缩、分裂、减半 + 基于rank、size的优化
        // 最终优化每次操作的时间复杂度 O(α(n)) α(n)<5

        testV();
    }

    static void testV() {
        // 测试泛型
		GenericUnionFind<Student> uf = new GenericUnionFind<>();
		Student stu1 = new Student(1, "jack");
		Student stu2 = new Student(2, "rose");
		Student stu3 = new Student(3, "jack");
		Student stu4 = new Student(4, "rose");
		uf.makeSet(stu1);
		uf.makeSet(stu2);
		uf.makeSet(stu3);
		uf.makeSet(stu4);

		uf.union(stu1, stu2);
		uf.union(stu3, stu4);

        Asserts.test(uf.isSame(stu1, stu2));
        Asserts.test(uf.isSame(stu3, stu4));
        Asserts.test(!uf.isSame(stu1, stu4));

		uf.union(stu1, stu4);
        Asserts.test(uf.isSame(stu1, stu4));
    }

    static void test(GenericUnionFind<Integer> uf) {
        // 测试泛型
        testV();

        // 初始化集合，将每个元素单独成一个集合
        for (int i = 0; i < count; i++) {
            uf.makeSet(i);
        }

        // 测试正确性
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

        // 测试效率
        Times.test(uf.getClass().getSimpleName(), () -> {
            for (int i = 0; i < count; i++) {
                uf.union((int)(Math.random() * count), (int)(Math.random() * count));
            }

            for (int i = 0; i < count; i++) {
                uf.isSame((int)(Math.random() * count), (int)(Math.random() * count));
            }
        });
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

class Student {
    private int age;
    private String name;
    public Student(int age, String name) {
        this.age = age;
        this.name = name;
    }
}
