package com.nius.sort.insert;

import com.nius.sort.Sort;

public class InsertionSort1<T extends Comparable<T>> extends Sort<T> {
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
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0; j--) {
                if (cmp(j, j-1) < 0) {
                    swap(j, j-1);
                }
            }
        }
    }
}
