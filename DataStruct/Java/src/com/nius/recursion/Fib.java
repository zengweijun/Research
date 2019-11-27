package com.nius.recursion;

// 斐波拉契数列
// 1 1 2 3 5 8 13 21 34 ...
// 编写一个程序，求第n项斐波拉契数

// 规律：每项之值等于前两项之和
// 递归式：f(n) = f(n-1) + f(n-2)

public class Fib {
    public static void main(String[] args) {
        int n = 9;
        System.out.println(fib1(n));
        System.out.println("-----------------------");
        System.out.println(fib2(n));
        System.out.println("-----------------------");
        System.out.println(fib3(n));
        System.out.println("-----------------------");
        System.out.println(fib4(n));
        System.out.println("-----------------------");
        System.out.println(fib5(n));
        System.out.println("-----------------------");
        System.out.println(fib6(n));
        System.out.println("-----------------------");
        System.out.println(fib7(n));
    }

    // 递归基，根据下边传入的n-1和n-2确定
    // 当问题规模为n=1时候，fib(1) = 1
    // 当问题规模为n=2时候，fib(2) = fib(1) = 1
    // 当问题规模为n<1时候，应该无解(不符合题意)
    // 因此这里递归基应确定为 n <= 2 ==> 1
    // 自顶向下   时间：O(n^2)   空间：O(n)
    static int fib1(int n) {
        if (n <= 2) return 1;
        return fib1(n-1) + fib1(n-2);
    }

    // 问题，画出计算过程后发现，上述fib1会造成大量重复计算
    // 优化方案：记忆化（将计算过的结果存储起来，下次用到直接取，而无需再计算）
    // 自顶向下     时间：O(n)   空间：O(n)
    static int fib2(int n) {
        if (n <= 2) return 1;
        // 数组中用results[n]表示第n个斐波拉契数
        // 为了方便处理，这里使用n+1个空间，利用下标1~n，相当于仅仅浪费0位置空间
        int[] results = new int[n+1];
        results[1] = results[2] = 1;
        return fib2(n, results);
    }
    static int fib2(int n, int[] results) {
        if (results[n] == 0) {
            results[n] = fib2(n-1, results) + fib2(n-2, results);
        }
        return results[n];
    }

    // 去除递归调用
    // 自底向上     时间：O(n)   空间：O(n)
    static int fib3(int n) {
        if (n <= 2) return 1;
        int[] results = new int[n+1];
        results[1] = results[2] = 1;
        for (int i = 3; i <= n; i++) {
            results[i] = results[i-1] + results[i-2];
        }
        return results[n];
    }

    // 每次计算只用到前两个元素，可以使用滚动数组
    // 自底向上     时间：O(n)   空间：O(1)
    static int fib4(int n) {
        if (n <= 2) return 1;
        // 滚动数组(n为基数，结果存在第1个，n为偶数，结果存在第0个)
        int[] results = new int[2];
        results[0] = results[1] = 1;
        for (int i = 3; i <= n; i++) {
            // i基数 results[3] = results[0] + results[1]; -> results[1];
            // i偶数 results[4] = results[0] + results[1]; -> results[0];
            // i基数 results[5] = results[0] + results[1]; -> results[1];
            // i偶数 results[6] = results[0] + results[1]; -> results[0];
            // results[i % 2] = results[(i-1) % 2] + results[(i-2) % 2];
            results[i % 2] = results[0] + results[1];
        }

        // 最终，如果n是基数，取results[1];如果n是偶数，取results[0].
        // 取results[n % 2]
        return results[n % 2];
    }

    // 仍然使用滚动数组，不过使用位运算效率更高
    // 自底向上     时间：O(n)   空间：O(1)
    static int fib5(int n) {
        if (n <= 2) return 1;
        int[] results = new int[2];
        results[0] = results[1] = 1;
        // 基数最后一位为1，偶数最后一位为0
        for (int i = 3; i <= n; i++) {
            // results[i & 1] = results[(i-1) & 1] + results[(i-2) & 1];
            results[i & 1] = results[0] + results[1];
        }
        return results[n & 1];
    }

    // 复用临时变量空间
    // 自底向上   时间：O(n)   空间：O(1)
    static int fib6(int n) {
        if (n <= 2) return 1;
        int first = 1;
        int second = 1;
        for (int i = 3; i <= n; i++) {
            second = second + first; // second记录结果
            first = second - first; // 要让first 等于之前的second
        }
        return second;
    }

    // 斐波拉契数学公式
    static int fib7(int n) {
        double c = Math.sqrt(5);
        return (int)((Math.pow((1+c)/2, n) - Math.pow((1-c)/2, n)) / c);
    }
}
