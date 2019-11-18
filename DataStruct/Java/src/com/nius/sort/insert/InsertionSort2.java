package com.nius.sort.insert;

import com.nius.sort.Sort;

public class InsertionSort2<T extends Comparable<T>> extends Sort<T> {
    @Override
    protected void sort() {
        // 从前往后排
        // 从后边未排序部分拿出第一个从后往前比，知道放到合适的位置
        // 插入排序(从后边选一个，掺入到前边拍好序的部分)
        // 将前部分视为已排序（可能为1个，第一个元素），后部分视为未排序
        // 拿到未排序的第一个元素，与前一个（已排序最后一个）相比，小于则交换
        // 重复上述步骤，直到末尾一个元素
        
        // 1 9 10   <-|->  3 8 5 4
        // 1 9 3 10  <-|->  8 5 4
        // 1 3 9 10  <-|->  8 5 4
        // 1 3 9 8 10  <-|->  5 4
        // 1 3 8 9 10  <-|->  5 4
        // 1 3 8 9 5 10  <-|->  4
        // 1 3 8 5 9 10  <-|->  4
        // 1 3 5 8 9 10  <-|->  4
        // 1 3 5 8 9 4 10
        // 1 3 5 8 4 9 10
        // 1 3 5 4 8 9 10
        // 1 3 4 5 8 9 10

        // 从第二个元素开始，往前检查
        // 优化:减少交换次数
        // 先记住需要插入的元素，在与前边序列逐个比较时，如果小则将元素往后依次挪动
        for (int i = 1; i < array.length; i++) {
            T v = array[i]; // 提前保存值
            int fitIndex = i; // 默认合适的位置
            for (int j = i; j > 0; j--) {
                int preIndex = j - 1;
                if (cmp(v, array[preIndex]) >= 0) break;
                array[j] = array[preIndex];
                fitIndex = preIndex;
            }
            array[fitIndex] = v;
        }
    }
}
