package com.nius;

import com.nius.sort.tools.Integers;

public class Main {

    public static void main(String[] args) {

        Integer[] arr1 = Integers.random(20, 1, 30);


        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i] + "\t");
        }
    }
}
