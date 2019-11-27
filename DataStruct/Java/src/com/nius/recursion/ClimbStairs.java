package com.nius.recursion;

// 爬楼梯（跳台阶）
public class ClimbStairs {
    public static void main(String[] args) {
        int n = 4;
        System.out.println(climbStairs1(n));
        System.out.println(climbStairs2(n));
        System.out.println(climbStairsExt1(n));
        System.out.println(climbStairsExt2(n));
    }


    // 1.有n个台阶，每次可以跨一步、也可以跨2步，爬完n个台阶有多少中不同的走法
    // dp算法逆向思维
    // dp(n)：爬n个阶梯的所有走法
    // n个阶梯时：
    // 第一步跨了1个阶梯时，剩余的n-1步有 dp(n-1)种走法
    // 第一步跨了2个阶梯时，剩余的n-2步有 dp(n-2)种走法
    // 而爬n个阶梯时，第一步只可能会存在这两种情况，因此
    // 爬n个阶梯的总情况就是这两种情况之和
    // dp(n) = dp(n-1) + dp(n-2) ==> 斐波拉契数列

    // 递归思想中考虑 n=1、n=2情况
    // dp(1)：爬1个阶梯的所有走法 = 1
    // dp(2)：爬2个阶梯的所有走法 = 2
    static int climbStairs1(int n) {
        if (n <= 2) return n;
        return climbStairs1(n-1) + climbStairs1(n-2);
    }

    // 斐波拉契优化思想（避免重复计算，提高效率）
    // 时间：O(n^2) --> O(n)
    // 空间：O(n) --> O(1)
    static int climbStairs2(int n) {
        if (n <= 2) return n;
        int first = 1;
        int second = 2;
        for (int i = 3; i <= n; i++) {
            second = first + second;
            first = second - first;
        }
        return second;
    }
    // 改题还可以使用找规律的形式，分别找出 dp(1)、dp(2)、dp(3)、dp(4)... 发现规律




    // 2.有n个台阶，每次可以跨一步、也可以跨2步...跨n步，爬完n个台阶有多少中不同的走法
    // dp算法逆向思维
    // dp(n)：爬n个阶梯的所有走法
    // n个阶梯时：
    // 第一步跨了1个阶梯时，剩余的n-1步有 dp(n-1)种走法
    // 第一步跨了2个阶梯时，剩余的n-2步有 dp(n-2)种走法
    // 第一步跨了3个阶梯时，剩余的n-3步有 dp(n-3)种走法
    // ...
    // 第一步跨了n个阶梯时，剩余的n-n步有 dp(n-n) = 1种走法
    // 而爬n个阶梯时，第一步只可能会存在这些情况，因此
    // 爬n个阶梯的总情况就是这些情况之和
    // dp(n) = dp(n-1) + dp(n-2) + dp(n-3) + ... + dp(2) + dp(1) + dp(0)
    // 又因为
    // dp(n-1) = dp(n-2) + dp(n-3) + dp(n-4) + ... + dp(2) + dp(1) + dp(0)
    // 两式相减
    // dp(n) - dp(n-1) = dp(n-1)
    // dp(n) = 2 * dp(n-1)   [n >= 2]
    static int climbStairsExt1(int n) {
        if (n <= 2) return n;
        return 2 * climbStairsExt1(n-1);
    }

    // 优化（转非递归）
    static int climbStairsExt2(int n) {
        if (n <= 2) return n;
        int result = 2;
        for (int i = 3; i <= n; i++) {
            result *= 2;
        }
        return result;
    }
}
