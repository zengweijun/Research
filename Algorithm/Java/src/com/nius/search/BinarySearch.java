package com.nius.search;

import com.nius.tools.Asserts;

public class BinarySearch {

    public static int INDEX_NOT_FOUND = -1;

    // 二分搜索算法（针对已经排序好的数组）
    // 基本思路
    // 1.取中间一个元素，与要搜索的元素对比
    // 1.1 若要搜索的元素大：选取大的一半继续，否则选择小的一半继续
    // 1.2 若相等，则结束搜索


    /** 查找value在有序数组中的位置 */
    public static int indexOf(int[] arr, int value) {
        if (null == arr || 0 == arr.length) return INDEX_NOT_FOUND;

        // 分段，最初的段为[0, arr.length)
        // 这里将分段开始位置为begin，结束位置为end，设置为开区间 [begin, end)
        // 中间的节点为mid，后边设置为开区间后，mid = (begin+end)/2
        // 这样分段的好处是保持了两段连续开区间，便于处理[begin, mid)，[mid, end)

        int begin = 0;
        int end = arr.length;

        // 退出循环条件，begin往后走，end往前走，当begin == end
        // 即出现类似 [3, 3) 这种属于无效区间的时候，说明未找到，退出循环
        // 比如[1, 3, 5] -> 2 ：begin = 0，end = 2，mid = 1
        // 由于2 < 3：begin = 0，end = 1，mid = 0，范围变为[1]（end为开区间）
        // 由于2>1，所以 begin = mid+1 = 1，end = 1，此时begin = mid 退出

        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (value > arr[mid]) {
                begin = mid + 1;
            } else if (value < arr[mid]) {
                end = mid;
            } else  {
                return mid;
            }
        }

        return INDEX_NOT_FOUND;
    }


    /** 查找value适合插入有序数组中的哪个位置 */
    public static int searchFitIndex(int[] arr, int value) {
        if (null == arr || 0 == arr.length) return INDEX_NOT_FOUND;

        // 找到数组中最后一个（可能存在多个相同值级元素）比value大的元素索引
        // 该位置就是最适合的位置，让后让后边元素一次往后移动
        // 这里之所以找最后一个，是为了算法的稳定性，如果不规定最后一个
        // 比如这种情况 [8, 8, 8, 8, 8, 8]再插入一个8的话，位置就可能是随机的
        // 随机位置会让算法失去稳定性

        int begin = 0;
        int end = arr.length;

        // 退出循环条件：begin == mid，
        // 根据分析 begin == mid 时正好是我们找到的最适合插入的位置
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (value >= arr[mid]) {
                begin = mid + 1;
            } else  {
                end = mid;
            }
        }

        return begin; // return end
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 6, 8, 11};

        Asserts.test((indexOf(arr, 0) == INDEX_NOT_FOUND), "找0");
        Asserts.test((indexOf(arr, 1) == 0), "找1");
        Asserts.test((indexOf(arr, 3) == 1), "找3");
        Asserts.test((indexOf(arr, 5) == 2), "找4");
        Asserts.test((indexOf(arr, 6) == 3), "找6");
        Asserts.test((indexOf(arr, 8) == 4), "找8");
        Asserts.test((indexOf(arr, 11) == 5), "找11");
        Asserts.test((indexOf(arr, 12) == INDEX_NOT_FOUND), "找12");


        Asserts.test((searchFitIndex(arr, 3) == 2), "3找到适合位置");
        Asserts.test((searchFitIndex(arr, 7) == 4), "7找到适合位置");
        Asserts.test((searchFitIndex(arr, 12) == 6), "12找到适合位置");
    }
}
