package com.nius.sort;

import com.nius.sort.*;
import com.nius.sort.bubble.BubbleSort1;
import com.nius.sort.bubble.BubbleSort2;
import com.nius.sort.bubble.BubbleSort3;
import com.nius.sort.tools.Asserts;
import com.nius.sort.tools.Integers;
import com.nius.sort.tools.Times;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        testSort();


//        Integer[] arr1 = {10, 7, 2, 4, 1, 8, 9, 3, 6, 7};
//        Integers.println(arr1);
//        new InsertionSort2().sort(arr1);
//        Integers.println(arr1);
//        Asserts.test(Integers.isAscOrder(arr1));
    }

    public static void testSort() {
        Integer[] arr1 = Integers.random(50000, 1, 30000);
        testSort(arr1,
                new BubbleSort1(),
                new BubbleSort2(),
                new BubbleSort3(),
                new SelectionSort(),
                new HeapSort(),
                new InsertionSort1(),
                new InsertionSort2()
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
