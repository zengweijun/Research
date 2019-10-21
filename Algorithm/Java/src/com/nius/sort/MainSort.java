package com.nius.sort;

import com.nius.sort.compare.HeapSort;
import com.nius.sort.compare.MergeSort;
import com.nius.sort.compare.QuickSort;
import com.nius.sort.compare.Sort;
import com.nius.tools.Asserts;
import com.nius.tools.Integers;

import java.util.Arrays;

public class MainSort {

    public static void main(String[] args) {
        testSort();


//        Integer[] arr1 = {10, 7, 2, 4, 1, 8, 9, 3, 6, 7};
//        Integers.println(arr1);
//        new InsertionSort2().sort(arr1);
//        Integers.println(arr1);
//        Asserts.test(Integers.isAscOrder(arr1));
    }

    public static void testSort() {
//        Integer[] arr1 = Integers.random(150000, 1, 150000);
        Integer[] arr1 = Integers.tailAscOrder(1, 11150000, 100);
//        Integers.println(arr1);
        testSort(arr1,
//                new BubbleSort1(),
//                new BubbleSort2(),
//                new BubbleSort3(),
//                new SelectionSort(),
                new HeapSort(),
//                new InsertionSort1(),
//                new InsertionSort2(),
//                new InsertionSort3(),
                new MergeSort(),
                new QuickSort()
        );
    }

    public static void testSort(Integer[] array, Sort... sorts) {
        for (Sort sort: sorts) {
            Integer[] newArray = Integers.copy(array);
            sort.sort(newArray);

            boolean isAscOrder = Integers.isAscOrder(newArray);
            Asserts.test(isAscOrder);
            if (!isAscOrder) { System.out.println(sort.getClass().getName()); }
        }

        Arrays.sort(sorts);

        for (Sort sort : sorts) {
            System.out.println(sort);
        }
    }
}
