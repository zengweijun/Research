package com.nius.ArrayList;

import java.util.ArrayList;
import java.util.Collections;

import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;

public class TestCollections {
	
	public static void main(String[] args) {
		test1();
	}
	
	public static void test1() {
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		arrayList.add(1);
		arrayList.add(3);
		arrayList.add(8);
		arrayList.add(2);
		System.out.println(arrayList);
		Collections.sort(arrayList);
		System.out.println(arrayList);
		Collections.reverse(arrayList);
		System.out.println(arrayList);
		Collections.swap(arrayList, 0, 3);
		System.out.println(arrayList);
		Collections.sort(arrayList);
		System.out.println(arrayList);
		Collections.shuffle(arrayList);
		System.out.println(arrayList);
		
	}
}
