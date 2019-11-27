package com.nius.dynamicProgramming;

public class _最长上升子序列 {
    public static void main(String[] args) {
        // 给定一个无序的整数数组，找到其中最长上升子序列的长度。
        // 没有做特别说明时，这里的"上升"要理解为"严格上升" 即[2,3,7,101]，不能是[2,2,3,7,101]
        // 输入:[10,9,2,2,5,3,7,101,18]
        // 输出: 4
        // 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。

        // 分析1：关于最值，优先考虑动态规划
        // 分析2：隐含意义，求最长上升子序列
        // 分析3：题目中未提到"连续"二字，"子序列"未提到"连续"就默认为不连续
        // 扩展：子串、区间 默认连续，子序列默认不连续

        // 1. 定义状态
        // dp(i) 以第i个元素结尾的最长上升子序列

        // 2.初始状态
        // dp(0) = [10]                             => 1
        // dp(1) = [9]                              => 1
        // dp(2) = [2]                              => 1
        // dp(3) = [2]                              => 1
        // dp(4) = [2, 5]                           => 2 = max(dp(2), dp(3)) + 1
        // dp(5) = [2, 3]                           => 3 = max(dp(2), dp(3)) + 1
        // dp(6) = [2, 3, 7] 或者 [2, 5, 7]          => 3 = max(dp(4), dp(5)) + 1
        // dp(7) = [2, 3, 7, 101] 或者 [2, 5, 7, 101]=> 4 = max(dp(2), dp(3), dp(4), dp(5), dp(6)) + 1
        // dp(8) = [2, 3, 7, 18] 或者 [2, 5, 7, 18]  => 4 = max(dp(2), dp(3), dp(4), dp(5), dp(6)) + 1

        // 3.状态转移方程
        // nums[i] <= min {nums[j]} : dp(i) = 1,                j∈[0~i-1]
        // nums[i] > nums[j]        : dp(i) = max {dp(j)} + 1,  j∈[0~i-1]

        System.out.println(lengthOfLIS1(new int[] {10,9,2,2,5,3,7,101,18}));
        System.out.println(lengthOfLIS2(new int[] {10,9,2,2,5,3,7,101,18}));
    }

    // 可使用二分搜索优化
    // 空间：O(n) 时间：O(n * logn)
    static int lengthOfLIS2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int max = 0;

        return max;
    }

    // 空间：O(n) 时间：O(n^2)
    static int lengthOfLIS1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        int max = dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] <= nums[j]) continue;
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

}
