package com.nius.sort.compare;
public class MergeSort<T extends Comparable<T>> extends Sort<T> {

    // 归并培训
    // 归并排序的总思想是，先讲一个序列不断二分
    // 直到每个数都分为单个元素的序列时，再两两按顺序合并
    // divide --> merge 过程
    // [5, 3, 2, 4, 1]   mid = (0 + 4)/2 = 2
    // [5, 3]   [2, 4, 1] 继续上述过程
    // [5] [3]   [2] [4, 1]
    // [5] [3]   [2]  [4] [1]
    // 合并
    // [5] [3]   [2]  [1, 4]
    // [3, 5]   [1, 2, 4]
    // [3, 5]   [1, 2, 4]
    // [1, 2, 3, 4, 5]

    // 可以看出，由于上述元素为基数，分配不均
    // 所以最后分割的片段会出现，不过无论是否均匀，最多只会存在某一半序列分割后多一个元素
    // 但是最终都会全部分割为单个元素的片段，最终将各个小片段按照分割步骤的逆序，按序合并
    // 单个序列为双元素序列，进而合并为四元素、八元素序列...最终合并为一个全然有序的序列

    private T[] leftArray;

    @Override
    protected void sort() {
        leftArray = (T[]) new Comparable[array.length >> 1];
        sort(0, array.length);
    }

    /// 对 [begin, end) 范围的数据进行归并排序
    private void sort(int begin, int end) {
        if ((end - begin) < 2) return;

        // divide
        int mid = (begin + end) >> 1;
        sort(begin, mid);
        sort(mid, end);

        // merge
        merge(begin, mid, end);
    }

    /// 将 [begin, mid) 和 [mid, end) 范围的序列合并成一个有序序列
    private void merge(int begin, int mid, int end) {
        int leftBegin = 0, leftEnd = mid - begin; // 左边数组范围（使用新数组备份，因此从0开始）
        int rightBegin = mid, rightEnd = end;     // 右边数组范围
        int destIndex = begin;    // array中用来存放目标元素的位置，从begin开始

        // 先备份左半部分到数组leftArray中
        for (int i = leftBegin; i < leftEnd; i++) {
            leftArray[i] = array[begin + i];
        }

        // 合并leftArray到array中
        // 只有当leftArray数组中还有元素时，才拷贝到array中，倘若
        // leftArray数组中元素已经全部被拷贝完毕，则说明现在array
        // 中[begin, end)范围元素已经拍好序了

        // 而如果发现array中，右边部分数组先完成拷贝，说明左边数组中
        // 的元素还没有拷贝完毕，此时只需将左边数组中的元素拷贝到剩余
        // 范围内的格子中

        // 左边还没有结束（如果左边结束，说明排序完毕）
        while (leftBegin < leftEnd) {
            if (rightBegin < rightEnd && cmp(array[rightBegin], leftArray[leftBegin]) < 0) {
                // 右边没有结束，且右边元素小于左边元素的时候
                // 才执行右边数组拷贝到array前部操作
                array[destIndex++] = array[rightBegin++];
            } else {
                // 如果右边结束 或者 左边较小，都将左边元素拷贝到array中index初
                array[destIndex++] = leftArray[leftBegin++];
            }
        }
    }
}
