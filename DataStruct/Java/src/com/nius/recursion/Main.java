package com.nius.recursion;

// 递归主要思维为只考虑函数输出结果，不考虑函数具体过程
// 递归基：根据递归控制变量临界值确定(当问题规模缩小到什么程度时可以直接得出问题的解)
// 1.自顶向下：递归主要思维为，即将问题不断拆解为子问题，继续不断拆解，直到可以直接得出子问题的解为止
// 2.找规律
// 递归的应用场景：总问题的解决思路和子问题的解决思路一致

// 递归100%可以转为非递归，最简单暴力的方式自己维护递归栈
// 递归解法可能是最有解，也可能不是
// 不过通常情况下尽量使用非递归

// 尾调用：最后一句代码为函数调用（单纯的调用，不能掺杂其它运算）
// 通常在许多编程语言中尾调用会被编译器优化，而尾调用特例尾递归是最适合尾调用优化的特例
// 因此使用递归时，尽量使用尾递归

import java.util.Stack;

public class Main {



    public static void main(String[] args) {
        log(4);
        System.out.println();
        log2(4);
        System.out.println();
        log3(4);
    }

// -----------log---------------------------------
    // 这个递归可以改为自己模拟维护栈空间
    // 查看log2进行示例
    // 当然log2并没有得到优化，孔家复杂度仍然为O(n)
    // 查看log3示例，这里复用临时变量存储空间以达到优化目的(自底向上)
    static void log(int n) {
        if (n < 1) return;
        log(n - 1);
        int v = n + 10;
        System.out.println(v);
    }


// -----------log2---------------------------------
    static void log2(int n) {
        Stack<StackFrame> stackFrames = new Stack<>();

        // 开辟递归栈
        while (n >= 1) {
            stackFrames.push(new StackFrame(n, n+10));
            n--;
        }
        // 回收递归栈
        while (!stackFrames.isEmpty()) {
            StackFrame sf = stackFrames.pop();
            System.out.println(sf.v);
        }
    }

    // 模拟函数栈(栈帧)
    static class StackFrame {
        int n;
        int v;
        public StackFrame(int n, int v) {
            this.n = n;
            this.v = v;
        }
    }

// -----------log3---------------------------------
    static void log3(int n) {
        for (int i = 1; i <= n; i++) {
            int v = 10 + i;
            System.out.println(v);
        }
    }
}
