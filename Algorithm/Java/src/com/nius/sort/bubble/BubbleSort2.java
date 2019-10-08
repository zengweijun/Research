package com.nius.sort.bubble;

import com.nius.sort.Sort;

public class BubbleSort2<T extends Comparable<T>> extends Sort<T> {

    @Override
    protected void sort() {
        // 从后往前排
        // 相邻两个元素两两比较，大的一个放后边
        // 冒泡排序总思想，每一趟排序完成，最后一个一定最大(或者最小)
        // 所以保证下一次参与排序的数都会少一个
        // 使用end变量限制，最后一个需要检查的元素
        // 第一次需要检查n-1个，第二次n-2...最后一次检查 最后两个，末尾元素索引为 index = 1

        // 这里直接对原来的数据（数组）进行操作的方法称为"原地算法"

        // 针对部分有序优化
        // 这里针对算法优化，对部分有序的数组可以提高效率
        // 比如 [1, 5, 2, 3, 4] 只需要经过第一趟，5就能摆到最后，因此后续排序操作为无用功
        // 用一个bool值记录，如果发现某一趟遍历已经没有发生交换，说明数组已全部排序完成
        for (int end = array.length - 1; end >= 1; end--) {
            boolean sorted = true;
            for (int begin = 0; begin < end; begin++) {
                if (cmp(begin, begin + 1) > 0) {
                    swap(begin, begin + 1);
                    sorted = false;
                }
            }
            if (sorted) break;
        }
    }
}
