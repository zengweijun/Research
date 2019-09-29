package com.nius.sort;

import com.sun.xml.internal.ws.assembler.jaxws.HandlerTubeFactory;

import java.io.PrintStream;

public class HeapSort<T extends Comparable<T>> extends Sort<T> {
    private int heapSize = 0;

    @Override
    protected void sort() {
        // 从后往前排

        heapSize = array.length;
        // 原地建堆（最大堆）、批量建堆
        // 使用自下而上的下滤(从最后一个非叶子节点开始)
        for (int i = (heapSize >> 1) - 1; i >= 0; i--) {
            siftDonw(i);
        }

        // 排序
        // 每次将最大元素（对顶元素）与最后一个交换，这样保证最后一个元素最大
        // 下次操作只需考虑前边的元素，由于此时的第一个元素是之前最后一个交换过来的
        // 此时只需将该元素下滤即可，而且此时的堆控制范围应该要撇开最后一个元素
        while (heapSize > 1) {
            swap(0, --heapSize);
            siftDonw(0);
        }

        // 时间复杂度
        // 原地建堆： O(n * log2n)
        // 排序： O(n * log2n)
        // 综合时间复杂度：O(n * log2n)
    }

    /**
     * 下滤，树的高度lon2n，最坏时间复杂度O(lon2n)
     */
    public void siftDonw(int index) {
        // 下滤
        // 1.先将index的值存起来
        // 2.依次让比自己大的值覆盖自己（小：结束）
        // 3.移动index到覆盖自己的目标位置
        // 4.循环直到叶子节点，最后将之前保存的值覆盖index

        // 存值
        T element = array[index];

        // 飞叶子节点数(一半)
        int half = heapSize >> 1;

        // 如果index已经是叶子节点，停止循环
        while (index < half) {

            // 找出最大子节点（默认选左子节点）
            int childIndex = (index << 1) + 1;

            // 检查右边的child是否更大(若右边子节点存在)，更大则用右边
            int rightIndex = (index << 1) + 2;
            if (rightIndex < heapSize && cmp(childIndex, rightIndex) < 0) {
                childIndex = rightIndex;
            }

            // 此时，最大直接点索引为childIndex
            // 判断index值是否大于子节点最大值，最大堆，小于才下滤
            // 如果当前节点与子节点已经构成单位最大堆，则停止下滤
            // 注意，逻辑是将element的值去与childIndex的值相比
            // 不能使用array[index]，因为index会被修改，取到的值不一定是当初需要下滤的值
            if (cmp(element, array[childIndex]) >= 0) break;

            // 下滤
            // 让子节点的值覆盖自己，同时移动index，继续下一组检查
            array[index] = array[childIndex];
            index = childIndex;
        }

        // 最后，index记录的为最后一个覆盖自己的值，用之前存的index值覆盖当前节点
        array[index] = element;
    }
}
