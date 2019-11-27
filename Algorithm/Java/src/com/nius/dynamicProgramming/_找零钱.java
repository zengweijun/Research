package com.nius.dynamicProgramming;

public class _找零钱 {


    public static void main(String[] args) {
        int n = 41; // 需要找的零钱总值（元）
        int[] faces = {1, 5, 20, 25}; // 可以找的硬币面值（元）
        // 问题：找n元所需的最少硬币数

        // 1.定义状态
        // dp(n) 找n元需要的最少硬币数

        // 2.边界值（初始值）
        //  1) 第一次选择的是一个硬币是1元面值， 则 dp(n) = dp(n-1) + 1
        //  2) 第一次选择的是一个硬币是5元面值， 则 dp(n) = dp(n-5) + 1
        //  3) 第一次选择的是一个硬币是20元面值，则 dp(n) = dp(n-20) + 1
        //  4) 第一次选择的是一个硬币是25元面值，则 dp(n) = dp(n-25) + 1

        // 3.状态转移方程(以上4种情况中最小的一种即是最优解)
        // dp(n) = min {dp(n-1), dp(n-5), dp(n-20), dp(n-25)}

        System.out.println("minCoins1: " + minCoins1(n));
        System.out.println("minCoins2: " + minCoins2(n));
        System.out.println("minCoins3: " + minCoins3(n));
        System.out.println("minCoins4: " + minCoins4(n, faces));
    }


    // 通用实现，未找出合理方案，返回-1
    static int minCoins4(int n, int[] faces) {
        if (n < 1 || faces == null || faces.length == 0) return -1;
        int[] dp = new int[n + 1];
        // 把 从 1 ~ n 的情况全部推出来
        // i 表示当前需要找的零钱
        for (int i = 1; i <= n; i++) {
            // 每一个i都可能出现上边四种情况，当然i < 某个硬币面值的时候，该硬币不能选择
            // 这里设置minCoins为一个最大值的目的是为了不影响后边选择最小值
            int minCoins = Integer.MAX_VALUE;
            for (int face : faces) {
                // 这里以防faces数组不是从小大排序，必须全不见面值都检查一次
                // 当前硬币面值比我们要找的零钱总数还大，不选
                if (i < face) continue;

                // 当前面值可以选，但是前一个面值 dp[i-face] 是无效值 -1，无法推出当前值，不选
                // 无效值：无法用当前数组中的硬币面值凑出 dp[i-face]
                int preCoins = dp[i-face];
                if (preCoins < 0) continue;

                // 需要找的零钱 i 大于等于当前硬币面值，可以选择
                // 同一个 i 在多次选择中结果中需要选取最小的一个才能得出最优解
                minCoins = Math.min(minCoins, preCoins + 1);
                // 注意：这里没有初始化1、5、20、25等初始值
                // 由于java数组初始化后直接全部默认初始值为0
                // 所以当i==1的时候，dp[1] = dp[1-1] + 1 = dp[0] + 1
                // 所以当i==5的时候，dp[5] = dp[5-1] + 1 = dp[4] + 1
            }

            if (minCoins == Integer.MAX_VALUE) {
                // 不能凑齐需要找的零钱
                dp[i] = -1;
            } else {
                dp[i] = minCoins;
            }
        }
        return dp[n];
    }

    // O(n) 优化(打印凑m需要哪些零钱面值)
    static int minCoins3 (int n) {
        if (n < 1) return -1;
        int[] dp = new int[n+1];
        int[] endFaces = new int[dp.length]; // 凑齐i元最后选择的一枚硬币面值
        for (int i = 1; i <= n; i++) {
            int minCount = Integer.MAX_VALUE;
            if (i >= 1) {
                int v = dp[i-1];
                if (v >= 0 && v < minCount) {
                    minCount = v;
                    endFaces[i] = 1;
                }
            }
            if (i >= 5) {
                int v = dp[i-5];
                if (v >= 0 && v < minCount) {
                    minCount = v;
                    endFaces[i] = 5;
                }
            }
            if (i >= 20) {
                int v = dp[i-20];
                if (v >= 0 && v < minCount) {
                    minCount = v;
                    endFaces[i] = 20;
                }
            }
            if (i >= 25) {
                int v = dp[i-25];
                if (v >= 0 && v < minCount) {
                    minCount = v;
                    endFaces[i] = 25;
                }
            }

            if (minCount == Integer.MAX_VALUE) {
                dp[i] = -1;
            } else {
                dp[i] = minCount + 1;
            }
        }
        showCoins(n, endFaces);
        return dp[n];
    }

    // O(n) 记忆化
    static int minCoins2 (int m) {
        if (m < 1) return -1;
        int[] dp = new int[m+1];
        for (int i = 1; i <= m; i++) {
            int minCount = Integer.MAX_VALUE;
            if (i >= 1) {
                int v = dp[i-1];
                if (v >= 0 && v < minCount) {
                    minCount = v;
                }
            }
            if (i >= 5) {
                int v = dp[i-5];
                if (v >= 0 && v < minCount) {
                    minCount = v;
                }
            }
            if (i >= 20) {
                int v = dp[i-20];
                if (v >= 0 && v < minCount) {
                    minCount = v;
                }
            }
            if (i >= 25) {
                int v = dp[i-25];
                if (v >= 0 && v < minCount) {
                    minCount = v;
                }
            }

            if (minCount == Integer.MAX_VALUE) {
                dp[i] = -1;
            } else {
                dp[i] = minCount + 1;
            }
        }
        return dp[m];
    }

    // O(n^3) 暴力递归
    static int minCoins1 (int m) {
        if (m < 1) return Integer.MAX_VALUE;
        if (m == 1 || m == 5 || m == 20 || m == 25) return 1;
        int cs1 = minCoins1(m-1);
        int cs2 = minCoins1(m-5);
        int cs3 = minCoins1(m-20);
        int cs4 = minCoins1(m-25);
        int minCount1 = Math.min(cs1, cs2);
        int minCount2 = Math.min(cs3, cs4);
        return Math.min(minCount1, minCount2) + 1;
    }

    static private void showCoins(int n, int[] endFaces) {
        System.out.print("[" + "凑齐" + n + "] = " );
        while (n > 0) {
            System.out.print(endFaces[n] + " ");
            n = n - endFaces[n];
        }
        System.out.println();
    }
}
