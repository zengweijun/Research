package com.nius.sort;

public class SelectionSort<T extends Comparable<T>> extends Sort<T> {
    @Override
    protected void sort() {
        // 选最大的一个放到最后
        // 每一伦便利都找出一个最大的，与最后一个交换
        // 每经过一轮，最后一个一定最大，下次排序可排除最后一个
        // 因此每一次排序都可以减少一个数

        for (int end = array.length - 1; end > 0; end--) {

            int maxIndex = 0;
            for (int begin = 1; begin < end; begin++) {
                // 如果有两个最大值 5，5，发生交换的应该是后边一个5
                // 这样才能保证该算法的"稳定性"，因此如果相等，默认去最后一个为最大值
                // 否则可能出现将前一个最大值交换到尾部，因此这里使用<=
                if (cmp(maxIndex, begin) <= 0) {
                    maxIndex = begin;
                }
            }
            swap(maxIndex, end);
        }
    }
}
