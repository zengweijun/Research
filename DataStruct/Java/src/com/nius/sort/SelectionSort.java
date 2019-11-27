package com.nius.sort;

public class SelectionSort<T extends Comparable<T>> extends Sort<T> {
    @Override
    protected void sort() {
        // 从后往前排
        // 选最大的一个放到最后
        // 每一伦便利都找出一个最大的，与最后一个交换
        // 每经过一轮，最后一个一定最大，下次排序可排除最后一个
        // 因此每一次排序都可以减少一个数

        for (int end = array.length - 1; end > 0; end--) {

            int maxIndex = 0;

            // 注意，这里的end是最后一个元素，这里必须要要用<=才能取到
            for (int begin = 1; begin <= end; begin++) {
                // 经过特例测试，某些情况下这里不能保证"稳定性"
                // 这里使用 <= 只能提高稳定性，既然不能保证稳定性，直接使用 <
                // 以减少if内部执行次数
                if (cmp(maxIndex, begin) < 0) {
                    maxIndex = begin;
                }
            }
            swap(maxIndex, end);
        }
    }
}
