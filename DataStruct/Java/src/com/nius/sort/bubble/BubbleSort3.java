package com.nius.sort.bubble;

import com.nius.sort.Sort;

public class BubbleSort3<T extends Comparable<T>> extends Sort<T> {

    @Override
    protected void sort() {
        // 相邻两个元素两两比较，大的一个放后边
        // 冒泡排序总思想，每一趟排序完成，最后一个一定最大(或者最小)
        // 所以保证下一次参与排序的数都会少一个
        // 使用end变量限制，最后一个需要检查的元素
        // 第一次需要检查n-1个，第二次n-2...最后一次检查 最后两个，末尾元素索引为 index = 1

        // 这里直接对原来的数据（数组）进行操作的方法称为"原地算法"

        // 针对部分有序优化
        // BubbleSort2虽然优化了尾部有序的情况，但是还有优化空间
        // 虽然后边一半数据拍好了序，但是便利的时候还是会照常便利

        // 对于 [1, 3, 2, 4, 6, 5, 7, 8, 9], 789 有序
        // 经过一轮，132456789，记录发生交换第二个位置5的位置
        // 只需要在排[1, 3, 2, 4, 5]，再经过一轮，[1, 2, 3, 4, 5]
        // 记录交换位置的第二个为3，只需要再排[1, 2]即可们可以发现，这种方法
        // 将排序的范围缩小的更快，效率会更高

        for (int end = array.length - 1; end > 0; end--) {
            // 注意，这里为什么记录的是第二个位置，主要原因是每次记录完成后
            // 进入下一次循环前，外层的end都会做一次自减操作，为了保证交换
            // 后前一个位置的元素能参与到下次检查中，记录的时候记录下第二个

            // 最后一次发生交换的位置第二个索引
            // 这里默认为1，如果刚进来发现数组是完全有序的，不会进入swap环节
            // 则将1赋值给end，经过外层end—-，会使end的结果变成0，循环停止
            int lastChangeIndex = 1;
            for (int begin = 0; begin < end; begin++) {
                if (cmp(begin, begin + 1) > 0) {
                    swap(begin, begin + 1);
                    lastChangeIndex = begin + 1;
                    // 这里的lastChangeIndex会赋值给end
                    // 而进入下一次循环前end会自减
                    // 为了保证begin能参与排序，使用begin+1
                }
            }
            end = lastChangeIndex;
        }
    }
}
