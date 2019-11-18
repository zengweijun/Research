package com.nius.sort.insert;

import com.nius.sort.Sort;
import com.nius.sort.tools.Integers;

public class InsertionSort3<T extends Comparable<T>> extends Sort<T> {

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

        // InsertionSort2 的优化，虽然减少了交换次数，但是查找次数还有优化空间
        // 对于插入排序，实际上前边部分元素是已经排好序的，因此相当于将一个元素插入
        // 到一个有序序列，此时无需逐个去比较，使用二分法可以快速找到合适的位置
        // 找到位置后，将后边元素依次往后一定，最后将目标元素插入后到该位置即可

        for (int i = 1; i < array.length; i++) {
            insert(i, searchFitIndex(i));
        }
    }

    // 将后边index位置的元素，插入到合适的前边位置fitIndex
    private void insert(int souceIndex, int fitIndex) {
        // 先备份index位置的元素
        T value = array[souceIndex];
        for (int i = souceIndex; i > fitIndex; i--) {
            array[i] = array[i - 1];
        }
        array[fitIndex] = value;
    }

    // 利用二分法查找index位置元素合适插入的位置
    // 查找区间范围为[0, index)
    private int searchFitIndex(int index) {
        // 在某个区间序列找到合适的插入位置
        // 这里需要考虑到用重复元素出现的情况[1, 8, 8 ,8, 9] 这种情况
        // 因此，应该找到第一个比当前元素大的位置，获得其索引
        int begin = 0;
        int end = index;

        while (begin < end) {
            int mid = (begin + end) >> 1;
            // 分段：[begin, mid) + (mid, end)
            if (cmp(index, mid) < 0) {
                end = mid; // 搜索范围挪到前一半
            } else {
                // 这里是 >= ，加一个=符号目的是如果出现有相同值级的元素
                // 默认不是适合的位置，继续往后找到比之更大的一个，这个比之
                // 更大的元素的位置，就是最适合插入的位置，这样的做法是为了
                // 算法的稳定性
                begin = mid + 1; // 搜索范围挪到后一半
            }

            // 上边无论哪种情况，最终到会在 begin == end 的时候导致循环结束
            // 而当 begin == end 的时候，有如下情况

            // 1. array[index] >= array[mid]，由于 = 会导致begin向后移动
            //    最终结果是，begin == end，此时，begin位置的元素要么不存
            //    要么刚好是序列中第一个比begin位置元素大的元素，就是我们要得位置

            // 2.array[index] >= array[mid]，结果是 end不断往前移动
            //    而end移动的步数最小为1，且始终大于begin，最终都会到达
            //    begin == end 的位置，同样道理，此时的begin或者end
            //    即为我们需要查找的位置

            // 综上：最终 begin == end，退出循环，此时的begin或者end即是
            // 我们要插入元素的最适合位置
        }

        return begin; // return end;
    }

    // 时间复杂度
    // O(n) * (O(log2n) + n) = O(n) * (n) = O(n^2)
    // 虽然复杂度表现上仍然为 O(n^2)，但实际效率却会高出很多



    public static void main(String[] args) {
        Integer[] arr = {1, 2, 10, 8,3 , 7, 5, 6};
        new InsertionSort3<Integer>().sort(arr);
        Integers.println(arr);
    }
}
