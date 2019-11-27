package com.nius.sort;

import java.text.DecimalFormat;

public abstract class Sort<T extends Comparable<T>> implements Comparable<Sort<T>> {
    protected T[] array;
    private long time;
    private int swapCount;
    private int cmpCount;
    private DecimalFormat fmt = new DecimalFormat("#.00");

    public void sort(T[] array) {
        if (array == null || array.length < 2) return;

        this.array = array;

        long begin = System.currentTimeMillis();
        sort();
        time = System.currentTimeMillis() - begin;
    }

    protected abstract void sort();

    // 返回值 > 0 : array[index1] > array[index2]
    // 返回值== 0 : array[index1]== array[index2]
    // 返回值 < 0 : array[index1] < array[index2]
    protected int cmp(int index1, int index2) {
        cmpCount++;
        return array[index1].compareTo(array[index2]);
    }

    protected int cmp(T t1, T t2) {
        cmpCount++;
        return t1.compareTo(t2);
    }

    protected void swap(int index1, int index2) {
        swapCount++;
        T tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }

    private String numberString(int number) {
        if (number < 10000) return "" + number;

        if (number < 100000000) return fmt.format(number / 10000.0) + "万";

        return fmt.format(number / 100000000.0) + "亿";
    }

    static class TestStableObj implements Comparable<TestStableObj> {
        private int cmpValue;
        private int checkValue;

        public TestStableObj(int cmpValue, int checkValue) {
            this.cmpValue = cmpValue;
            this.checkValue = checkValue;
        }

        @Override
        public int compareTo(TestStableObj o) {
            return this.cmpValue - o.cmpValue;
        }
    }

    boolean isStable() {
        if (this instanceof SelectionSort) return false;

        TestStableObj[] objs = new TestStableObj[20];
        for (int i = 0; i < objs.length; i++) {
            objs[i] = new TestStableObj(10, i*10);
        }

        sort((T[]) objs);

        for (int i = 1; i < objs.length; i++) {
            int checkValue = objs[i].checkValue;
            int prevCheckValue = objs[i - 1].checkValue;
            if (checkValue != prevCheckValue + 10) return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String timeStr = "耗时：" + (time / 1000.0) + "s(" + time + "ms)";
        String compareCountStr = "比较：" + numberString(cmpCount);
        String swapCountStr = "交换：" + numberString(swapCount);
        // stableStr这个要放在最后，防止影响cmpCount，swapCount，time等成员变量的值
        String stableStr = "稳定性：" + isStable();
        return "【" + getClass().getSimpleName() + "】\n"
                + stableStr + " \t"
                + timeStr + " \t"
                + compareCountStr + "\t "
                + swapCountStr + "\n"
                + "------------------------------------------------------------------";
    }

    @Override
    public int compareTo(Sort<T> o) {
        int result = (int)(time - o.time);
        if (result != 0) return result;

        result = cmpCount - o.cmpCount;
        if (result != 0) return result;

        return swapCount - o.swapCount;
    }
}
