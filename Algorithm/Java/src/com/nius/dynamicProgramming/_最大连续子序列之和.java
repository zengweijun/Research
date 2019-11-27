package com.nius.dynamicProgramming;

public class _最大连续子序列之和 {


    public static void main(String[] args) {
        // 给定-一个长度为n的整数序列，求它的最大连续子序列和
        // 比如-2、1、-3、4、-1、2、1、-5、4 的最大连续子序列和是 4 + (-1)+2+ 1 = 6
        // 分析：等同于求和最大的连续子序列

        // 1.状态
        // dp(i) 以第i个元素结尾的最大连续子序列之和

        // 2.初始状态
        // dp(0)            = -2  => nums[0] --> 初始状态
        // dp(1)            = 1   => nums[1]
        // dp(2) = (-3) + 1 = -2  => nums[2] + dp(1)
        // dp(3)            = 4   => nums[3]
        // dp(4) = (-1) + 4 = 3   => nums[4] + dp(3)
        // dp(5) = 2 + 3    = 5   => nums[5] + dp(4)
        // dp(6) = 1 + 5    = 6   => nums[6] + dp(5)
        // dp(7) = (-5) + 6 = 1   => nums[6] + dp(6)
        // dp(8) = 4 + 1    = 5   => nums[6] + dp(7)

        // 3.状态转移方程
        // 以dp(i) 结尾的子序列，邻近dp(i-1)子序列和的条件就看dp(i-1)是否大于0
        // dp(i-1)  > 0：dp(i) = dp(i-1) + nums[i]
        // dp(i-1) <= 0：dp(i) = nums[i]

        System.out.println(maxSubArr1(new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        System.out.println(maxSubArr2(new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }

    // 观察dp(i)仅仅用了dp(i-1),因此可以用一个变量记录dp(i-1)的值即可
    // 这里dp默认等于上一个i的dp值，巧妙使用dp作为prev
    // 时间O(n) 空间O(1)
    static int maxSubArr2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int dp = nums[0];
        int max = dp;
        for (int i = 1; i < nums.length; i++) {
            if (dp > 0) {
                dp += nums[i];
            } else {
                dp = nums[i];
            }
            if (max < dp) max = dp;
        }
        return max;
    }

    // 时间O(n) 空间O(n)
    static int maxSubArr1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        int max = dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int prev = dp[i - 1];
            if (prev > 0) {
                dp[i] = nums[i] + prev;
            } else {
                dp[i] = nums[i];
            }
            if (max < dp[i]) {
                max = dp[i];
            }
        }
        return max;
    }
}
