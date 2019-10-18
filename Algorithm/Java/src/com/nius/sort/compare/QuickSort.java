package com.nius.sort.compare;

public class QuickSort <T extends Comparable<T>> extends Sort<T> {
    @Override
    protected void sort() {

        // 快速排序
        // 选择一个 pivot 轴点（中心店、关键点）元素
        // 检查数组，将大的元素放右边，将小的元素放左边
        // 轴点通常选择0位置元素
        sort(0, array.length);
    }

    /// 对 [begin, end) 范围的元素进行快速排序
    private void sort(int begin, int end) {
        if ((end - begin) < 2) return;

        // 先确定轴点元素索引
        int pivotIndex = pivotIndex1(begin, end);

        // 对子序列快速排序
        // 这里对轴点元素两边的元素进行排序，由于已经确定左边的较小
        // 右边的较大，因此可以跳过轴点元素，选取两边范围
        sort(begin, pivotIndex);
        sort(pivotIndex + 1, end);
    }

    /// 构造出 [begin, end) 范围的轴点元素
    /// 轴点元素的最终位置
    private int pivotIndex1(int begin, int end) {
        // 当发现序列 [1, 2, 3, 4, 5]
        // 此时1位轴点元素，会构造成出的序列俩边极度不平衡
        // 2，3，4，5同理，因此这里为了避免这种情况，不要选择第一个元素作为轴点
        // 但为了处理方便，我们随机挑一个元素与0位置元素交换，已达到此目的

        // Math.random() -> [0, 1)范围类小数
        swap(begin, begin + (int) (Math.random() * (end - begin)));

        // 备份begin位置元素
        T value = array[begin];
        // 让end指向最后一个元素
        end--;

        // 构造轴点左右序列
        // 注意，这里不可能出现begin > end的情况
        // 因为循环内部要么出现begin+1
        // 要么出现end-1，因此不会出现begin > end

        // 第一次实行从右到左的扫描
        boolean isRightToLeft = true;

        while (begin < end) {
            if (isRightToLeft) {
                if (cmp(array[end], value) > 0) {
                    end--;
                } else { // <=
                    array[begin++] = array[end];
                    isRightToLeft = false; // 切换扫描方向
                }
            } else  {
                if (cmp(array[begin], value) < 0) {
                    begin++;
                } else { // >=
                    array[end--] = array[begin];
                    isRightToLeft = true;
                }
            }
        }

        // 这里发现两边元素等于轴点元素需要交换的目的是为了使得轴点元素两边尽量分段均匀
        // 这样会减小时间复杂度
        // [1a, 1b, 1c, 1d]
        // 如上序列会导致选1a做轴点，排一次
        // 1b做轴点，排一次；1c...1d。造成轴点两边元素极度不平衡
        // 构造轴点的查询深度会增加，效率较低
        // 因此发现相等的时候做一次交换。而且左右扫描，最终会使轴点的位置落到中间
        // 减小搜索深度，减小时间复杂度

        // 将轴点元素放入最终的位置
        array[begin] = value;
        // 返回轴点元素的位置
        return begin;
    }

    private int pivotIndex2(int begin, int end) {
        // 随机选择一个元素跟begin位置进行交换
        swap(begin, begin + (int)(Math.random() * (end - begin)));

        // 备份begin位置的元素
        T pivot = array[begin];
        // end指向最后一个元素
        end--;

        while (begin < end) {
            while (begin < end) {
                if (cmp(pivot, array[end]) < 0) { // 右边元素 > 轴点元素
                    end--;
                } else { // 右边元素 <= 轴点元素
                    array[begin++] = array[end];
                    break;
                }
            }
            while (begin < end) {
                if (cmp(pivot, array[begin]) > 0) { // 左边元素 < 轴点元素
                    begin++;
                } else { // 左边元素 >= 轴点元素
                    array[end--] = array[begin];
                    break;
                }
            }
        }

        // 将轴点元素放入最终的位置
        array[begin] = pivot;
        // 返回轴点元素的位置
        return begin;
    }
}
