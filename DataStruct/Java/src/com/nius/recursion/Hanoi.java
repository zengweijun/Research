package com.nius.recursion;

// 汉诺塔
// 从左到右有A、B、C三根柱子，其中A柱子上面有从小叠到大的n个圆盘，
// 现要求将A柱子上的圆盘移到C柱子上去，期间只有一个原则：一次只能
// 移到一个盘子且大盘子不能在小盘子上面，求移动的步骤和移动的次数
public class Hanoi {
    public static void main(String[] args) {
        hanoi(3, "A", "B", "C");
    }

    // dp 算法
    // dp(n)：n个圆盘从A移动到C需要的次数
    // dp(1) = 1
    // dp(2) = 3
    // dp(3) = 7
    // ...
    // dp(n) = 2^n - 1

    // 移动步骤（n个盘子）
    // 当 n==1 时：直接从 A->C
    // 当 n>1  时：
    // 1)先将 1 ~ n-1 编号的 n-1 个盘子移动到 A->B
    // 2)再讲 编号为n的第 n 号盘子移动到 A->C 柱子
    // 3)将 1 ~ n-1 编号的 n-1 个盘子 移动到 B->C 柱子

    // 从上边逻辑分析可以知道，3）和 1）功能相同，且递归到n>1的判断
    // 定义一个函数，将n个盘子从A移动到C，期间可能需要经过B中转

    // 递归方式
    // 功能：将n个盘子从A移动到C，期间可能需要通过B中专
    static void hanoi(int n, String A, String B, String C) {
        if (n < 1) return;
        if (n == 1) {
            move(n, A, C);
            return;
        }

        // 先将1~n-1编号的n-1个盘子从A移动到B（此过程可能需要C来中转）
        hanoi(n-1, A, C, B); // 形成递归
        // 将编号为n的第 n 号盘子直接从A移动到C（此过程无需中转）
        move(n, A, C);
        // 先将1~n-1编号的n-1个盘子从B移动到C（此过程可能需要A来中转）
        hanoi(n-1, B, A, C); // 形成递归
    }

    // 功能：将编号为i的盘子，从from移动到to，无需中专
    static void move(int i, String from, String to) {
        System.out.println("将" + i + "号盘子从" + from + "移动到" + to);
    }


    // 非递归方式



















}
