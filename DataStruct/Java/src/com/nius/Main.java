package com.nius;

import com.nius.sort.SelectionSort;
import com.nius.sort.bubble.BubbleSort1;
import com.nius.sort.bubble.BubbleSort2;
import com.nius.sort.bubble.BubbleSort3;
import com.nius.sort.Sort;
import com.nius.sort.tools.Integers;
import com.nius.sort.tools.Times;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
//        Integer[] arr1 = Integers.tailAscOrder(1, 50000, 2000);
        Integer[] arr1 = Integers.random(50000, 1, 30000);

        testSort(arr1, new BubbleSort1(), new BubbleSort2(), new BubbleSort3(), new SelectionSort());
    }

    public static void testSort(Integer[] array, Sort... sorts) {
        for (Sort sort: sorts) {
            Integer[] newArray = Integers.copy(array);
            sort.sort(newArray);
            Integers.isAscOrder(newArray);
        }

        Arrays.sort(sorts);

        for (Sort sort : sorts) {
            System.out.println(sort);
        }
    }
}
